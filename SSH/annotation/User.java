package com.menghao.ssh.entity;

import javax.persistence.*;

/**
 * @Description:User实体类
 * @Date: 2017/3/6 14:45
 * @Author: Mr.m
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = “t_username”,nullable=false,length=32) 
    private String username;
    @Column(name = “t_password”,nullable=false,length=32) 
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
