package com.zt.controller;

import com.zt.pojo.Catelog;
import com.zt.pojo.Goods;
import com.zt.service.CatelogService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/catelog")
public class CatelogController {
    @Resource
    private CatelogService catelogService;

    /**
     * 添加目录
     */
    @RequestMapping(value = "/addCatelog")
    public void addCatelog(Catelog catelog){
        if(catelog != null)
            catelogService.addCatelog(catelog);
    }
    /**
     * 修改目录
     */
    @RequestMapping(value = "/editCatelog")
    public void editCatelog(Catelog catelog){
        if(catelog != null)
            catelogService.updateByPrimaryKey(catelog);
    }
    /**
     * 删除目录,同时将该目录下的商品移到其他目录下 ？？？
     */
    @RequestMapping(value = "/deleteCatelog")
    public void deleteCatelog(Integer id){
        if(id >= 0){
            Catelog catelog = catelogService.selectByPrimaryKey(id);
            catelogService.deleteCatelogByPrimaryKey(id);
        }
    }
}
