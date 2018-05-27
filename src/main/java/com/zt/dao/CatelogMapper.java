package com.zt.dao;

import com.zt.pojo.Catelog;
import java.util.List;

public interface CatelogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Catelog record);

    int insertSelective(Catelog record);

    Catelog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Catelog record);

    int updateByPrimaryKey(Catelog record);

    int updateNameStatus(Catelog catelog);

    int updateCatelogNum(Integer id, Integer number);

    List<Catelog> getCatelogList();
}