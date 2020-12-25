package com.diamone.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBanl {


    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 214748367 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("***next:"+ next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstanceList) {
        int index=getIncrement() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
