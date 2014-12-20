package com.opservice.service;

import com.yellowcar.api.op.OrderListBy;
import com.yellowcar.view.OrderDetailView;
import com.yellowcar.view.OrderGeneralView;
import com.yellowcar.view.OrderServiceView;
import com.yellowcar.entities.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhangfan on 2014/12/10.
 */
public interface OrderViewServiceIn {
    public String getOrderDetailView(String orderCode);

    public void generateOrderServiceViewList(List<OrderServiceView> list, OrderProductDetail orderProductDetail, Timestamp sourceOrderTime);

    public List<OrderGeneralView> getOrderGeneralViews(OrderListBy orderListBy);

    public void generateOrderGeneralViews(List<OrderGeneralView> list, Order order);

    public void insertOrderDetailView(OrderDetailView orderDetailView);

    public List<Product> getProducts();

    public List<ProductVehiclePackage> getProductVehiclePackages();

    public List<ServiceClause> getServiceClauses();

    public List<ServicePackage> getServicePackages();



}
