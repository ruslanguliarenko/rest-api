create table products
(
    id int auto_increment,
    model varchar(255) not null,
    price decimal not null,
    constraint products_pk
        primary key (id)
);