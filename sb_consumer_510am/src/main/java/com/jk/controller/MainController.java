package com.jk.controller;


import com.jk.model.UserBean;
import com.jk.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;


    @RequestMapping("query")
    public List<UserBean> query(){
        List<UserBean>  list = mainService.queryList();
        return list;
    }
}
