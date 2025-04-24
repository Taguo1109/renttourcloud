package com.spring.cloud.rentorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.spring.cloud"})
public class RentorderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentorderApplication.class, args);
    }

}
