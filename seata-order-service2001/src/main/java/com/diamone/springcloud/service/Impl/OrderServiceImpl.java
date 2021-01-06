package com.diamone.springcloud.service.Impl;

import com.diamone.springcloud.dao.OrderDao;
import com.diamone.springcloud.domain.Order;
import com.diamone.springcloud.service.AccountService;
import com.diamone.springcloud.service.OrderService;
import com.diamone.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    @Override
    public void create(Order order) {
        log.info("---->开始创建订单");
        orderDao.create(order);
        log.info("--->订单微服务开始调用库存");

        storageService.decrease(order.getProductId(),order.getCount());
        log.info("--->订单微服务开始到end");
        accountService.decrease(order.getUserId(),order.getMoney());

        //修改订单状态
        log.info("---->修改订单状态");
        orderDao.update(order.getUserId(),0);
        log.info("---->修改订单结束");
    }
}
