package ru.otus.spring.runner;

import org.springframework.stereotype.Component;
import ru.otus.spring.model.Question;
import ru.otus.spring.service.InputOutputService;
import ru.otus.spring.service.InputOutputServiceImpl;
import ru.otus.spring.service.QuestionService;

import java.util.List;

@Component
public class RunnerImpl implements Runner {

    private final QuestionService service;

    public RunnerImpl(QuestionService service) {
        this.service = service;
    }

    @Override
    public void run() {
        List<Question> questions = service.getAllQuestions();
        InputOutputService inOutService = new InputOutputServiceImpl(System.in, System.out);
        inOutService.output("QUESTIONS:");
        questions.forEach(q -> inOutService.output(q.getQuestion()));
        System.out.println("\r\nANSWERS:");
        questions.forEach(q -> inOutService.output(q.getAnswer()));
    }
}
