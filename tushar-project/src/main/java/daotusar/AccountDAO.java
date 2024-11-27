package daotusar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Account;

public class AccountDAO {
    private Connection connection;

    public AccountDAO(Connection connection) {
        this.connection = connection;
    }

    // Create Account
    public void createAccount(Account account) throws SQLException {
        String query = "INSERT INTO accounts (user_id, account_type, balance, currency) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, account.getUserId());
            statement.setString(2, account.getAccountType());
            statement.setDouble(3, account.getBalance());
            statement.setString(4, account.getCurrency());
            statement.executeUpdate();
        }
    }

    // Get Account by ID
    public Account getAccountById(int accountId) throws SQLException {
        String query = "SELECT * FROM accounts WHERE account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getInt("account_id"));
                account.setUserId(resultSet.getInt("user_id"));
                account.setAccountType(resultSet.getString("account_type"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setCurrency(resultSet.getString("currency"));
                account.setCreatedAt(resultSet.getTimestamp("created_at"));
                return account;
            }
        }
        return null;
    }

    // Get Accounts by User
    public List<Account> getAccountsByUserId(int userId) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM accounts WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getInt("account_id"));
                account.setUserId(resultSet.getInt("user_id"));
                account.setAccountType(resultSet.getString("account_type"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setCurrency(resultSet.getString("currency"));
                account.setCreatedAt(resultSet.getTimestamp("created_at"));
                accounts.add(account);
            }
        }
        return accounts;
    }

    // Update Account Balance
    public void updateAccountBalance(int accountId, double newBalance) throws SQLException {
        String query = "UPDATE accounts SET balance = ? WHERE account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, newBalance);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        }
    }
}
