package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.runner.Runner;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        Runner runner = context.getBean(Runner.class);
        runner.run();
    }
}
