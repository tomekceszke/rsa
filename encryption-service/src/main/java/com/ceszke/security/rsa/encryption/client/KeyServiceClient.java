package com.ceszke.security.rsa.encryption.client;

import com.ceszke.security.rsa.keypair.KeyPair;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("keypair-service")
public interface KeyServiceClient {

    @GetMapping("/keypair")
    KeyPair getKeyPair(@RequestParam("length")  int length);

}
