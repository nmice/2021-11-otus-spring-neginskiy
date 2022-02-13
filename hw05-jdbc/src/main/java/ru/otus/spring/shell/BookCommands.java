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

    @ShellMethod(value = "Input book name command", key = {"ib", "insert book"})
    public String save(@ShellOption String name, @ShellOption String genre, @ShellOption String author) {

        book.setName(name);
        book.setAuthorId(1);
        book.setGenreId(1);

        return "Book " + name + " saved";
    }

    /*private Availability isTestingCommandAvailable() {
        if (student.getFirstname() == null || student.getFirstname().isEmpty()) {
            return Availability.unavailable(messageService.getMessage("firstname.invalid"));
        } else if (student.getSecondname() == null || student.getSecondname().isEmpty()) {
            return Availability.unavailable(messageService.getMessage("secondname.invalid"));
        } else {
            return Availability.available();
        }
    }*/
}