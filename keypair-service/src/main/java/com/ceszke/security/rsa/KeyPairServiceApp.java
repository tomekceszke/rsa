package com.ceszke.security.rsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class KeyPairServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(KeyPairServiceApp.class, args);
    }
}
