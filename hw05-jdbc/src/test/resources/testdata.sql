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

insert into book (id, title, authorId, genreId)
values (1, 'Don Quixote', 1, 1);
insert into book (id, title, authorId, genreId)
values (2, 'Java Headfirst', 2, 2);
insert into book (id, title, authorId, genreId)
values (3, 'After three it`s too late', 3, 3);
