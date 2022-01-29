package ru.otus.spring.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Student {

    private String firstname;
    private String secondname;

    public Student(String firstname, String secondname) {
        this.firstname = firstname;
        this.secondname = secondname;
    }

    @Override
    public String toString() {
        return firstname + " " + secondname;
    }

}
