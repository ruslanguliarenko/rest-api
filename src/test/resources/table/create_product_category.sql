create table product_category
(
    product_id int,
    category_id int,

    constraint product_category_pk
        primary key (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)

);