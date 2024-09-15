package com.example.demo1.controllers;

import com.example.demo1.ReloadableController;
import com.example.demo1.ViewManager;
import com.example.demo1.models.Appointment;
import com.example.demo1.services.AppointmentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class AppointmentController implements ReloadableController {
  
  @FXML
  private TableView<Appointment> appointmentTable;
  
  @FXML
  private TableColumn<Appointment, Integer> columnId;
  
  @FXML
  private TableColumn<Appointment, String> columnDateTime;
  
  @FXML
  private TableColumn<Appointment, String> columnReason;
  
  @FXML
  private TableColumn<Appointment, String> columnStatus;
  
  @FXML
  private TableColumn<Appointment, Integer> columnPatientId;
  
  @FXML
  private TableColumn<Appointment, Integer> columnDoctorId;
  
  @FXML
  private Button addButton;
  
  @FXML
  private Button editButton;
  
  @FXML
  private Button deleteButton;
  
  // Instancia del servicio
  private AppointmentService appointmentService = new AppointmentService();
  private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
  
  @FXML
  public void initialize() {
    // Configurar las columnas con los campos correspondientes
    columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
    columnDateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
    columnReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
    columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    columnPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
    columnDoctorId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
    
    // Deshabilitar los botones de Editar y Eliminar inicialmente
    editButton.setDisable(true);
    deleteButton.setDisable(true);
    
    // Agregar listener a la selección de la tabla
    appointmentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      if (newSelection != null) {
        editButton.setDisable(false);
        deleteButton.setDisable(false);
      } else {
        editButton.setDisable(true);
        deleteButton.setDisable(true);
      }
    });
    
    // Cargar las citas
    loadAppointments();
  }
  
  // Cargar citas desde el servicio y ponerlas en la tabla
  public void loadAppointments() {
    List<Appointment> appointments = appointmentService.getAppointments();
    appointmentList.clear();
    appointmentList.addAll(appointments);
    appointmentTable.setItems(appointmentList);
  }
  
  
  // Método para agregar una nueva cita
  @FXML
  public void addAppointment() throws IOException {
    // Abrir el formulario de cita médica pasando null para indicar que es una nueva cita
    ViewManager.getInstance().openAppointmentForm(null); // Paso null para nueva cita
  }
  
  // Método para editar una cita seleccionada
  @FXML
  public void editAppointment() throws IOException {
    Appointment selected = appointmentTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      // Abrir el formulario de cita médica pasando la cita seleccionada para edición
      ViewManager.getInstance().openAppointmentForm(selected); // Paso la cita para edición
    }
  }
  
  
  
  // Método para eliminar una cita seleccionada
  @FXML
  public void deleteAppointment() throws IOException {
    Appointment selected = appointmentTable.getSelectionModel().getSelectedItem();
    if (selected != null) {
      appointmentService.deleteAppointment(selected.getId());
      appointmentList.remove(selected); // Eliminar de la tabla también
      
      //Para ver los cambios luego de eliminar
      ViewManager.getInstance().switchScene("appointment");
    }
  }
  
  @Override
  public void reloadData() {
    this.loadAppointments();
  }
}
