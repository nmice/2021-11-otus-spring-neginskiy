package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.service.InputOutputService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {

    private final InputStream in;
    private final PrintStream out;

    public InputOutputServiceImpl() {
        this.in = System.in;
        this.out = System.out;
    }

    @Override
    public String input() {
        Scanner scanner = new Scanner(in);
        return scanner.nextLine();
    }

    @Override
    public void output(String s) {
        out.println(s);
    }

    @Override
    public void output(Object... s) {
        String concatted = Arrays.stream(s)
                .map(Object::toString)
                .reduce(String::concat)
                .orElseThrow(RuntimeException::new);
        output(concatted);
    }
}