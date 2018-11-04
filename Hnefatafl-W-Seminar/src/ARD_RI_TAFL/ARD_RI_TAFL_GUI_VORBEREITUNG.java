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

public class ARD_RI_TAFL_GUI_VORBEREITUNG extends Application {
  // Anfang Attribute
  private Label ueberschriftRusse = new Label();
  private Label ueberschriftSchwede = new Label();
  private Button auswahlRusseMensch = new Button();
  private Button auswahlRusseSchwer = new Button();
  private Button auswahlRusseEinfach = new Button();
  private Button auswahlSchwedeMensch = new Button();
  private Button auswahlSchwedeSchwer = new Button();
  private Button auswahlSchwedeEinfach = new Button();
  private Button uebergangSpiel = new Button();
  private Stage primaryStage;
  private String spielerRusse;
  private String spielerSchwede;
  
  private Button spielBeenden = new Button();
  // Ende Attribute
  
  public void start(Stage primaryStage) { 
    Pane root = new Pane();
    Scene scene = new Scene(root, 1264, 682);
    this.primaryStage = primaryStage;
    this.spielerRusse = "";
    this.spielerSchwede = "";
    // Anfang Komponenten
    
    ueberschriftRusse.setLayoutX(0);
    ueberschriftRusse.setLayoutY(28);
    ueberschriftRusse.setPrefHeight(105);
    ueberschriftRusse.setPrefWidth(640);
    ueberschriftRusse.setText("Russen (Jäger)");
    ueberschriftRusse.setFont(Font.font("Dialog", 50));
    ueberschriftRusse.setAlignment(Pos.CENTER);
    root.getChildren().add(ueberschriftRusse);
    ueberschriftSchwede.setLayoutX(640);
    ueberschriftSchwede.setLayoutY(25);
    ueberschriftSchwede.setPrefHeight(105);
    ueberschriftSchwede.setPrefWidth(640);
    ueberschriftSchwede.setText("Schweden (Gejagte)");
    ueberschriftSchwede.setAlignment(Pos.CENTER);
    ueberschriftSchwede.setFont(Font.font("Dialog", 50));
    root.getChildren().add(ueberschriftSchwede);
    auswahlRusseMensch.setLayoutX(48);
    auswahlRusseMensch.setLayoutY(152);
    auswahlRusseMensch.setPrefHeight(105);
    auswahlRusseMensch.setPrefWidth(537);
    auswahlRusseMensch.setOnAction(
      (event) -> {auswahlRusseMensch_Action(event);} 
    );
    auswahlRusseMensch.setFont(Font.font("Dialog", 35));
    auswahlRusseMensch.setText("Mensch");
    auswahlRusseMensch.setTextAlignment(TextAlignment.CENTER);
    auswahlRusseMensch.setAlignment(Pos.CENTER);
    root.getChildren().add(auswahlRusseMensch);
    auswahlRusseSchwer.setLayoutX(48);
    auswahlRusseSchwer.setLayoutY(408);
    auswahlRusseSchwer.setPrefHeight(105);
    auswahlRusseSchwer.setPrefWidth(537);
    auswahlRusseSchwer.setOnAction(
      (event) -> {auswahlRusseSchwer_Action(event);} 
    );
    auswahlRusseSchwer.setText("Schwer");
    auswahlRusseSchwer.setFont(Font.font("Dialog", 35));
    auswahlRusseSchwer.setAlignment(Pos.CENTER);
    root.getChildren().add(auswahlRusseSchwer);
    auswahlRusseEinfach.setLayoutX(48);
    auswahlRusseEinfach.setLayoutY(280);
    auswahlRusseEinfach.setPrefHeight(105);
    auswahlRusseEinfach.setPrefWidth(537);
    auswahlRusseEinfach.setOnAction(
      (event) -> {auswahlRusseEinfach_Action(event);} 
    );
    auswahlRusseEinfach.setText("Einfach");
    auswahlRusseEinfach.setFont(Font.font("Dialog", 35));
    auswahlRusseEinfach.setAlignment(Pos.CENTER);
    root.getChildren().add(auswahlRusseEinfach);
    auswahlSchwedeMensch.setLayoutX(680);
    auswahlSchwedeMensch.setLayoutY(152);
    auswahlSchwedeMensch.setPrefHeight(105);
    auswahlSchwedeMensch.setPrefWidth(537);
    auswahlSchwedeMensch.setOnAction(
      (event) -> {auswahlSchwedeMensch_Action(event);} 
    );
    auswahlSchwedeMensch.setText("Mensch");
    auswahlSchwedeMensch.setFont(Font.font("Dialog", 35));
    auswahlSchwedeMensch.setAlignment(Pos.CENTER);
    root.getChildren().add(auswahlSchwedeMensch);
    auswahlSchwedeSchwer.setLayoutX(680);
    auswahlSchwedeSchwer.setLayoutY(408);
    auswahlSchwedeSchwer.setPrefHeight(105);
    auswahlSchwedeSchwer.setPrefWidth(537);
    auswahlSchwedeSchwer.setOnAction(
      (event) -> {auswahlSchwedeSchwer_Action(event);} 
    );
    auswahlSchwedeSchwer.setText("Schwer");
    auswahlSchwedeSchwer.setFont(Font.font("Dialog", 35));
    auswahlSchwedeSchwer.setAlignment(Pos.CENTER);
    root.getChildren().add(auswahlSchwedeSchwer);
    auswahlSchwedeEinfach.setLayoutX(680);
    auswahlSchwedeEinfach.setLayoutY(280);
    auswahlSchwedeEinfach.setPrefHeight(105);
    auswahlSchwedeEinfach.setPrefWidth(537);
    auswahlSchwedeEinfach.setOnAction(
      (event) -> {auswahlSchwedeEinfach_Action(event);} 
    );
    auswahlSchwedeEinfach.setText("Einfach");
    auswahlSchwedeEinfach.setFont(Font.font("Dialog", 35));
    auswahlSchwedeEinfach.setAlignment(Pos.CENTER);
    root.getChildren().add(auswahlSchwedeEinfach);
    uebergangSpiel.setLayoutX(48);
    uebergangSpiel.setLayoutY(544);
    uebergangSpiel.setPrefHeight(113);
    uebergangSpiel.setPrefWidth(537);
    uebergangSpiel.setOnAction(
      (event) -> {uebergangSpiel_Action(event);} 
    );
    uebergangSpiel.setText("Auf in die Jagd!");
    uebergangSpiel.setFont(Font.font("Dialog", 45));
    uebergangSpiel.setAlignment(Pos.CENTER);
    uebergangSpiel.setDisable(true);
    root.getChildren().add(uebergangSpiel);
    spielBeenden.setLayoutX(680);
    spielBeenden.setLayoutY(544);
    spielBeenden.setPrefHeight(113);
    spielBeenden.setPrefWidth(537);
    spielBeenden.setOnAction(
      (event) -> {spielBeenden_Action(event);} 
    );
    spielBeenden.setText("Spiel beenden");
    spielBeenden.setFont(Font.font("Dialog", 40));
    root.getChildren().add(spielBeenden);
    // Ende Komponenten
    
    primaryStage.setOnCloseRequest(e -> System.exit(0));
    primaryStage.setTitle("ARD_RI_TAFL_GUI_VORBEREITUNG");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    launch(args);
  }
  public void auswahlRusseMensch_Action(Event evt) {
    this.spielerRusse = "mensch";
    auswahlSchwedeEinfach.setDisable(false);
    auswahlSchwedeSchwer.setDisable(false);
    uebergangSpielEnable(spielerRusse, spielerSchwede);
  }

  public void auswahlRusseSchwer_Action(Event evt) {
    this.spielerRusse = "schwer";
    auswahlSchwedeEinfach.setDisable(true);
    auswahlSchwedeSchwer.setDisable(true);
    uebergangSpielEnable(spielerRusse, spielerSchwede);
  }

  public void auswahlRusseEinfach_Action(Event evt) {
    this.spielerRusse = "einfach";
    auswahlSchwedeEinfach.setDisable(true);
    auswahlSchwedeSchwer.setDisable(true);
    uebergangSpielEnable(spielerRusse, spielerSchwede);
  }

  public void auswahlSchwedeMensch_Action(Event evt) {
    this.spielerSchwede = "mensch";
    auswahlRusseEinfach.setDisable(false);
    auswahlRusseSchwer.setDisable(false);
    uebergangSpielEnable(spielerRusse, spielerSchwede);
  }

  public void auswahlSchwedeSchwer_Action(Event evt) {
    this.spielerSchwede = "schwer";
    auswahlRusseEinfach.setDisable(true);
    auswahlRusseSchwer.setDisable(true);
    uebergangSpielEnable(spielerRusse, spielerSchwede);
  }

  public void auswahlSchwedeEinfach_Action(Event evt) {
    this.spielerSchwede = "einfach";
    auswahlRusseEinfach.setDisable(true);
    auswahlRusseSchwer.setDisable(true);
    uebergangSpielEnable(spielerRusse, spielerSchwede);
  }

  public void uebergangSpiel_Action(Event evt) {
    new ARD_RI_TAFL_GUI_SPIEL(spielerRusse, spielerSchwede).start(new Stage());
    primaryStage.close();
    uebergangSpielEnable(spielerRusse, spielerSchwede);
  }
  
  public void uebergangSpielEnable(String spielerRusse, String spielerSchwede) {
  if (!spielerRusse.equals("") && !spielerSchwede.equals("")) {
      uebergangSpiel.setDisable(false);
    }
  }

  public void spielBeenden_Action(Event evt) {
    primaryStage.close();

  }

  // Ende Methoden
}

