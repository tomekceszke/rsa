package com.ceszke.rsa.keys;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class KeysController {

    private KeysService keysService;

    @GetMapping
    public KeyPair getKeyPair(@RequestParam(required = false, defaultValue = "128") int bitLength) {
        long start = System.currentTimeMillis();
        KeyPair keyPair = keysService.createKeyPair(bitLength);
        log.info("Return generated key pair (bit length: {}) in {} miliseconds", bitLength,  System.currentTimeMillis()-start);

        return keyPair;
    }
}
