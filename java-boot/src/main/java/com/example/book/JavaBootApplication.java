package com.example.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JavaBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaBootApplication.class, args);
    }

}
