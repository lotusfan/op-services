package com.opservice.sendrequest;

import com.opservice.Body;

/**
 * Created by zhangfan on 2015/1/10.
 */
public class TestListModel extends Body{
    private String datatime;
    private String customerNamePy;


    public String getCustomerNamePy() {
        return customerNamePy;
    }

    public void setCustomerNamePy(String customerNamePy) {
        this.customerNamePy = customerNamePy;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }


    @Override
    public String toString() {
        return "TestListModel{" +
                "datatime='" + datatime + '\'' +
                ", customerNamePy='" + customerNamePy + '\'' +
                '}';
    }
}
