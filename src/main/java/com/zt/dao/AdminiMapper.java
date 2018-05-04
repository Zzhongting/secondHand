package com.zt.dao;

import com.zt.pojo.Admini;
import java.util.List;

public interface AdminiMapper {
    int deleteByPrimaryKey(String id);

    int insert(Admini record);

    int insertSelective(Admini record);

    Admini selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Admini record);

    int updateByPrimaryKey(Admini record);

    List<Admini> getAdminiList();
}