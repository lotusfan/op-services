package com.opservice.service;

import com.opservice.dao.*;
import com.yellowcar.entities.ServicePackagePriceCalendar;
import com.yellowcar.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangfan on 2014/12/11.
 */
public class OrderServiceParent {
    @Autowired
    protected OrderServiceIn orderServiceIn;
    @Autowired
    protected ProductServiceIn productServiceIn;
    @Autowired
    protected SServiceServiceIn sServiceServiceIn;
    @Autowired
    protected SupplierServiceIn supplierServiceIn;
    @Autowired
    protected VehicleServiceIn vehicleServiceIn;
    @Autowired
    protected UserServiceIn userServiceIn;
}
