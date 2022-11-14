create table if not exists book (
    id bigserial not null,
    title varchar(150) not null,
    author varchar(150) not null,
    description varchar(150),
    constraint book_pk primary key (id)
);

comment on table book is 'Книги';
comment on column book.id is 'ID';
comment on column book.title is 'Название книги';
comment on column book.author is 'Автор';
comment on column book.description is 'Описание';

insert into book (id, title, author, description)
values (1, 'Crime and Punishment', 'F. Dostoevsky', null);
insert into book (id, title, author, description)
values (2, 'Anna Karenina', 'L. Tolstoy', null);
insert into book (id, title, author, description)
values (3, 'The Brothers Karamazov', 'F. Dostoevsky', null);
insert into book (id, title, author, description)
values (4, 'War and Peace', 'L. Tolstoy', null);
insert into book (id, title, author, description)
values (5, 'Dead Souls', 'N. Gogol', null);