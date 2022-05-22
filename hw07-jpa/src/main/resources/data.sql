insert into genre (id, name)
values (1, 'Novel');
insert into genre (id, name)
values (2, 'Programming');
insert into genre (id, name)
values (3, 'Psychology');

insert into author (id, name)
values (1, 'Miguel De Cervantes');
insert into author (id, name)
values (2, 'Kathy Sierra');
insert into author (id, name)
values (3, 'Masaru Ibuka');

insert into book (id, title, author_id, genre_id)
values (1, 'Don Quixote', 1, 1);
insert into book (id, title, author_id, genre_id)
values (2, 'Java Headfirst', 2, 2);
insert into book (id, title, author_id, genre_id)
values (3, 'After three it`s too late', 3, 3);

insert into commentary (id, text, book_id)
values (1, 'Very interesting story!', 1);
insert into commentary (id, text, book_id)
values (2, 'This book was changed my life))', 2);
insert into commentary (id, text, book_id)
values (3, 'Wow, its unbelievable..', 3);