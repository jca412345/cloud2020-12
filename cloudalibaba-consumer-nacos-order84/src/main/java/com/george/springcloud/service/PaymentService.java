package com.george.springcloud.service;

import com.diamone.springcloud.dto.CommonResult;
import com.diamone.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Yang Hao
 * @date 2020-09-24 09:35
 */
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackServiceImpl.class)
public interface PaymentService {
    @GetMapping(value = "/paymentSQL/{id}")
    CommonResult<Payment> PaymentSQL(@PathVariable("id") Long id);
}
