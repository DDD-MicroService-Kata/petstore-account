CREATE TABLE `user` (
  `id`                    BIGINT             NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `first_name`         VARCHAR(20)  NOT NULL,
  `last_name`             VARCHAR(100)       NOT NULL,
  `phone_number`       VARCHAR(11),
  `email_address`     VARCHAR(30)
);