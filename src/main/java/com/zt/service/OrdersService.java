package com.zt.service;

import org.springframework.stereotype.Service;

import java.util.List;
import com.zt.pojo.Orders;

@Service
public interface OrdersService {
    /*新增订单*/
    public int addOrder(Orders orders);
    /*根据id查找订单*/
    Orders selectByPrimaryKey(Integer id);
    /*根据id删除订单*/
    int deleteByPrimaryKey(Integer id);
    /*查询用户id查询该用户所有订单*/
    List<Orders> selectByUserKey(Integer id);
}

