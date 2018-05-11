package com.zt.service;

import com.zt.pojo.Goods;

import java.io.InputStream;
import java.util.List;

public interface GoodsService {
    /**
     * 发布商品
     * @param goods
     * @param duration 允许上架时长
     */
    public int addGood(Goods goods, Integer duration);

    /**
     * 通过主键获取商品
     * @param goodsId
     * @return
     */
    public Goods getGoodsByPrimaryKey(Integer goodsId);

    /**
     * 通过主键删除商品
     * @param id
     */
    public void deleteGoodsByPrimaryKey(Integer id);

    /**
     * 更新商品信息
     * @param goods
     */
    public void updateGoodsByPrimaryKeyWithBLOBs(Goods goods);

    /**
     * 更改商品状态（下架）
     * @param goodId
     */
    public void updateByGoodsId(int goodId,String end_time);

    /**
     * 获取所有商品信息
     */
    public List<Goods> getAllGoods();

    /**
     * 获取所有未交易的商品商品信息
     */
    public List<Goods> getAllOnlineGoods();
    /**
     * 模糊查询搜索商品信息
     */
    List<Goods> searchGoods(String name, String describle);


    /**
     * 通过商品分类获取商品信息
     */
    public List<Goods> getGoodsByCatelog(Integer id);

    /**
     * 根据分类id,并进行时间排序,获取前limit个结果
     * @param catelogId
     * @param limit
     * @return
     */
    public List<Goods> getGoodsByCatelogOrderByDate(Integer catelogId, Integer limit);

    /**
     * 根据用户的id，查询出该用户的发布的所有商品
     * @param user_id
     * @return
     */
    public List<Goods> getGoodsByUserId(Integer user_id);

    /**
     * 将商品导出到表格
     */
    public InputStream getInputStream() throws Exception;

    /**
     * 查询当前页商品
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Goods> getPageGoods(int pageNum, int pageSize);
}