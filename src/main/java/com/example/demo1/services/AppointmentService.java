package com.example.demo1.services;

import com.example.demo1.config.DatabaseConnection;
import com.example.demo1.models.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
  
  // Insertar cita médica
  public void insertAppointment(Timestamp dateTime, String reason, String status, long patientId, long doctorId) {
    String sql = "INSERT INTO Appointment (dateTime, reason, status, patientId, doctorId) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      //pstmt.setInt(1, id);
      pstmt.setTimestamp(1, dateTime);
      pstmt.setString(2, reason);
      pstmt.setString(3, status);
      pstmt.setLong(4, patientId);
      pstmt.setLong(5, doctorId);
      pstmt.executeUpdate();
      System.out.println("Cita médica insertada exitosamente.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  // Leer todas las citas médicas
  // Leer todas las citas médicas y retornarlas como una lista
  public List<Appointment> getAppointments() {
    List<Appointment> appointmentList = new ArrayList<>();
    String sql = "SELECT * FROM Appointment";
    try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
      
      while (rs.next()) {
        int id = rs.getInt("id");
        Timestamp dateTime = rs.getTimestamp("dateTime");
        String reason = rs.getString("reason");
        String status = rs.getString("status");
        int patientId = rs.getInt("patientId");
        int doctorId = rs.getInt("doctorId");
        
        // Crear una nueva instancia de Appointment y agregarla a la lista
        Appointment appointment = new Appointment(id, dateTime.toString(), reason, status, patientId, doctorId);
        appointmentList.add(appointment);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return appointmentList; // Retorna la lista de citas médicas
  }
  
  
  // Actualizar cita médica
  public void updateAppointment(int id, String newReason, String newStatus) {
    String sql = "UPDATE Appointment SET reason = ?, status = ? WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, newReason);
      pstmt.setString(2, newStatus);
      pstmt.setInt(3, id);
      pstmt.executeUpdate();
      System.out.println("Cita médica actualizada exitosamente.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  // Eliminar cita médica
  public void deleteAppointment(int id) {
    String sql = "DELETE FROM Appointment WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
      System.out.println("Cita médica eliminada exitosamente.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
