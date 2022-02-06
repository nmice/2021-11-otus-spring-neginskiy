package ru.otus.spring.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Book {

    private String id;

    private String name;

    private String author;

    private String genre;

}
