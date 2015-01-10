package com.opservice.dao;


import com.yellowcar.entities.User;

/**
 * Created by shangrenpeng on 11/13/14.
 */
public interface UserMapper {
    public int registerOnDuplicateUpdateByPhoneNumber(User user);
    public int saveUser(User user);
    public int updateUserById(User user);
    public int updateUserByPhoneNumber(User user);
    public User getUserById(Long id);
    public User getUserByPhoneNumber(String phone);
}
