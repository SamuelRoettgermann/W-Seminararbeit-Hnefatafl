package ARD_RI_TAFL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.geometry.*;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 03.11.2018
  * @author 
  */

public class ARD_RI_TAFL_GUI_REGELN extends Application {
  // Anfang Attribute
  private Button uebergangVorbereitung = new Button();
  private Stage primaryStage;
  // Ende Attribute
  
  public void start(Stage primaryStage) { 
    Pane root = new Pane();
    Scene scene = new Scene(root, 1264, 682);
    this.primaryStage = primaryStage;
    // Anfang Komponenten
    
    uebergangVorbereitung.setLayoutX(384);
    uebergangVorbereitung.setLayoutY(536);
    uebergangVorbereitung.setPrefHeight(105);
    uebergangVorbereitung.setPrefWidth(489);
    uebergangVorbereitung.setOnAction(
      (event) -> {uebergangVorbereitung_Action(event);} 
    );
    uebergangVorbereitung.setText("Verstanden!");
    uebergangVorbereitung.setAlignment(Pos.CENTER);
    uebergangVorbereitung.setFont(Font.font("Dialog", 55));
    root.getChildren().add(uebergangVorbereitung);
    // Ende Komponenten
    
    primaryStage.setOnCloseRequest(e -> System.exit(0));
    primaryStage.setTitle("ARD_RI_TAFL_GUI_REGELN");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    launch(args);
  }
  public void uebergangVorbereitung_Action(Event evt) {
    new ARD_RI_TAFL_GUI_VORBEREITUNG().start(new Stage());
    primaryStage.close();

  }

  // Ende Methoden
}
