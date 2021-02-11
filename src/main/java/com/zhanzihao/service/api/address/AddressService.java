package com.zhanzihao.service.api.address;

import com.zhanzihao.model.Address;

import java.util.List;

/**
 * @author zhanzihao
 * 2021-02-11
 */
public interface AddressService {

    /**
     * 获取地址列表
     */
    List<Address> listAddresses(Long userId);

    /**
     * 新增地址
     */
    void insertAddress(Address address);

    /**
     * 删除地址
     */
    void deleteAddress(Long id);
}
