package com.diamone.springcloud.service;

import ch.qos.logback.core.util.TimeUtil;
import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.micrometer.core.instrument.util.TimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    //正确的
    public String paymentinfoOK(Integer id){
        return "线程池： "+Thread.currentThread().getName()+"   paymentinfoOK.id: "+id;
    }
    //错误的
    public String paymentinfoError(Integer id){

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+"   出现错误,timeout.id: "+id;
    }


    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否用短融器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//最小的请求数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//短融多久后试着发送一条
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率，这是60%
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("****id不能为负");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功,流水号:"+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数"+id;
    }
}
