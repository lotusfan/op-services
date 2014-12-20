package com.opservice.dao;

import com.yellowcar.entities.ServicePackage;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/10.
 */
public interface ServicePackageMapper {
    public ServicePackage getServicePackageById(long servicePackageId);

    public void insertServicePackage(ServicePackage servicePackage);

    public List<ServicePackage> getSPByServiceId(long serviceId);
}
