package com.zhanzihao.service.api.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhanzihao.assembler.OrderAssembler;
import com.zhanzihao.assembler.OrderProductAssembler;
import com.zhanzihao.constant.PaymentType;
import com.zhanzihao.dto.api.order.AliPayResponse;
import com.zhanzihao.dto.api.order.OrderRequest;
import com.zhanzihao.dto.api.order.OrderResponse;
import com.zhanzihao.mapper.AddressMapper;
import com.zhanzihao.mapper.OrderMapper;
import com.zhanzihao.mapper.OrderProductMapper;
import com.zhanzihao.mapper.ProductMapper;
import com.zhanzihao.model.Order;
import com.zhanzihao.model.OrderProduct;
import com.zhanzihao.model.Product;
import com.zhanzihao.service.alipay.AliPayService;
import com.zhanzihao.utils.OrderSnGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author zhanzihao
 * 2021-02-12
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper mOrderMapper;

    @Autowired
    private OrderAssembler mOrderAssembler;

    @Autowired
    private OrderProductMapper mOrderProductMapper;

    @Autowired
    private OrderProductAssembler mOrderProductAssembler;

    @Autowired
    private AddressMapper mAddressMapper;

    @Autowired
    private AliPayService mAliPayService;

    @Autowired
    private OrderSnGenerateUtil mOrderSnGenerateUtil;

    @Autowired
    private ProductMapper mProductMapper;

    @Override
    public Page<OrderResponse> pageOrders(Long userId, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        List<OrderResponse> orders = mOrderMapper.listByUserId(userId)
                .stream()
                .map(order -> {
                    OrderResponse response = mOrderAssembler.toApiResponse(order);
                    QueryWrapper<OrderProduct> wrapper = new QueryWrapper<>();
                    wrapper.eq("order_sn", response.getOrderSn());
                    response.setProducts(
                            mOrderProductMapper.selectList(wrapper)
                                    .stream()
                                    .map(mOrderProductAssembler::toProducts)
                                    .collect(Collectors.toList()));
                    response.setAddress(mAddressMapper.selectById(order.getAddressId()));
                    return response;
                }).collect(Collectors.toList());
        log.info(orders.toString());
        return new PageImpl<>(orders.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, orders.size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AliPayResponse createOrder(OrderRequest request) {

        if (Strings.isNotBlank(request.getOrderSn()) && !Objects.isNull(request.getTotalPrice())) {
            AliPayResponse response = new AliPayResponse();
            response.setOrderSn(request.getOrderSn());
            response.setTotalPrice(request.getTotalPrice());
            response.setResponse(mAliPayService.createOrder(request.getUserId(), request.getOrderSn(), request.getTotalPrice(), Instant.now().getEpochSecond() + "", PaymentType.BUY).body);
            return response;
        }
        // 生成订单号
        String orderSn = mOrderSnGenerateUtil.generate();

        Order order = new Order();
        order.setAddressId(request.getAddressId());
        order.setUserId(request.getUserId());
        order.setOrderSn(orderSn);
        order.setCreatedAt(Instant.now().getEpochSecond());
        order.setUpdatedAt(Instant.now().getEpochSecond());

        // 循环插入购买的商品以及计算总价
        AtomicReference<BigDecimal> totalPrice = new AtomicReference<>(new BigDecimal(0));
        request.getProducts().forEach(it -> {
            Product product = mProductMapper.selectById(it.getProductId());
            if (Objects.isNull(product)) {
                return;
            }
            // 库存为0不能购买
            if (product.getInventory() == 0) {
                throw new RuntimeException("库存为0不能购买");
            }
            // 计算库存量
            if (product.getInventory() < it.getPurchaseNum()) {
                throw new RuntimeException("购买数量大于库存");
            }
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderSn(orderSn);
            orderProduct.setProductId(it.getProductId());
            orderProduct.setProductName(product.getName());
            orderProduct.setProductImage(product.getImage());
            orderProduct.setProductPrice(product.getPrice());
            orderProduct.setQuantity(it.getPurchaseNum());
            orderProduct.setCreatedAt(Instant.now().getEpochSecond());
            orderProduct.setUpdatedAt(Instant.now().getEpochSecond());
            mOrderProductMapper.insert(orderProduct);

            // 计算总金额
            totalPrice.getAndSet(totalPrice.get().add(new BigDecimal(String.valueOf(product.getPrice().multiply(new BigDecimal(it.getPurchaseNum()))))));
        });

        order.setTotalPrice(totalPrice.get());
        if (mOrderMapper.insertOne(order) > 0) {
            // 下单成功 减少库存
            request.getProducts().forEach(it -> {
                mProductMapper.reduceInventoriesById(it.getProductId(), it.getPurchaseNum());
            });
            AliPayResponse response = new AliPayResponse();
            response.setOrderSn(orderSn);
            response.setTotalPrice(totalPrice.get());
            response.setResponse(mAliPayService.createOrder(request.getUserId(), orderSn, totalPrice.get(), Instant.now().getEpochSecond() + "", PaymentType.BUY).body);
            log.info(response.toString());
            return response;
        }
        return null;
    }
}
