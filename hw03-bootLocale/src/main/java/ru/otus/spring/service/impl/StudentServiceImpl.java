package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.Student;
import ru.otus.spring.service.InputOutputService;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final InputOutputService inputOutputService;
    private final MessageService messageService;

    public StudentServiceImpl(InputOutputService inputOutputService, MessageService messageService) {
        this.inputOutputService = inputOutputService;
        this.messageService = messageService;
    }

    @Override
    public Student inputStudentData() {
        inputOutputService.output(messageService.getMessage("input.firstname"));
        String firstname = inputOutputService.input();
        inputOutputService.output(messageService.getMessage("input.secondname"));
        String secondname = inputOutputService.input();
        return new Student(firstname, secondname);
    }
}
