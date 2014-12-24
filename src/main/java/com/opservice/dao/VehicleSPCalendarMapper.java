package com.opservice.dao;

import com.yellowcar.entities.VehicleSupplierPriceCalendar;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/22.
 */
public interface VehicleSPCalendarMapper {
    public List<VehicleSupplierPriceCalendar> getVehilceSPCBy(VehicleSupplierPriceCalendar vspc);
}
