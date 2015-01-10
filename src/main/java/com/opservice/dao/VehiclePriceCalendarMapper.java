package com.opservice.dao;

import com.yellowcar.entities.VehiclePriceCalendar;

import java.util.List;

/**
 * Created by colin on 14/11/23.
 */
public interface VehiclePriceCalendarMapper {

    public Long save(VehiclePriceCalendar vp);

    public void update(VehiclePriceCalendar vp);

    public VehiclePriceCalendar getById(Long id);

    public List<VehiclePriceCalendar> getBy(VehiclePriceCalendar vp);

}
