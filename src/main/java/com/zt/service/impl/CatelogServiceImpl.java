package com.zt.service.impl;

import com.github.pagehelper.PageHelper;
import com.zt.dao.CatelogMapper;
import com.zt.pojo.Catelog;
import com.zt.service.CatelogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("catelogService")
public class CatelogServiceImpl implements CatelogService {

    @Resource
    private CatelogMapper catelogMapper;

    /*增加目录*/
    public void addCatelog(Catelog catelog){
        if(catelog != null){
            catelogMapper.insertSelective(catelog);
        }
    }
    /*删除目录*/
    public void deleteCatelogByPrimaryKey(Integer id){
        if(id >= 1){
            catelogMapper.deleteByPrimaryKey(id);
        }
    }
    /*修改目录信息*/
    public int updateByPrimaryKey(Catelog record){
        if(record != null){
            return catelogMapper.updateByPrimaryKey(record);
        }
        return 0;
    }
    /*根据id修改该目录下的商品数量*/
    public int updateCatelogNum(Integer id, Integer number){
        if(id >= 1 && number >= 0){
            return catelogMapper.updateCatelogNum(id,number);
        }
        return 0;
    }

    /*获取所有的目录*/
    public List<Catelog> getAllCatelog(){
        return catelogMapper.getCatelogList();
    }
    /*根据id查询该目录下的信息*/
    public Catelog selectByPrimaryKey(Integer id){
        if(id >= 0){
            return catelogMapper.selectByPrimaryKey(id);
        }
        return null;
    }
    /*分页查询目录*/
    public List<Catelog> getPageCatelog(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);//分页核心代码
        return getAllCatelog();
    }
}
