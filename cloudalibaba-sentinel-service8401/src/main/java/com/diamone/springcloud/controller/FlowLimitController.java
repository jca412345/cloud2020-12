package com.diamone.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/test1")
    public String test1(){
        return "----test1";
    }
    @GetMapping("/test2")
    public String test2(){
        log.info(Thread.currentThread().getName()+"\t"+"..test2");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "----test2";
    }

    @GetMapping("/testHotkey")
    @SentinelResource(value = "hotkey",blockHandler = "deal_hotkey")
    public String testHotkey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2){
        return "---testhotkey";
    }

    public String deal_hotkey(String p1, String p2, BlockException b){
        return "*--这是deal方法";
    }
}
