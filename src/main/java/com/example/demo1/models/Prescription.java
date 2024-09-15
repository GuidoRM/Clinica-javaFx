package com.example.demo1.models;

public class Prescription {
  private long id;
  private String creationDate;
  private String dosage;
  private String frequency;
  private String treatmentDuration;
  private String medicationName;
  
  // Constructor con todos los campos
  public Prescription(long id, String creationDate, String dosage, String frequency, String treatmentDuration, String medicationName) {
    this.id = id;
    this.creationDate = creationDate;
    this.dosage = dosage;
    this.frequency = frequency;
    this.treatmentDuration = treatmentDuration;
    this.medicationName = medicationName;
  }
  
  // Getters y Setters
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getCreationDate() {
    return creationDate;
  }
  
  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }
  
  public String getDosage() {
    return dosage;
  }
  
  public void setDosage(String dosage) {
    this.dosage = dosage;
  }
  
  public String getFrequency() {
    return frequency;
  }
  
  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }
  
  public String getTreatmentDuration() {
    return treatmentDuration;
  }
  
  public void setTreatmentDuration(String treatmentDuration) {
    this.treatmentDuration = treatmentDuration;
  }
  
  public String getMedicationName() {
    return medicationName;
  }
  
  public void setMedicationName(String medicationName) {
    this.medicationName = medicationName;
  }
}
