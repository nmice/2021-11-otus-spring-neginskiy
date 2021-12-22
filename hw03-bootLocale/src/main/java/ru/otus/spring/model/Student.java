package ru.otus.spring.model;

import lombok.Getter;

@Getter
public class Student {

    private final String firstname;
    private final String secondname;

    public Student(String firstname, String secondname) {
        this.firstname = firstname;
        this.secondname = secondname;
    }

    @Override
    public String toString() {
        return firstname + " " + secondname;
    }
}
