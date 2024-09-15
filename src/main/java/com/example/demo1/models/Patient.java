package com.example.demo1.models;

import java.sql.Date;

public class Patient {
  private long id;
  private String fullName;
  private Date birthDate;
  private boolean gender;
  private String address;
  private String phoneNumber;
  private String bloodType;
  private String insuranceNumber;
  
  // Constructor vacío
  public Patient() {
  }
  
  // Constructor con todos los campos
  public Patient(long id, String fullName, Date birthDate, boolean gender, String address, String phoneNumber, String bloodType, String insuranceNumber) {
    this.id = id;
    this.fullName = fullName;
    this.birthDate = birthDate;
    this.gender = gender;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.bloodType = bloodType;
    this.insuranceNumber = insuranceNumber;
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
  
  public Date getBirthDate() {
    return birthDate;
  }
  
  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }
  
  public boolean isGender() {
    return gender;
  }
  
  public void setGender(boolean gender) {
    this.gender = gender;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  public String getBloodType() {
    return bloodType;
  }
  
  public void setBloodType(String bloodType) {
    this.bloodType = bloodType;
  }
  
  public String getInsuranceNumber() {
    return insuranceNumber;
  }
  
  public void setInsuranceNumber(String insuranceNumber) {
    this.insuranceNumber = insuranceNumber;
  }
}
