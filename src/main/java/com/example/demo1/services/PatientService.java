package com.example.demo1.services;

import com.example.demo1.config.DatabaseConnection;
import com.example.demo1.models.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientService {
  public List<Patient> getAllPatients() {
    List<Patient> patients = new ArrayList<>();
    String query = "SELECT * FROM Patient";
    
    try (Connection connection = DatabaseConnection.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {
      
      while (resultSet.next()) {
        Patient patient = new Patient(
            resultSet.getLong("id"),
            resultSet.getString("fullName"),
            resultSet.getDate("birthDate"),
            resultSet.getBoolean("gender"),
            resultSet.getString("address"),
            resultSet.getString("phoneNumber"),
            resultSet.getString("bloodType"),
            resultSet.getString("insuranceNumber")
        );
        patients.add(patient);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return patients;
  }
  
  public void insertPatient(Patient patient) {
    String query = "INSERT INTO Patient (fullName, birthDate, gender, address, phoneNumber, bloodType, insuranceNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
      
      statement.setString(1, patient.getFullName());
      statement.setDate(2, patient.getBirthDate());
      statement.setBoolean(3, patient.isGender());
      statement.setString(4, patient.getAddress());
      statement.setString(5, patient.getPhoneNumber());
      statement.setString(6, patient.getBloodType());
      statement.setString(7, patient.getInsuranceNumber());
      statement.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
