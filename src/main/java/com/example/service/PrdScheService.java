package com.example.service;

import com.example.entity.PrdSche;
import com.example.page.PageResultBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 这里给dao层的代码拷贝过来先使用
 * created by cfa
 **/

public interface PrdScheService {
    int deleteByPrimaryKey(String id);

    int insert(PrdSche record);

    int insertSelective(PrdSche record);

    PrdSche selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PrdSche record);

    int updateByPrimaryKey(PrdSche record);

    PageResultBean<List<PrdSche>> selectAreaAll(Integer pageNum, Integer pageSize);
}
