package com.zt.service.impl;

import com.github.pagehelper.PageHelper;
import com.zt.dao.UserMapper;
import com.zt.pojo.User;
import com.zt.service.UserService;
import com.zt.util.WriteExcel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /*注册用户*/
    public void addUser(User user){
        if(user != null){
            userMapper.insert(user);
        }
    }
    /*根据id删除用户*/
    public void deleteUserByPrimaryKey(Integer id){
        if(id >=1 ){
            userMapper.deleteByPrimaryKey(id);
        }
    }
    /*修改用户信息*/
    public void updateUser(User user){
        if(user != null)
            userMapper.updateByPrimaryKey(user);
    }
    /*更新用户商品数量*/
    public int updateGoodsNum(Integer id, Integer goodsNum){
        return userMapper.updateGoodsNum(id,goodsNum);
    }
    /*通过id获取用户信息*/
    public User selectByPrimaryKey(Integer id){
        if(id >= 1){
            return userMapper.selectByPrimaryKey(id);
        }
        return null;
    }
    /*通过手机号获取用户信息*/
    public User getUserByPhone(String phone){
        if(StringUtils.isNotBlank(phone)){
            return  userMapper.getUserByPhone(phone);
        }
        return null;
    }
    /*分页查询用户,获取当前页用户*/
    public List<User> getPageUser(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return getAllUser();
    }
    /*获取所有用户*/
    public List<User> getAllUser(){
        return userMapper.getUserList();
    }

    /*用户信息导入表格*/
    public InputStream getInputStream() throws Exception {
        String[] title=new String[]{"序号","手机号","姓名","QQ","开通时间","商品数量","用户权限","地址"};
        List<User> list=userMapper.getUserList();
        List<Object[]>  dataList = new ArrayList<Object[]>();
        for(int i=0;i<list.size();i++){
            Object[] obj=new Object[8];
            obj[0]=list.get(i).getId();
            obj[1]=list.get(i).getPhone();
            obj[2]=list.get(i).getUsername();
            obj[3]=list.get(i).getQq();
            obj[4]=list.get(i).getCreateAt();
            obj[5]=list.get(i).getGoodsNum();
            obj[6]=list.get(i).getPower();
            obj[7]=list.get(i).getAddress();
            dataList.add(obj);
        }
        WriteExcel ex = new WriteExcel(title, dataList);
        InputStream in;
        in = ex.export();
        return in;
    }

    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {}
        return session;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

}