package com.opservice.service;

import com.yellowcar.entities.Vehicle;
import com.yellowcar.entities.VehiclePackage;
import com.yellowcar.entities.VehiclePriceCalendar;

/**
 * Created by zhangfan on 2014/12/11.
 */
public interface VehicleServiceIn {

    public Vehicle getVehicle(long vehicleId);

    public VehiclePackage getVehiclePackage(long vehiclePackageId);

    public VehiclePriceCalendar getVehiclePriceCalendar(long vehiclePackageId, int year, int month, int day);
}
