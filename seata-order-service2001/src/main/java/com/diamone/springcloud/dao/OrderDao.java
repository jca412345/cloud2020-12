package com.diamone.springcloud.dao;

import com.diamone.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //1.创建订单
    void create(Order order);

    //修改订单状态,从0改1
    void update(@Param("userId") Long userId,@Param("status") Integer status);
}
