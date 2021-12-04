package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import ru.otus.spring.repository.QuestionDao;
import ru.otus.spring.repository.QuestionDaoCsv;

@Configuration
@TestPropertySource("classpath:app-test.properties")
public class TestConfig {

    public QuestionDao questionDaoCsv(@Value("questions-test.csv") Resource questionsResource) {
        return new QuestionDaoCsv(questionsResource);
    }
}
