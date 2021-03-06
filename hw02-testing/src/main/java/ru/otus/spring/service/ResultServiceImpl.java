package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.TestResult;

@Service
public class ResultServiceImpl implements ResultService {

    private final InputOutputService inputOutputService;

    public ResultServiceImpl(InputOutputService inputOutputService) {
        this.inputOutputService = inputOutputService;
    }

    @Override
    public void printResult(TestResult testResult) {
        inputOutputService.output("YOUR RESULT:");
        inputOutputService.output(testResult.getStudent() + ", You answered " +
                (testResult.getScore() == testResult.getQuestionsSize() ? "all the" : testResult.getScore()) +
                " questions correctly.");
    }
}
