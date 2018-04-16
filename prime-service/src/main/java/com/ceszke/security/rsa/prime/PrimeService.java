package com.ceszke.security.rsa.prime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Slf4j
@Service
public class PrimeService {


    public BigInteger computeRandomPrime(int bitLength) {
        if (bitLength < 2)
            throw new ArithmeticException("Bit length must not be lower than 2");
        /*BigInteger.probablePrime(bitLength, new Random()).intValue();*/
        return new BigInteger(bitLength, 15, new SecureRandom());
    }


}
