package com.zt.controller;

import com.zt.pojo.Admini;
import com.zt.pojo.Catelog;
import com.zt.pojo.Goods;
import com.zt.pojo.User;
import com.zt.service.AdminiService;
import com.zt.service.CatelogService;
import com.zt.service.GoodsService;
import com.zt.service.UserService;
import com.zt.util.UserGrid;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private AdminiService adminService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private UserService userService;
    @Resource
    private CatelogService catelogService;


    @RequestMapping(value = "/goodsList",method = RequestMethod.GET)
    public String goodsList() {
        return "/admin/goodsList";
    }

    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public String userList() {
        return "/admin/userList";
    }

    @RequestMapping(value = "/adminList",method = RequestMethod.GET)
    public String adminsList() {
        return "/admin/adminList";
    }

    @RequestMapping(value = "/catelogList",method = RequestMethod.GET)
    public String catelogList() {
        return "/admin/catelogList";
    }

    @RequestMapping(value = "/logins")
    public String login() { return "/admin/logins"; }

    @RequestMapping(value = "/checkID/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String existUser(@PathVariable("id") String id, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        Admini admini = adminService.selectByPrimaryKey(id);
        if (admini != null) {
            // 管理员存在
            response.getWriter().println("1");
        } else {
            //管理员不存在
            response.getWriter().println("0");
        }
        return null;
    }
    /**
     * 管理员登录
     * @param request
     * @param admini
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView loginValidate(HttpServletRequest request, HttpServletResponse response, Admini admini, ModelMap modelMap) {
        Admini admin = adminService.selectByPrimaryKey(admini.getId());
        String url=request.getHeader("Referer");
        if(admin != null) {
           if(admini.getName().equals(admin.getName())&&admini.getPassword().equals(admin.getPassword())){
               request.getSession().setAttribute("cur_admin",admin);
               return new ModelAndView("redirect:/admin/userList");
           } else{
               modelMap.put("Msg","密码错误");
           }
        }
        return new ModelAndView("/admin/logins");
    }
    /*获取用户信息*/
    @RequestMapping(value="/getUserInfo",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public User getUserById(@RequestParam("id") Integer id){
        User user = userService.selectByPrimaryKey(id);
        return user;
    }

    /*获取目录信息*/
    @RequestMapping(value="/getCatelogInfo",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Catelog getCatelogInfo(@RequestParam("id") Integer id){
        Catelog catelog = catelogService.selectByPrimaryKey(id);
        return catelog;
    }

    /*获取商品信息*/
    @RequestMapping(value="/getGoodInfo",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Goods getGoodById(@RequestParam("id") Integer id){
        Goods good = goodsService.getGoodsByPrimaryKey(id);
        return good;
    }

    /*获取管理员信息*/
    @RequestMapping(value="/getAdminInfo",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Admini getAdminById(@RequestParam("id") String id){
        Admini admin  = adminService.selectByPrimaryKey(id);
        return admin;
    }

    /*获取所有管理员*/
    @RequestMapping(value = "/adminis",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public UserGrid getdminisList(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount) {
        int total = adminService.getAllAdmini().size();
        List<Admini>  list = adminService.getPageAdmin(current,rowCount);
        UserGrid userGrid = new UserGrid();
        userGrid.setCurrent(current);
        userGrid.setRowCount(rowCount);
        userGrid.setRows(list);
        userGrid.setTotal(total);
        return userGrid;
    }

    /*获取所有商品*/
    @RequestMapping(value = "/goods",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public UserGrid getGoodsList(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount) {
        int total = goodsService.getAllGoods().size();
        List<Goods>  list = goodsService.getPageGoods(current,rowCount);
        UserGrid userGrid = new UserGrid();
        userGrid.setCurrent(current);
        userGrid.setRowCount(rowCount);
        userGrid.setRows(list);
        userGrid.setTotal(total);
        return userGrid;
    }

    /*获取所有用户*/
    @RequestMapping(value = "/users",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public UserGrid getUserList(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount) {
        int total = userService.getAllUser().size();
        List<User>  list = userService.getPageUser(current,rowCount);
        UserGrid userGrid = new UserGrid();
        userGrid.setCurrent(current);
        userGrid.setRowCount(rowCount);
        userGrid.setRows(list);
        userGrid.setTotal(total);
        return userGrid;
    }

    /*获取所有目录*/
    @RequestMapping(value = "/catelog",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public UserGrid getCatelogList(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount) {
        int total = catelogService.getAllCatelog().size();
        List<Catelog>  list = catelogService.getPageCatelog(current,rowCount);
        UserGrid userGrid = new UserGrid();
        userGrid.setCurrent(current);
        userGrid.setRowCount(rowCount);
        userGrid.setRows(list);
        userGrid.setTotal(total);
        return userGrid;
    }

    //将商品信息导出到Excel
    @RequestMapping("/exportGoods")
    public void exportGoods(HttpServletResponse response) throws Exception{
        InputStream is=goodsService.getInputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("contentDisposition", "attachment;filename=AllGoods.xls");
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(is,output);
    }
    //将用户信息导出到Excel
    @RequestMapping("/exportUser")
    public void export(HttpServletResponse response) throws Exception{
        InputStream is=userService.getInputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("contentDisposition", "attachment;filename=AllUsers.xls");
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(is,output);
    }

    //将管理员信息导出到Excel
    @RequestMapping("/exportAdmin")
    public void exportAdmin(HttpServletResponse response) throws Exception{
        InputStream is=adminService.getInputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("contentDisposition", "attachment;filename=AllUsers.xls");
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(is,output);
    }
    /**
     * 修改用户活跃度
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/updateInfoPower")
    public ModelAndView updateInfoPower(User user, ModelMap modelMap) {
        userService.updateUser1(user);//执行修改操作
        return new ModelAndView("redirect:/admin/userList");
    }

    /**
     * 修改商品信息状态
     * @param good
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/updateInfoStatus")
    public ModelAndView updateInfoStatus(Goods good, ModelMap modelMap) {
        goodsService.updateStatus(good);//执行修改操作
        return new ModelAndView("redirect:/admin/goodsList");
    }

    /**
     * 修改管理员
     * @param admini
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/updateAdmin")
    public ModelAndView updateAdmin(Admini admini, ModelMap modelMap) {
        adminService.updateAdminiByPrimaryKey(admini);
        return new ModelAndView("redirect:/admin/adminList");
    }


    /**
     * 添加管理员
     */
    @RequestMapping(value = "/becomeManage")
    public String Manager(HttpServletRequest request, Admini admin){
        if(admin != null){
            adminService.addAdmin(admin);
        }
        return "redirect:/admin/adminList";
    }


    /**
     * 删除用户，并把其发布的未交易的商品删除
     */
    @RequestMapping(value = "/blackList")
    @ResponseBody
    public String blackList(@RequestParam("id")String id,HttpServletResponse response) throws IOException {
        if(StringUtils.isNotBlank(id)) {
            List<Goods> allGoods = goodsService.getGoodsByUserId(Integer.parseInt(id));
            for (Goods goods : allGoods) {
                goodsService.deleteGoodsByPrimaryKey(goods.getId());
            }
            userService.deleteUserByPrimaryKey(Integer.parseInt(id));
            response.getWriter().println("1");
        }
        return null;
    }

    /**
     * 删除商品
     */
    @RequestMapping(value = "/deleGood")
    @ResponseBody
    public String deleGood(@RequestParam("id")String id,HttpServletResponse response) throws IOException {
        if(StringUtils.isNotBlank(id)) {

            goodsService.deleteGoodsByPrimaryKey(Integer.parseInt(id));
            response.getWriter().println("1");
        }
        return null;
    }

    /**
     * 删除管理员
     */
    @RequestMapping(value = "/deleteAdmin")
    @ResponseBody
    public String deleteAdmin(@RequestParam("id")String id,HttpServletResponse response) throws IOException {
        if(StringUtils.isNotBlank(id)) {
            adminService.deleteAdminByPrimaryKey(id);
            response.getWriter().println("1");
        }
        return null;
    }
}