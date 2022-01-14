package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.TestResult;
import ru.otus.spring.service.InputOutputService;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

    private final InputOutputService inputOutputService;
    private final MessageService messageService;

    public ResultServiceImpl(InputOutputService inputOutputService, MessageService messageService) {
        this.inputOutputService = inputOutputService;
        this.messageService = messageService;
    }

    @Override
    public void printResult(TestResult testResult) {
        inputOutputService.output(messageService.getMessage("result"));
        if (isExcellentResult(testResult)) {
            inputOutputService.output(
                    messageService.getMessage("result.score.all", testResult.getStudent()));
        } else {
            inputOutputService.output(messageService.getMessage("result.score",
                    testResult.getStudent(),
                    testResult.getScore())
            );
        }
    }

    private boolean isExcellentResult(TestResult testResult) {
        return testResult.getScore() == testResult.getQuestionsSize();
    }
}
