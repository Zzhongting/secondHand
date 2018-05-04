package com.zt.service;

import com.zt.pojo.Admini;

import java.io.InputStream;
import java.util.List;

public interface AdminiService {
    /*添加管理员*/
    public void addAdmin(Admini admin);
    /*删除管理员*/
    public void deleteAdminByPrimaryKey(String id);
    /*修改管理员信息*/
    public void updateAdminiByPrimaryKey(Admini admini);
    /*根据id查询管理员信息*/
    Admini selectByPrimaryKey(String id);
    /*查询当前页的管理员信息*/
    public List<Admini> getPageAdmin(int pageNum, int pageSize);
    /*获取所有管理员信息*/
    public List<Admini> getAllAdmini();
    /*将管理员信息导入表格*/
    InputStream getInputStream() throws Exception;
}