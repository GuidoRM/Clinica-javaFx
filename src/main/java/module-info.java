module com.example.demo1 {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;
  
  opens com.example.demo1.models to javafx.base;
  opens com.example.demo1.controllers to javafx.fxml;
  opens com.example.demo1 to javafx.fxml;  // Añadir esta línea
  
  exports com.example.demo1;
  exports com.example.demo1.controllers;
}
