DROP TABLE IF EXISTS Books;
CREATE TABLE Books(ID BIGINT PRIMARY KEY, NAME VARCHAR(255), AUTHOR_ID BIGINT, GENRE_ID BIGINT);

DROP TABLE IF EXISTS Authors;
CREATE TABLE Authors(ID BIGINT PRIMARY KEY, NAME VARCHAR(255) UNIQUE);

DROP TABLE IF EXISTS Genres;
CREATE TABLE Genres(ID BIGINT PRIMARY KEY, NAME VARCHAR(255) UNIQUE);

ALTER TABLE Books
ADD CONSTRAINT FK_Books_To_Authors FOREIGN KEY(AUTHOR_ID) REFERENCES Authors(Id);
ALTER TABLE Books
ADD CONSTRAINT FK_Books_To_Genres FOREIGN KEY(GENRE_ID) REFERENCES Genres(Id);