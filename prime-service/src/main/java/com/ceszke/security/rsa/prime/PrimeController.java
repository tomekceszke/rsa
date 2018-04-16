package com.ceszke.security.rsa.prime;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@AllArgsConstructor
//@RequestMapping("/prime")
@Slf4j
public class PrimeController {

    private PrimeService primeService;

    @GetMapping
    public BigInteger getRandomPrime(@RequestParam(required = false, defaultValue = "16") int bitLength) {
        long start = System.currentTimeMillis();
        BigInteger randomPrime = primeService.computeRandomPrime(bitLength);
        log.info("Return random prime {} (max bit length {}) in {} miliseconds", randomPrime, bitLength,  System.currentTimeMillis()-start);

        return randomPrime;
    }
}
