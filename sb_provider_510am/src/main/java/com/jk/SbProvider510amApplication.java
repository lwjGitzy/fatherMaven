package com.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement  //开启当前项目的事务管理
@ImportResource("classpath:spring-dubbo-provider.xml") //加载spring-dubbo-provider.xml文件
@MapperScan("com.jk.dao")//扫描dao层
@SpringBootApplication  //springboot项目的启动注解
public class SbProvider510amApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbProvider510amApplication.class, args);
    }

}
