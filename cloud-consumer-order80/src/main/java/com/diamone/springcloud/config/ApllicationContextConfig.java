package com.diamone.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApllicationContextConfig {

    @Bean
    @LoadBalanced//负载均衡能力 这是去掉之后用自己的，里面的controller里只有一个能用，就是最下面的lb，如果想用其他的，得打开它
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Primary
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
//
