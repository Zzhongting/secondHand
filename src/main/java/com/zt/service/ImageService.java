package com.zt.service;

import com.zt.pojo.Image;

import java.util.List;

public interface ImageService {
    int insert(Image record);

    /* 通过商品id获取该商品的图片 */
    public List<Image> getImagesByGoodsPrimaryKey(Integer goodsId);

    /*  通过商品Id删除商品 */
    int deleteImagesByGoodsPrimaryKey(Integer goodsId);
}