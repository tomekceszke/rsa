package com.ceszke.demo.primechecker.prime;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/primes")
@Slf4j
public class PrimeController {

    private PrimeService primeService;

    @GetMapping
    public List<Long> getPrimes(@RequestParam(required = false, defaultValue = "2") long from, @RequestParam(required = false, defaultValue = "100000") long to) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<List<Long>> primesFuture1 = primeService.getPrimesInRange(from, to/2);
        CompletableFuture<List<Long>> primesFuture2 = primeService.getPrimesInRange(to/2, to);
        //CompletableFuture.allOf(primesFuture1,primesFuture2).join();

        List<Long> primes = new ArrayList<>(primesFuture1.get());
        primes.addAll(primesFuture2.get());

        log.info("Found {} prime numbers in {} miliseconds", primes.size(), System.currentTimeMillis()-start);
        return primes;
    }
}
