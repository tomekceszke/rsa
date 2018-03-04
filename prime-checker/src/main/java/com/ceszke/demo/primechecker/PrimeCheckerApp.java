package com.ceszke.demo.primechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PrimeCheckerApp {

//    @Bean
//    public Executor asyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(3);
//        executor.setMaxPoolSize(3);
//        executor.setQueueCapacity(500);
//        executor.setThreadNamePrefix("PrimeChecker-");
//        executor.initialize();
//        return executor;
//    }

    public static void main(String[] args) {
        SpringApplication.run(PrimeCheckerApp.class, args);

    }
}
