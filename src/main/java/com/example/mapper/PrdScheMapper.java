package com.example.mapper;

import com.example.entity.PrdSche;

import java.util.List;

public interface PrdScheMapper {
    int deleteByPrimaryKey(String id);

    int insert(PrdSche record);

    int insertSelective(PrdSche record);

    PrdSche selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PrdSche record);

    int updateByPrimaryKey(PrdSche record);

    /**
     * 查询全部
     * @return
     */
    List<PrdSche> selectAreaAll();
}