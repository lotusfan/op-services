package com.opservice.model;

import com.opservice.Body;

/**
 * Created by zhangfan on 2015/1/17.
 */
public class OrderCancelPMSModel extends Body {
    private String orderCode;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public String toString() {
        return "OrderCancelPMSModel{" +
                "orderCode='" + orderCode + '\'' +
                '}';
    }
}
