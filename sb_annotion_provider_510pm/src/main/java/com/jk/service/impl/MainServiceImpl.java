package com.jk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.MainMapper;
import com.jk.model.UserBean;
import com.jk.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service(version = "1.0.0")
public class MainServiceImpl implements MainService {

    @Autowired
    private MainMapper mainMapper;
    //这是生产者
    @Override
    public List<UserBean> queryList() {
        List<UserBean> list = mainMapper.queryList();
        return list;
    }
}
