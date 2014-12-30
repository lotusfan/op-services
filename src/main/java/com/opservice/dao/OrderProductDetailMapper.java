package com.opservice.dao;

import com.yellowcar.entities.OrderProductDetail;

import java.util.List;

/**
 * Created by colin on 14/11/16.
 */
public interface OrderProductDetailMapper {
    public Long save(OrderProductDetail detail);

    public void update(OrderProductDetail detail);

//    public OrderOperation getById(Long id);

    public List<OrderProductDetail> getBy(OrderProductDetail detail);

    public List<OrderProductDetail> getByTime(String startTime, String endTime);

    public OrderProductDetail getCarByOrderCode(String orderCode);

}
