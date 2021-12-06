package ru.otus.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.Student;

import java.io.IOException;

@Service
public class StudentServiceImpl implements StudentService {

    public static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final InputOutputService inputOutputService;

    public StudentServiceImpl(InputOutputService inputOutputService) {
        this.inputOutputService = inputOutputService;
    }

    @Override
    public Student inputStudentData() {
        inputOutputService.output("Input your firstname:");
        String firstname = null;
        try {
            firstname = inputOutputService.input();
        } catch (IOException e) {
            inputOutputService.output("Failed to input firstname");
            log.error("Failed to input firstname", e);
        }
        inputOutputService.output("Input your secondname:");
        String secondname = null;
        try {
            secondname = inputOutputService.input();
        } catch (IOException e) {
            inputOutputService.output("Failed to input secondname");
            log.error("Failed to input secondname", e);
        }
        return new Student(firstname, secondname);
    }
}
