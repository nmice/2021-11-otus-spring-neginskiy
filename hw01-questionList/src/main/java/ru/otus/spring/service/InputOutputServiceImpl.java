package ru.otus.spring.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputOutputServiceImpl implements InputOutputService {

    private final InputStream in;
    private final PrintStream out;

    public InputOutputServiceImpl(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public String input() throws IOException {
        Scanner scanner = new Scanner(in);
        return scanner.nextLine();
    }

    @Override
    public void output(String s) {
        out.println(s);
    }
}
