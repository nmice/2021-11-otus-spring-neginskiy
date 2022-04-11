package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {

    private final Scanner scanner;
    private final PrintStream out;

    public InputOutputServiceImpl(@Value("#{ T(java.lang.System).in}") InputStream in,
                                  @Value("#{ T(java.lang.System).out}") PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    @Override
    public String input() {
        return scanner.nextLine();
    }

    @Override
    public void output(Object o) {
        out.println(o.toString());
    }
}
