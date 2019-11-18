INSERT INTO categories(name) values ('bike');
INSERT INTO categories(name) values ('car');

INSERT INTO products(model, price) values ('Giant', 4000.20);
INSERT INTO products(model, price) values ('BMV', 41000);
INSERT INTO products(model, price) values ('Audi', 53000.0);

INSERT INTO product_category(product_id, category_id) values (1, 1);
INSERT INTO product_category(product_id, category_id) values (2, 2);
INSERT INTO product_category(product_id, category_id) values (3, 2);