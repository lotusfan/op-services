package com.opservice.service.Impl;

import com.opservice.dao.ServiceClauseMapper;
import com.opservice.dao.ServicePackagePriceCalendarMapper;
import com.opservice.dao.ServicePackageSupplierPriceCalendarMapper;
import com.opservice.dao.ServicePackageMapper;
import com.opservice.service.SServiceServiceIn;
import com.yellowcar.entities.ServiceClause;
import com.yellowcar.entities.ServicePackage;
import com.yellowcar.entities.ServicePackagePriceCalendar;
import com.yellowcar.entities.ServicePackageSPriceCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/11.
 */
@Service
public class SServiceServiceImpl implements SServiceServiceIn {

    @Autowired
    private ServicePackagePriceCalendarMapper servicePackagePriceCalendarMapper;

    @Autowired
    private ServicePackageMapper servicePackageMapper;

    @Autowired
    private ServiceClauseMapper serviceClauseMapper;

    @Autowired
    private ServicePackageSupplierPriceCalendarMapper servicePackageSupplierPriceCalendarMapper;

    @Override
    public ServicePackagePriceCalendar getServicePPCalendarBy(long servicePackageId, int year, int month, int day) {
        ServicePackagePriceCalendar sppc = new ServicePackagePriceCalendar();
        sppc.setServicePackageId(servicePackageId);
        sppc.setYear(year);
        sppc.setMonth(month);
        sppc.setDay(day);
        List<ServicePackagePriceCalendar> list = servicePackagePriceCalendarMapper.getSPPCBy(sppc);
        return (list !=null && list.size()==1)?list.get(0):null;
        // return getSPPCById(servicePackageId);
    }
    @Override
    public ServicePackagePriceCalendar getSPPCById(long servicePackageId) {
        return servicePackagePriceCalendarMapper.getSPPCById(servicePackageId);
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

    @Override
    public List<ServicePackageSPriceCalendar> getSPSPCBy(ServicePackageSPriceCalendar spspc) {
        return servicePackageSupplierPriceCalendarMapper.getServicePSPCBy(spspc);
    }

    @Override
    public List<ServicePackagePriceCalendar> getSPPCBy(ServicePackagePriceCalendar sppc) {
        return servicePackagePriceCalendarMapper.getSPPCBy(sppc);
    }
}
