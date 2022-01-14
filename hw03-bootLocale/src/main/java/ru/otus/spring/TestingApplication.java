package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.runner.TestingRunner;

@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TestingApplication.class, args);
        TestingRunner runner = context.getBean(TestingRunner.class);
        runner.run();
    }

}
