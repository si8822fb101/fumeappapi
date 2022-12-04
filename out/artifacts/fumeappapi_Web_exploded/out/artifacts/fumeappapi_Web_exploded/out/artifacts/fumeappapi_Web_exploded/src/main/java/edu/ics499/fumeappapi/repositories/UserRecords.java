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
        String sql = "INSERT INTO users_(user_name, pin) VALUES(?,?)";
        Connection conn = this.connect();
        PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setString(1,username);
        prepStmt.setString(2, pin);
        prepStmt.executeUpdate();

    }

    public String find(String username, String pin) throws SQLException{
        String sql = "SELECT user_name, pin FROM users_ WHERE user_name = ? AND pin = ?";
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

}
