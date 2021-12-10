package ru.otus.spring.service;

import java.io.IOException;

public interface InputOutputService {

    String input() throws IOException;

    void output(String s);

    void output(Object... s);
}
