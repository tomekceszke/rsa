package com.ceszke.security.rsa.prime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrimeServiceTest {


    @Autowired
    private PrimeService primeService;

    @Test
    public void shouldComputeRandomPrimes() {
        Assert.assertTrue(primeService.computeRandomPrime(2).isProbablePrime(15));
        Assert.assertTrue(primeService.computeRandomPrime(8).isProbablePrime(15));
        Assert.assertTrue(primeService.computeRandomPrime(16).isProbablePrime(15));
        Assert.assertTrue(primeService.computeRandomPrime(24).isProbablePrime(15));
        Assert.assertTrue(primeService.computeRandomPrime(31).isProbablePrime(15));
    }

    @Test(expected = ArithmeticException.class)
    public void shouldNotComputeTooLongRandomPrimes() {
        primeService.computeRandomPrime(32);
    }

    @Test(expected = ArithmeticException.class)
    public void shouldNotComputeTooShortRandomPrimes() {
        primeService.computeRandomPrime(1);
    }
}
