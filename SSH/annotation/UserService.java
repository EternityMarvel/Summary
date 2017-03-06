package com.menghao.ssh.service;

import com.menghao.ssh.entity.User;
import com.menghao.ssh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Description:
 * @Date: 2017/3/6 14:53
 * @Author: Mr.m
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void save(User entity) throws Exception {
        if (entity != null) {
            userMapper.save(entity);
        } else {
            throw new Exception("保存出错");
        }
    }

}
