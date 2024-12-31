package daotusar;

import java.sql.*;
import model.User;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Create User
    public void createUser(User user) throws SQLException {
        String query = "INSERT INTO users (name, email, password_hash) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.executeUpdate();
        }
    }

    // Get User by ID
    public User getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));
                return user;
            }
        }
        return null;
    }

    // Get User by Email
    public User getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));
                return user;
            }
        }
        return null;
    }

    // Delete User
    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }
}
