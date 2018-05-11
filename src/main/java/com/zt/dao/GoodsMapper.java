package com.zt.dao;

import com.zt.pojo.Goods;
import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Goods record);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    List<Goods> selectAllGoods();

    List<Goods> selectOnlineGoods();

    List<Goods> searchGoods(String name,String describe);

    int updateByGoodsId(String end_time,int goodsId);

    List<Goods> getGoodsByUserId(Integer user_id);

    List<Goods> selectByCatelogOrderByDate(Integer catelogId, Integer limit);

    List<Goods> selectByCatelog(Integer id);
}