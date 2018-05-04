package com.zt.service;

import com.zt.pojo.Catelog;

import java.util.List;

public interface CatelogService {
    /*增加目录*/
    public void addCatelog(Catelog catelog);
    /*删除目录*/
    public void deleteCatelogByPrimaryKey(Integer id);
    /*根据id修改该目录下的商品数量*/
    int updateCatelogNum(Integer id, Integer number);
    /*修改目录信息*/
    int updateByPrimaryKey(Catelog record);
    /*获取所有的目录*/
    public List<Catelog> getAllCatelog();
    /*根据id查询该目录下的信息*/
    Catelog selectByPrimaryKey(Integer id);
    /*分页查询目录*/
    List<Catelog> getPageCatelog(int current, int rowCount);
}
