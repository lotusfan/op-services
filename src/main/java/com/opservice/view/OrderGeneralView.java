package com.opservice.view;

/**
 * Created by zhangfan on 2014/12/10.
 */
public class OrderGeneralView {
    private String orderCode;
    private String useDay;              //    用车日
    private String fromDay;             //    距用车日时间
    private int status;                 //    状态
    private String useTime;             //    用车时间
    private String productTitle;        //    用车产品类型
    private String customerName;        //    客户姓名
    private String orderSource;         //    订单来源
    private String task;                //    待办提醒
    private String supplierName;        //    供应商名称


    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUseDay() {
        return useDay;
    }

    public void setUseDay(String useDay) {
        this.useDay = useDay;
    }

    public String getFromDay() {
        return fromDay;
    }

    public void setFromDay(String fromDay) {
        this.fromDay = fromDay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "OrderGeneralView{" +
                "orderCode='" + orderCode + '\'' +
                ", useDay='" + useDay + '\'' +
                ", fromDay='" + fromDay + '\'' +
                ", status=" + status +
                ", useTime='" + useTime + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderSource='" + orderSource + '\'' +
                ", task='" + task + '\'' +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}
