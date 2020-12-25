package com.diamone.springcloud.controller;

import com.diamone.springcloud.dto.CommonResult;
import com.diamone.springcloud.dto.codeenum.CodeEnum;
import com.diamone.springcloud.entities.Payment;
import com.diamone.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***插入结果" + result);
        CommonResult commonResult = new CommonResult();
        if (result > 0) {

            commonResult.setCode(CodeEnum.SUCCESS.getCode());
            commonResult.setMessage(CodeEnum.SUCCESS.getMessage()+" serverPort:"+serverPort);
            commonResult.setDate(result);
            return commonResult;
        } else {
            commonResult.setCode(CodeEnum.FAIL.getCode());
            commonResult.setMessage(CodeEnum.FAIL.getMessage()+" serverPort:"+serverPort);
            commonResult.setDate(null);
            return commonResult;
        }
    }

    @GetMapping(value = "{id}/get")
    public CommonResult getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***返回结果" + payment);
        CommonResult commonResult = new CommonResult();
        if (payment != null) {
            commonResult.setCode(CodeEnum.SUCCESS.getCode());
            commonResult.setMessage(CodeEnum.SUCCESS.getMessage()+" serverPort:"+serverPort);
            commonResult.setDate(payment);
            return commonResult;
        } else {
            commonResult.setCode(CodeEnum.FAIL.getCode());
            commonResult.setMessage(CodeEnum.FAIL.getMessage()+" serverPort:"+serverPort);
            commonResult.setDate(null);
            return commonResult;
        }
    }

    @GetMapping(value = "/lb")
    public String getPayment(){
        return serverPort;
    }

}
