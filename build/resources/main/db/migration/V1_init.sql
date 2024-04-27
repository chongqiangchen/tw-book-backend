create table book
(
    id bigint auto_increment primary key,
    title varchar(20) not null,
    author varchar(20) not null,
    publication_year varchar(10) not null,
    isbn varchar(20) not null,
    created_at datetime not null,
    updated_at datetime not null
)
