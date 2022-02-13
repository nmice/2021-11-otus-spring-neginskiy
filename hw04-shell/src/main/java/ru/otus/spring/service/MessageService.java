package ru.otus.spring.service;

public interface MessageService {

    String getMessage(String message);

    String getMessage(String message, Object... args);
}
