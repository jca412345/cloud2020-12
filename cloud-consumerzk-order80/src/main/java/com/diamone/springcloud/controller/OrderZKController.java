package com.diamone.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderZKController {
    public static final String INVOKE_URL="http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/payment/zk")
    public String paymentinfo(){
        String result=restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
        return result;
    }
}
