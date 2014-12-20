package com.opservice.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yellowcar.api.op.OrderListBy;
import com.yellowcar.api.op.OrderViewServiceIn;
import com.opservice.service.OrderServiceParent;
import com.yellowcar.view.*;
import com.yellowcar.entities.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangfan on 2014/12/10.
 */

public class OrderViewServiceImpl extends OrderServiceParent implements OrderViewServiceIn {

    private int ORDERSTATUS_CREATE = 0;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public OrderDetailView getOrderDetailView(String orderCode) {

        OrderDetailView orderDetailView = new OrderDetailView();
        List<OrderServiceView> list = new ArrayList<OrderServiceView>();
        orderDetailView.setServices(list);

        //Order表
        Order order = orderServiceIn.getOrder(orderCode);

        orderDetailView.setOrderCode(order.getCode());
        orderDetailView.setChildNum(order.getChildNum()+"");
        orderDetailView.setPersonNum(order.getPersonNum()+"");
        orderDetailView.setPhone(order.getPhone());
        orderDetailView.setProductId(order.getProductId()+"");
        orderDetailView.setStatus(order.getStatus()+"");
        orderDetailView.setTripDate(sdf.format(order.getTripDate()));
        orderDetailView.setTripBegin(order.getTripBegin());
        orderDetailView.setProductTitle(order.getProductTitle());
        orderDetailView.setAmount(order.getAmount()+"");
        orderDetailView.setRemarks(order.getRemarks()+"");
        orderDetailView.setUserId(order.getUserId()+"");
        orderDetailView.setDayNum(order.getDayNum()+"");


        //Order附属表
        OrderSubsidiary orderSubsidiary = orderServiceIn.getOrderSubsidiary(orderCode);

        orderDetailView.setOrderSource(orderSubsidiary.getOrderSource());
        orderDetailView.setSourceOrderTime(sdf.format(orderSubsidiary.getSourceOrderTime()));
        orderDetailView.setOutId(orderSubsidiary.getOutId());
        orderDetailView.setTask(orderSubsidiary.getTask());
        orderDetailView.setCustomerName(orderSubsidiary.getCustomerName());
        orderDetailView.setCustomerEmail(orderSubsidiary.getCustomerEmail());
        orderDetailView.setPhoneLocal(orderSubsidiary.getPhoneLocal());
        orderDetailView.setCarType(orderSubsidiary.getCarType());
        orderDetailView.setReceivePlace(orderSubsidiary.getReceivePlace());
        orderDetailView.setRemind(orderSubsidiary.getRemind());
        orderDetailView.setFlightNum(orderSubsidiary.getFlightNum());
        orderDetailView.setFlightTime(sdf.format(orderSubsidiary.getFlightTime()));
        orderDetailView.setRemarksPlace(orderSubsidiary.getRemarksPlace());

        JSONArray jsonArray = JSONArray.parseArray(orderSubsidiary.getRemarksPlace());
        List<ReceivePlace> listrp = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            ReceivePlace receivePlace = new ReceivePlace();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            receivePlace.setDestination(jsonObject.getString("destination"));
            receivePlace.setDeaddress(jsonObject.getString("deaddress"));
            receivePlace.setGtime(jsonObject.getString("gtime"));
            listrp.add(receivePlace);
        }
        orderDetailView.setListrp(listrp);


        //Order Details 多表
        List<OrderProductDetail> opdlist = orderServiceIn.getOrderProductDetailList(orderCode);
        for (OrderProductDetail orderProductDetail : opdlist) {
            if (orderProductDetail.getVehiclePackageId() != 0 && orderProductDetail.getServicePackageId() == 0) {
                //用车
                orderDetailView.setVehiclePackageId(orderProductDetail.getVehiclePackageId()+"");
                orderDetailView.setUseTime(sdf.format(orderProductDetail.getUseTime()));
                orderDetailView.setSupplierId(orderProductDetail.getSupplierId()+"");
                Supplier supplier = supplierServiceIn.getSupplier(orderProductDetail.getSupplierId());
                orderDetailView.setSupplierName(supplier.getName());

                orderDetailView.setCarStatus(orderProductDetail.getStatus()+"");

                VehiclePackage vehiclePackage = vehicleServiceIn.getVehiclePackage(Long.parseLong(orderDetailView.getVehiclePackageId()));
                orderDetailView.setVehiclePackageDesc(vehiclePackage.getDesc());

                Vehicle vehicle = vehicleServiceIn.getVehicle(vehiclePackage.getVehicleId());
                orderDetailView.setVehicleTitle(vehicle.getTitle());
                // 销售价
                Timestamp sourceOrderTime = orderSubsidiary.getSourceOrderTime();
                VehiclePriceCalendar vehiclePriceCalendar = vehicleServiceIn.getVehiclePriceCalendar(orderProductDetail.getVehiclePackageId(), sourceOrderTime.getYear()+1900, sourceOrderTime.getMonth()+1, sourceOrderTime.getDate());
                if (vehiclePriceCalendar != null) orderDetailView.setSellPrice(vehiclePriceCalendar.getPrice()+"");
                //成本价，价格调整未录入

            } else {
                //Services 多表
                generateOrderServiceViewList(list, orderProductDetail, orderSubsidiary.getSourceOrderTime());
            }
        }
        System.out.println(JSONObject.toJSONString(orderDetailView));
        return orderDetailView;
    }

    @Override
    public void generateOrderServiceViewList(List<OrderServiceView> list, OrderProductDetail orderProductDetail, Timestamp sourceOrderTime) {
        OrderServiceView orderServiceView = new OrderServiceView();
        orderServiceView.setOrderProductDetailId(orderProductDetail.getId()+"");
        orderServiceView.setSupplierId(orderProductDetail.getSupplierId()+"");
        Supplier supplier = supplierServiceIn.getSupplier(orderProductDetail.getSupplierId());
        orderServiceView.setSupplierName(supplier.getName());

        orderServiceView.setStatus(orderProductDetail.getStatus()+"");
        orderServiceView.setServiceId(orderProductDetail.getServiceId()+"");
        orderServiceView.setServicePackageId(orderProductDetail.getServicePackageId()+"");
        ServicePackage servicePackage = sServiceServiceIn.getServicePackageById(orderProductDetail.getServicePackageId());
        orderServiceView.setServicePackageDesc(servicePackage.getServicePackageDesc());


        Timestamp useTime = orderProductDetail.getUseTime();
        if (useTime == null) return;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
        orderServiceView.setUseHms(simpleDateFormat1.format(useTime));//时、分、秒
        orderServiceView.setUseDate(simpleDateFormat.format(useTime));//年、月、日
        orderServiceView.setUnit(orderProductDetail.getUnit()+"");
        orderServiceView.setCount(orderProductDetail.getCount()+"");
        orderServiceView.setRemarks(orderProductDetail.getRemarks());
        // 销售价格
        ServicePackagePriceCalendar servicePackagePriceCalendar = sServiceServiceIn.getServicePPCalendarBy(
                orderProductDetail.getServicePackageId(), sourceOrderTime.getYear()+1900, sourceOrderTime.getMonth()+1, sourceOrderTime.getDate());
        if (servicePackagePriceCalendar != null) orderServiceView.setSellPrice(servicePackagePriceCalendar.getPrice()+"");
        //成本，调整

        list.add(orderServiceView);
    }

    @Override
    public List<OrderGeneralView> getOrderGeneralViews(OrderListBy orderListBy) {

        List<OrderGeneralView> listv = new ArrayList<OrderGeneralView>();
        //时间差查询
        if (orderListBy.getStatime() != null && orderListBy.getEndtime() != null) {
            List<Order> list = orderServiceIn.getOrderListByTimedif(orderListBy.getStatime(), orderListBy.getEndtime());
            for (Order orderr : list) {
                generateOrderGeneralViews(listv, orderr);
            }
        }
        //OrderCode查询
        String orderCode = orderListBy.getOrdercode();
        if (orderCode != null && orderCode.length() > 0) {
            generateOrderGeneralViews(listv, orderServiceIn.getOrder(orderCode));
        }
        return listv;
    }

    @Override
    public void generateOrderGeneralViews(List<OrderGeneralView> list, Order order) {


        OrderGeneralView orderGeneralView = new OrderGeneralView();
        list.add(orderGeneralView);

        orderGeneralView.setProductTitle(order.getProductTitle());
        orderGeneralView.setStatus(order.getStatus()+"");
        orderGeneralView.setOrderCode(order.getCode());

        OrderSubsidiary orderSubsidiary = orderServiceIn.getOrderSubsidiary(order.getCode());
        if (orderSubsidiary == null) return;
        orderGeneralView.setCustomerName(orderSubsidiary.getCustomerName());
        orderGeneralView.setOrderSource(orderSubsidiary.getOrderSource());
        orderGeneralView.setTask(orderSubsidiary.getTask());

        OrderProductDetail orderProductDetail = orderServiceIn.getOrderVehicleDetail(order.getCode());
        if (orderProductDetail == null) return;
        Timestamp useTime = orderProductDetail.getUseTime();
        if (useTime == null) return;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(" HH:mm:ss");
        orderGeneralView.setUseTime(simpleDateFormat1.format(useTime));//时、分、秒
        orderGeneralView.setUseDay(simpleDateFormat.format(useTime));//年、月、日
        String fromday = (System.currentTimeMillis() - useTime.getTime()) / 24 / 3600000 + "";
        orderGeneralView.setFromDay(fromday);//距用车日时间差

        Supplier supplier = supplierServiceIn.getSupplier(orderProductDetail.getSupplierId());
        if (supplier == null) return;
        orderGeneralView.setSupplierName(supplier.getName());
    }


    @Override
    public void insertOrderDetailView(OrderDetailView orderDetailView){

        List<OrderProductDetail> opdlist = new ArrayList<OrderProductDetail>();
        //生成订单号
        boolean flag = true;
        String orderCode = orderDetailView.getOrderCode();
        if (orderCode == null || orderCode.length() == 0) {
            orderCode = "code_" + System.currentTimeMillis();
            flag = true;
        }
        orderCode = "code_" + System.currentTimeMillis();
        //order表
        Order order = new Order();

        order.setCode(orderCode);//生成规则
        order.setChildNum(Integer.parseInt(orderDetailView.getChildNum()));
        order.setPersonNum(Integer.parseInt(orderDetailView.getPersonNum()));
        order.setPhone(orderDetailView.getPhone());
        order.setProductId(Long.parseLong(orderDetailView.getProductId()));
        order.setProductTitle(orderDetailView.getProductTitle()); //产品Title
        order.setUserId(Long.parseLong(orderDetailView.getUserId()));
        order.setDayNum(Integer.parseInt(orderDetailView.getDayNum()));
        order.setTripDate(Timestamp.valueOf(orderDetailView.getTripDate()));
        order.setAmount(new BigDecimal(orderDetailView.getAmount()));//订单总金额
        order.setPayAmount(new BigDecimal(0));//支付总金额
        order.setTripBegin(orderDetailView.getTripBegin());
        order.setRemarks(orderDetailView.getRemarks());
        order.setPayTime(Timestamp.valueOf(orderDetailView.getSourceOrderTime())); //支付时间--下单时间
        order.setStatus(ORDERSTATUS_CREATE);//订单状态（创建）

        //order_subsidiary表
        OrderSubsidiary orderSubsidiary = new OrderSubsidiary();

        orderSubsidiary.setOrderCode(orderCode);
        orderSubsidiary.setCarType(orderDetailView.getCarType());
        orderSubsidiary.setCustomerEmail(orderDetailView.getCustomerEmail());
        orderSubsidiary.setCustomerName(orderDetailView.getCustomerName());
        orderSubsidiary.setFlightNum(orderDetailView.getFlightNum());
        orderSubsidiary.setFlightTime(Timestamp.valueOf(orderDetailView.getFlightTime()));
        orderSubsidiary.setOrderSource(orderDetailView.getOrderSource());
        orderSubsidiary.setOutId(orderDetailView.getOutId());
        orderSubsidiary.setPhoneLocal(orderDetailView.getPhoneLocal());
        orderSubsidiary.setReceivePlace(orderDetailView.getReceivePlace());
        orderSubsidiary.setRemarksPlace(orderDetailView.getRemarksPlace());//地点json
        orderSubsidiary.setRemind(orderDetailView.getRemind());

        //order_detail表 车信息
        OrderProductDetail orderProductDetail = new OrderProductDetail();

        orderProductDetail.setOrderCode(orderCode);
        orderProductDetail.setVehiclePackageId(Long.parseLong(orderDetailView.getVehiclePackageId()));
        orderProductDetail.setServiceId(0L);
        orderProductDetail.setServicePackageId(0L);
        orderProductDetail.setCount(1);
        orderProductDetail.setSupplierId(Long.parseLong(orderDetailView.getSupplierId()));
        orderProductDetail.setPathId(0L);//没有用到
        orderProductDetail.setUseTime(Timestamp.valueOf(orderDetailView.getUseTime()));
        orderProductDetail.setStatus(ORDERSTATUS_CREATE);

        opdlist.add(orderProductDetail);

        //order_detail Service信息
        List<OrderServiceView> list = orderDetailView.getServices();

        for (OrderServiceView orderServiceView : list) {
            OrderProductDetail orderPDetail = new OrderProductDetail();
            orderPDetail.setOrderCode(orderCode);
            orderPDetail.setUnit(Integer.parseInt(orderServiceView.getUnit()));
            orderPDetail.setUnitPrice(new BigDecimal(0));
            orderPDetail.setSupplierId(Long.parseLong(orderServiceView.getSupplierId()));
            orderPDetail.setCount(Integer.parseInt(orderServiceView.getCount()));
            orderPDetail.setServicePackageId(Long.parseLong(orderServiceView.getServicePackageId()));
            orderPDetail.setServiceId(Long.parseLong(orderServiceView.getServiceId()));
            orderPDetail.setRemarks(orderServiceView.getRemarks());
            orderPDetail.setStatus(ORDERSTATUS_CREATE);//订单状态  （创建）
            orderPDetail.setUseTime(Timestamp.valueOf(orderServiceView.getUseTime()));
            opdlist.add(orderPDetail);
        }
        if(flag){
            orderServiceIn.insertOrderSubsidiary(orderSubsidiary);
            orderServiceIn.insertOrder(order);
            orderServiceIn.insertOrderProductDetails(opdlist);
        }else {
         //更新订单
        }
    }
}
