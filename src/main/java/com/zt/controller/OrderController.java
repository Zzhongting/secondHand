package com.zt.controller;

import com.zt.pojo.User;
import com.zt.pojo.Orders;
import com.zt.service.GoodsService;
import com.zt.service.OrdersService;
import com.zt.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Resource
    private OrdersService orderService;
    @Resource
    private GoodsService goodService;
    /**
     * 添加订单
     * @param order
     */
    @RequestMapping(value = "/subOrder")
    public String addOrder(HttpServletRequest request, @ModelAttribute("order") Orders order) {
        if(order!=null) {
            User cur_user = (User) request.getSession().getAttribute("cur_user");
            order.setUserid(cur_user.getId());
            order.setOrderdate(DateUtil.getNowDate());
            int i = orderService.addOrder(order);
            //数据插入成功,商品下架,同时设置下架时间
            if(i == 1){
               goodService.updateByGoodsId(order.getGoodsId(),DateUtil.getNowDate());
            }
        }
        return "redirect:/user/allOrders";
    }

}
