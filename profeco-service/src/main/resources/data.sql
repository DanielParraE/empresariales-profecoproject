delete from markets;
insert into markets(id, name, rfc, image, web_page) values (1, 'Walmart',  'WALM420112PP1', 'http://localhost:8091/files/abc-market.png', 'http://walmart.com');
insert into markets(id, name, rfc, image, web_page) values (2, 'Soriana',  'SORI420112PP1', 'http://localhost:8091/files/abc-market.png', 'http://soriana.com');

delete from products;
insert into products(id, name, description, image) values (1, 'pepsi', 'refresco copia de coca cola', 'http://localhost:8091/files/abc-product.png');
insert into products(id, name, description, image) values (2, 'coca cola', 'refresco original', 'http://localhost:8091/files/abc-product.png');
insert into products(id, name, description, image) values (3, 'big cola', 'refresco patito', 'http://localhost:8091/files/abc-product.png');

--delete from rel_marketsproducts;
--insert into rel_marketsproducts(marketproduct_id, market_id, product_id, price) values (1, 1, 1, 50.5);
--insert into rel_marketsproducts(marketproduct_id, market_id, product_id, price) values (2, 1, 2, 100.5);
--insert into rel_marketsproducts(marketproduct_id, market_id, product_id, price) values (3, 2, 1, 55.5);
--insert into rel_marketsproducts(marketproduct_id, market_id, product_id, price) values (4, 2, 2, 105.5);
--insert into rel_marketsproducts(marketproduct_id, market_id, product_id, price) values (5, 1, 3, 115.5);

DELETE FROM inconsistencies;

insert into inconsistencies(id, real_price, published_price, evidence, description, purchased_at, consumer_id, product_id, market_id) values (1, 100, 50, 'evidencia', 'Me negaron el precio que decia ahi porque se supone que estaba mal.','2018-09-05',1, 1, 1);
insert into inconsistencies(id, real_price, published_price, evidence, description, purchased_at, consumer_id, product_id, market_id) values (2, 200, 50, 'evidencia', 'valia el doble de lo publicado','2019-09-05',2, 2, 1);

--DELETE FROM tbl_markets
--
--INSERT INTO tbl_markets (id, name, rfc, web_page) VALUES (1, 'Walmart', '1234567891123', 'example1.com')
--INSERT INTO tbl_markets (id, name, rfc, web_page) VALUES (2, 'MWC', '1234567891124', 'example2.com')
--INSERT INTO tbl_markets (id, name, rfc, web_page) VALUES (3, 'Ley', '1234567891125', 'example3.com')