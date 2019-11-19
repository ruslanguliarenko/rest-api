drop table roles if EXISTS;
create table roles(
    id int auto_increment ,
    name varchar (255)not null,
    constraint roles_pk
        primary key (id)

);