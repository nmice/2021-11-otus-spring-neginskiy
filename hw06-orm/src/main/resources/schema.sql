DROP TABLE IF EXISTS book;
CREATE TABLE book
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    title     VARCHAR(255),
    author_id BIGINT,
    genre_id  BIGINT
);

DROP TABLE IF EXISTS author;
CREATE TABLE author
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE
);

DROP TABLE IF EXISTS genre;
CREATE TABLE genre
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE
);

DROP TABLE IF EXISTS commentary;
CREATE TABLE commentary
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    text    VARCHAR(255),
    book_id BIGINT
);

ALTER TABLE book
    ADD CONSTRAINT fk_book_to_author FOREIGN KEY (author_id) REFERENCES author (id);

ALTER TABLE book
    ADD CONSTRAINT fk_book_to_genre FOREIGN KEY (genre_id) REFERENCES genre (id);

ALTER TABLE commentary
    ADD CONSTRAINT fk_commentary_to_book FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE;