package com.zt.controller;

import com.zt.pojo.*;
import com.zt.service.*;
import com.zt.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CatelogService catelogService;
    @Autowired
    private UserService userService;

    /**
     * 首页显示商品，每一类商品查询6件，根据最新上架排序 key的命名为catelogGoods1、catelogGoods2....
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/homeGoods")
    public ModelAndView homeGoods(HttpServletRequest request,ModelMap modelmap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //商品种类数量
        List<Catelog> catelogs = catelogService.getAllCatelog();
        if(catelogs != null && catelogs.size() > 0){
            //每个种类显示商品数量
            int goodsSize = 6;
            Map map = new HashMap();
            for (Catelog cat:catelogs) {
                List<GoodsExtend> goodsAndImage = null;
                List<Goods> goodsList =(cat.getId()==0) ? goodsService.getAllOnlineGoods().subList(0,goodsSize):
                   goodsService.getGoodsByCatelogOrderByDate(cat.getId(), goodsSize);
                if(goodsList != null && goodsList.size() > 0){
                    goodsAndImage = new ArrayList<GoodsExtend>(goodsList.size()+1);
                    for (Goods go:goodsList) {
                        GoodsExtend goodsExtend = new GoodsExtend();
                        List<Image> images = imageService.getImagesByGoodsPrimaryKey(go.getId());
                        goodsExtend.setGoods(go);
                        goodsExtend.setImages(images);
                        goodsAndImage.add(goodsExtend);
                    }
                    map.put(cat.getId(),goodsAndImage);
                    modelAndView.addObject("map", map);
                }
            }
            if(request.getParameter("noPassword") != null){
                String reset =request.getParameter("noPassword");
                if(reset.equals("false")){
                    modelmap.put("noPassword","noPassword");
                }
            }
            modelAndView.addObject("catelog",catelogs);
            modelAndView.setViewName("goods/homeGoods");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/search")
    public ModelAndView searchGoods(@RequestParam(value = "str",required = false) String str)throws Exception {
        str=new String(str.getBytes("ISO8859-1"),"UTF-8");
        List<Goods> goodsList = goodsService.searchGoods(str,str);
        List<GoodsExtend> goodsExtendList = new ArrayList<GoodsExtend>();
        for(int i = 0;i<goodsList.size();i++) {
            GoodsExtend goodsExtend = new GoodsExtend();
            Goods goods = goodsList.get(i);
            List<Image> imageList = imageService.getImagesByGoodsPrimaryKey(goods.getId());
            goodsExtend.setGoods(goods);
            goodsExtend.setImages(imageList);
            goodsExtendList.add(i,goodsExtend);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goodsExtendList", goodsExtendList);
        modelAndView.addObject("search",str);
        modelAndView.setViewName("/goods/searchGoods");
        return modelAndView;
    }

    /**
     * 查询该类商品
     * @param id
     * 要求该参数不为空
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/catelog/{id}")
    public ModelAndView catelogGoods(HttpServletRequest request,@PathVariable("id") Integer id) throws Exception {
        List<Goods> goodsList = (id == 0) ?
             goodsService.getAllOnlineGoods()
            : goodsService.getGoodsByCatelog(id);
        Catelog catelog = catelogService.selectByPrimaryKey(id);
        List<GoodsExtend> goodsExtendList = new ArrayList<GoodsExtend>();
        for(int i = 0;i<goodsList.size();i++) {
            GoodsExtend goodsExtend = new GoodsExtend();
            Goods goods = goodsList.get(i);
            List<Image> imageList = imageService.getImagesByGoodsPrimaryKey(goods.getId());
            goodsExtend.setGoods(goods);
            goodsExtend.setImages(imageList);
            goodsExtendList.add(i,goodsExtend);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goodsExtendList", goodsExtendList);
        modelAndView.addObject("catelog", catelog);
        modelAndView.setViewName("/goods/catelogGoods");
        return modelAndView;
    }

    /**
     * 根据商品id查询该商品详细信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goodsId/{id}")
    public ModelAndView getGoodsById(@PathVariable("id") Integer id,@RequestParam(value = "str",required = false) String str) throws Exception {
        Goods goods = goodsService.getGoodsByPrimaryKey(id);
        User seller = userService.selectByPrimaryKey(goods.getUserId());
        Catelog catelog = catelogService.selectByPrimaryKey(goods.getCatelogId());
        GoodsExtend goodsExtend = new GoodsExtend();
        List<Image> imageList = imageService.getImagesByGoodsPrimaryKey(id);
        goodsExtend.setGoods(goods);
        goodsExtend.setImages(imageList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goodsExtend", goodsExtend);
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("search",str);
        modelAndView.addObject("catelog", catelog);
        modelAndView.setViewName("/goods/detailGoods");
        return modelAndView;
    }

    /**
     * 修改商品信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editGoods/{id}")
    public ModelAndView editGoods(@PathVariable("id") Integer id) throws Exception {

        Goods goods = goodsService.getGoodsByPrimaryKey(id);
        List<Image> imageList = imageService.getImagesByGoodsPrimaryKey(id);
        GoodsExtend goodsExtend = new GoodsExtend();
        goodsExtend.setGoods(goods);
        goodsExtend.setImages(imageList);
        ModelAndView modelAndView = new ModelAndView();
        // 将商品信息添加到model
        modelAndView.addObject("goodsExtend", goodsExtend);
        modelAndView.setViewName("/goods/editGoods");
        return modelAndView;
    }

    /**
     * 提交商品更改信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editGoodsSubmit")
    public String editGoodsSubmit(HttpServletRequest request,Goods goods) throws Exception {
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        String polish_time = DateUtil.getNowDay();
        goods.setPolishTime(polish_time);
        goodsService.updateGoodsByPrimaryKeyWithBLOBs(goods);
        return "redirect:/user/allGoods";
    }

    /**
     * 更改擦亮时间
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatePolishTime/{id}")
    public String updatePolishTime(ModelMap map,HttpServletRequest request,@PathVariable("id") Integer id) throws Exception {
        String url=request.getHeader("Referer");
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        Goods goods = goodsService.getGoodsByPrimaryKey(id);
        if(goods != null){
            String today = DateUtil.getNowDate();
            //如果当前时间大于擦亮时间三十天后的日期，将时间更新
            String polishTime = DateUtil.getLastTime(goods.getPolishTime(),30);
            if(today.compareTo(polishTime)>=0){
                goods.setPolishTime(today);
                goodsService.updateGoodsByPrimaryKeyWithBLOBs(goods);
                map.put("polishSucess","yes");
                return "redirect:/user/allGoods";
            } else {
                map.put("polishFailed","no");
                return "redirect:/user/allGoods";
            }
        }
        return null;
    }


    /**
     * 商品下架
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/offGoods")
    public ModelAndView offGoods() throws Exception {

        return null;
    }

    /**
     * 用户删除商品
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteGoods/{id}")
    public String deleteGoods(HttpServletRequest request,@PathVariable("id") Integer id) throws Exception {
        Goods goods = goodsService.getGoodsByPrimaryKey(id);
        //删除商品后，catlog的number-1，user表的goods_num-1，image删除,更新session的值
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        int number = cur_user.getGoodsNum();
        Integer calelog_id = goods.getCatelogId();
        Catelog catelog = catelogService.selectByPrimaryKey(calelog_id);
        catelogService.updateCatelogNum(calelog_id,catelog.getNumber()-1);
        userService.updateGoodsNum(cur_user.getId(),number-1);
        cur_user.setGoodsNum(number-1);
        request.getSession().setAttribute("cur_user",cur_user);//修改session值
        imageService.deleteImagesByGoodsPrimaryKey(id);
        goodsService.deleteGoodsByPrimaryKey(id);
        return "redirect:/user/allGoods";
    }
    /**
     * 发布商品
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publishGoods")
    public String publishGoods(HttpServletRequest request) {
        //可以校验用户是否登录
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        if(cur_user == null) {
            return "/goods/homeGoods";
        } else {
            return "/goods/pubGoods";
        }
    }
    /**
     * 提交发布的商品信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publishGoodsSubmit")
    public String publishGoodsSubmit(HttpServletRequest request, Image ima, Goods goods, MultipartFile image)
            throws Exception {
        //查询出当前用户cur_user对象，便于使用id
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        String url = request.getHeader("Referer");
        if(goods != null){
            goods.setUserId(cur_user.getId());
            goods.setStatus(1);
            int i = goodsService.addGood(goods,10);//在goods表中插入物品
            List<Goods> good = goodsService.getGoodsByUserId(cur_user.getId());
            int goodsId = good.get(0).getId();
            ima.setGoodsId(goodsId);
            imageService.insert(ima);//在image表中插入商品图片
            //发布商品后，catlog的number+1，user表的goods_num+1，更新session的值
            int number = cur_user.getGoodsNum();
            Integer calelog_id = goods.getCatelogId();
            Catelog catelog = catelogService.selectByPrimaryKey(calelog_id);
            catelogService.updateCatelogNum(calelog_id,catelog.getNumber()+1);
            userService.updateGoodsNum(cur_user.getId(),number+1);
            cur_user.setGoodsNum(number+1);
            request.getSession().setAttribute("cur_user",cur_user);//修改session值
            return "redirect:/user/allGoods";
        } else {
            return "redirecr:"+url;
        }
    }

    /**
     * 上传物品
     * @param session
     * @param myfile
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    public  Map<String,Object> uploadFile(HttpSession session,MultipartFile myfile) throws IllegalStateException, IOException{
        //原始名称
        String oldFileName = myfile.getOriginalFilename(); //获取上传文件的原名
        //存储图片的物理路径
        String file_path = session.getServletContext().getRealPath("upload");
        //上传图片
        if(myfile!=null && oldFileName!=null && oldFileName.length()>0){
            //新的图片名称
            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            //新图片
            File newFile = new File(file_path+"/"+newFileName);
            //将内存中的数据写入磁盘
            myfile.transferTo(newFile);
            //将新图片名称返回到前端
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("success", "成功啦");
            map.put("imgUrl",newFileName);
            return  map;
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("error","图片不合法");
            return map;
        }
    }
}