package com.example.controller;

import com.example.entity.PrdSche;
import com.example.page.PageResultBean;
import com.example.service.PrdScheService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 这里的@RestController相当于 @ResponseBody+@Controller
 * 使用@RestController 相当于使每个方法都加上了 @ResponseBody 注解
 * created by cfa  2018-11-06 下午 11:30
 **/
@RestController
@RequestMapping("prdSche")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    private final PrdScheService prdScheService;

    @Autowired
    public TestController(PrdScheService prdScheService) {
        this.prdScheService = prdScheService;
    }
    @RequestMapping("insert")
    public int insert() {
        int i=0;
        PrdSche prdSche = new PrdSche();
        prdSche.setCurVers(123654789);
        i = prdScheService.insert(prdSche);
        return i;
    }

    @RequestMapping("query")
    public PageResultBean<List<PrdSche>> selectAreaAll(@RequestParam(name="pagenum",required = false,defaultValue = "1") Integer pageNum,
                                                      @RequestParam(name="pagesize",required = false,defaultValue = "10") Integer pageSize){
            System.out.println("query------------");
        return prdScheService.selectAreaAll(pageNum,pageSize);
    }
    /**
     * 这里的@GetMapping相当于@RequestMapping(value = "/hello", method = RequestMethod.GET)
     * created by cfa  2018-11-06 下午 11:29
     **/
    @GetMapping("hello")
    public String test() throws Exception {
        logger.info("hereTest");
        return "I love you";
    }



}
