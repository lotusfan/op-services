package com.opservice.service.Impl;

import com.opservice.Request;
import com.opservice.model.OrderCancelPMSModel;
import com.opservice.model.OrderTransferPMSModel;
import com.opservice.sendrequest.AbstractOPController;
import com.opservice.service.OPRequestServiceIn;
import com.opservice.service.OrderServiceIn;
import com.yellowcar.api.op.OrderOptionalServiceIn;
import com.yellowcar.entities.Order;
import com.yellowcar.entities.OrderProductDetail;
import com.yellowcar.entities.OrderSubsidiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

/**
 * Created by zhangfan on 2015/1/17.
 */
@Service
public class OPRequestSeviceImpl extends AbstractOPController implements OPRequestServiceIn {

    private final String URLTRANSFER = "";
    private final String URLCANCEL = "";
    @Autowired
    private OrderServiceIn orderServiceIn;
    public void sendOrderTransferToPMS(String orderCode) {

        Order order = orderServiceIn.getOrder(orderCode);
        OrderSubsidiary orderSubsidiary = orderServiceIn.getOrderSubsidiary(orderCode);
        List<OrderProductDetail> orderProductDetails = orderServiceIn.getOrderProductDetailList(orderCode);
        OrderTransferPMSModel orderTransferPMSModel = new OrderTransferPMSModel();
        Request request = new Request();
        request.setB(orderTransferPMSModel);

        try {
            executeRequest(URLTRANSFER, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void sendOrderCancelToPMS(String orderCode) {

        Request request = new Request();
        OrderCancelPMSModel orderCancelPMSModel = new OrderCancelPMSModel();

        try {
            executeRequest(URLCANCEL, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
