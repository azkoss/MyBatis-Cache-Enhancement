package com.github.wanggit.mybatis.cache.enhancement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.github.wanggit.mybatis.cache.enhancement.dao.mapper")
@ComponentScan
@WebAppConfiguration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
