package com.zt.service.impl;

import com.github.pagehelper.PageHelper;
import com.zt.dao.CatelogMapper;
import com.zt.dao.GoodsMapper;
import com.zt.dao.UserMapper;
import com.zt.pojo.Catelog;
import com.zt.pojo.Goods;
import com.zt.pojo.User;
import com.zt.service.GoodsService;
import com.zt.util.DateUtil;
import com.zt.util.WriteExcel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private CatelogMapper catelogMapper;
    @Resource
    private UserMapper userMapper;

    public int addGood(Goods goods , Integer duration) {
        if(goods != null && duration >= 0){
            String startTime = DateUtil.getNowDay();
            String polishTime = startTime;
            //添加上架时间,擦亮时间
            goods.setPolishTime(polishTime);
            goods.setStartTime(startTime);
            goods.setStatus(1);
            return goodsMapper.insert(goods);
        }
        return 0;
    }
    /*通过主键获取商品*/
    public Goods getGoodsByPrimaryKey(Integer goodsId) {
        if(goodsId >= 1)
            return goodsMapper.selectByPrimaryKey(goodsId);
        return null;
    }
    /*通过主键删除商品*/
    public void deleteGoodsByPrimaryKey(Integer id) {
        if(id >= 1)
            goodsMapper.deleteByPrimaryKey(id);
    }

    /*获取所有商品信息*/
    public List<Goods> getAllGoods() {
        return goodsMapper.selectAllGoods();
    }

    /**
     * 获取所有未交易的商品商品信息
     */
    public List<Goods> getAllOnlineGoods(){
        return goodsMapper.selectOnlineGoods();
    }

    /*模糊查询搜索商品信息*/
    public List<Goods> searchGoods(String name, String describle) {
        if(StringUtils.isNotBlank(name)||StringUtils.isNotBlank(describle)){
            return goodsMapper.searchGoods(name,describle);
        }
        return  null;
    }

    /*更新商品信息*/
    public void updateGoodsByPrimaryKeyWithBLOBs(Goods goods) {
        if(goods != null)
            goodsMapper.updateByPrimaryKeyWithBLOBs(goods);
    }
    /*商品下架*/
    public void updateByGoodsId(int goodId,String end_time){
        if(goodId >= 0)
            goodsMapper.updateByGoodsId(end_time,goodId);
    }

    /*管理员管理商品*/
    public void updateStatus(Goods goods){
        if(goods!= null)
            goodsMapper.updateStatus(goods);
    }

    /*根据用户的id，查询出该用户的发布的所有商品*/
    public List<Goods> getGoodsByUserId(Integer user_id) {
        if(user_id >= 0)
            return goodsMapper.getGoodsByUserId(user_id);
        return null;
    }
    /*分页查询*/
    public List<Goods> getPageGoods(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页核心代码
        return goodsMapper.selectOnlineGoods();
    }
    public List<Goods> getGoodsByCatelogOrderByDate(Integer catelogId, Integer limit) {
        return goodsMapper.selectByCatelogOrderByDate(catelogId , limit);
    }

    public List<Goods> getGoodsByCatelog(Integer id) {
       return goodsMapper.selectByCatelog(id);
    }

    /*商品信息导入表格*/
    public InputStream getInputStream() throws Exception {
        String[] title=new String[]{"序号","商品名","类别","出售价格","上架时间","下架时间","商品状态","用户名"};
        List<Goods> list = getAllGoods();
        List<Object[]>  dataList = new ArrayList<Object[]>();
        for(int i=0;i<list.size();i++){
            Object[] obj=new Object[8];
            obj[0]=list.get(i).getId();
            obj[1]=list.get(i).getName();
            Catelog catelog = catelogMapper.selectByPrimaryKey(list.get(i).getCatelogId());
            obj[2] = catelog.getName();
            obj[3]=list.get(i).getPrice();
            obj[4]=list.get(i).getStartTime();
            obj[5]=list.get(i).getEndTime();
            if(list.get(i).getStatus()==1){
                obj[6]="正常";
            }else{
                obj[6]="已交易";
            }
            User user = userMapper.selectByPrimaryKey(list.get(i).getUserId());
            obj[7] = user.getUsername();
            dataList.add(obj);
        }
        WriteExcel ex = new WriteExcel(title, dataList);
        InputStream in;
        in = ex.export();
        return in;
    }
}