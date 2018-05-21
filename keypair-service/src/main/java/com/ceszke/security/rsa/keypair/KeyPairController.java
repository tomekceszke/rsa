package com.ceszke.security.rsa.keypair;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class KeyPairController {

    private KeyPairService keyPairService;

    @GetMapping
    public KeyPair getKeyPair(@RequestParam(required = false, defaultValue = "${prime.defaultKeyLength:128}") int length) {
        long start = System.currentTimeMillis();
        KeyPair keyPair = keyPairService.createKeyPair(length);
        log.info("Return generated key pair (bit length: {}) in {} miliseconds", length,  System.currentTimeMillis()-start);
        return keyPair;
    }
}
