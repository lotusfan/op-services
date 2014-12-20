package com.opservice.service;

import com.yellowcar.entities.ServiceClause;
import com.yellowcar.entities.ServicePackage;
import com.yellowcar.entities.ServicePackagePriceCalendar;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/11.
 */
public interface SServiceServiceIn {
    public ServicePackagePriceCalendar getSPPCById(long servicePackageId);

    public ServicePackagePriceCalendar getServicePPCalendarBy(long servicePackageId, int year, int month, int day);

    public ServicePackage getServicePackageById(long servicePackageId);

    public List<ServiceClause> getServiceClauses();

    public List<ServicePackage> getSPByServiceId(long serviceId);


}
