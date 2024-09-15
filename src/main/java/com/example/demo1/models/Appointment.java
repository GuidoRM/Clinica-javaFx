package com.example.demo1.models;

public class Appointment {
  private int id;
  private String dateTime;
  private String reason;
  private String status;
  private int patientId;
  private int doctorId;
  
  public Appointment(int id, String dateTime, String reason, String status, int patientId, int doctorId) {
    this.id = id;
    this.dateTime = dateTime;
    this.reason = reason;
    this.status = status;
    this.patientId = patientId;
    this.doctorId = doctorId;
  }
  
  // Getters y Setters para cada campo
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getDateTime() {
    return dateTime;
  }
  
  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }
  
  public String getReason() {
    return reason;
  }
  
  public void setReason(String reason) {
    this.reason = reason;
  }
  
  public String getStatus() {
    return status;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public int getPatientId() {
    return patientId;
  }
  
  public void setPatientId(int patientId) {
    this.patientId = patientId;
  }
  
  public int getDoctorId() {
    return doctorId;
  }
  
  public void setDoctorId(int doctorId) {
    this.doctorId = doctorId;
  }
}
