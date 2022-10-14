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

UPDATE `sbi`.`transaction`
SET
`timestamp` = '2022-09-12 05:36:14.239000'
WHERE `id` = 3;

select * from transaction where id=1, transaction_with contains 'ABC';

-- search
select * from transaction t where t.transaction_with like '%ABC%';
select * from transaction t where t.transaction_with like '%%';
-- filter by date
-- start only
select * from transaction t where date_format(t.timestamp, '%Y-%m-%d')>='2022-08-01' AND t.account.accountId=1;
-- end only
select * from transaction t where date_format(t.timestamp, '%Y-%m-%d')<='2022-12-12' AND t.account.accountId=1;
-- start and end
select * from transaction t where date_format(t.timestamp, '%Y-%m-%d')>='2022-08-01' AND date_format(t.timestamp, '%Y-%m-%d')<='2022-12-12' AND t.account.accountId=1;
-- neither start nor end
select * from transaction t where date_format(t.timestamp, '%Y-%m-%d')>='1900-01-01' AND date_format(t.timestamp, '%Y-%m-%d')<='2022-12-14' AND t.account.accountId=1; /** less than current date */
-- sort by time
-- ascending
select * from transaction t order by timestamp;
-- descending
select * from transaction t order by timestamp desc;
-- sort by amount
-- ascending
select * from transaction t order by amount;
-- descending
select * from transaction t order by amount desc;
-- sort by transaction_with -- optional
-- ascending
select * from transaction t order by transaction_with;
-- descending
select * from transaction t order by transaction_with desc;


SET @search = '%%';/** add % characters to the original search string */
SET @orderby = 'type'; /** add descending if needed */ /** default orderby value must be timestamp */
SET @startdate= '2022-10-11';
SET @enddate= '2022-12-14';
SET @accountid = 3;
-- final JPA Queries
select * from transaction t where 
t.transaction_with like @search
and date_format(t.timestamp, '%Y-%m-%d')>=@startdate
and date_format(t.timestamp, '%Y-%m-%d')<=@enddate
and t.account_id=@accountid
order by type;

