package com.opservice.dao;

import com.yellowcar.entities.ServiceClause;
import com.yellowcar.entities.ServicePackage;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/17.
 */
public interface ServiceClauseMapper {

    public List<ServiceClause> getServiceClauses();
}
