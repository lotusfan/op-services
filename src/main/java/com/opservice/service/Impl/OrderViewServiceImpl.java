package com.opservice.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opservice.service.OrderServiceParent;
import com.yellowcar.api.op.OrderListBy;
import com.yellowcar.api.op.OrderViewServiceIn;
import com.yellowcar.entities.*;
import com.yellowcar.view.OrderDetailView;
import com.yellowcar.view.OrderGeneralView;
import com.yellowcar.view.OrderServiceView;
import com.yellowcar.view.RemarksPlace;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangfan on 2014/12/10.
 */

public class OrderViewServiceImpl extends OrderServiceParent implements OrderViewServiceIn {

    private final int ORDERSTATUS_CREATE = 0;
    private final int PAY_STATUS = 2;            //支付状态
    private final int REFUND_STASUS = 6;         //拒绝状态
    private final String PRIMEPRICEMODIFY = "primePriceModify";
    private final String SELLPRICEMODIFY = "sellPriceModify";
    private final String ORDERPICPREFIX = "http://192.168.199.153:8202/pic/";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdfsp = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdfhm = new SimpleDateFormat("HH:mm:ss");

    @Override
    public OrderDetailView getOrderDetailView(String orderCode) {

        OrderDetailView orderDetailView = new OrderDetailView();
        List<OrderServiceView> list = new ArrayList<OrderServiceView>();
        orderDetailView.setServices(list);

        //Order表
        Order order = orderServiceIn.getOrder(orderCode);
        if (order == null) return null;

        orderDetailView.setOrderCode(order.getCode());
        orderDetailView.setChildNum(order.getChildNum() + "");
        orderDetailView.setPersonNum(order.getPersonNum() + "");
        orderDetailView.setPhone(order.getPhone());
        orderDetailView.setProductId(order.getProductId() + "");
        orderDetailView.setStatus(order.getStatus());
        try {
            orderDetailView.setTripDate(order.getTripDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        orderDetailView.setTripBegin(order.getTripBegin());
        orderDetailView.setProductTitle(order.getProductTitle());
        orderDetailView.setAmount(order.getAmount());
        orderDetailView.setRemarks(order.getRemarks() + "");
        orderDetailView.setUserId(order.getUserId() + "");
        orderDetailView.setDayNum(order.getDayNum() + "");


        //Order附属表
        OrderSubsidiary orderSubsidiary = orderServiceIn.getOrderSubsidiary(orderCode);
        List<RemarksPlace> listrp = new ArrayList<>();
        if (orderSubsidiary != null) {

            orderDetailView.setOrderSubsidiaryId(orderSubsidiary.getId() + "");
            orderDetailView.setOrderSource(orderSubsidiary.getOrderSource());
            if (orderSubsidiary.getSourceOrderTime() != null) {
                orderDetailView.setSourceOrderTime(sdf.format(orderSubsidiary.getSourceOrderTime()));
            }
            if (orderSubsidiary.getOutId() != null) {
                orderDetailView.setOutId(orderSubsidiary.getOutId());
            }
            orderDetailView.setTask(orderSubsidiary.getTask());
            orderDetailView.setCustomerName(orderSubsidiary.getCustomerName());
            orderDetailView.setCustomerEmail(orderSubsidiary.getCustomerEmail());
            orderDetailView.setPhoneLocal(orderSubsidiary.getPhoneLocal());
            orderDetailView.setCarType(orderSubsidiary.getCarType());
            orderDetailView.setReceivePlace(orderSubsidiary.getReceivePlace());
            orderDetailView.setRemind(orderSubsidiary.getRemind());
            orderDetailView.setFlightNum(orderSubsidiary.getFlightNum());
            if (orderSubsidiary.getFlightTime() != null) {
                orderDetailView.setFlightTime(sdf.format(orderSubsidiary.getFlightTime()));
            }
            orderDetailView.setTripDetail(orderSubsidiary.getTripDetail());
            orderDetailView.setTripPhone(orderSubsidiary.getTripPhone());
            orderDetailView.setCustomerNamePy(orderSubsidiary.getCustomerNamePy());
            orderDetailView.setRemarksPlace(orderSubsidiary.getRemarksPlace());
            if (orderSubsidiary.getOrderPics() != null && orderSubsidiary.getOrderPics().length() > 0)
                orderDetailView.setOrderPics(ORDERPICPREFIX + orderSubsidiary.getOrderPics());
            JSONArray jsonArray = JSONArray.parseArray(orderSubsidiary.getRemarksPlace());
            if (jsonArray != null && jsonArray.size() != 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    RemarksPlace remarksPlace = new RemarksPlace();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.containsKey("destination"))
                        remarksPlace.setDestination(jsonObject.getString("destination"));
                    if (jsonObject.containsKey("deaddress"))
                        remarksPlace.setDeaddress(jsonObject.getString("deaddress"));
                    if (jsonObject.containsKey("gtime"))
                        remarksPlace.setGtime(jsonObject.getString("gtime"));
                    if (jsonObject.containsKey("phone"))
                        remarksPlace.setPhone(jsonObject.getString("phone"));
                    listrp.add(remarksPlace);
                }
            }
        }
        orderDetailView.setListrp(listrp);

        //Order Details 多表
        List<OrderProductDetail> opdlist = orderServiceIn.getOrderProductDetailList(orderCode);
        for (OrderProductDetail orderProductDetail : opdlist) {
            if (orderProductDetail.getVehiclePackageId() == 0 && orderProductDetail.getServiceId() == 0)
                continue;
            if (orderProductDetail.getVehiclePackageId() != 0 && orderProductDetail.getServicePackageId() == 0) {
                //用车
                orderDetailView.setOrderProductDetailId(orderProductDetail.getId() + "");
                orderDetailView.setVehiclePackageId(orderProductDetail.getVehiclePackageId() + "");
                if (orderProductDetail.getUseTime() != null)
                    orderDetailView.setUseTime(sdf.format(orderProductDetail.getUseTime()));
                orderDetailView.setSupplierId(orderProductDetail.getSupplierId() + "");
                orderDetailView.setChangePriceFlag(orderProductDetail.getChangePriceFlag() + "");
                Supplier supplier = supplierServiceIn.getSupplier(orderProductDetail.getSupplierId());
                if (supplier != null)
                    orderDetailView.setSupplierName(supplier.getName());

                orderDetailView.setCarStatus(orderProductDetail.getStatus());

                VehiclePackage vehiclePackage = vehicleServiceIn.getVehiclePackage(Long.parseLong(orderDetailView.getVehiclePackageId()));
                orderDetailView.setVehiclePackageDesc(vehiclePackage.getDesc());

                Vehicle vehicle = vehicleServiceIn.getVehicle(vehiclePackage.getVehicleId());
                orderDetailView.setVehicleTitle(vehicle.getTitle());

                Timestamp sourceOrderTime = orderProductDetail.getUseTime();
                if (sourceOrderTime != null) {
                    //销售价
                    VehiclePriceCalendar vehiclePriceCalendar = vehicleServiceIn.getVehiclePriceCalendar(orderProductDetail.getVehiclePackageId(), sourceOrderTime.getYear() + 1900, sourceOrderTime.getMonth() + 1, sourceOrderTime.getDate());
                    if (vehiclePriceCalendar != null)
                        orderDetailView.setSellPrice(vehiclePriceCalendar.getPrice() + "");
                    //成本价
                    VehicleSupplierPriceCalendar vspc = new VehicleSupplierPriceCalendar();
                    vspc.setVehiclePackageId(orderProductDetail.getVehiclePackageId());
                    vspc.setYear(sourceOrderTime.getYear() + 1900);
                    vspc.setMonth(sourceOrderTime.getMonth() + 1);
                    vspc.setDay(sourceOrderTime.getDate());
                    vspc.setSupplierId(orderProductDetail.getSupplierId());
                    List<VehicleSupplierPriceCalendar> listvspc = vehicleServiceIn.getVehicleSPCBy(vspc);
                    if (listvspc != null && listvspc.size() == 1) {
                        orderDetailView.setPrimePrice(listvspc.get(0).getPrice() + "");
                        orderDetailView.setMoneyType(listvspc.get(0).getMoneyType() + "");
                    }
                }
                //价格调整
                if (orderProductDetail.getChangePriceFlag() == 1) {
                    //成本价格调整
                    OrderItem orderItem = new OrderItem();
                    orderItem.setSourceId(orderProductDetail.getId());
                    List<OrderItem> listoi = orderServiceIn.getOrderItemBy(orderItem);
                    if (listoi != null && listoi.size() == 1)
                        orderDetailView.setSellPriceModify(listoi.get(0).getAmount() + "");
                    //销售价格调整
                    OrderPrimeItem orderPrimeItem = new OrderPrimeItem();
                    orderPrimeItem.setSourceId(orderProductDetail.getId());
                    List<OrderPrimeItem> listopi = orderServiceIn.getOrderPrimeItemBy(orderPrimeItem);
                    if (listopi != null && listopi.size() == 1)
                        orderDetailView.setPrimePriceModify(listopi.get(0).getAmount() + "");
                }


            } else {
                //Services 多表
                generateOrderServiceViewList(list, orderProductDetail, null);
            }
        }
        return orderDetailView;
    }

    @Override
    public void generateOrderServiceViewList(List<OrderServiceView> list, OrderProductDetail orderProductDetail, Timestamp sourceOrderTime) {
        OrderServiceView orderServiceView = new OrderServiceView();
        orderServiceView.setOrderProductDetailId(orderProductDetail.getId() + "");
        orderServiceView.setSupplierId(orderProductDetail.getSupplierId() + "");
        Supplier supplier = supplierServiceIn.getSupplier(orderProductDetail.getSupplierId());
        if (supplier != null)
            orderServiceView.setSupplierName(supplier.getName());

        orderServiceView.setStatus(orderProductDetail.getStatus() + "");
        orderServiceView.setServiceId(orderProductDetail.getServiceId() + "");
        orderServiceView.setServicePackageId(orderProductDetail.getServicePackageId() + "");

        ServicePackage servicePackage = sServiceServiceIn.getServicePackageById(orderProductDetail.getServicePackageId());
        if (servicePackage != null) {
            orderServiceView.setServicePackageDesc(servicePackage.getServicePackageDesc());
            orderServiceView.setChangePriceFlag(orderProductDetail.getChangePriceFlag() + "");
        }


        if (orderProductDetail.getUnit() != null) {

            orderServiceView.setUnit(orderProductDetail.getUnit() + "");
            switch (orderProductDetail.getUnit()) {
                case 0:
                    orderServiceView.setUnitDefinition("天");
                    break;
                case 1:
                    orderServiceView.setUnitDefinition("/人份");
                    break;
                case 2:
                    orderServiceView.setUnitDefinition("份");
                    break;
            }
        }

        orderServiceView.setCount(orderProductDetail.getCount() + "");
        orderServiceView.setRemarks(orderProductDetail.getRemarks());
        Timestamp useTime = orderProductDetail.getUseTime();
        if (useTime != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
            orderServiceView.setUseHms(simpleDateFormat1.format(useTime));//时、分、秒
//            orderServiceView.setUseDate(simpleDateFormat.format(useTime));//年、月、日
            orderServiceView.setUseDate(sdf.format(useTime));
            // 销售价格
            ServicePackagePriceCalendar servicePackagePriceCalendar = sServiceServiceIn.getServicePPCalendarBy(
                    orderProductDetail.getServicePackageId(), useTime.getYear() + 1900, useTime.getMonth() + 1, useTime.getDate());
            if (servicePackagePriceCalendar != null)
                orderServiceView.setSellPrice(servicePackagePriceCalendar.getPrice() + "");
            //成本价格
            ServicePackageSPriceCalendar spspc = new ServicePackageSPriceCalendar();
            spspc.setServicePackageId(orderProductDetail.getServicePackageId());
            spspc.setSupplierId(orderProductDetail.getSupplierId());
            spspc.setYear(useTime.getYear() + 1900);
            spspc.setMonth(useTime.getMonth() + 1);
            spspc.setDay(useTime.getDate());
            List<ServicePackageSPriceCalendar> listspspc = sServiceServiceIn.getSPSPCBy(spspc);
            if (listspspc != null && listspspc.size() == 1) {
                orderServiceView.setPrimePrice(listspspc.get(0).getPrice() + "");
                orderServiceView.setMoneyType(listspspc.get(0).getMoneyType() + "");
            }
        }
        //价格调整
        if (orderProductDetail.getChangePriceFlag() == 1) {
            //成本价格调整
            OrderItem orderItem = new OrderItem();
            orderItem.setSourceId(orderProductDetail.getId());
            List<OrderItem> listoi = orderServiceIn.getOrderItemBy(orderItem);
            if (listoi != null && listoi.size() == 1)
                orderServiceView.setSellPriceModify(listoi.get(0).getAmount() + "");
            //销售价格调整
            OrderPrimeItem orderPrimeItem = new OrderPrimeItem();
            orderPrimeItem.setSourceId(orderProductDetail.getId());
            List<OrderPrimeItem> listopi = orderServiceIn.getOrderPrimeItemBy(orderPrimeItem);
            if (listopi != null && listopi.size() == 1)
                orderServiceView.setPrimePriceModify(listopi.get(0).getAmount() + "");
        }
        list.add(orderServiceView);
    }

    @Override
    public List<OrderGeneralView> getOrderGeneralViews(OrderListBy orderListBy) {

//        List<OrderGeneralView> listv = new ArrayList<OrderGeneralView>();
        if (orderListBy.getStatime() == null || orderListBy.getStatime().length() == 0) {
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 3);
                orderListBy.setStatime(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (orderListBy.getEndtime() == null || orderListBy.getEndtime().length() == 0) {
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 3);
                orderListBy.setEndtime(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            orderListBy.setEndtime(orderListBy.getEndtime() + " 23:59:59");
        }

        if (orderListBy.getCustomername() != null && orderListBy.getCustomername().length() > 0) {
            orderListBy.setCustomername("%" + orderListBy.getCustomername().replaceAll(" ", "%") + "%");
        }
        if (orderListBy.getProducttitle() != null && orderListBy.getProducttitle().length() > 0) {
            orderListBy.setProducttitle("%" + orderListBy.getProducttitle().replaceAll(" ", "%") + "%");
        }

        if (orderListBy.getOrdersource() != null && orderListBy.getOrdersource().length() > 0) {
            orderListBy.setOrdersource("%" + orderListBy.getOrdersource().replaceAll(" ", "%") + "%");
        }

        List<OrderGeneralView> list = orderServiceIn.getOrderGeneralViewBy(orderListBy);
        if (list != null) {
            for (OrderGeneralView orderGeneralView : list) {
                Timestamp useTime = orderGeneralView.getUseTime();
                if (useTime != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(" HH:mm:ss");
                    orderGeneralView.setUseHms(simpleDateFormat1.format(useTime));//时、分、秒
                    orderGeneralView.setUseDay(simpleDateFormat.format(useTime));//年、月、日
                    try {
                        Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
                        Date date1 = simpleDateFormat.parse(orderGeneralView.getUseDay());
                        String fromday = (date1.getTime() - date.getTime()) / 24 / 3600000 + "";
                        orderGeneralView.setFromDay(fromday);//距用车日时间差
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (orderGeneralView.getSupplierId() != null && orderGeneralView.getSupplierId().length() > 0) {
                    Supplier supplier = supplierServiceIn.getSupplier(Long.parseLong(orderGeneralView.getSupplierId()));
                    if (supplier != null)
                        orderGeneralView.setSupplierName(supplier.getName());
                }

            }
        }
        return list;
       /* //时间段查询
        if (orderListBy.getStatime().length() > 0 && orderListBy.getEndtime().length() > 0) {
            List<OrderProductDetail> list = orderServiceIn.getOrderProductDetailByTime(orderListBy.getStatime(), orderListBy.getEndtime());
            if (list != null) {
                for (OrderProductDetail orderProductDetail : list) {
                    generateOrderGeneralViews(listv, orderServiceIn.getOrder(orderProductDetail.getOrderCode()), orderProductDetail,
                            orderServiceIn.getOrderSubsidiary(orderProductDetail.getOrderCode()));
                }
            }
        }
        //OrderCode查询
        String orderCode = orderListBy.getOrdercode();
        if (orderCode != null && orderCode.length() > 0) {
            generateOrderGeneralViews(listv, orderServiceIn.getOrder(orderCode), orderServiceIn.getOrderProductDetailCarByOrderCode(orderCode),
                    orderServiceIn.getOrderSubsidiary(orderCode));
        }*/
    }

    /*
    旧方法   弃用
     */
    @Override
    public void generateOrderGeneralViews(List<OrderGeneralView> list, Order order, OrderProductDetail orderProductDetail, OrderSubsidiary orderSubsidiary) {


        OrderGeneralView orderGeneralView = new OrderGeneralView();
        list.add(orderGeneralView);

        orderGeneralView.setProductTitle(order.getProductTitle());
        orderGeneralView.setOrderStatus(order.getStatus() + "");
        orderGeneralView.setOrderCode(order.getCode());

        if (orderSubsidiary != null) {
            orderGeneralView.setCustomerName(orderSubsidiary.getCustomerName());
            orderGeneralView.setOrderSource(orderSubsidiary.getOrderSource());
            orderGeneralView.setTask(orderSubsidiary.getTask());
        }

        if (orderProductDetail != null) {
            Timestamp useTime = orderProductDetail.getUseTime();
            if (useTime != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(" HH:mm:ss");
                orderGeneralView.setUseHms(simpleDateFormat1.format(useTime));//时、分、秒
                orderGeneralView.setUseDay(simpleDateFormat.format(useTime));//年、月、日
                try {
                    Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
                    Date date1 = simpleDateFormat.parse(orderGeneralView.getUseDay());
                    String fromday = (date.getTime() - date1.getTime()) / 24 / 3600000 + "";
                    orderGeneralView.setFromDay(fromday);//距用车日时间差
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Supplier supplier = supplierServiceIn.getSupplier(orderProductDetail.getSupplierId());
            if (supplier != null)
                orderGeneralView.setSupplierName(supplier.getName());
        }

    }


    @Override
    public void insertOrderDetailView(OrderDetailView orderDetailView) {

        List<OrderProductDetail> opdlist = new ArrayList<OrderProductDetail>();
        //生成订单号
        boolean flag = false;
        String orderCode = orderDetailView.getOrderCode();
        if (orderCode == null || orderCode.length() == 0) {
            orderCode = "code_" + System.currentTimeMillis();
            flag = true;
        }
        BigDecimal amount = new BigDecimal(0);

        try {

            //order_detail表 车信息
            amount = amount.add(handleVehicle(orderDetailView, orderCode, flag));

            //order_detail Service信息
            List<OrderServiceView> list = orderDetailView.getServices();
            for (OrderServiceView orderServiceView : list) {
                amount = amount.add(handleService(orderServiceView, orderCode));
            }
            //订单和订单附属表
            handleOrderAndOrderSubsidiary(orderDetailView, orderCode, amount, flag);

           /* if (Integer.parseInt(orderDetailView.getStatus()) == PAYSTATUS) {
                //发送订单信息 快照 到PMS    OP->PMS 供应商，路线Map映射
                oPRequestServiceIn.sendOrderTransferToPMS(orderCode);
            } else if (Integer.parseInt(orderDetailView.getStatus()) == REFUNDSTASUS) {
                //发送联消订单号到PMS
                oPRequestServiceIn.sendOrderCancelToPMS(orderCode);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String handleOrderAndOrderSubsidiary(OrderDetailView orderDetailView, String orderCode, BigDecimal amount, boolean flag) {

        try {
            //order表
            Order order = new Order();

            order.setCode(orderCode);//生成规则
            if (orderDetailView.getChildNum() != null && orderDetailView.getChildNum().length() > 0)
                order.setChildNum(Integer.parseInt(orderDetailView.getChildNum()));
            if (orderDetailView.getPersonNum() != null && orderDetailView.getPersonNum().length() > 0)
                order.setPersonNum(Integer.parseInt(orderDetailView.getPersonNum()));
            if (orderDetailView.getPhone() != null && orderDetailView.getPhone().length() > 0) {
                order.setPhone(orderDetailView.getPhone());
                User user = userServiceIn.getUserByPhone(orderDetailView.getPhone());
                order.setUserId((user != null) ? user.getId() : 0L);
            }
            if (orderDetailView.getProductId() != null && orderDetailView.getProductId().length() > 0) {
                order.setProductId(Long.parseLong(orderDetailView.getProductId()));
            }
            order.setProductTitle(orderDetailView.getProductTitle()); //产品Title
            if (orderDetailView.getDayNum() != null && orderDetailView.getDayNum().length() > 0)
                order.setDayNum(Integer.parseInt(orderDetailView.getDayNum()));

            order.setTripDate(orderDetailView.getUseTime());
            if (orderDetailView.getAmount() != null) {
                order.setAmount(amount);//订单总金额 后台计算
                order.setPayAmount(orderDetailView.getAmount());//支付总金额
                order.setFinalAmount(orderDetailView.getAmount());
            }
            order.setTripBegin(orderDetailView.getTripBegin());
            order.setRemarks(orderDetailView.getRemarks());
            if (orderDetailView.getSourceOrderTime() != null && orderDetailView.getSourceOrderTime().length() > 0){
                order.setPayTime(new Timestamp(sdf.parse(orderDetailView.getSourceOrderTime()).getTime())); //支付时间--下单时间
            }else {
                order.setPayTime(new Timestamp(sdf.parse("0000-00-00 00:00:00").getTime())); //默认时间
            }
            order.setStatus(orderDetailView.getStatus());//订单状态（创建）
            order.setCreateTime(new Timestamp(System.currentTimeMillis()));

            //order_subsidiary表
            OrderSubsidiary orderSubsidiary = new OrderSubsidiary();

            orderSubsidiary.setOrderCode(orderCode);
            orderSubsidiary.setCarType(orderDetailView.getCarType());
            orderSubsidiary.setCustomerEmail(orderDetailView.getCustomerEmail());
            orderSubsidiary.setCustomerName(orderDetailView.getCustomerName());
            orderSubsidiary.setFlightNum(orderDetailView.getFlightNum());
            if (orderDetailView.getFlightTime() != null && orderDetailView.getFlightTime().length() > 0){
                orderSubsidiary.setFlightTime(new Timestamp(sdf.parse(orderDetailView.getFlightTime()).getTime()));
            }else {
                orderSubsidiary.setFlightTime(new Timestamp(0L));
            }
            orderSubsidiary.setOrderSource(orderDetailView.getOrderSource());
            orderSubsidiary.setOutId(orderDetailView.getOutId());
            orderSubsidiary.setPhoneLocal(orderDetailView.getPhoneLocal());
            orderSubsidiary.setReceivePlace(orderDetailView.getReceivePlace());
            orderSubsidiary.setRemarksPlace(orderDetailView.getRemarksPlace());//地点json
            orderSubsidiary.setRemind(orderDetailView.getRemind());
            if (orderDetailView.getSourceOrderTime() != null && orderDetailView.getSourceOrderTime().length() > 0){
                orderSubsidiary.setSourceOrderTime(new Timestamp(sdf.parse(orderDetailView.getSourceOrderTime()).getTime()));
            }else {
                orderSubsidiary.setSourceOrderTime(new Timestamp(0L));
            }
            orderSubsidiary.setTask(orderDetailView.getTask());
//            orderSubsidiary.setOrderPics(ORDERPICPREFIX + orderDetailView.getOrderPics());
            orderSubsidiary.setTripDetail(orderDetailView.getTripDetail());
            orderSubsidiary.setTripPhone(orderDetailView.getTripPhone());
            orderSubsidiary.setCustomerNamePy(orderDetailView.getCustomerNamePy());
            orderSubsidiary.setCreateTime(new Timestamp(System.currentTimeMillis()));
            if (orderDetailView.getOrderSubsidiaryId() != null && orderDetailView.getOrderSubsidiaryId().length() > 0) {
                orderServiceIn.updateOSById(orderSubsidiary);
            } else {
                orderServiceIn.insertOrderSubsidiary(orderSubsidiary);
            }
            //order日志
            orderServiceIn.insertOrderOperation(order);
            //数据持久
            if (flag) {
                orderServiceIn.insertOrder(order);
            } else {
                orderServiceIn.updateOById(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderCode;
    }

    public BigDecimal handleVehicle(OrderDetailView orderDetailView, String orderCode, boolean flag) throws Exception {
        //order_detail表 车信息
        OrderProductDetail orderProductDetail = new OrderProductDetail();
        BigDecimal amount = new BigDecimal(0);

        if (orderDetailView.getOrderProductDetailId() != null && orderDetailView.getOrderProductDetailId().length() != 0)
            orderProductDetail.setId(Long.parseLong(orderDetailView.getOrderProductDetailId()));
        orderProductDetail.setOrderCode(orderCode);
        if (orderDetailView.getVehiclePackageId() != null && orderDetailView.getVehiclePackageId().length() > 0) {
            orderProductDetail.setVehiclePackageId(Long.parseLong(orderDetailView.getVehiclePackageId()));
        } else {
            orderProductDetail.setVehiclePackageId(1L);
        }
        orderProductDetail.setServiceId(0L);
        orderProductDetail.setServicePackageId(0L);
        orderProductDetail.setCount(1);
        if (orderDetailView.getSupplierId() != null && orderDetailView.getSupplierId().length() > 0)
            orderProductDetail.setSupplierId(Long.parseLong(orderDetailView.getSupplierId()));
        orderProductDetail.setPathId(0L);//没有用到
        orderProductDetail.setStatus(orderDetailView.getCarStatus());
        if (!"".equals(orderDetailView.getPrimePriceModify()) || !"".equals(orderDetailView.getSellPriceModify())) {
            orderProductDetail.setChangePriceFlag(1);
        }
        orderProductDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String useTimeStr = orderDetailView.getUseTime();
        if (useTimeStr != null && useTimeStr.length() > 0) {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            Timestamp useTime = new Timestamp(sdf.parse(useTimeStr).getTime());
            orderProductDetail.setUseTime(useTime);
            //价格查询
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(useTime);
            if (orderDetailView.getVehiclePackageId() != null && orderDetailView.getVehiclePackageId().length() > 0) {
                VehiclePriceCalendar vehiclePriceCalendar = vehicleServiceIn.getVehiclePriceCalendar(orderProductDetail.getVehiclePackageId(),
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
                if (vehiclePriceCalendar != null) {
                    if (orderDetailView.getSellPriceModify() != null && orderDetailView.getSellPriceModify().length() > 0) {
                        amount = vehiclePriceCalendar.getPrice().add(new BigDecimal(orderDetailView.getSellPriceModify()));
                        amount = amount.multiply(new BigDecimal(orderProductDetail.getCount()));
                    } else {
                        amount = vehiclePriceCalendar.getPrice().multiply(new BigDecimal(orderProductDetail.getCount()));
                    }
                }
                orderProductDetail.setPayAmount(amount);
            }
        }else {
            orderProductDetail.setUseTime(new Timestamp(0L));
        }
        //数据持久
        if (flag) {
            orderServiceIn.insertOrderProductDetail(orderProductDetail);
        } else {
            orderServiceIn.updateOrderProductDetail(orderProductDetail);
        }
        handleItem(orderDetailView.getPrimePriceModify(), orderDetailView.getSellPriceModify(), orderProductDetail);
        return amount;
    }

    public BigDecimal handleService(OrderServiceView orderServiceView, String orderCode) throws Exception {

        OrderProductDetail orderPDetail = new OrderProductDetail();
        BigDecimal amount = new BigDecimal(0);

        if (orderServiceView.getOrderProductDetailId() != null && orderServiceView.getOrderProductDetailId().length() != 0)
            orderPDetail.setId(Long.parseLong(orderServiceView.getOrderProductDetailId()));
        orderPDetail.setOrderCode(orderCode);
        if (orderServiceView.getUnit() != null && orderServiceView.getUnit().length() > 0)
            orderPDetail.setUnit(Integer.parseInt(orderServiceView.getUnit()));
        if (orderServiceView.getSupplierId() != null && orderServiceView.getSupplierId().length() > 0)
            orderPDetail.setSupplierId(Long.parseLong(orderServiceView.getSupplierId()));
        if (orderServiceView.getCount() != null && orderServiceView.getCount().length() > 0) {
            Integer count = Integer.parseInt(orderServiceView.getCount());
            orderPDetail.setCount(count);
        } else {
            orderPDetail.setCount(1);
        }
        if (orderServiceView.getServicePackageId() != null && orderServiceView.getServicePackageId().length() > 0) {
            orderPDetail.setServicePackageId(Long.parseLong(orderServiceView.getServicePackageId()));
        } else {
            orderPDetail.setServicePackageId(10000L);
        }
        if (orderServiceView.getServiceId() != null && orderServiceView.getServiceId().length() > 0) {
            orderPDetail.setServiceId(Long.parseLong(orderServiceView.getServiceId()));
        } else {
            orderPDetail.setServiceId(10000L);
        }
        orderPDetail.setRemarks(orderServiceView.getRemarks());
        if (orderServiceView.getStatus() != null && orderServiceView.getStatus().length() > 0)
            orderPDetail.setStatus(Integer.parseInt(orderServiceView.getStatus()));//订单状态  （创建）

        String useTimeStr = orderServiceView.getUseTime();
        if (useTimeStr != null && useTimeStr.length() > 0) {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            Timestamp useTime = new Timestamp(sdf.parse(useTimeStr).getTime());
            orderPDetail.setUseTime(useTime);
            //价格查询
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(useTime);
            if (orderServiceView.getServicePackageId() != null && orderServiceView.getServicePackageId().length() > 0) {
                ServicePackagePriceCalendar servicePackagePriceCalendar = sServiceServiceIn.getServicePPCalendarBy(orderPDetail.getServicePackageId(),
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
                if (servicePackagePriceCalendar != null) {
                    orderPDetail.setUnitPrice(servicePackagePriceCalendar.getPrice());
                    if (orderServiceView.getSellPriceModify() != null && orderServiceView.getSellPriceModify().length() > 0) {
                        amount = servicePackagePriceCalendar.getPrice().add(new BigDecimal(orderServiceView.getSellPriceModify()));
                        amount = amount.multiply(new BigDecimal(orderServiceView.getCount()));
                    } else {
                        amount = servicePackagePriceCalendar.getPrice().multiply(new BigDecimal(orderServiceView.getCount()));
                    }
                    orderPDetail.setPayAmount(amount);
                }
            }
        }
        if (!"".equals(orderServiceView.getPrimePriceModify()) || !"".equals(orderServiceView.getSellPriceModify())) {
            orderPDetail.setChangePriceFlag(1);
        }
        orderPDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if (orderServiceView.getOrderProductDetailId() != null && orderServiceView.getOrderProductDetailId().length() > 0) {
            orderServiceIn.updateOrderProductDetail(orderPDetail);
        } else {
            orderServiceIn.insertOrderProductDetail(orderPDetail);
        }
        handleItem(orderServiceView.getPrimePriceModify(), orderServiceView.getSellPriceModify(), orderPDetail);
        return amount;
    }

    public void handleItem(String primePriceModify, String sellPriceModify, OrderProductDetail orderProductDetail) {
        if (primePriceModify != null && primePriceModify.length() > 0) {
            OrderPrimeItem orderPrimeItem = new OrderPrimeItem();
            orderPrimeItem.setOrderCode(orderProductDetail.getOrderCode());
            orderPrimeItem.setSourceId(orderProductDetail.getId());
            orderPrimeItem.setAmount(new BigDecimal(primePriceModify));
            orderPrimeItem.setCreateTime(new Timestamp(System.currentTimeMillis()));
            orderServiceIn.insertOrUpdateOrderPrimeItem(orderPrimeItem);
        }
        if (sellPriceModify != null && sellPriceModify.length() > 0) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderCode(orderProductDetail.getOrderCode());
            orderItem.setSourceId(orderProductDetail.getId());
            orderItem.setAmount(new BigDecimal(sellPriceModify));
            orderItem.setCreateTime(new Timestamp(System.currentTimeMillis()));
            orderServiceIn.insertOrUpdateOrderItem(orderItem);
        }

    }

    @Override
    public void changeOrderStatus() {

    }
}
