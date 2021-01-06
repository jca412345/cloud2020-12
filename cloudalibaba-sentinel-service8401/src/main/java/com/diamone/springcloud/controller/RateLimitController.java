package com.diamone.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.diamone.springcloud.dto.CommonResult;
import com.diamone.springcloud.entities.Payment;
import com.diamone.springcloud.myhandler.CustomBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handlerException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试",new Payment(2021L,"ser2021"));
    }

    public CommonResult handlerException(BlockException b){
        return new CommonResult(444,b.getClass().getCanonicalName()+"\t 服务不可用");
    }

    @GetMapping("/rateLimit/custom")
    @SentinelResource(value = "customerBlock",
            blockHandlerClass = CustomBlockHandler.class,
            blockHandler = "handlerException1")
    public CommonResult customerBlock(){
        return new CommonResult(200,"按url限流测试",new Payment(2021L,"ser2023"));

    }
}
