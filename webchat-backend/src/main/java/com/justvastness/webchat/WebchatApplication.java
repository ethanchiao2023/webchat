package com.justvastness.webchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.justvastness.webchat.*")
public class WebchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebchatApplication.class, args);
    }

}
