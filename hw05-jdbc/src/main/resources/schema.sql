DROP TABLE IF EXISTS book;
CREATE TABLE book
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(255),
    authorId BIGINT,
    genreId  BIGINT
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

ALTER TABLE book
    ADD CONSTRAINT fk_book_to_author FOREIGN KEY (authorId) REFERENCES author (Id);

ALTER TABLE book
    ADD CONSTRAINT fk_book_to_genre FOREIGN KEY (genreId) REFERENCES genre (Id);