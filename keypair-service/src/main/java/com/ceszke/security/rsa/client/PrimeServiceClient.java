package com.ceszke.security.rsa.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@FeignClient("prime-service")
public interface PrimeServiceClient {

    @GetMapping("/prime")
    BigInteger getRandomPrime(@RequestParam("length")  int length);


}
