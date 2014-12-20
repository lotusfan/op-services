package com.opservice.dao;

import com.yellowcar.entities.Vehicle;

import java.util.List;

/**
 * Created by colin on 14/11/20.
 */
public interface VehicleMapper {

    /**
     * 保存车型信息
     *
     * @param vehicle
     * @return
     */
    public Long save(Vehicle vehicle);

    public void update(Vehicle vehicle);

    public Vehicle getById(Long id);

    public List<Vehicle> getBy(Vehicle vehicle);
}
