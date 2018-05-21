package com.ceszke.security.rsa.keypair;

import com.ceszke.security.rsa.client.PrimeServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

import static java.math.BigInteger.ONE;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeyPairService {

    private final PrimeServiceClient primeServiceClient;

    @Value("${prime.certainty:15}")
    private Integer certainty;


    @HystrixCommand(fallbackMethod = "createKeyPairWithLocalPrimeGenerator")
    KeyPair createKeyPair(int bitLength) {
        BigInteger p = primeServiceClient.getRandomPrime(bitLength / 2);
        BigInteger q = primeServiceClient.getRandomPrime(bitLength / 2);
        return assembleKeyPair(bitLength, p, q);
    }

    @SuppressWarnings("unused")
    KeyPair createKeyPairWithLocalPrimeGenerator(int bitLength) {
        log.warn("Using fallback method createKeyPairWithLocalPrimeGenerator() due to some problems with createKeyPair()");
        SecureRandom rnd = new SecureRandom();
        BigInteger p = new BigInteger(bitLength / 2, certainty, rnd);
        BigInteger q = new BigInteger(bitLength / 2, certainty, rnd);
        return assembleKeyPair(bitLength, p, q);
    }

    private KeyPair assembleKeyPair(int bitLength, BigInteger p, BigInteger q) {
        BigInteger phi = computeEulerFunctionValue(p, q);
        BigInteger n = computeModulus(p, q);
        BigInteger e = computeEncryptionExponent(bitLength, phi);
        BigInteger d = computeDecryptionExponent(e, phi);
        return KeyPair.builder()
                .privateKey(Key.builder().exponent(e).modulus(n).build())
                .publicKey(Key.builder().exponent(d).modulus(n).build())
                .build();
    }

    private BigInteger computeEulerFunctionValue(BigInteger p, BigInteger q) {
        return p.subtract(ONE).multiply(q.subtract(ONE));
    }

    private BigInteger computeModulus(BigInteger p, BigInteger q) {
        return p.multiply(q);
    }

    private BigInteger computeEncryptionExponent(int bitLength, BigInteger phi) {
        BigInteger e;
        do {
            e = new BigInteger(bitLength, new SecureRandom());
        } while ((e.compareTo(phi) != 1) || (e.gcd(phi).compareTo(BigInteger.valueOf(1)) != 0));
        return e;
    }

    private BigInteger computeDecryptionExponent(BigInteger e, BigInteger phi) {
        //return e.modInverse(phi);
        return new BigInteger("65537");
    }

}
