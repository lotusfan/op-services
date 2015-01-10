package com.opservice.dao;

import com.yellowcar.entities.Supplier;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/10.
 */
public interface SupplierMapper {
    public Supplier getSupplierById(long supplierId);

    public void insertSupplier(Supplier supplier);

    public List<Supplier> getSuppliers();
}
