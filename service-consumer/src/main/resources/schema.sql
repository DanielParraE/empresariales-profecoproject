DROP TABLE IF EXISTS tbl_consumers;

CREATE TABLE tbl_consumers (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  full_name VARCHAR(250) NOT NULL,
  rfc VARCHAR(15) NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  email VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS tbl_complains;

CREATE TABLE tbl_complains (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  reason VARCHAR(250) NOT NULL,
  description VARCHAR(200) NOT NULL,
  evidence VARCHAR(200) NOT NULL,
  date_created DATE NOT NULL,
  consumer_id BIGINT,
  market_id BIGINT NOT NULL,
  FOREIGN KEY (consumer_id) REFERENCES tbl_consumers(id)
);
