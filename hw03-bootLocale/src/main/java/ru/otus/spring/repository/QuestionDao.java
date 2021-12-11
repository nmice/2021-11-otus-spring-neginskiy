package ru.otus.spring.repository;

import ru.otus.spring.model.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {

    List<Question> findAllQuestions();
}
