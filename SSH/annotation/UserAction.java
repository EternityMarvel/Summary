package com.menghao.ssh.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.menghao.ssh.entity.User;
import com.menghao.ssh.service.UserService;


/**
 * @Description:
 * @Date: 2017/3/6 14:50
 * @Author: Mr.m
 */
@Results( { @Result(name="success",location="/success.jsp"),
        @Result(name="failure",location="/failure.jsp") }) 
public class UserAction extends ActionSupport {
    @Resource
    private UserService userService;
    private User user;
    
    @Action(value="save")
    public String addUser() {
        try {
            userService.save(user);        
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        return "success";
        
    }    

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    
}
