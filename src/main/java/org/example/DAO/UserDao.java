package org.example.DAO;

import org.example.Config.AppConfig;
import org.example.DatabaseConnector;
import org.example.Models.Domain;
import org.example.Models.Person;
import org.example.Models.PersonDto;
import org.example.Models.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDao {

    public User getUserByUsername(String username) {
        User user = null;

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {

            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String retrievedUsername = resultSet.getString("username");
                    String retrievedPassword = resultSet.getString("password");
                    user = new User(retrievedUsername, retrievedPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}