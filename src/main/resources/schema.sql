DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS transactions;

CREATE TABLE IF NOT EXISTS accounts (
  account_id INT PRIMARY KEY,
  principal  DOUBLE
);

CREATE TABLE IF NOT EXISTS transactions (
  transaction_id   INT PRIMARY KEY,
  account_id       INT,
  transaction_type VARCHAR(6),
  timestamp DATETIME,
  amount           DOUBLE
);
