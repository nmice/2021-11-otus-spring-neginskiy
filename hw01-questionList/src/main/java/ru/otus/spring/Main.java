package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.QuestionService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);

        List<String> questions = service.getAllQuestions();
        List<String> answers = service.getAllAnswers();

        System.out.println("QUESTIONS:");
        questions.forEach(System.out::println);

        System.out.println("\r\nANSWERS:");
        answers.forEach(System.out::println);
    }
}
