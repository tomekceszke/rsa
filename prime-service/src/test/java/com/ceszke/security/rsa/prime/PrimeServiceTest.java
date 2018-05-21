package com.ceszke.security.rsa.prime;

import com.ceszke.security.rsa.PrimeServiceApp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PrimeServiceApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeServiceTest {

    @Autowired
    private PrimeService primeService;

    @Value("${prime.certainty:15}")
    private Integer certainty;

    @Value("${prime.maximumPrimeLength:1024}")
    private Integer maximumPrimeLength;


    @Test
    public void shouldComputeSomeRandomPrimes() {
        Stream.of(2, 4, 8, 16, 32, 64, 128, 256, 512, 1024).forEach(this::checkComputedRandomPrime);
    }

    private void checkComputedRandomPrime(int bitLength) {
        long start = System.currentTimeMillis();
        BigInteger randomPrime = primeService.computeRandomPrime(bitLength);
        System.out.format("Found %d-length random prime in %d milliseconds\n", bitLength, System.currentTimeMillis() - start);
        Assert.assertEquals(bitLength, randomPrime.bitLength());
        Assert.assertTrue(randomPrime.isProbablePrime(certainty));
    }

    @Test(expected = ArithmeticException.class)
    public void shouldNotComputeTooLongRandomPrimes() {
        primeService.computeRandomPrime(maximumPrimeLength *2);
    }

    @Test(expected = ArithmeticException.class)
    public void shouldNotComputeTooShortRandomPrime() {
        primeService.computeRandomPrime(1);
    }

    @Test(expected = ArithmeticException.class)
    public void shouldNotComputeRandomPrimeWithNegativeLength() {
        primeService.computeRandomPrime(-1);
    }
}
