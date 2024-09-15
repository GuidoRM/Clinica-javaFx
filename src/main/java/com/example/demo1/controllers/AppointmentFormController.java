package com.example.demo1.controllers;

import com.example.demo1.ViewManager;
import com.example.demo1.models.Appointment;
import com.example.demo1.models.Doctor;
import com.example.demo1.models.Patient;
import com.example.demo1.services.AppointmentService;
import com.example.demo1.services.DoctorService;
import com.example.demo1.services.PatientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class AppointmentFormController {
  
  @FXML
  private ComboBox<Patient> patientComboBox;  // Menú desplegable para pacientes
  
  @FXML
  private ComboBox<Doctor> doctorComboBox;    // Menú desplegable para doctores
  
  @FXML
  private TextField reasonField;
  
  @FXML
  private TextField statusField;
  
  @FXML
  private TextField dateTimeField;
  
  @FXML
  private Button saveButton;
  
  private AppointmentService appointmentService = new AppointmentService();
  private PatientService patientService = new PatientService();
  private DoctorService doctorService = new DoctorService();
  
  private Appointment currentAppointment; // Para saber si estamos editando o creando
  
  @FXML
  public void initialize() {
    // Cargar pacientes y doctores en los ComboBox
    loadPatients();
    loadDoctors();
    
    // Configurar el CellFactory para mostrar el fullName en lugar del objeto completo
    patientComboBox.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
      @Override
      protected void updateItem(Patient patient, boolean empty) {
        super.updateItem(patient, empty);
        setText(empty ? "" : patient.getFullName());
      }
    });
    patientComboBox.setButtonCell(new javafx.scene.control.ListCell<>() {
      @Override
      protected void updateItem(Patient patient, boolean empty) {
        super.updateItem(patient, empty);
        setText(empty ? "" : patient.getFullName());
      }
    });
    
    doctorComboBox.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
      @Override
      protected void updateItem(Doctor doctor, boolean empty) {
        super.updateItem(doctor, empty);
        setText(empty ? "" : doctor.getFullName());
      }
    });
    doctorComboBox.setButtonCell(new javafx.scene.control.ListCell<>() {
      @Override
      protected void updateItem(Doctor doctor, boolean empty) {
        super.updateItem(doctor, empty);
        setText(empty ? "" : doctor.getFullName());
      }
    });
  }
  
  // Método para inicializar el formulario con los datos de la cita si se está editando
  public void setAppointment(Appointment appointment) {
    this.currentAppointment = appointment;
    
    if (appointment != null) {
      // Seleccionar el paciente y doctor en los ComboBox
      patientComboBox.getSelectionModel().select(findPatientById(appointment.getPatientId()));
      doctorComboBox.getSelectionModel().select(findDoctorById(appointment.getDoctorId()));
      reasonField.setText(appointment.getReason());
      statusField.setText(appointment.getStatus());
      dateTimeField.setText(appointment.getDateTime().toString());
    }
  }
  
  // Método para cargar pacientes en el ComboBox
  private void loadPatients() {
    List<Patient> patients = patientService.getAllPatients();
    ObservableList<Patient> patientList = FXCollections.observableArrayList(patients);
    patientComboBox.setItems(patientList);
  }
  
  // Método para cargar doctores en el ComboBox
  private void loadDoctors() {
    List<Doctor> doctors = doctorService.getAllDoctors();
    ObservableList<Doctor> doctorList = FXCollections.observableArrayList(doctors);
    doctorComboBox.setItems(doctorList);
  }
  
  // Buscar un paciente por su ID (para seleccionar en ComboBox)
  private Patient findPatientById(long patientId) {
    for (Patient patient : patientComboBox.getItems()) {
      if (patient.getId() == patientId) {
        return patient;
      }
    }
    return null;
  }
  
  // Buscar un doctor por su ID (para seleccionar en ComboBox)
  private Doctor findDoctorById(long doctorId) {
    for (Doctor doctor : doctorComboBox.getItems()) {
      if (doctor.getId() == doctorId) {
        return doctor;
      }
    }
    return null;
  }
  
  // Este método guarda o actualiza la cita
  @FXML
  public void saveAppointment() throws IOException {
    Patient selectedPatient = patientComboBox.getSelectionModel().getSelectedItem();
    Doctor selectedDoctor = doctorComboBox.getSelectionModel().getSelectedItem();
    String reason = reasonField.getText();
    String status = statusField.getText();
    Timestamp dateTime = Timestamp.valueOf(dateTimeField.getText());
    
    if (selectedPatient == null || selectedDoctor == null) {
      System.out.println("Paciente o Doctor no seleccionados.");
      return; // No permitir guardar sin seleccionar
    }
    
    if (currentAppointment == null) {
      // Crear nueva cita
      appointmentService.insertAppointment(dateTime, reason, status, selectedPatient.getId(), selectedDoctor.getId());
    } else {
      // Actualizar cita existente
      appointmentService.updateAppointment(currentAppointment.getId(), reason, status);
    }
    
    // Cerrar el formulario al guardar o editar
    closeForm();
  }
  
  // Cerrar el formulario
  private void closeForm() throws IOException {
    ViewManager.getInstance().switchScene("appointment");
  }
  
  // Limpiar el formulario
  public void clearForm() {
    patientComboBox.getSelectionModel().clearSelection();
    doctorComboBox.getSelectionModel().clearSelection();
    reasonField.clear();
    statusField.clear();
    dateTimeField.clear();
  }
}
