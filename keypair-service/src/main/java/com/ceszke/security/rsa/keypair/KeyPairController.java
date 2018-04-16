package com.ceszke.security.rsa.keypair;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class KeyPairController {

    private KeyPairService keyPairService;

    @GetMapping("/{length}")
    public KeyPair getKeyPair(@PathVariable Integer length) {
        if(length==null){
            length = 128;
        }
        long start = System.currentTimeMillis();
        KeyPair keyPair = keyPairService.createKeyPair(length);
        log.info("Return generated key pair (bit length: {}) in {} miliseconds", length,  System.currentTimeMillis()-start);

        return keyPair;
    }
}
