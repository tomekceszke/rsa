package com.ceszke.security.rsa.encryption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EncryptionServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(EncryptionServiceApp.class, args);

    }
}
