package com.opservice.service;

import com.yellowcar.entities.Path;
import com.yellowcar.entities.Product;
import com.yellowcar.entities.ProductVehiclePackage;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/11.
 */
public interface ProductServiceIn {

    public Product getProduct(long productId);

    public ProductVehiclePackage getProductVehiclePackage(int productId, int vehiclePackageId );

    public List<Product> getProducts();

    public List<ProductVehiclePackage> getPVPByProductId(long productId);

    public Path getPathById(long pathId);

}
