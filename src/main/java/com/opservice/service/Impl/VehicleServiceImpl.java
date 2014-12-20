package com.opservice.service.Impl;

import com.opservice.dao.VehicleMapper;
import com.opservice.dao.VehiclePackageMapper;
import com.opservice.dao.VehiclePriceCalendarMapper;
import com.opservice.service.VehicleServiceIn;
import com.yellowcar.entities.Vehicle;
import com.yellowcar.entities.VehiclePackage;
import com.yellowcar.entities.VehiclePriceCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return vehiclePriceCalendarMapper.getBy(vehiclePriceCalendar).get(0);
    }
}
