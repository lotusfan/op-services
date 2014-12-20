package com.opservice.dao;

import com.yellowcar.entities.OrderSubsidiary;

/**
 * Created by zhangfan on 2014/12/10.
 */
public interface OrderSubsidiaryMapper {
    public OrderSubsidiary getOrderSubsidiary(String order_code);

    public void insertOrderSubsidiary(OrderSubsidiary orderSubsidiary);
}
