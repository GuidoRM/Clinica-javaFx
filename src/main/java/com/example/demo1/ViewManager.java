package com.example.demo1;

import com.example.demo1.controllers.AppointmentFormController;
import com.example.demo1.models.Appointment;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewManager {
  private static ViewManager instance;
  private Stage primaryStage;
  private Map<String, Scene> scenes = new HashMap<>();
  private Map<String, Object> controllers = new HashMap<>();
  
  // Singleton Pattern
  public ViewManager() throws IOException {
    loadView("main", "main-view.fxml");
    loadView("appointment", "appointment-view.fxml");
    loadView("appointment-form", "appointment-form.fxml");
    loadView("patients", "patient-view.fxml");
  }
  
  public static ViewManager getInstance() throws IOException {
    if (instance == null) {
      instance = new ViewManager();
    }
    return instance;
  }
  
  // Establecer el Stage principal
  public void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }
  
  // Método para cambiar entre vistas
  public void switchScene(String viewName) {
    Scene scene = scenes.get(viewName);
    Object controller = controllers.get(viewName);
    
    // Verificar si el controlador implementa ReloadableController y llamar a reloadData()
    if (controller instanceof ReloadableController) {
      ((ReloadableController) controller).reloadData();
    }
    
    if (scene != null) {
      primaryStage.setScene(scene);
      primaryStage.show();
    } else {
      System.out.println("No se encontró la vista: " + viewName);
    }
  }
  
  // Cargar vistas desde los archivos FXML y almacenar sus controladores
  public void loadView(String viewName, String fxmlFile) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
    Parent root = loader.load();
    
    Scene scene = new Scene(root);
    scenes.put(viewName, scene);
    
    // Guardar el controlador de la vista
    Object controller = loader.getController();
    controllers.put(viewName, controller);
  }
  
  public Object getController(String viewName) {
    return controllers.get(viewName);
  }
  public void openAppointmentForm(Appointment appointment) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/appointment-form.fxml"));
    Parent root = loader.load();
    AppointmentFormController formController = loader.getController();
    
    if (appointment != null) {
      formController.setAppointment(appointment); // Si estamos editando, pasamos la cita
    }
    
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
  
  
}
