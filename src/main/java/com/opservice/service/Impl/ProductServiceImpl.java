package com.opservice.service.Impl;

import com.opservice.dao.PathMapper;
import com.opservice.dao.ProductMapper;
import com.opservice.dao.ProductVehiclePackageMapper;
import com.opservice.service.ProductServiceIn;
import com.yellowcar.entities.Path;
import com.yellowcar.entities.Product;
import com.yellowcar.entities.ProductVehiclePackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/11.
 */
@Service
public class ProductServiceImpl implements ProductServiceIn {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductVehiclePackageMapper productVehiclePackageMapper;

    @Autowired
    private PathMapper pathMapper;

    @Override
    public ProductVehiclePackage getProductVehiclePackage(int productId, int vehiclePackageId) {
        return null;
    }

    @Override
    public Product getProduct(long productId) {
        return productMapper.getById(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productMapper.getProducts();
    }

    @Override
    public List<ProductVehiclePackage> getPVPByProductId(long productId) {
        return productVehiclePackageMapper.getPVPByProductId(productId);
    }

    @Override
    public Path getPathById(long pathId) {
        return pathMapper.getById(pathId);
    }
}
