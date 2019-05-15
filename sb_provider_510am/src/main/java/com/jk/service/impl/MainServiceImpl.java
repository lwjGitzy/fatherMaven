package com.jk.service.impl;

import com.jk.dao.MainMapper;
import com.jk.model.UserBean;
import com.jk.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("mainService")//与spring-dubbo-provider.xml中ref mainService的对应
public class MainServiceImpl implements MainService {

    @Autowired
    private MainMapper mainMapper;
//这是生产者
    @Override
    public List<UserBean> queryList() {


     /*   ArrayList<UserBean> list = new ArrayList<>();
        UserBean userBean = new UserBean();
            userBean.setUserName("aaa");
            userBean.setUserAge(18);
            userBean.setUserSex(0);
            userBean.setUserTime("2000-01-01");
            userBean.setUserId(11);

        UserBean userBean2 = new UserBean();
            userBean2.setUserName("aaa");
            userBean2.setUserAge(18);
            userBean2.setUserSex(0);
            userBean2.setUserTime("2000-01-01");
            userBean2.setUserId(22);

        list.add(userBean);
        list.add(userBean2);*/
        List<UserBean> list = mainMapper.queryList();
        return list;
    }
}
