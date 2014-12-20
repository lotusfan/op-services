package com.opservice.service.Impl;

import com.alibaba.fastjson.JSON;
import com.opservice.service.OrderServiceIn;
import com.yellowcar.api.op.OrderListBy;
import com.yellowcar.api.op.OrderViewServiceIn;
import com.opservice.service.SServiceServiceIn;
import com.yellowcar.view.OrderGeneralView;
import com.yellowcar.entities.Order;
import com.yellowcar.entities.OrderProductDetail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class OrderViewServiceImplTest {


    @Autowired
    private OrderViewServiceIn orderViewServiceIn;
    @Autowired
    private OrderServiceIn orderServiceIn;

    @Autowired
    private SServiceServiceIn sServiceServiceIn;



    @Before
    public void setUp() throws Exception {
        System.out.println("aaaaaa");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("bbbbbb");
    }



    public void testGetOrder() throws Exception {
       orderServiceIn.getOrder("code_1417709720546");
    }



    public void testGetOrderView() {

       // System.out.println(System.currentTimeMillis());
       System.out.println(orderViewServiceIn.getOrderDetailView("code_1417759878917"));
    }



  /*  public void testGetGeneralViews() {
        List<OrderGeneralView> list = orderViewServiceIn.getOrderGeneralViews();
        System.out.println(list.size());
        for (OrderGeneralView orderGeneralView : list) {
            System.out.println(orderGeneralView);
        }
    }*/


    public void testGetOrdersList() {
        List<Order> list = orderServiceIn.getOrderList();
        for (Order order : list) {
            System.out.println(order);

        }

    }



    public void testGetServicePPCalendarBy() {
        sServiceServiceIn.getServicePPCalendarBy(14L,12,11,10);
        //sServiceServiceIn.getSPPCById(1L);
    }


    public void testInsertOrder() {
        Order order = new Order();
        order.setCode("zzzzzzzzzzzzzzzzzz");
        orderServiceIn.insertOrder(order);
    }


    public void testInsertOrderProductDetails() {
        List<OrderProductDetail> list = new ArrayList<OrderProductDetail>();
        OrderProductDetail orderProductDetail = new OrderProductDetail();
        orderProductDetail.setOrderCode("zzzzzzzzzzzzzzzzz");
        list.add(orderProductDetail);
        orderServiceIn.insertOrderProductDetails(list);
    }


    public void testGetOrderListByTimedif() {
        System.out.println(orderServiceIn.getOrderListByTimedif("2014-12-09", "2014-12-10").size());
    }

    @Test
    public void testGetOrderGeneralViews() {
        OrderListBy orderListBy = new OrderListBy();
//        orderListBy.setStatime("2014-12-09");
//        orderListBy.setEndtime("2014-12-10");
        orderListBy.setOrdercode("code_1417759878917");
        List list = orderViewServiceIn.getOrderGeneralViews(orderListBy);
        System.out.println(list.size());
    }
/*
    public void testGetOrderSubsidiary() {
        orderService.getOrderSubsidiary("code_1417762452555");
    }


    public void testGetOrderProductDetailList() {
        orderService.getOrderProductDetailList("code_1417709720546");
    }

    public void testGetProduct() {
        orderService.getProduct(10L);
    }


    public void testSupplier() {
        orderService.getSupplier(100L);
    }


    public void testVehicle() {
        orderService.getVehicle(200L);
    }


    public void testVehiclePackage() {
        orderService.getVehiclePackage(7);
    }
    public void testVehiclePriceCalendar() {
        orderService.getVehiclePriceCalendar(2L,2014,11,18);
    }*/

}