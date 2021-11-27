package ru.otus.spring.service;

import java.util.List;

public interface QuestionService {

    String getCorrectAnswer(String question);

    List<String> getAllQuestions();

    List<String> getAllAnswers();
}
