package com.opservice.service.Impl;

import com.opservice.dao.UserMapper;
import com.opservice.service.UserServiceIn;
import com.yellowcar.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangfan on 2015/1/5.
 */
@Service
public class UserServiceImp implements UserServiceIn{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhoneNumber(phone);
    }
}
