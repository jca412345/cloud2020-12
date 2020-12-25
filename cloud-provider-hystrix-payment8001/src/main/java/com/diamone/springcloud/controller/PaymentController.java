package com.diamone.springcloud.controller;

import com.diamone.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "galbol")//默认的降级方法
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    //正确的
    @GetMapping(value = "/payment/hystrix/{id}/ok")
    @HystrixCommand//没有指定的就去galbol
    public String paymentinfoOK(@PathVariable("id") Integer id){
        String result=paymentService.paymentinfoOK(id);
        System.out.println("***");
        return result;
    }


    //错误的
    @HystrixCommand(fallbackMethod = "paymentinfo_ErrorHandle",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")}
    )
    @GetMapping(value = "/payment/hystrix/{id}/timeout")
    public String paymentinfoError(@PathVariable("id") Integer id){
      return paymentService.paymentinfoError(id);
    }



    public String paymentinfo_ErrorHandle(Integer id){
        return "线程池："+Thread.currentThread().getName()+"paymentinfoError id :"+id;
    }

    //这是全局fallback
    public String galbol(){
        return "全局的降级出错方法";
    }

    /**
     * 服务熔断
     *
     * @param id
     * @return
     */
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        return result;
    }
}
