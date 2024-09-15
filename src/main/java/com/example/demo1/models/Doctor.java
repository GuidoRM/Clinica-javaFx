package com.example.demo1.models;

public class Doctor {
  private long id;
  private String fullName;
  private String specialty;
  private String licenseNumber;
  private int yearsOfExperience;
  private String consultationHours;
  private String officeLocation;
  
  // Constructor vacío
  public Doctor() {
  }
  
  // Constructor con todos los campos
  public Doctor(long id, String fullName, String specialty, String licenseNumber, int yearsOfExperience, String consultationHours, String officeLocation) {
    this.id = id;
    this.fullName = fullName;
    this.specialty = specialty;
    this.licenseNumber = licenseNumber;
    this.yearsOfExperience = yearsOfExperience;
    this.consultationHours = consultationHours;
    this.officeLocation = officeLocation;
  }
  
  // Getters y Setters
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getFullName() {
    return fullName;
  }
  
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  
  public String getSpecialty() {
    return specialty;
  }
  
  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }
  
  public String getLicenseNumber() {
    return licenseNumber;
  }
  
  public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }
  
  public int getYearsOfExperience() {
    return yearsOfExperience;
  }
  
  public void setYearsOfExperience(int yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience;
  }
  
  public String getConsultationHours() {
    return consultationHours;
  }
  
  public void setConsultationHours(String consultationHours) {
    this.consultationHours = consultationHours;
  }
  
  public String getOfficeLocation() {
    return officeLocation;
  }
  
  public void setOfficeLocation(String officeLocation) {
    this.officeLocation = officeLocation;
  }
}
