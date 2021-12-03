package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.model.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@PropertySource("classpath:app-test.properties")
@Configuration
class QuestionDaoCsvTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    void findAllQuestionsTest() {
        /*List<Question> list = questionDao.findAllQuestions();
        assertEquals(5, list.size());*/
    }

}