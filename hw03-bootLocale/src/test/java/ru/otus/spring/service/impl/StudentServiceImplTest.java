package ru.otus.spring.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.model.Student;
import ru.otus.spring.service.InputOutputService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentServiceImplTest {

    public static final String FIRSTNAME = "IVAN";
    public static final String SECONDNAME = "PETROV";

    @Autowired
    private StudentServiceImpl studentService;

    @MockBean
    private InputOutputService inputOutputService;

    @DisplayName("Должен возвращать корректный объект Student по итогу ввода")
    @Test
    void inputStudentDataTest() {
        Mockito.when(inputOutputService.input()).thenReturn(FIRSTNAME, SECONDNAME);
        Student student = studentService.inputStudentData();
        assertEquals(student.getFirstname(), FIRSTNAME);
        assertEquals(student.getSecondname(), SECONDNAME);
    }
}