DELETE FROM tbl_consumers

INSERT INTO tbl_consumers (id, full_name, rfc, phone_number, email) VALUES (1, 'Juan', '1234567891123', '6449879878', 'example1@gmail.com')
INSERT INTO tbl_consumers (id, full_name, rfc, phone_number, email) VALUES (2, 'Marisol', '1234567891124', '6559873216', 'example2@gmail.com')
INSERT INTO tbl_consumers (id, full_name, rfc, phone_number, email) VALUES (3, 'Beni', '1234567891125', '6777419638', 'example3@gmail.com')

DELETE FROM tbl_complains

INSERT INTO tbl_complains (id, reason, description, evidence, date_created, consumer_id, market_id) VALUES (1, 'Malos precios', 'Me negaron el precio que decia ahi porque se supone que estaba mal.', 'Bruh', '2022-04-08', 1, 1)
