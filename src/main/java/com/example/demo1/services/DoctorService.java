package com.example.demo1.services;

import com.example.demo1.config.DatabaseConnection;
import com.example.demo1.models.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {
  
  public List<Doctor> getAllDoctors() {
    List<Doctor> doctors = new ArrayList<>();
    String query = "SELECT * FROM Doctor";
    
    try (Connection connection = DatabaseConnection.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {
      
      while (resultSet.next()) {
        Doctor doctor = new Doctor(
            resultSet.getLong("id"),
            resultSet.getString("fullName"),
            resultSet.getString("specialty"),
            resultSet.getString("licenseNumber"),
            resultSet.getInt("yearsOfExperience"),
            resultSet.getString("consultationHours"),
            resultSet.getString("officeLocation")
        );
        doctors.add(doctor);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return doctors;
  }
  
  public void insertDoctor(Doctor doctor) {
    String query = "INSERT INTO Doctor (fullName, specialty, licenseNumber, yearsOfExperience, consultationHours, officeLocation) VALUES (?, ?, ?, ?, ?, ?)";
    
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
      
      statement.setString(1, doctor.getFullName());
      statement.setString(2, doctor.getSpecialty());
      statement.setString(3, doctor.getLicenseNumber());
      statement.setInt(4, doctor.getYearsOfExperience());
      statement.setString(5, doctor.getConsultationHours());
      statement.setString(6, doctor.getOfficeLocation());
      statement.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  // Otras operaciones CRUD según necesites...
}
