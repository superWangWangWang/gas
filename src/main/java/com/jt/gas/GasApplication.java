package com.jt.gas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableScheduling//开启定时任务
@SpringBootApplication
public class GasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GasApplication.class, args);
    }

}
