package com.opservice.view;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zhangfan on 2014/12/9.
 */
public class OrderServiceView implements Serializable{

    private long orderProductDetailId;
    private long supplierId;                //    供应商ID
    private String supplierName;            //    供应商
    private int status;                     //    状态
    private long serviceId;                 //    服务类型ID
    private String serviceTitle;            //    服务类型
    private long servicePackageId;          //    套餐类型ID
    private String getServicePackageDesc;   //    套餐类型
    private Timestamp useTime;              //    参观时间
    private String useDate;                //   参观日期
    private String useHms;                  //  参观时分秒
    private int unit;                       //    价格单位
    private int count;                      //    购买人数
    private String priceId;                 //    价格ID
    private String moneyType;               //    成本价货币种类
    private String primePrice;              //    成本价格
    //    成本价调整
    private String sellPrice;               //    销售价格
    //    销售价调整
    private String remarks;                 //    订单备注

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public long getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(long servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    public Timestamp getUseTime() {
        return useTime;
    }

    public void setUseTime(Timestamp useTime) {
        this.useTime = useTime;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public String getUseHms() {
        return useHms;
    }

    public void setUseHms(String useHms) {
        this.useHms = useHms;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
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

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getOrderProductDetailId() {
        return orderProductDetailId;
    }

    public void setOrderProductDetailId(long orderProductDetailId) {
        this.orderProductDetailId = orderProductDetailId;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getGetServicePackageDesc() {
        return getServicePackageDesc;
    }

    public void setGetServicePackageDesc(String getServicePackageDesc) {
        this.getServicePackageDesc = getServicePackageDesc;
    }

    @Override
    public String toString() {
        return "OrderServiceView{" +
                "orderProductDetailId=" + orderProductDetailId +
                ", supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", status=" + status +
                ", serviceId=" + serviceId +
                ", servicePackageId=" + servicePackageId +
                ", useTime=" + useTime +
                ", unit=" + unit +
                ", count=" + count +
                ", priceId='" + priceId + '\'' +
                ", moneyType='" + moneyType + '\'' +
                ", primePrice=" + primePrice +
                ", sellPrice=" + sellPrice +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
