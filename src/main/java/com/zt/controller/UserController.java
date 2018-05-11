package com.zt.controller;

import com.zt.pojo.*;
import com.zt.service.*;
import com.zt.util.DateUtil;
import com.zt.util.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> bf14b1afce697f4148e462c054988775fd4219a8
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> bf14b1afce697f4148e462c054988775fd4219a8
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private  UserService userService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private ImageService imageService;
    @Resource
    private OrdersService ordersService;

    /**
     * 用户注册
     * @param user1
     * @return
     */
    @RequestMapping(value = "/addUser")
    public String addUser(HttpServletRequest request,@ModelAttribute("user") User user1) {
        String url=request.getHeader("Referer");
        if(user1 != null) {
            User user = userService.getUserByPhone(user1.getPhone());
<<<<<<< HEAD
=======
            //检测该用户是否已经注
>>>>>>> bf14b1afce697f4148e462c054988775fd4219a8
            if(user==null) {
                //创建开始时间
                user1.setCreateAt(DateUtil.getNowDate());
                //对密码进行MD5加密
                user1.setPassword(MD5.md5(user1.getPassword()));
                user1.setGoodsNum(0);
                userService.addUser(user1);
<<<<<<< HEAD
            }
=======
            } else{
                //用户已注册
            }
        } else{

>>>>>>> bf14b1afce697f4148e462c054988775fd4219a8
        }
        return "redirect:"+url;
    }

    /**
     * 验证登录
     * @param request
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView loginValidate(HttpServletRequest request, HttpServletResponse response, User user, ModelMap modelMap) {
        String url = request.getHeader("Referer");
        if(user != null) {
            //获取数据库中的用户信息
            User cur_user = userService.getUserByPhone(user.getPhone());
            //用户存在
            if (cur_user != null) {
                //判断密码是否匹配
                String pwd = MD5.md5(user.getPassword());
                if (pwd.equals(cur_user.getPassword())) {
                    request.getSession().setAttribute("cur_user", cur_user);
                    return new ModelAndView("redirect:" + url);
                } else {
                    //密码错误
<<<<<<< HEAD
                    modelMap.put("noPassword","noPassword");
                    return new ModelAndView("redirect:/goods/homeGoods");
                }
            }
        }
        return new ModelAndView("redirect:"+url);
    }

    @RequestMapping(value = "/checkPhone/{phone}", method = RequestMethod.POST)
    @ResponseBody
    public String existUser(@PathVariable("phone") String phone, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        User cur_user = userService.getUserByPhone(phone);
        if (cur_user != null) {
            // 用户名存在
            response.getWriter().println("1");
        } else {
            // 可以使用用户名
            response.getWriter().println("0");
        }
        return null;
    }

=======
                }
            } else {
                //用户不存在，
                return new ModelAndView("false");
            }
        }else{

        }
        return new ModelAndView("redirect:"+url);
    }
>>>>>>> bf14b1afce697f4148e462c054988775fd4219a8
    /**
     * 找回密码
     * @param request
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/findPassword")
    public ModelAndView findPassword(HttpServletRequest request, User user, ModelMap modelMap) {
        String url=request.getHeader("Referer");
        if(user != null){
            User cur_user  = userService.getUserByPhone(user.getPhone());
            if(cur_user==null||!user.getUsername().equals(cur_user.getUsername())){
                url="/";
                //输入的电话号码或者用户名有误
                return new ModelAndView("redirect:"+url);
            }
            String str = MD5.md5(user.getPassword());
            cur_user.setPassword(str);//更改当前用户的密码
            userService.updateUser(cur_user);
            request.getSession().setAttribute("cur_user",cur_user);//修改session值
<<<<<<< HEAD
=======
        }else{

>>>>>>> bf14b1afce697f4148e462c054988775fd4219a8
        }
         return new ModelAndView("redirect:"+url);
    }
    /**
     * 更改用户名
     * @param request
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/changeName")
    public ModelAndView changeName(HttpServletRequest request, User user, ModelMap modelMap) {
        String url = request.getHeader("Referer");
        if (user != null) {
            //从session中获取出当前用户
            User cur_user = (User) request.getSession().getAttribute("cur_user");
            cur_user.setUsername(user.getUsername());//更改当前用户的用户名
            userService.updateUser(cur_user);//执行修改操作
            request.getSession().setAttribute("cur_user", cur_user);//修改session值
        }else {

        }
        return new ModelAndView("redirect:" + url);
    }
    /**
     * 完善或修改信息
     * @param request
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/updateInfo")
    public ModelAndView updateInfo(HttpServletRequest request, User user, ModelMap modelMap) {
        //从session中获取出当前用户
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        userService.updateUser(cur_user);//执行修改操作
        request.getSession().setAttribute("cur_user",cur_user);//修改session值
        return new ModelAndView("redirect:/user/basic");
    }


    /**
     * 用户退出
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute("cur_user",null);
        return "redirect:/goods/homeGoods";
    }


    /**
     * 个人中心
     * @return
     */
    @RequestMapping(value = "/home")
    public String home() {
        return "/user/home";
    }

    /**
     * 个人信息设置
     * @return
     */
    @RequestMapping(value = "/basic")
    public String basic() {
        return "/user/basic";
    }




    /**
     * 我的闲置
     * 查询出所有的用户商品以及商品对应的图片
     * @return  返回的model为 goodsAndImage对象,该对象中包含goods 和 images，参考相应的类
     */
    @RequestMapping(value = "/allGoods")
    public ModelAndView goods(HttpServletRequest request) {
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        Integer userId = cur_user.getId();
        List<Goods> goodsList = goodsService.getGoodsByUserId(userId);
        List<GoodsExtend> goodsAndImage = new ArrayList<GoodsExtend>();
        for (int i = 0; i < goodsList.size() ; i++) {
            //将用户信息和image信息封装到GoodsExtend类中，传给前台
            GoodsExtend goodsExtend = new GoodsExtend();
            Goods goods = goodsList.get(i);
            List<Image> images = imageService.getImagesByGoodsPrimaryKey(goods.getId());
            goodsExtend.setGoods(goods);
            goodsExtend.setImages(images);
            goodsAndImage.add(i, goodsExtend);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("goodsAndImage",goodsAndImage);
        mv.setViewName("/user/goods");
        return mv;
    }

    /**
     * 我的订单
     * 查询出所有订单
     * @return
     */
    @RequestMapping(value = "/allOrders")
    public ModelAndView orders(HttpServletRequest request) {
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        Integer userId = cur_user.getId();
        List<Orders> ordersList = ordersService.selectByUserKey(userId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("orderList",ordersList);
        mv.setViewName("/user/orders");
        return mv;
    }
}