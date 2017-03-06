package com.menghao.ssh.mapper;

import com.menghao.ssh.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Date: 2017/3/6 14:57
 * @Author: Mr.m
 */
@Component
public class UserMapper {

    @Autowired
    public SessionFactory sessionFactory;

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public void save(User user){
        getCurrentSession().save(user);
    }

}
