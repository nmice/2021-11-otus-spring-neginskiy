package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.Question;
import ru.otus.spring.repository.QuestionDao;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getAllQuestions() {
        return dao.findAllQuestions();
    }
}
