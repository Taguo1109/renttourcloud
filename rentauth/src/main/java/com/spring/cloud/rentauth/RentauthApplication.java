package com.spring.cloud.rentauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spring.cloud"})
public class RentauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentauthApplication.class, args);
    }

}
