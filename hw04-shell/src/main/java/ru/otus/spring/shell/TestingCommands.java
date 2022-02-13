package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.model.Student;
import ru.otus.spring.runner.TestingRunner;
import ru.otus.spring.service.MessageService;
import ru.otus.spring.service.StudentService;

@ShellComponent
@RequiredArgsConstructor
public class TestingCommands {

    private final TestingRunner testingRunner;
    private final MessageService messageService;
    private final StudentService studentService;
    private Student student = new Student();

    @ShellMethod(value = "Input firstname command", key = {"f", "fn", "firstname"})
    public String inputFirstName(@ShellOption(defaultValue = "Студент") String firstname) {
        student.setFirstname(firstname);
        return messageService.getMessage("firstname.ok", firstname);
    }

    @ShellMethod(value = "Input second command", key = {"s", "sn", "secondname"})
    public String inputSecondName(@ShellOption(defaultValue = "Обыкновенный") String secondname) {
        student.setSecondname(secondname);
        return messageService.getMessage("secondname.ok", secondname);
    }

    @ShellMethod(value = "Introduce your fullname with hints", key = {"i", "in", "introduce"})
    public String introduce() {
        student = studentService.inputStudentData();
        return messageService.getMessage("fullname.ok", student.getFirstname(), student.getSecondname());
    }

    @ShellMethod(value = "Run testing command", key = {"r", "run", "run-testing"})
    @ShellMethodAvailability("isTestingCommandAvailable")
    public void runTesting() {
        testingRunner.runTesting(student);
    }

    private Availability isTestingCommandAvailable() {
        if (student.getFirstname() == null || student.getFirstname().isEmpty()) {
            return Availability.unavailable(messageService.getMessage("firstname.invalid"));
        } else if (student.getSecondname() == null || student.getSecondname().isEmpty()) {
            return Availability.unavailable(messageService.getMessage("secondname.invalid"));
        } else {
            return Availability.available();
        }
    }
}