package com.diamone.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

//实现负载均衡算法
public interface LoadBanl {
    ServiceInstance instance(List<ServiceInstance> serviceInstanceList);
}
