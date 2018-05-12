package com.zt.service.impl;

import com.github.pagehelper.PageHelper;
import com.zt.dao.AdminiMapper;
import com.zt.pojo.Admini;
import com.zt.service.AdminiService;
import com.zt.util.WriteExcel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("AdminService")
public class AdminiServiceImpl implements AdminiService {

    @Resource
    private AdminiMapper adminiMapper;

    /*添加管理员*/
    public void addAdmin(Admini admin){
        if(admin != null){
            adminiMapper.insert(admin);
        }
    }
    /*删除管理员*/
    public void deleteAdminByPrimaryKey(String id){
        if(StringUtils.isNotBlank(id)){
            adminiMapper.deleteByPrimaryKey(id);
        }
    }
    /*修改管理员信息*/
    public void updateAdminiByPrimaryKey(Admini admini){
        if(admini != null){
            adminiMapper.updateByPrimaryKey(admini);
        }
    }
    /*根据id查询管理员信息*/
    public Admini selectByPrimaryKey(String id){
        if(StringUtils.isNotBlank(id))
           return  adminiMapper.selectByPrimaryKey(id);
        return  null;
    }
    /*查询当前页的管理员信息*/
    public List<Admini> getPageAdmin(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);//分页核心代码
        return  getAllAdmini();
    }
    /*获取所有管理员信息*/
    public List<Admini> getAllAdmini(){
        return adminiMapper.getAdminiList();
    }
    /*将管理员信息导入表格*/
    public InputStream getInputStream() throws Exception{
        String[] title=new String[]{"编号","姓名","密码"};
        List<Admini> list=adminiMapper.getAdminiList();
        List<Object[]>  dataList = new ArrayList<Object[]>();
        for(int i=0;i<list.size();i++){
            Object[] obj=new Object[4];
            obj[0]=list.get(i).getId();
            obj[1]=list.get(i).getPassword();
            obj[2]=list.get(i).getName();
            dataList.add(obj);
        }
        WriteExcel ex = new WriteExcel(title, dataList);
        InputStream in;
        in = ex.export();
        return in;
    }
}
