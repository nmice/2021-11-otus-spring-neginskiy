package ru.otus.spring.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.Commentary;
import ru.otus.spring.repository.CommentaryRepository;


@DisplayName("Repository для работы с комментариями должен ")
@DataJpaTest
class CommentaryRepositoryJpaTest {

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private TestEntityManager em;

    private final String NEW_COMMENT = "Very interesting story! But too little time for Sancho...";

    @DisplayName("обновлять текст комментария по Id")
    @Test
    void shouldCorrectUpdateTextById() {
        commentaryRepository.updateTextById(1L, NEW_COMMENT);
        Commentary c = em.find(Commentary.class, 1L);
        Assertions.assertThat(c.getText()).isEqualTo(NEW_COMMENT);
    }
}