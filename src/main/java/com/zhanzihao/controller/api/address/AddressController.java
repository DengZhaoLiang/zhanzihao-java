package com.zhanzihao.controller.api.address;

import com.zhanzihao.model.Address;
import com.zhanzihao.service.api.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhanzihao
 * 2021-02-11
 */
@Validated
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService mAddressService;

    @GetMapping("/user/{userId}")
    public List<Address> listAddresses(@PathVariable Long userId) {
        return mAddressService.listAddresses(userId);
    }

    @PostMapping("")
    public void insertAddress(@RequestBody @Validated Address address) {
        mAddressService.insertAddress(address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        mAddressService.deleteAddress(id);
    }
}
