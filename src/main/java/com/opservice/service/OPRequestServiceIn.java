package com.opservice.service;

/**
 * Created by zhangfan on 2015/1/17.
 */
public interface OPRequestServiceIn {
    public void sendOrderTransferToPMS(String orderCode);

    public void sendOrderCancelToPMS(String orderCode);
}

