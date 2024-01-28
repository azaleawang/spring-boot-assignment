DROP TABLE IF EXISTS currency;

-- TODO code should be unique
CREATE TABLE currency (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL ,
    zh_code VARCHAR(255) NOT NULL
);
