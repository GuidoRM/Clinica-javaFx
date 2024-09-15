package com.example.demo1.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static final String URL = "jdbc:mysql://junction.proxy.rlwy.net:24760/railway?useSSL=false";
  private static final String USER = "root";
  private static final String PASSWORD = "DTglaNprsWvNTiTYlyBJBYxpnoyassAs";
  
  public static Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(URL, USER, PASSWORD);
      System.out.println("Conexión exitosa a la base de datos!");
    } catch (ClassNotFoundException e) {
      System.out.println("Driver JDBC no encontrado.");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println("Conexión fallida.");
      e.printStackTrace();
    }
    return connection;
  }
  
  public static void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
