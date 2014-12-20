package com.opservice.dao;

import com.yellowcar.entities.VehiclePackage;
import com.yellowcar.view.VehiclePackageListView;

import java.util.List;

/**
 * Created by colin on 14/11/20.
 */
public interface VehiclePackageMapper {

    public Long save(VehiclePackage vehiclePackage);


    public void update(VehiclePackage vehiclePackage);


    public List<VehiclePackage> getBy(VehiclePackage vehiclePackage);


    public VehiclePackage getById(Long id);


    public List<VehiclePackageListView> getListViewByProductId(Long productId);


    /**
     * 根据产品的id，获取该产品可以使用的用车套装视图列表
     *
     */
    public List<VehiclePackage> getByProductId(Long productId);
}
