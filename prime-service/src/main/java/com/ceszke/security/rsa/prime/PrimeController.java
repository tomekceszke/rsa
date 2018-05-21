package com.ceszke.security.rsa.prime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PrimeController {

    private final PrimeService primeService;

    @GetMapping
    public BigInteger getRandomPrime(@RequestParam(required = false, defaultValue = "${prime.defaultPrimeLength:64}") int length) {
        long start = System.currentTimeMillis();
        BigInteger randomPrime = primeService.computeRandomPrime(length);
        log.info("Return random prime {} (bit length {}) in {} miliseconds", randomPrime, length,  System.currentTimeMillis()-start);
        return randomPrime;
    }
}
