package com.diamone.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.diamone.springcloud.dto.CommonResult;
import com.diamone.springcloud.entities.Payment;

public class CustomBlockHandler {
    public static CommonResult handlerException(BlockException be){
        return new CommonResult(444,"按自定义的限流测试");
    }
    public static CommonResult handlerException1(BlockException be){
        return new CommonResult(4444,"按自定义的限流测试2");
    }
}
