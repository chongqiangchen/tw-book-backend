create table book
(
    id bigint auto_increment primary key,
    title varchar(20) not null,
    author varchar(20) not null,
    publication_year varchar(10) not null,
    isbn varchar(20) not null,
    created_at datetime not null,
    updated_at datetime not null
);

# insert data
insert into book (title, author, publication_year, isbn, created_at, updated_at) values ('The Great Gatsby', 'F. Scott Fitzgerald', '1925', '9780743273565', now(), now());
insert into book (title, author, publication_year, isbn, created_at, updated_at) values ('To Kill a Mockingbird', 'Harper Lee', '1960', '9780061120084', now(), now());
insert into book (title, author, publication_year, isbn, created_at, updated_at) values ('1984', 'George Orwell', '1949', '9780451524935', now(), now());
insert into book (title, author, publication_year, isbn, created_at, updated_at) values ('Pride and Prejudice', 'Jane Austen', '1813', '9780679783268', now(), now());
insert into book (title, author, publication_year, isbn, created_at, updated_at) values ('The Catcher in the Rye', 'J.D. Salinger', '1951', '9780316769488', now(), now());
insert into book (title, author, publication_year, isbn, created_at, updated_at) values ('The Hobbit', 'J.R.R. Tolkien', '1937', '9780547928227', now(), now());
insert into book (title, author, publication_year, isbn, created_at, updated_at) values ('Fahrenheit 451', 'Ray Bradbury', '1953', '9781451673319', now(), now());
insert into book (title, author, publication_year, isbn, created_at, updated_at) values ('The Lord of the Rings', 'J.R.R. Tolkien', '1954', '9780618640157', now(), now());