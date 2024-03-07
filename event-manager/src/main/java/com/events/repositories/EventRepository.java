package com.events.repositories;

import com.events.models.Event;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {
    private static final String JDBC_URL = "jdbc:h2:./src/main/resources/db/events";

    public static void createTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS events (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "address VARCHAR(255)," +
                    "category VARCHAR(255)," +
                    "dateTime TIMESTAMP," +
                    "description VARCHAR(1000))";
            statement.executeUpdate(sql);
            System.out.println("Success creating table events");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean tableExists() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             ResultSet resultSet = connection.getMetaData().getTables(null, null, "EVENTS", null)) {
            var result = resultSet.next();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertEvent(Event event) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO events (name, address, category, dateTime, description) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getAddress());
            preparedStatement.setString(3, event.getCategory());
            preparedStatement.setObject(4, event.getDateTime());
            preparedStatement.setString(5, event.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM events ORDER BY DATETIME DESC")) {
            while (resultSet.next()) {
                Event event = new Event(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("address"),
                        resultSet.getString("category"),
                        resultSet.getObject("dateTime", LocalDateTime.class)
                );
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public static void updateEvent(Event event) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE events SET name = ?, address = ?, category = ?, dateTime = ?, description = ? WHERE id = ?")) {
            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getAddress());
            preparedStatement.setString(3, event.getCategory());
            preparedStatement.setObject(4, event.getDateTime());
            preparedStatement.setString(5, event.getDescription());
            preparedStatement.setInt(6, event.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEvent(int eventId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM events WHERE id = ?")) {
            preparedStatement.setInt(1, eventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
