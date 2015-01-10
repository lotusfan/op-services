package com.opservice.service;

import com.yellowcar.entities.Supplier;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/11.
 */
public interface SupplierServiceIn {
    public Supplier getSupplier(long supplierId);

    public List<Supplier> getSuppliers();
}
