package com.diamone.springcloud.controller;

import com.diamone.springcloud.dto.CommonResult;
import com.diamone.springcloud.entities.Payment;
import com.diamone.springcloud.service.PaymentFeignService;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableHystrix
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/{id}/get")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        System.out.println("进入controller");
        return paymentFeignService.getById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon,客服端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }

    @GetMapping("/consumer/payment/{id}/newget")
    public String getPayment(@PathVariable("id") Integer id){
        return paymentFeignService.paymentinfoOK(id);
    }
}
