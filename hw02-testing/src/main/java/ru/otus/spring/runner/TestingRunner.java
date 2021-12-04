package ru.otus.spring.runner;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.Question;
import ru.otus.spring.model.Student;
import ru.otus.spring.service.InputOutputService;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class TestingRunner implements Runner {

    private final QuestionService service;
    private final InputOutputService inputOutputService;

    public TestingRunner(QuestionService service, InputOutputService inputOutputService) {
        this.service = service;
        this.inputOutputService = inputOutputService;
    }

    @Override
    public void run() {
        List<Question> questions = service.getAllQuestions();
        Student student = inputStudentData();
        inputOutputService.output("LETS GO TEST");
        int score = 0;
        String answerList = getAnswerList(questions);
        Collections.shuffle(questions);
        for (Question question : questions) {
            printQuestionWithAnswerList(question, answerList);
            int answerId = inputAnswerId();
            score = checkAnswerAndGetScore(score, question, answerId);
        }
        inputOutputService.output("YOUR RESULT:");
        inputOutputService.output(student + ", You answered " +
                (score == questions.size() ? "all the" : score) +
                " questions correctly.");
    }

    private int inputAnswerId() {
        try {
            return Integer.parseInt(inputOutputService.input());
        } catch (IOException e) {
            inputOutputService.output("Failed to input number of answer");
            throw new RuntimeException(e);
        }
    }

    private int checkAnswerAndGetScore(int score, Question question, int answerId) {
        if (answerId == question.getId()) {
            score++;
        }
        return score;
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

    private Student inputStudentData() {
        inputOutputService.output("Input your firstname:");
        String firstname = null;
        try {
            firstname = inputOutputService.input();
        } catch (IOException e) {
            inputOutputService.output("Failed to input firstname");
            throw new RuntimeException(e);
        }
        inputOutputService.output("Input your secondname:");
        String secondname = null;
        try {
            secondname = inputOutputService.input();
        } catch (IOException e) {
            inputOutputService.output("Failed to input secondname");
            throw new RuntimeException(e);
        }
        return new Student(firstname, secondname);
    }
}
