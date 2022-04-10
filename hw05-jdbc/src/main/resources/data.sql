insert into genre (id, name)
values (11, 'Novel');
insert into genre (id, name)
values (22, 'Programming');
insert into genre (id, name)
values (33, 'Psychology');

insert into author (id, name)
values (11, 'Miguel De Cervantes');
insert into author (id, name)
values (22, 'Kathy Sierra');
insert into author (id, name)
values (33, 'Masaru Ibuka');

insert into book (id, title, authorId, genreId)
values (11, 'Don Quixote', 11, 11);
insert into book (id, title, authorId, genreId)
values (22, 'Java Headfirst', 22, 22);
insert into book (id, title, authorId, genreId)
values (33, 'After three it`s too late', 33, 33);
