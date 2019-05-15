package com.jk.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.UserBean;
import com.jk.service.MainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MainService  mainService;
    //@Reference(version = "1.0.0")
    @RequestMapping("query")
    public List<UserBean> queryList(){
        List<UserBean> list = mainService.queryList();
        return list;
    }

}
