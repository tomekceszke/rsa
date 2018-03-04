package com.ceszke.demo.primechecker.prime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class PrimeService {


    @Async
    public CompletableFuture<List<Long>> getPrimesInRange(long from, long to) {
        log.info("Start counting prime numbers from {} to {}", from, to);
        CompletableFuture<List<Long>> primes = CompletableFuture.completedFuture(detectPrimesInRange(from, to));

        return primes;
    }

    private List<Long> detectPrimesInRange(long from, long to) {
        List<Long> primes = new ArrayList<>();

        for (long l = from; l < to; l++) {
            boolean isPrime = true;
            for (long j = 2; j < l; j++) {
                if (l % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(l);
            }
        }
        return primes;
    }
}
