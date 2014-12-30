package com.opservice.dao;

import com.yellowcar.entities.OrderPrimeItem;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/26.
 */
public interface OrderPrimeItemMapper {
    public List<OrderPrimeItem> getOrderPrimeItemBy(OrderPrimeItem orderPrimeItem);

    public Long save(OrderPrimeItem orderPrimeItem);
}
