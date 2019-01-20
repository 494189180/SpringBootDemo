package com.example.service.Impl;

import com.example.controller.TestController;
import com.example.mapper.PrdScheMapper;
import com.example.entity.PrdSche;
import com.example.page.PageResultBean;
import com.example.service.PrdScheService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 这里的@Service注解相当于自动注册到Spring的Bean
 * 相当于原来的Application.xml里的 <bean id="areaServiceImpl" class="com.example.service.impl.AreaServiceImpl"/>
 **/
@Service
public class PrdScheImpl implements PrdScheService,Serializable {
    private static final Logger logger = LoggerFactory.getLogger(PrdScheImpl.class);
    
    private final PrdScheMapper prdScheMapper;
    @Autowired
    public PrdScheImpl(PrdScheMapper prdScheMapper) {
        this.prdScheMapper = prdScheMapper;
    }


    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }


    //测试异常
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(PrdSche record) {
        int i = 0;
        try {
            i = prdScheMapper.insert(record);
        } catch (Exception e) {
            logger.error("异常信息:"+e.getMessage());
            //throw new RuntimeException();
        }
        return i;
    }

    @Override
    public int insertSelective(PrdSche record) {
        return 0;
    }

    @Override
    public PrdSche selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(PrdSche record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PrdSche record) {
        return 0;
    }

    @Override
    public PageResultBean<List<PrdSche>> selectAreaAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PrdSche> prdScheList = prdScheMapper.selectAreaAll();
        return new PageResultBean<List<PrdSche>>(new PageInfo(prdScheList));
    }
}
