package ARD_RI_TAFL;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
  private Label label1 = new Label();
  private Label label2 = new Label();
  private Label label3 = new Label();
  private Label label4 = new Label();
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
    label1.setLayoutX(0);
    label1.setLayoutY(24);
    label1.setPrefHeight(97);
    label1.setPrefWidth(1259);
    label1.setText("Ard Ri");
    label1.setContentDisplay(ContentDisplay.CENTER);
    label1.setAlignment(Pos.CENTER);
    label1.setUnderline(true);
    label1.setFont(Font.font("Dialog", 70));
    root.getChildren().add(label1);
    label2.setLayoutX(56);
    label2.setLayoutY(120);
    label2.setPrefHeight(89);
    label2.setPrefWidth(1099);
    label2.setText("Herzlich Willkommen zu Ard Ri!");
    label2.setAlignment(Pos.CENTER);
    label2.setFont(Font.font("Dialog", 40));
    root.getChildren().add(label2);
    label3.setLayoutX(64);
    label3.setLayoutY(200);
    label3.setPrefHeight(193);
    label3.setPrefWidth(1123);
    label3.setText("Falls Sie mit den Regeln von Ard Ri und/oder diesem Programm nicht vertraut sind finden Sie im gleichen Ordner in dem auch dieses Spiel gestartet haben eine PDF-Datei mit dem Namen Anleitung.pdf."+"\n"+"Sollten sie mit den Regeln und diesem Programm bereits vertraut sein gelangen Sie über den Verstanden-Knopf unten zur Spielvorbereitung.");
    label3.setWrapText(true);
    label3.setFont(Font.font("Dialog", 20));
    root.getChildren().add(label3);
    label4.setLayoutX(350);
    label4.setLayoutY(400);
    label4.setPrefHeight(129);
    label4.setPrefWidth(523);
    label4.setText("erstellt von: Samuel Röttgermann"+"\n"+"Veröffentlichungsdatum: 06.11.2018");
    label4.setAlignment(Pos.CENTER);
    label4.setFont(Font.font("Dialog", 20));
    root.getChildren().add(label4);
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

