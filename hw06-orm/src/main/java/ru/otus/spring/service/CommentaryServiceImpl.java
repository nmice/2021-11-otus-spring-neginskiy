package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Commentary;
import ru.otus.spring.repository.CommentaryRepository;

@Service
@Transactional
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryRepository commentaryRepository;

    public CommentaryServiceImpl(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    @Override
    public Commentary getById(long id) {
        return commentaryRepository.getById(id);
    }
}
