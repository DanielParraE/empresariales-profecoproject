DELETE FROM consumers;

INSERT INTO consumers (id, full_name, rfc, phone_number, email, image) VALUES (1, 'Juan', 'JULM8305281A9', '6449879878', 'juan@gmail.com', 'http://localhost:8091/files/abc-person.jpg');
INSERT INTO consumers (id, full_name, rfc, phone_number, email, image) VALUES (2, 'Marisol', 'MALM8305281A9', '6559873216', 'marisol@gmail.com', 'http://localhost:8091/files/abc-person.jpg');
INSERT INTO consumers (id, full_name, rfc, phone_number, email, image) VALUES (3, 'Beni', 'BELM8305281A9', '6777419638', 'beni@gmail.com', 'http://localhost:8091/files/abc-person.jpg');

delete from markets;
insert into markets(id, name, rfc, image, web_page) values (1, 'Walmart',  'WALM420112PP1', 'http://localhost:8091/files/abc-market.png', 'http://walmart.com');
insert into markets(id, name, rfc, image, web_page) values (2, 'Soriana',  'SORI420112PP1', 'http://localhost:8091/files/abc-market.png', 'http://soriana.com');

delete from addresses;
insert into addresses(id, post_code, city, street, market_id) values(1, '85900', 'Obregón', 'calle 200', 1);
insert into addresses(id, post_code, city, street, market_id) values(2, '85901', 'Obregón', 'Antonio Caso', 1);
insert into addresses(id, post_code, city, street, market_id) values(3, '85902', 'Obregón', 'Laguna del Nainari', 2);


delete from categories;
insert into categories(id, name, description) values (1, 'bebidas', 'bebidas azucaradas');

delete from products;
insert into products(id, name, description, category_id, image) values (1, 'pepsi', 'refresco copia de coca cola', 1, 'http://localhost:8091/files/abc-product.png');
insert into products(id, name, description, category_id, image) values (2, 'coca cola', 'refresco original', 1, 'http://localhost:8091/files/abc-product.png');
insert into products(id, name, description, category_id, image) values (3, 'big cola', 'refresco patito', 1, 'http://localhost:8091/files/abc-product.png');

delete from rel_marketsproducts;
insert into rel_marketsproducts(marketproduct_id, market_id, product_id, price) values (1, 1, 1, 50.5);
insert into rel_marketsproducts(marketproduct_id, market_id, product_id, price) values (2, 1, 2, 100.5);
insert into rel_marketsproducts(marketproduct_id, market_id, product_id, price) values (3, 2, 1, 55.5);
insert into rel_marketsproducts(marketproduct_id, market_id, product_id, price) values (4, 2, 2, 105.5);
insert into rel_marketsproducts(marketproduct_id, market_id, product_id, price) values (5, 1, 3, 115.5);

delete from product_review;
insert into product_review(id, market_product_id, comment, rating, consumer_id) values (1, 1, 'excelente', 5.0, 1);
insert into product_review(id, market_product_id, comment, rating, consumer_id) values (2, 1, 'Regular', 3.2, 2);

delete from market_review;
insert into market_review(id, market_id, comment, rating, consumer_id, created_at) values (3, 1, 'excelente lugar', 5.0, 1, '2019-09-05');
insert into market_review(id, market_id, comment, rating, consumer_id, created_at) values (4, 1, 'lugar pequeño', 3.2, 2, '2020-10-01');

DELETE FROM inconsistencies;

insert into inconsistencies(id, real_price, published_price, evidence, description, purchased_at, consumer_id, market_product_id) values (1, 100, 50, 'evidencia', 'Me negaron el precio que decia ahi porque se supone que estaba mal.','2018-09-05',1, 1);
insert into inconsistencies(id, real_price, published_price, evidence, description, purchased_at, consumer_id, market_product_id) values (2, 200, 50, 'evidencia', 'valia el doble de lo publicado','2019-09-05',2, 2);