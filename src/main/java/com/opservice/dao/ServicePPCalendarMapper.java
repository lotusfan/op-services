package com.opservice.dao;

import com.yellowcar.entities.ServicePackagePriceCalendar;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/10.
 */
public interface ServicePPCalendarMapper {
    public ServicePackagePriceCalendar getSPPCById(long servicePackageId);

    public void insertServicePPCalendar(ServicePackagePriceCalendar servicePPCalendar);

    public List<ServicePackagePriceCalendar> getServicePPCalendarBy(ServicePackagePriceCalendar servicePackagePriceCalendar);



}
