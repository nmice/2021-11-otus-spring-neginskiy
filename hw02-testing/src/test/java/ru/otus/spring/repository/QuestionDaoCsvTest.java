package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.config.TestConfig;
import ru.otus.spring.model.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class QuestionDaoCsvTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    void findAllQuestionsTest() {
        List<Question> list = questionDao.findAllQuestions();
        assertEquals(5, list.size());
        assertEquals("Q1", list.get(0).getQuestion());
        assertEquals("Q2", list.get(1).getQuestion());
        assertEquals("Q3", list.get(2).getQuestion());
        assertEquals("Q4", list.get(3).getQuestion());
        assertEquals("Q5", list.get(4).getQuestion());
        assertEquals("A1", list.get(0).getAnswer());
        assertEquals("A2", list.get(1).getAnswer());
        assertEquals("A3", list.get(2).getAnswer());
        assertEquals("A4", list.get(3).getAnswer());
        assertEquals("A5", list.get(4).getAnswer());
        assertEquals(1, list.get(0).getId());
        assertEquals(2, list.get(1).getId());
        assertEquals(3, list.get(2).getId());
        assertEquals(4, list.get(3).getId());
        assertEquals(5, list.get(4).getId());
    }
}