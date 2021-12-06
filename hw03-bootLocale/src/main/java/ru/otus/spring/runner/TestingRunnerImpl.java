package ru.otus.spring.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.Question;
import ru.otus.spring.model.Student;
import ru.otus.spring.model.TestResult;
import ru.otus.spring.service.InputOutputService;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.ResultService;
import ru.otus.spring.service.StudentService;

import java.util.Collections;
import java.util.List;

@Service
public class TestingRunnerImpl implements TestingRunner {
    private static final Logger log = LoggerFactory.getLogger(TestingRunnerImpl.class);

    private final QuestionService service;
    private final InputOutputService inputOutputService;
    private final StudentService studentService;
    private final ResultService resultService;

    public TestingRunnerImpl(QuestionService service, InputOutputService inputOutputService, StudentService studentService, ResultService resultService) {
        this.service = service;
        this.inputOutputService = inputOutputService;
        this.studentService = studentService;
        this.resultService = resultService;
    }

    @Override
    public void run() {
        List<Question> questions = service.getAllQuestions();
        Student student = studentService.inputStudentData();
        inputOutputService.output("LETS GO TEST");
        String answerList = getAnswerList(questions);
        Collections.shuffle(questions);
        var testResult = new TestResult(student, questions.size());
        for (Question question : questions) {
            printQuestionWithAnswerList(question, answerList);
            int answerId = inputAnswerId();
            testResult.apply(checkAnswer(question, answerId));
        }
        resultService.printResult(testResult);
    }

    private int inputAnswerId() {
        int answerId = 0;
        try {
            answerId = Integer.parseInt(inputOutputService.input());
        } catch (Exception e) {
            inputOutputService.output("Failed to input number of answer");
            log.error("Failed to input number of answer", e);
        }
        return answerId;
    }

    private boolean checkAnswer(Question question, int answerId) {
        return answerId == question.getId();
    }

    private void printQuestionWithAnswerList(Question question, String answerList) {
        inputOutputService.output(question.getQuestion() + "?");
        inputOutputService.output(answerList);
    }

    private String getAnswerList(List<Question> questions) {
        return questions.stream()
                .map(q -> (q.getId() + ". " + q.getAnswer() + (q.getId() == questions.size() ? "" : "\r\n")))
                .reduce("", String::concat);
    }
}
