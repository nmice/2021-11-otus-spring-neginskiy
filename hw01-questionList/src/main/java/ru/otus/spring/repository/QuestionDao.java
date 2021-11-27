package ru.otus.spring.repository;

import java.util.List;

public interface QuestionDao {

    String findAnswerByQuestion(String question);

    List<String> findAllQuestions();

    List<String> findAllAnswers();
}
