package com.ceszke.security.rsa.keypair;

import com.ceszke.security.rsa.client.PrimeServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Random;

import static java.math.BigInteger.ONE;

@Service
@AllArgsConstructor
@Slf4j
public class KeyPairService {

    private PrimeServiceClient primeServiceClient;

    //private static final BigInteger ONE = BigInteger.valueOf(1);

    KeyPair createKeyPair(int bitLength) {
        BigInteger p = primeServiceClient.getRandomPrime(bitLength / 2);
        BigInteger q = primeServiceClient.getRandomPrime(bitLength / 2);
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
            e = new BigInteger(bitLength, new Random());
        } while ((e.compareTo(phi) != 1) || (e.gcd(phi).compareTo(BigInteger.valueOf(1)) != 0));
        return e;
    }

    private BigInteger computeDecryptionExponent(BigInteger e, BigInteger phi) {
        return e.modInverse(phi);
    }

}
