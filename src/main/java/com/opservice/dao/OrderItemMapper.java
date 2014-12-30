package com.opservice.dao;

import com.yellowcar.entities.OrderItem;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/25.
 */
public interface OrderItemMapper {
    public List<OrderItem> getBy(OrderItem orderItem);

    public Long save(OrderItem orderItem);
}
