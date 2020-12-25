package com.diamone.springcloud.service;

import com.diamone.springcloud.dto.CommonResult;
import com.diamone.springcloud.entities.Payment;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")

public interface PaymentFeignService {

    @GetMapping(value = "/payment/{id}/get")
    CommonResult getById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();


    @GetMapping(value = "/payment/hystrix/{id}/ok")
    String paymentinfoOK(@PathVariable("id") Integer id);

}
