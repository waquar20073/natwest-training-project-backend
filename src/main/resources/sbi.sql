create database sbi;
use sbi;

CREATE TABLE `sbi`.`account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `api_key` VARCHAR(45) NOT NULL,
  `balance` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
  
INSERT INTO account(username,password,api_key,balance) VALUES('johny','123','afaosfjasffiowaf242@fio',2314.5);
INSERT INTO account(username,password,api_key,balance) VALUES('johny2','123','gsda51asffiowaf242@fio',23114.5);
INSERT INTO account(username,password,api_key,balance) VALUES('johny3','123','3trssfj2ffiowaf242@fio',25314.5);
SHOW TABLES;
DESC account;
DESC transaction;
DROP TABLE transaction;
DROP TABLE sbi.account;
SELECT * FROM account;
SELECT * FROM transaction;