package com.opservice.dao;

import com.yellowcar.entities.ProductVehiclePackage;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/17.
 */
public interface ProductVehiclePackageMapper {

    public List<ProductVehiclePackage> getPVPByProductId(long productId);
}
