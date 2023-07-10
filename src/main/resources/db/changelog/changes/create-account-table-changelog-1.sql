CREATE TABLE account (
    id INT AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    identifier BIGINT,
    passport_number BIGINT,
    annual_income DECIMAL(10, 2),
    available_balance DECIMAL(10, 2),
    credit_balance DECIMAL(10, 2),
    deposit_balance DECIMAL(10, 2),
    credit_limit DECIMAL(10, 2),
    primary key (id)
);