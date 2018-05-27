package com.zt.controller;

import com.zt.pojo.Catelog;
import com.zt.pojo.Goods;
import com.zt.service.CatelogService;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/catelog")
public class CatelogController {
    @Resource
    private CatelogService catelogService;

    /**
     * 添加目录
     */
    @RequestMapping(value = "/addCatelog")
    public String addCatelog(Catelog catelog){
        if(catelog != null)
            catelogService.addCatelog(catelog);
        return "/admin/catelogList";
    }
    /**
     * 修改目录
     */
    @RequestMapping(value = "/editCatelog")
    public String editCatelog(Catelog catelog){
        if(catelog != null)
            catelogService.updateByPrimaryKey(catelog);
        return "admin/catelogList";
    }
    /**
     * 删除目录,同时将该目录下的商品移到其他目录下 ？？？
     */
    @RequestMapping(value = "/deleteCatelog")
    @ResponseBody
    public String deleteCatelog(Integer id, HttpServletResponse response) throws IOException {
        if(id >= 0){
            Catelog catelog = catelogService.selectByPrimaryKey(id);
            catelogService.deleteCatelogByPrimaryKey(id);
            response.getWriter().println("1");
        }
        return null;
    }
}
