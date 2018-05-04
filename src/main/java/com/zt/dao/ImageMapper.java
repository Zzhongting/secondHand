package com.zt.dao;

import com.zt.pojo.Image;

import java.util.List;

public interface ImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    List<Image> selectByGoodsPrimaryKey(Integer goodsId);

    int deleteImagesByGoodsPrimaryKey(Integer goodsId);
}