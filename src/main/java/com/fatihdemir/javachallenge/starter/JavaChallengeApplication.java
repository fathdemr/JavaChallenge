package com.fatihdemir.javachallenge.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.fatihdemir"})
@ComponentScan(basePackages = {"com.fatihdemir"})
@EnableJpaRepositories(basePackages = {"com.fatihdemir"})
public class JavaChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaChallengeApplication.class, args);
    }

}
