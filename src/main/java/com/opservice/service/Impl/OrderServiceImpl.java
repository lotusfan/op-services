package com.opservice.service.Impl;

import com.opservice.dao.*;
import com.opservice.service.OrderServiceIn;
import com.yellowcar.entities.*;
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
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderPrimeItemMapper orderPrimeItemMapper;

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
        return   (list != null && list.size() == 1)?list.get(0):null;
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

    @Override
    public void updateOSById(OrderSubsidiary orderSubsidiary) {
        orderSubsidiaryMapper.updateOSById(orderSubsidiary);
    }

    @Override
    public void updateOById(Order order) {
        orderMapper.update(order);
    }

    @Override
    public void updateOPById(List<OrderProductDetail> list) {
        for (OrderProductDetail orderProductDetail : list) {
            orderProductDetailMapper.update(orderProductDetail);
        }
    }

    @Override
    public List<OrderItem> getOrderItemBy(OrderItem orderItem) {
        return orderItemMapper.getBy(orderItem);
    }

    @Override
    public List<OrderPrimeItem> getOrderPrimeItemBy(OrderPrimeItem orderPrimeItem) {
        return orderPrimeItemMapper.getOrderPrimeItemBy(orderPrimeItem);
    }

    @Override
    public Long insertOrUpdateOrderPrimeItem(OrderPrimeItem orderPrimeItem) {
        return orderPrimeItemMapper.save(orderPrimeItem);
    }

    @Override
    public Long insertOrUpdateOrderItem(OrderItem orderItem) {
        return orderItemMapper.save(orderItem);
    }

    @Override
    public void insertOrderProductDetail(OrderProductDetail orderProductDetail) {
        orderProductDetailMapper.save(orderProductDetail);
    }

    public void updateOrderProductDetail(OrderProductDetail orderProductDetail) {
        orderProductDetailMapper.update(orderProductDetail);

    }

    @Override
    public List<OrderProductDetail> getOrderProductDetailByTime(String startTime, String endTime) {
        List<OrderProductDetail> list = orderProductDetailMapper.getByTime(startTime, endTime + " 23:59:59");
        return (list != null && list.size() > 0)?list:null;
    }

    @Override
    public OrderProductDetail getOrderProductDetailCarByOrderCode(String orderCode) {

        return orderProductDetailMapper.getCarByOrderCode(orderCode);
    }
}
