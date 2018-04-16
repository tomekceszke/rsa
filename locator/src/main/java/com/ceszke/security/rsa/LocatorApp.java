package com.ceszke.security.rsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LocatorApp {

    public static void main(String[] args) {
        SpringApplication.run(LocatorApp.class, args);
    }
}
