package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.spring.config.FileNameProviderImpl;
import ru.otus.spring.model.Question;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class QuestionDaoCsvTest {

    @Autowired
    private QuestionDaoCsv questionDao;

    @MockBean
    private FileNameProviderImpl fileNameProvider;

    @DisplayName("Должен возвращать список вопросов из csv-файла")
    @Test
    void findAllQuestionsTest() {
        Mockito.when(fileNameProvider.getFileName()).thenReturn("questions-test.csv");
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
