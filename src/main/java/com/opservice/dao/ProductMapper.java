package com.opservice.dao;


import com.yellowcar.entities.Product;

import java.util.List;

/**
 * Created by colin on 14/11/20.
 */
public interface ProductMapper {

    public Long save(Product product);

    public void update(Product product);

    public Product getById(Long id);

    public List<Product> getBy(Product product);


    /**
     * 根据线路id，获取产品信息
     *
     * @param pathId
     * @return
     */
    public List<Product> getByPathId(Long pathId);

    //fan加
    public List<Product> getProducts();
}
