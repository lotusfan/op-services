package com.opservice.service.Impl;

import com.opservice.dao.SupplierMapper;
import com.opservice.service.SupplierServiceIn;
import com.yellowcar.entities.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/11.
 */
@Service
public class SupplierServiceImpl implements SupplierServiceIn {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public Supplier getSupplier(long supplierId) {
        return supplierMapper.getSupplierById(supplierId);
    }

    @Override
    public List<Supplier> getSuppliers() {
        return supplierMapper.getSuppliers();
    }
}
