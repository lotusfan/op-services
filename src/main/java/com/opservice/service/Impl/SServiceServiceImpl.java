package com.opservice.service.Impl;

import com.opservice.dao.ServiceClauseMapper;
import com.opservice.dao.ServicePPCalendarMapper;
import com.opservice.dao.ServicePackageMapper;
import com.opservice.service.SServiceServiceIn;
import com.yellowcar.entities.ServiceClause;
import com.yellowcar.entities.ServicePackage;
import com.yellowcar.entities.ServicePackagePriceCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/11.
 */
@Service
public class SServiceServiceImpl implements SServiceServiceIn {

    @Autowired
    private ServicePPCalendarMapper servicePPCalendarMapper;

    @Autowired
    private ServicePackageMapper servicePackageMapper;

    @Autowired
    private ServiceClauseMapper serviceClauseMapper;

    @Override
    public ServicePackagePriceCalendar getServicePPCalendarBy(long servicePackageId, int year, int month, int day) {
        ServicePackagePriceCalendar sppc = new ServicePackagePriceCalendar();
        sppc.setServicePackageId(servicePackageId);
        sppc.setYear(year);
        sppc.setMonth(month);
        sppc.setDay(day);
        return servicePPCalendarMapper.getServicePPCalendarBy(sppc).get(0);
        // return getSPPCById(servicePackageId);
    }
    @Override
    public ServicePackagePriceCalendar getSPPCById(long servicePackageId) {
        return servicePPCalendarMapper.getSPPCById(servicePackageId);
    }

    @Override
    public ServicePackage getServicePackageById(long servicePackageId) {
        return servicePackageMapper.getServicePackageById(servicePackageId);
    }

    @Override
    public List<ServiceClause> getServiceClauses() {
        return serviceClauseMapper.getServiceClauses();
    }

    @Override
    public List<ServicePackage> getSPByServiceId(long serviceId) {
        return servicePackageMapper.getSPByServiceId(serviceId);
    }
}
