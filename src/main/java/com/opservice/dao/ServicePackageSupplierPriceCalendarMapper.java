package com.opservice.dao;

import com.yellowcar.entities.ServicePackagePriceCalendar;
import com.yellowcar.entities.ServicePackageSPriceCalendar;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/22.
 */
public interface ServicePackageSupplierPriceCalendarMapper {
    public List<ServicePackageSPriceCalendar> getServicePSPCBy(ServicePackageSPriceCalendar spspc);
}
