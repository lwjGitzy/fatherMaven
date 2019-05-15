package com.jk.mapper;

import com.jk.model.TypeBean;
import org.springframework.stereotype.Component;

@Component
public interface MainMapper {
    void addType(TypeBean typeBean);
}
