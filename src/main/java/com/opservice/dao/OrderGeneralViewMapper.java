package com.opservice.dao;


import com.yellowcar.api.op.OrderListBy;
import com.yellowcar.view.OrderGeneralView;

import java.util.List;

/**
 * Created by zhangfan on 2014/12/31.
 */
public interface OrderGeneralViewMapper {
    public List<OrderGeneralView> getBy(OrderListBy orderListBy);
}
