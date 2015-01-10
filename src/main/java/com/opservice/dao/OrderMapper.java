package com.opservice.dao;

import com.yellowcar.entities.Order;

import java.util.List;

/**
 * Created by colin on 14/11/26.
 */
public interface OrderMapper {
    public Long save(Order order);

    public void update(Order order);

    public Order getById(Long id);

    public List<Order> getBy(Order order);

    public List<Order> getOrders();

    public List<Order> getOrdersByTimedif(String staTime, String endTime);

}
