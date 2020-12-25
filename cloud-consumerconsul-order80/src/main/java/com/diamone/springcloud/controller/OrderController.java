package com.diamone.springcloud.controller;

import com.diamone.springcloud.dto.CommonResult;
import com.diamone.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class OrderController {

    public static final String INVOKE_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/payment/consul")
    public String paymentinfo(){
        String result=restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
        return result;
    }
}
