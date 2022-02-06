package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Book;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private Book book = new Book();

    @ShellMethod(value = "Input book name command", key = {"bn", "bookname"})
    public String save(@ShellOption String name, @ShellOption String genre, @ShellOption String author) {
        book.setName(name);
        book.setAuthor(author);
        book.setGenre(genre);

        return "Book " + name + " saved";
    }

    @ShellMethod(value = "Input second command", key = {"bg", "bookgenre"})
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