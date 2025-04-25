CREATE TABLE IF NOT EXISTS transactions
(
    id          VARCHAR(36) PRIMARY KEY,
    amount      DECIMAL(19, 2),
    type        VARCHAR(50),
    description TEXT,
    timestamp   TIMESTAMP
);