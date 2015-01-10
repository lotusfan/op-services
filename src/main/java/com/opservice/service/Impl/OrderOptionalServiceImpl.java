package com.opservice.service.Impl;

import com.opservice.service.ProductServiceIn;
import com.opservice.service.SServiceServiceIn;
import com.opservice.service.SupplierServiceIn;
import com.opservice.service.VehicleServiceIn;
import com.yellowcar.api.op.OrderOptionalServiceIn;
import com.yellowcar.entities.*;
import com.yellowcar.view.VehiclePackageView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangfan on 2014/12/20.
 */
public class OrderOptionalServiceImpl implements OrderOptionalServiceIn {

    @Autowired
    private ProductServiceIn productServiceIn;
    @Autowired
    private SServiceServiceIn sServiceServiceIn;
    @Autowired
    private VehicleServiceIn vehicleServiceIn;
    @Autowired
    private SupplierServiceIn supplierServiceIn;


    @Override
    public List<Product> getProducts() {
        return productServiceIn.getProducts();
    }

    @Override
    public List<ServiceClause> getServiceClauses() {
        return sServiceServiceIn.getServiceClauses();
    }

    @Override
    public List<ProductVehiclePackage> getProductVehiclePackages(long productId) {
        return productServiceIn.getPVPByProductId(productId);
    }

    @Override
    public List<ServicePackage> getServicePackages(long serviceId) {
        return sServiceServiceIn.getSPByServiceId(serviceId);
    }

    @Override
    public List<VehiclePackageView> getVehicles(long productId) {
        List<ProductVehiclePackage> listpvp = productServiceIn.getPVPByProductId(productId);
        List<VehiclePackageView> listvpv = new ArrayList<>();
        for (ProductVehiclePackage productVehiclePackage : listpvp) {
            VehiclePackageView vehiclePackageView = new VehiclePackageView();
            VehiclePackage vehiclePackage = vehicleServiceIn.getVehiclePackage(productVehiclePackage.getVehiclePackageId());
            Vehicle vehicle = vehicleServiceIn.getVehicle(vehiclePackage.getVehicleId());
            vehiclePackageView.setVehiclePackageId(vehiclePackage.getId()+"");
            vehiclePackageView.setVehicleTitle(vehicle.getTitle());
            listvpv.add(vehiclePackageView);
        }
        return listvpv;
    }

    @Override
    public Path getPathById(long pathId) {
        return productServiceIn.getPathById(pathId);
    }

    @Override
    public List<ServicePackage> getSPByServiceId(long serviceId) {
        return sServiceServiceIn.getSPByServiceId(serviceId);
    }

    @Override
    public List<Supplier> getSuppliers() {
        return supplierServiceIn.getSuppliers();
    }
}
