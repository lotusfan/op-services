package com.opservice.service;

import com.yellowcar.api.op.OrderPriceIn;
import com.yellowcar.entities.*;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/11.
 */
public interface OrderServiceIn {

    public Order getOrder(String orderCode);

    public List<OrderProductDetail> getOrderProductDetailList(String orderCode);

    public OrderSubsidiary getOrderSubsidiary(String orderCode);

    public List<Order> getOrderList();

    public OrderProductDetail getOrderVehicleDetail(String orderCode); //查询车相关信息表serviceID = 0, servicePackageId = 0;

    public void insertOrder(Order order);

    public void insertOrderProductDetails(List<OrderProductDetail> list);

    public List<Order> getOrderListBy(Order order);

    public List<Order> getOrderListByTimedif(String staTime, String endTime);

    public void insertOrderSubsidiary(OrderSubsidiary orderSubsidiary);

    public void updateOSById(OrderSubsidiary orderSubsidiary);

    public void updateOById(Order order);

    public void updateOPById(List<OrderProductDetail> list);

    public List<OrderItem> getOrderItemBy(OrderItem orderItem);

    public List<OrderPrimeItem> getOrderPrimeItemBy(OrderPrimeItem orderPrimeItem);

    public Long insertOrUpdateOrderPrimeItem(OrderPrimeItem orderPrimeItem);

    public Long insertOrUpdateOrderItem(OrderItem orderItem);

    public void insertOrderProductDetail(OrderProductDetail orderProductDetail);

    public void updateOrderProductDetail(OrderProductDetail orderProductDetail);

    public List<OrderProductDetail> getOrderProductDetailByTime(String startTime, String endTime);

    public OrderProductDetail getOrderProductDetailCarByOrderCode(String orderCode);

}
