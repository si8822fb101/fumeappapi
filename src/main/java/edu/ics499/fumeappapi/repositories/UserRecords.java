package edu.ics499.fumeappapi.repositories;

import java.sql.*;

public class UserRecords {
    private Connection connect(){
        String url = "jdbc:sqlite:fume.sqlite";
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void insert(String username, String pin) throws SQLException {
        createNewTable();
        String sql = "INSERT INTO users_(user_name, pin) VALUES(?,?);";
        Connection conn = this.connect();
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setString(1,username);
        prepStmt.setString(2, pin);
        prepStmt.executeUpdate();

    }

    public String findByUserAndPin(String username, String pin) throws SQLException{
        createNewTable();
        String sql = "SELECT user_name, pin FROM users_ WHERE user_name = ? AND pin = ?;";
        String userInfo = "";
        Connection conn  = this.connect();
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setString(1,username);
        prepStmt.setString(2,pin);
        ResultSet res = prepStmt.executeQuery();

        while(res.next()){
            userInfo = res.getString("user_name") + res.getString("pin");
        }
        return userInfo;
    }

    public String findByUser(String username) throws SQLException{
        createNewTable();
        String sql = "SELECT user_name, pin FROM users_ WHERE user_name = ?;";
        String userInfo = "";
        Connection conn  = this.connect();
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setString(1,username);
        ResultSet res = prepStmt.executeQuery();

        while(res.next()){
            userInfo = res.getString("user_name");
        }
        return userInfo;
    }

    public void createNewTable () {
        String url = "jdbc:sqlite:C://sqlite/db/tests.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users_ ( user_name varchar(20) PRIMARY KEY, pin varchar(20) NOT NULL);";

        try (Connection conn  = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
