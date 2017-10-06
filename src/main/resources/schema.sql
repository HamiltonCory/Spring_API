DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS transactions;

CREATE TABLE accounts (
  accountId INT PRIMARY KEY,
  principal  DOUBLE
);

CREATE TABLE transactions (
  transactionId   INT PRIMARY KEY,
  accountId       INT,
  transactionType VARCHAR(8),
  timestamp VARCHAR(20),
  amount           DOUBLE
);
