package com.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("spring-dubbo-consumer.xml")
@SpringBootApplication
public class SbConsumer510amApplication {
    //消费者
//http://localhost:8081/query
    public static void main(String[] args) {
        SpringApplication.run(SbConsumer510amApplication.class, args);
    }

}
