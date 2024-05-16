package com.sparta.personaltask2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PersonalTask2Application {

    public static void main(String[] args) {
        SpringApplication.run(PersonalTask2Application.class, args);
    }

}
