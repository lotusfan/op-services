package com.opservice.dao;

import com.yellowcar.entities.Path;

import java.util.List;
import java.util.Map;

/**
 * Created by colin on 14/11/20.
 */
public interface PathMapper {

    public Long save(Path path);

    public void update(Path path);

    public Path getById(Long id);

    public List<Path> getBy(Path path);

    public List<Path> getByPage(Map<String, Object> params);

}
