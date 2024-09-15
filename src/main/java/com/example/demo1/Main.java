package com.example.demo1;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    // Inicializar las dimensiones del Stage
    primaryStage.setWidth(800);
    primaryStage.setHeight(600);
    
    // Obtener la instancia del ViewManager y configurar el Stage principal
    ViewManager viewManager = ViewManager.getInstance();
    viewManager.setPrimaryStage(primaryStage);
    
    // Cambiar a la vista principal (puedes cambiar la vista aquí si es necesario)
    viewManager.switchScene("main");
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
