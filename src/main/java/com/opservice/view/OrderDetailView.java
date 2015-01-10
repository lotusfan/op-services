package com.opservice.view;


import com.alibaba.fastjson.JSONArray;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhangfan on 2014/12/9.
 */
public class OrderDetailView {

    //主表车的详细信息
    //order表中数据
    String orderCode;               //            订单ID
    BigDecimal amount;              //            订单总价
    int status;                     //            订单状态
    String primeTotalPrice;         //            成本总价
    String phone;                   //            中国手机
    String productTitle;            //            产品类型
    long productId;                 //            产品类型ID
    int childNum, personNum;        //            客人数量（儿童，成人）
    String tripBegin;               //            出发地点
    Timestamp tripDate;             //            出发时间
    String remarks;                 //            订单备注
    long userId;                     //            用户ID
    String userName;                //            用户姓名
    int dayNum;                     //            用车天数
    //order_product_detial表中数据
    String supplierName;             //            供应商           (未录入)
    long supplierId;                //            供应商ID
    Timestamp useTime;              //            用车日期
    long vehiclePackageId;          //            所需车型（ID）
    String vehiclePackageDesc;      //            车型package描述
    String vehicleTitle;            //            车辆信息title
    String moneyType;               //            成本货币种类
    String primePrice;              //            成本价格
    String primePriceModify;        //            成本价调整
    BigDecimal sellPrice;           //            销售价格
    String sellPriceModify;         //            销售价调整
    //order_subsidiary表中数据
    String orderSource;             //            订单来源
    Timestamp sourceOrderTime;      //            下单日期
    String outId;                   //            外部ID
    String task;                    //            代办任务
    String customerName;            //            客人姓名
    String customerEmail;           //            客人邮箱
    String phoneLocal;              //            泰国手机
    String carType;                 //            车型车牌
    String receivePlace;            //            接送地点
    String remind;                  //            通知提醒
    String flightNum;               //            航班号
    Timestamp flightTime;           //            航班抵达
    String remarksPlace;            //            json（到达地点，出发时间，详细地址）
    String priceId;                 //            价格ID(生成)
    int carStatus;               //            用车状态
    JSONArray rpjsonArray;      //              remarksPlace转换的


    //订单服务集合
    private List<OrderServiceView> services;


    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPrimeTotalPrice() {
        return primeTotalPrice;
    }

    public void setPrimeTotalPrice(String primeTotalPrice) {
        this.primeTotalPrice = primeTotalPrice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getChildNum() {
        return childNum;
    }

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public String getTripBegin() {
        return tripBegin;
    }

    public void setTripBegin(String tripBegin) {
        this.tripBegin = tripBegin;
    }

    public Timestamp getTripDate() {
        return tripDate;
    }

    public void setTripDate(Timestamp tripDate) {
        this.tripDate = tripDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public Timestamp getUseTime() {
        return useTime;
    }

    public void setUseTime(Timestamp useTime) {
        this.useTime = useTime;
    }

    public long getVehiclePackageId() {
        return vehiclePackageId;
    }

    public void setVehiclePackageId(long vehiclePackageId) {
        this.vehiclePackageId = vehiclePackageId;
    }

    public String getVehiclePackageDesc() {
        return vehiclePackageDesc;
    }

    public void setVehiclePackageDesc(String vehiclePackageDesc) {
        this.vehiclePackageDesc = vehiclePackageDesc;
    }

    public String getVehicleTitle() {
        return vehicleTitle;
    }

    public void setVehicleTitle(String vehicleTitle) {
        this.vehicleTitle = vehicleTitle;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public String getPrimePrice() {
        return primePrice;
    }

    public void setPrimePrice(String primePrice) {
        this.primePrice = primePrice;
    }

    public String getPrimePriceModify() {
        return primePriceModify;
    }

    public void setPrimePriceModify(String primePriceModify) {
        this.primePriceModify = primePriceModify;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getSellPriceModify() {
        return sellPriceModify;
    }

    public void setSellPriceModify(String sellPriceModify) {
        this.sellPriceModify = sellPriceModify;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public Timestamp getSourceOrderTime() {
        return sourceOrderTime;
    }

    public void setSourceOrderTime(Timestamp sourceOrderTime) {
        this.sourceOrderTime = sourceOrderTime;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }


    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getReceivePlace() {
        return receivePlace;
    }

    public void setReceivePlace(String receivePlace) {
        this.receivePlace = receivePlace;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public Timestamp getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Timestamp flightTime) {
        this.flightTime = flightTime;
    }

    public String getRemarksPlace() {
        return remarksPlace;
    }

    public void setRemarksPlace(String remarksPlace) {
        this.remarksPlace = remarksPlace;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public List<OrderServiceView> getServices() {
        return services;
    }

    public void setServices(List<OrderServiceView> services) {
        this.services = services;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPhoneLocal() {
        return phoneLocal;
    }

    public void setPhoneLocal(String phoneLocal) {
        this.phoneLocal = phoneLocal;
    }

    public String getCarType() {
        return carType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public int getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(int carStatus) {
        this.carStatus = carStatus;
    }

    public JSONArray getRpjsonArray() {
        return rpjsonArray;
    }

    public void setRpjsonArray(JSONArray rpjsonArray) {
        this.rpjsonArray = rpjsonArray;
    }

    @Override
    public String toString() {
        return "OrderDetailView{" +
                "orderCode='" + orderCode + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                ", primeTotalPrice='" + primeTotalPrice + '\'' +
                ", phone='" + phone + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", productId=" + productId +
                ", childNum=" + childNum +
                ", personNum=" + personNum +
                ", tripBegin='" + tripBegin + '\'' +
                ", tripDate=" + tripDate +
                ", remarks='" + remarks + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", dayNum=" + dayNum +
                ", supplierName='" + supplierName + '\'' +
                ", supplierId=" + supplierId +
                ", useTime=" + useTime +
                ", vehiclePackageId=" + vehiclePackageId +
                ", vehiclePackageDesc='" + vehiclePackageDesc + '\'' +
                ", vehicleTitle='" + vehicleTitle + '\'' +
                ", moneyType='" + moneyType + '\'' +
                ", primePrice='" + primePrice + '\'' +
                ", primePriceModify='" + primePriceModify + '\'' +
                ", sellPrice=" + sellPrice +
                ", sellPriceModify='" + sellPriceModify + '\'' +
                ", orderSource='" + orderSource + '\'' +
                ", sourceOrderTime=" + sourceOrderTime +
                ", outId='" + outId + '\'' +
                ", task='" + task + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", phoneLocal='" + phoneLocal + '\'' +
                ", carType='" + carType + '\'' +
                ", receivePlace='" + receivePlace + '\'' +
                ", remind='" + remind + '\'' +
                ", flightNum='" + flightNum + '\'' +
                ", flightTime=" + flightTime +
                ", remarksPlace='" + remarksPlace + '\'' +
                ", priceId='" + priceId + '\'' +
                ", carStatus='" + carStatus + '\'' +
                ", services=" + services +
                '}';
    }
}
