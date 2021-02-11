package com.zhanzihao.service.api.address;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhanzihao.mapper.AddressMapper;
import com.zhanzihao.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanzihao
 * 2021-02-11
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper mAddressMapper;

    @Override
    public List<Address> listAddresses(Long userId) {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return mAddressMapper.selectList(wrapper);
    }

    @Override
    public void insertAddress(Address address) {
        mAddressMapper.insert(address);
    }

    @Override
    public void deleteAddress(Long id) {
        mAddressMapper.deleteById(id);
    }
}
