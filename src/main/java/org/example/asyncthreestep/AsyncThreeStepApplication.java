package org.example.asyncthreestep;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class AsyncThreeStepApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncThreeStepApplication.class, args);
    }

}
