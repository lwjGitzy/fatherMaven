package com.jk.dao;


import com.jk.model.UserBean;
import org.springframework.stereotype.Component;

import java.util.List;


public interface MainMapper {
    List<UserBean> queryList();
}
