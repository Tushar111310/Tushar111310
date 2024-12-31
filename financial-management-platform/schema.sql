CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    account_type VARCHAR(50), -- e.g., 'checking', 'savings', 'credit_card'
    balance DECIMAL(15, 2) DEFAULT 0.00,
    currency VARCHAR(10) DEFAULT 'USD',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    transaction_type VARCHAR(50), -- e.g., 'deposit', 'withdrawal', 'transfer'
    amount DECIMAL(15, 2),
    description TEXT,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);
CREATE TABLE budgets (
    budget_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    category VARCHAR(100), -- e.g., 'food', 'transportation', 'entertainment'
    budget_amount DECIMAL(15, 2),
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
CREATE TABLE goals (
    goal_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    goal_name VARCHAR(255),
    target_amount DECIMAL(15, 2),
    current_amount DECIMAL(15, 2) DEFAULT 0.00,
    target_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
