package com.inf600c.demo.persistence;

import com.inf600c.demo.model.User;
import java.sql.*;

public class Database {
    private static Connection conn = null;

    private static void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/inf600c?" +
                            "user=debian-sys-maint&password=1234");
        } catch (SQLException ex) {
            // will handler later
        }
    }
    public static int create(User user) {
        if (conn == null) {
            connect();
        }
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (firstname, lastname) VALUES (?, ?)");
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.executeUpdate();

            Statement stmt2 = conn.createStatement();
            ResultSet rs = stmt2.executeQuery("SELECT id FROM users ORDER BY id DESC LIMIT 1");
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            // will handler later
        }
        return 0;
    }

    public static void update(User user) {
        if (conn == null) {
            connect();
        }
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET firstname = ?, lastname = ? WHERE id = ?");
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            // will handler later
        }
    }

    public static void delete(int id) {
        if (conn == null) {
            connect();
        }
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            // will handler later
        }
    }

    public static User getUser(int id) {
        if (conn == null) {
            connect();
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users where id = " + id);
            User user = null;
            while (rs.next()) {
                user = new User(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("id")
                        // rs.getString("img")
                        );
            }
            return user;
        } catch (Exception e) {
            // will handler later
        }

        return null;
    }
}
