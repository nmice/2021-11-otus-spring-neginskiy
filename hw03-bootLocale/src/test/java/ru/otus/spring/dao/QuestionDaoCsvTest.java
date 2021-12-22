package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.model.Question;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestConfiguration("application-test")
class QuestionDaoCsvTest {

    @Autowired
    private QuestionDaoCsv questionDao;

    @MockBean
    private AppProps props;

    @DisplayName("Должен возвращать список вопросов из csv-файла в заданной локали")
    @Test
    void findAllQuestionsTest() {
        Mockito.when(props.getCsvfiles()).thenReturn(Map.of("en_EN", "questions-test.csv"));
        Mockito.when(props.getLocaleTag()).thenReturn("en_EN");
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
