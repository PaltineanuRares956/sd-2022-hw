CREATE TABLE {DB}.`Transaction` ( `id` BIGINT NOT NULL AUTO_INCREMENT , `sum` FLOAT NOT NULL , `date` DATE NOT NULL , `sender_account_id` BIGINT NOT NULL , `receiver_account_id` BIGINT NOT NULL , PRIMARY KEY (`id`), INDEX (`sender_account_id`), INDEX (`receiver_account_id`)) ENGINE = InnoDB;