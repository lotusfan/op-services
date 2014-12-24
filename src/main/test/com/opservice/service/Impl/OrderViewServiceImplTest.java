package com.opservice.service.Impl;

import com.alibaba.fastjson.JSON;
import com.opservice.service.OrderServiceIn;
import com.opservice.service.VehicleServiceIn;
import com.yellowcar.api.op.OrderListBy;
import com.yellowcar.api.op.OrderPriceIn;
import com.yellowcar.api.op.OrderViewServiceIn;
import com.opservice.service.SServiceServiceIn;
import com.yellowcar.entities.ServicePackageSPriceCalendar;
import com.yellowcar.entities.VehicleSupplierPriceCalendar;
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

import java.math.BigDecimal;
import java.sql.Timestamp;
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
    @Autowired
    private VehicleServiceIn vehicleServiceIn;
    @Autowired
    private OrderPriceIn orderPriceIn;



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


    public void testUpdateOPById() {
        Order order = new Order();
        order.setCode("code_1419220726948");
//        order.setProductTitle("product");
//        order.setAmount(new BigDecimal("400.00"));
//        order.setProductId(123456789L);
//        order.setDayNum(5);
//        order.setChildNum(9);
        order.setPersonNum(9);
//        order.setPayAmount(new BigDecimal(653));
//        order.setPayTime(new Timestamp(System.currentTimeMillis()));
//        order.setTripDate(new Timestamp(System.currentTimeMillis()));
        orderServiceIn.updateOById(order);

    }


    public void testGetServicePSPCBy() {
        ServicePackageSPriceCalendar spspc = new ServicePackageSPriceCalendar();
        spspc.setServicePackageId(1);
        spspc.setSupplierId(1);
        spspc.setYear(2014);
        spspc.setMonth(12);
        spspc.setDay(22);
        System.out.println(sServiceServiceIn.getSPSPCBy(spspc));
    }


    public void testGetVehicleSPCBy() {
        VehicleSupplierPriceCalendar vspc = new VehicleSupplierPriceCalendar();
        vspc.setSupplierId(1);
        vspc.setVehiclePackageId(1);
        vspc.setYear(2014);
        vspc.setMonth(12);
        vspc.setDay(22);
        System.out.println(vehicleServiceIn.getVehicleSPCBy(vspc));
    }


    public void testGetVehiclePrice() {
        System.out.println(orderPriceIn.getVehiclePrice(1+"",1+"","2014-12-16"));
    }

    @Test
    public void testGetServicePackagePrice() {
        System.out.println(orderPriceIn.getServicePackagePrice(1+"", 1+"", "2014-12-22"));
    }

}