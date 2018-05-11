package com.zt.service;

import com.zt.pojo.User;

import java.io.InputStream;
import java.util.List;


public interface UserService {
    /*注册用户*/
    public void addUser(User user);
    /*根据id删除用户*/
    public void deleteUserByPrimaryKey(Integer id);
    /*修改用户信息*/
    public void updateUser(User user);
    /*更新用户商品数量*/
    public int updateGoodsNum(Integer id, Integer goodsNum);
    /*通过id获取用户信息*/
    public User selectByPrimaryKey(Integer id);
    /*通过手机号获取用户信息*/
    public User getUserByPhone(String phone);
    /*分页查询用户*/
    public List<User> getPageUser(int pageNum, int pageSize);
    /*获取所有用户*/
    public List<User> getAllUser();
    public InputStream getInputStream() throws Exception;
}