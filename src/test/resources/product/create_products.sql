DROP TABLE product_category IF EXISTS;
DROP TABLE categories IF EXISTS;
DROP TABLE products IF EXISTS;
create table categories
(
    id int auto_increment,
    name varchar(255) not null,
    constraint categories_pk
        primary key (id)
);
create table products
(
    id int auto_increment,
    model varchar(255) not null,
    price decimal not null,
    constraint products_pk
        primary key (id)
);
create table product_category
(
    product_id int,
    category_id int,

    constraint product_category_pk
        primary key (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)

);