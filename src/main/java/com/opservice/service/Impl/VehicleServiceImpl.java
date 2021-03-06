package com.opservice.service.Impl;

import com.opservice.dao.VehicleMapper;
import com.opservice.dao.VehiclePackageMapper;
import com.opservice.dao.VehiclePriceCalendarMapper;
import com.opservice.dao.VehicleSPCalendarMapper;
import com.opservice.service.VehicleServiceIn;
import com.yellowcar.entities.Vehicle;
import com.yellowcar.entities.VehiclePackage;
import com.yellowcar.entities.VehiclePriceCalendar;
import com.yellowcar.entities.VehicleSupplierPriceCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/11.
 */
@Service
public class VehicleServiceImpl implements VehicleServiceIn {

    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private VehiclePackageMapper vehiclePackageMapper;
    @Autowired
    private VehiclePriceCalendarMapper vehiclePriceCalendarMapper;
    @Autowired
    private VehicleSPCalendarMapper vehicleSPCalendarMapper;

    @Override
    public Vehicle getVehicle(long vehicleId) {
        return vehicleMapper.getById(vehicleId);
    }

    @Override
    public VehiclePackage getVehiclePackage(long vehiclePckageId) {
        return vehiclePackageMapper.getById(vehiclePckageId);
    }

    @Override
    public VehiclePriceCalendar getVehiclePriceCalendar(long vehiclePackageId, int year, int month, int day) {
        VehiclePriceCalendar vehiclePriceCalendar = new VehiclePriceCalendar();
        vehiclePriceCalendar.setVehiclePackageId(vehiclePackageId);
        vehiclePriceCalendar.setYear(year);
        vehiclePriceCalendar.setMonth(month);
        vehiclePriceCalendar.setDay(day);
        List<VehiclePriceCalendar> list = vehiclePriceCalendarMapper.getBy(vehiclePriceCalendar);
        return (list != null && list.size()>0) ? list.get(0):null;
    }

    @Override
    public List<VehicleSupplierPriceCalendar> getVehicleSPCBy(VehicleSupplierPriceCalendar vspc) {
        return vehicleSPCalendarMapper.getVehilceSPCBy(vspc);
    }

    @Override
    public List<VehiclePriceCalendar> getVPCBy(VehiclePriceCalendar vpc) {
        return vehiclePriceCalendarMapper.getBy(vpc);
    }
}
