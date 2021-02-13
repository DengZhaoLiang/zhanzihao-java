package com.zhanzihao.controller.api.alipay;

import com.zhanzihao.service.alipay.AliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/alipay")
public class AliPayController {

    @Autowired
    private AliPayService mAliPayService;

    @GetMapping("/notify")
    public void aliPayNotify(@RequestParam("orderSn") String orderSn) {
        mAliPayService.aliPayNotify(orderSn);
    }

}
