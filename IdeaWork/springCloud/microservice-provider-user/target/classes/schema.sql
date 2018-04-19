DROP TABLE USER IF EXISTS;
CREATE TABLE user (
  id       BIGINT GENERATED BY DEFAULT AS IDENTITY,
  username VARCHAR(40),
  name     VARCHAR(20),
  age      INT(3),
  balance  DECIMAL(10, 2),
  PRIMARY KEY (id)
);