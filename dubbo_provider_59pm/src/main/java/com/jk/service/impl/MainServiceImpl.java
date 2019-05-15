package com.jk.service.impl;

import com.jk.mapper.MainMapper;
import com.jk.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private MainMapper mainMapper;


    @Override
    public String heloName(String name, String age) {
        return "姓名"+name+",年龄"+age;
    }

    @Override
    public void sayHello(String name, String hobby) {
      System.out.println("姓名:"+name+",爱好:"+hobby);
    }
}
