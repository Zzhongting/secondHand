package com.zt.service.impl;

import com.zt.dao.OrdersMapper;
import com.zt.pojo.Orders;
import com.zt.service.OrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrdersService{
    @Resource
    private OrdersMapper ordersMapper;

    public int addOrder(Orders orders){
        if(orders != null)
            return ordersMapper.insert(orders);
        return 0;
    }
    public Orders selectByPrimaryKey(Integer id){
        if(id >= 0)
            return ordersMapper.selectByPrimaryKey(id);
        return null;
    }
    public int deleteByPrimaryKey(Integer id){
        if(id >= 0)
            return ordersMapper.deleteByPrimaryKey(id);
        return 0;
    }
    public List<Orders> selectByUserKey(Integer id){
        if(id >= 0)
            return ordersMapper.selectByUserKey(id);
        return null;
    }
}
