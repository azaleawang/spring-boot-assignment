DROP TABLE IF EXISTS currency;

CREATE TABLE currency
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    code    VARCHAR(8),
    zh_code VARCHAR(8)
);
