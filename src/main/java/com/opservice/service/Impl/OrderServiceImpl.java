package com.opservice.service.Impl;

import com.opservice.dao.OrderMapper;
import com.opservice.dao.OrderProductDetailMapper;
import com.opservice.dao.OrderSubsidiaryMapper;
import com.opservice.service.OrderServiceIn;
import com.yellowcar.entities.Order;
import com.yellowcar.entities.OrderProductDetail;
import com.yellowcar.entities.OrderSubsidiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/11.
 */
@Service
public class OrderServiceImpl implements OrderServiceIn {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderProductDetailMapper orderProductDetailMapper;
    @Autowired
    private OrderSubsidiaryMapper orderSubsidiaryMapper;

    @Override
    public List<Order> getOrderList() {
        return orderMapper.getOrders();
    }

    @Override
    public OrderProductDetail getOrderVehicleDetail(String orderCode) {

        OrderProductDetail orderProductDetail = new OrderProductDetail();
        orderProductDetail.setOrderCode(orderCode);
        orderProductDetail.setServicePackageId(0L);
        List<OrderProductDetail> list = orderProductDetailMapper.getBy(orderProductDetail);
        return list.get(0);
    }

    @Override
    public Order getOrder(String orderCode) {

        Order order = new Order();
        order.setCode(orderCode);
        List<Order> list = orderMapper.getBy(order);
        return list.get(0);
    }

    @Override
    public OrderSubsidiary getOrderSubsidiary(String orderCode) {
        return orderSubsidiaryMapper.getOrderSubsidiary(orderCode);
    }

    @Override
    public List<OrderProductDetail> getOrderProductDetailList(String orderCode) {

        OrderProductDetail orderProductDetail = new OrderProductDetail();
        orderProductDetail.setOrderCode(orderCode);
        List<OrderProductDetail> list = orderProductDetailMapper.getBy(orderProductDetail);
        return list;
    }

    @Override
    public void insertOrder(Order order) {
        orderMapper.save(order);
    }

    @Override
    public void insertOrderProductDetails(List<OrderProductDetail> list) {

        for (OrderProductDetail orderProductDetail : list) {
            orderProductDetailMapper.save(orderProductDetail);
        }
    }

    @Override
    public List<Order> getOrderListBy(Order order) {
        return null;
    }

    @Override
    public List<Order> getOrderListByTimedif(String staTime, String endTime) {
        List<Order> list = orderMapper.getOrdersByTimedif(staTime, endTime);
        return list;
    }

    @Override
    public void insertOrderSubsidiary(OrderSubsidiary orderSubsidiary) {
        orderSubsidiaryMapper.insertOrderSubsidiary(orderSubsidiary);
    }
}
