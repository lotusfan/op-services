package com.opservice.dao;


import com.yellowcar.entities.ServicePriceCalendar;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 获取附加服务销售报价
 *
 */
public interface ServicePriceCalendarMapper {

    /**
     * 服务的销售价格
     *
     * @param sp
     * @return
     */
    public Long save(ServicePriceCalendar sp);


    /**
     * 更新服务的销售价格
     *
     * @param sp
     */
    public void update(ServicePriceCalendar sp);


    public ServicePriceCalendar getById(Long id);


    public List<ServicePriceCalendar> getBy(ServicePriceCalendar sp);
}
