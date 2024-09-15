package com.example.demo1.controllers;

import com.example.demo1.config.DatabaseConnection;
import com.example.demo1.models.Patient;
import com.example.demo1.models.Prescription;
import com.example.demo1.services.PatientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PatientController {
  
  @FXML
  private TableView<Patient> patientTable;
  
  @FXML
  private TableColumn<Patient, Long> columnId;
  
  @FXML
  private TableColumn<Patient, String> columnFullName;
  
  @FXML
  private TableColumn<Patient, String> columnBirthDate;
  
  @FXML
  private TableColumn<Patient, Boolean> columnGender;
  
  @FXML
  private TableColumn<Patient, String> columnAddress;
  
  @FXML
  private TableColumn<Patient, String> columnPhoneNumber;
  
  @FXML
  private TableColumn<Patient, String> columnBloodType;
  
  @FXML
  private TableColumn<Patient, String> columnInsuranceNumber;
  
  @FXML
  private TextField patientIdInput;
  
  @FXML
  private Label prescriptionCountLabel;
  
  @FXML
  private TableView<Prescription> prescriptionTable; // Tabla para mostrar los detalles de las recetas
  
  @FXML
  private TableColumn<Prescription, Long> prescriptionIdColumn;
  
  @FXML
  private TableColumn<Prescription, String> creationDateColumn;
  
  @FXML
  private TableColumn<Prescription, String> dosageColumn;
  
  @FXML
  private TableColumn<Prescription, String> frequencyColumn;
  
  @FXML
  private TableColumn<Prescription, String> treatmentDurationColumn;
  
  @FXML
  private TableColumn<Prescription, String> medicationNameColumn;
  
  private PatientService patientService = new PatientService();
  private ObservableList<Patient> patientList = FXCollections.observableArrayList();
  private ObservableList<Prescription> prescriptionList = FXCollections.observableArrayList(); // Lista de recetas
  
  @FXML
  public void initialize() {
    // Configurar las columnas de la tabla de pacientes
    columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
    columnFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    columnBloodType.setCellValueFactory(new PropertyValueFactory<>("bloodType"));
    columnInsuranceNumber.setCellValueFactory(new PropertyValueFactory<>("insuranceNumber"));
    
    
    // Configurar las columnas de la tabla de recetas
    prescriptionIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
    dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));
    frequencyColumn.setCellValueFactory(new PropertyValueFactory<>("frequency"));
    treatmentDurationColumn.setCellValueFactory(new PropertyValueFactory<>("treatmentDuration"));
    medicationNameColumn.setCellValueFactory(new PropertyValueFactory<>("medicationName"));
    
    // Cargar los pacientes en la tabla
    loadPatients();
  }
  
  private void loadPatients() {
    List<Patient> patients = patientService.getAllPatients();
    patientList.clear();
    patientList.addAll(patients);
    patientTable.setItems(patientList);
  }
  
  // Método para ejecutar el procedimiento almacenado y cargar las recetas
  @FXML
  private void getPrescriptionCount() {
    String input = patientIdInput.getText();
    if (input == null || input.isEmpty()) {
      prescriptionCountLabel.setText("Ingrese un ID válido");
      return;
    }
    
    long patientId = Long.parseLong(input);
    int prescriptionCount = executeProcedure(patientId);
    
    // Mostrar el número de recetas encontradas
    prescriptionCountLabel.setText("Cantidad de recetas: " + prescriptionCount);
    
    // Mostrar los detalles de las recetas en la tabla
    loadPrescriptions(patientId);
  }
  
  private int executeProcedure(long patientId) {
    int count = 0;
    Connection connection = null;
    CallableStatement callableStatement = null;
    
    try {
      connection = DatabaseConnection.getConnection();
      callableStatement = connection.prepareCall("{CALL GetAllPrescriptionsForAdultPatient(?)}");
      callableStatement.setLong(1, patientId);
      
      ResultSet resultSet = callableStatement.executeQuery();
      while (resultSet.next()) {
        count++; // Contar cuántas recetas fueron devueltas
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeConnection(connection);
    }
    
    return count;
  }
  
  
  
  private void loadPrescriptions(long patientId) {
    prescriptionList.clear(); // Limpiar la lista anterior
    Connection connection = null;
    CallableStatement callableStatement = null;
    
    try {
      connection = DatabaseConnection.getConnection();
      callableStatement = connection.prepareCall("{CALL GetAllPrescriptionsForAdultPatient(?)}");
      callableStatement.setLong(1, patientId);
      
      ResultSet resultSet = callableStatement.executeQuery();
      
      // Verificar si el ResultSet tiene columnas que indiquen un mensaje
      if (resultSet.next()) {
        try {
          // Intentar obtener el mensaje de la primera fila, si existe
          String message = resultSet.getString("mensaje");
          
          // Si existe la columna "mensaje", es que el paciente es menor de 18 años
          if (message != null) {
            // Lanzar alerta cuando el paciente es menor de edad
            showAlert("Paciente menor de edad", message);
            prescriptionCountLabel.setText(message); // Mostrar mensaje en la interfaz
            return; // Salir del método porque no hay más datos que procesar
          }
        } catch (SQLException e) {
          // Si no hay columna "mensaje", procesamos como recetas
          do {
            Prescription prescription = new Prescription(
                resultSet.getLong("prescription_id"),
                resultSet.getString("creationDate"),
                resultSet.getString("dosage"),
                resultSet.getString("frequency"),
                resultSet.getString("treatmentDuration"),
                resultSet.getString("medication_name")
            );
            prescriptionList.add(prescription); // Agregar la receta a la lista
          } while (resultSet.next()); // Continuar mientras haya más filas
          
          prescriptionTable.setItems(prescriptionList); // Mostrar la lista en la tabla
        }
      } else {
        // Si el resultSet está vacío
        System.out.println("No se encontraron recetas para el paciente con ID: " + patientId);
        prescriptionCountLabel.setText("No se encontraron recetas.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeConnection(connection);
    }
  }
  
  // Método para mostrar una alerta
  private void showAlert(String title, String message) {
    Alert alert = new Alert(AlertType.WARNING); // Tipo de alerta WARNING
    alert.setTitle(title);
    alert.setHeaderText(null); // Puedes poner un encabezado aquí si lo deseas
    alert.setContentText(message); // Mensaje de la alerta
    alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
  }
  
}
