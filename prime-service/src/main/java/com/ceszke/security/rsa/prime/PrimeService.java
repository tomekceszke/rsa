package com.ceszke.security.rsa.prime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
class PrimeService {

    @Value("${prime.certainty:15}")
    private Integer certainty;

    @Value("${prime.maximumPrimeLength:1024}")
    private Integer maximumPrimeLength;


    BigInteger computeRandomPrime(int bitLength) {
        if (bitLength < 2)
            throw new ArithmeticException("Bit length must not be lower than 2");
        if (bitLength > maximumPrimeLength)
            throw new ArithmeticException("Bit length must not be greater than "+ maximumPrimeLength);
        return new BigInteger(bitLength, certainty , new SecureRandom());
    }

}
