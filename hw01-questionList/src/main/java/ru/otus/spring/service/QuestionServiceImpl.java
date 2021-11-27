package ru.otus.spring.service;

import ru.otus.spring.repository.QuestionDao;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public String getCorrectAnswer(String question) {
        return dao.findAnswerByQuestion(question);
    }

    @Override
    public List<String> getAllQuestions() {
        return dao.findAllQuestions();
    }

    @Override
    public List<String> getAllAnswers() {
        return dao.findAllAnswers();
    }
}
