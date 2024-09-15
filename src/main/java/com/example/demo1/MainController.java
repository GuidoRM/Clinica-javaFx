package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainController {
  @FXML
  private Label welcomeText;
  
  @FXML
  protected void onHelloButtonClick(ActionEvent event) throws IOException {
    // Cambiar a la vista de 'appointment'
    ViewManager.getInstance().switchScene("appointment");
  }
  
  @FXML
  protected void onHelloButtonClick2(ActionEvent event) throws IOException {
    // Cambiar a la vista de 'appointment'
    ViewManager.getInstance().switchScene("patients");
  }
}