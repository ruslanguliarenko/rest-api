
create sequence if not exists hibernate_sequence start with 100;

create table if not exists categories
(
    id   int auto_increment,
    name varchar(255),

    constraint category_pk
        primary key (id)
);

create table if not exists products
(
    id          int auto_increment,
    name        varchar(255),
    description varchar(255),
    price decimal,

    constraint product_pk
        primary key (id)
);

create table if not exists product_category
(
    product_id  int,
    category_id int ,

    constraint product_category_pk
        primary key (product_id, category_id),
    constraint product_category_categories_fk
        foreign key (category_id) references categories (id) on delete cascade,
    constraint product_category_products_fk
        foreign key (product_id) references products (id) on delete cascade
);

create table if not exists roles
(
    id   int auto_increment,
    name varchar(255),

    constraint role_pk
        primary key (id)
);

create table if not exists users
(
    id    int auto_increment,
    name  varchar(255),
    email varchar(255),

    constraint user_pk
        primary key (id)
);

create table if not exists user_role
(
    user_id int,
    role_id int,

    constraint user_role_pk
        primary key (user_id, role_id),
    constraint user_role_roles_fk
        foreign key (role_id) references roles (id),
    constraint user_role_users_fk
        foreign key (user_id) references users (id)
);

create table if not exists comments
(
    id         int auto_increment,
    text       varchar(255),
    user_id    int not null,
    product_id int not null,
    comment_id int,

    constraint comment_pk
        primary key (id),
    constraint comments_users_fk
        foreign key (user_id) references users (id),
    constraint comments_products_fk
        foreign key (product_id) references products (id),
    constraint comments_comments_fk
        foreign key (comment_id) references comments (id)
);


create table if not exists orders
(
    id        int auto_increment,
    orderDate date,
    client_id int not null,

    constraint order_pk
        primary key (id),
    constraint orders_users_fk
        foreign key (client_id) references users (id)
);

create table if not exists order_items
(
    order_id   int not null,
    product_id int not null,
    quantity   int not null,

    constraint order_item_pk
        primary key (order_id, product_id),
    constraint order_items_orders_fk
        foreign key (order_id) references orders (id),
    constraint order_items_products_fk
        foreign key (product_id) references products (id)
);
