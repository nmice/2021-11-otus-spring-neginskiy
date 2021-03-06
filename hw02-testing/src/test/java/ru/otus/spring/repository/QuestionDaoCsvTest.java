package ru.otus.spring.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.config.TestConfig;
import ru.otus.spring.model.Question;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class QuestionDaoCsvTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    void findAllQuestionsTest() {
        List<Question> expectedQuestionList = buildQuestionList();
        List<Question> actualQuestionList = questionDao.findAllQuestions();
        assertEquals(expectedQuestionList, actualQuestionList);
    }

    private List<Question> buildQuestionList() {
        return Arrays.asList(
                new Question("Q1", "A1", 1),
                new Question("Q2", "A2", 2),
                new Question("Q3", "A3", 3),
                new Question("Q4", "A4", 4),
                new Question("Q5", "A5", 5)
        );
    }
}
