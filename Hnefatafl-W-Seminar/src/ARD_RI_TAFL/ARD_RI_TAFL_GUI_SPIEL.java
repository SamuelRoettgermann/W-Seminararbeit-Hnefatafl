package ARD_RI_TAFL;

import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.image.*;
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

public class ARD_RI_TAFL_GUI_SPIEL extends Application {
	// Anfang Attribute
	//private GridPane grid;
	private Button uebergangVorbereitung = new Button();
	private TextArea Ereignisfeld = new TextArea();
	private Stage primaryStage;
	private Button spielBeenden = new Button();
	private Line line1 = new Line();
	private Line line2 = new Line();
	private Line line3 = new Line();
	private Line line4 = new Line();
	private Line line5 = new Line();
	private Line line6 = new Line();
	private Line line7 = new Line();
	private Line line8 = new Line();
	private Line line9 = new Line();
	private Line line10 = new Line();
	private VERWALTUNG verwaltung;
	private int x_Ausgang;
	private int y_Ausgang;
	private String richtung;
	private int felder;
	private ToggleButton[][] erstauswahl;
	private ToggleButton[][] zweitauswahl;
	private String spielerRusseTesten;
	private String spielerSchwedeTesten;
	private String aktivesTeam;
	private Circle[][] figuren;
	// Ende Attribute




	public ARD_RI_TAFL_GUI_SPIEL(String spielerRusse, String spielerSchwede) {
		ZUG schwarz = null;
		ZUG weiss = null;
		y_Ausgang = 0;
		x_Ausgang = 0;
		richtung = "x";
		felder = 0;
		spielerRusseTesten = spielerRusse;
		spielerSchwedeTesten = spielerSchwede;
		aktivesTeam = "schwarz";


		if(spielerRusse.equals("mensch")) {
			schwarz = new ZUG_MENSCH("schwarz");
		}
		else if(spielerRusse.equals("einfach")) {
			schwarz = new ZUG_MASCHINE_LEICHT("schwarz");
		}
		else if(spielerRusse.equals("schwer")) {
			schwarz = new ZUG_MASCHINE_SCHWER("schwarz");
		}

		if(spielerSchwede.equals("mensch")) {
			weiss = new ZUG_MENSCH("weiss");
		}
		else if(spielerSchwede.equals("einfach")) {
			weiss = new ZUG_MASCHINE_LEICHT("weiss");
		}
		else if(spielerSchwede.equals("schwer")) {
			weiss = new ZUG_MASCHINE_SCHWER("weiss");
		}

		this.verwaltung = new VERWALTUNG(schwarz, weiss);
	}

	public void start(Stage primaryStage) { 
		Pane root = new Pane();
		root.setBackground(new Background(new BackgroundFill(Color.rgb(204, 102, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(root, 1264, 682);
		this.primaryStage = primaryStage;



		// Anfang Komponenten


		uebergangVorbereitung.setLayoutX(888);
		uebergangVorbereitung.setLayoutY(464);
		uebergangVorbereitung.setPrefHeight(89);
		uebergangVorbereitung.setPrefWidth(289);
		uebergangVorbereitung.setOnAction(
				(event) -> {uebergangVorbereitung_Action(event);} 
				);
		uebergangVorbereitung.setText("Zurück zum Menü");
		uebergangVorbereitung.setFont(Font.font("Dialog", 30));
		root.getChildren().add(uebergangVorbereitung);
		Ereignisfeld.setLayoutX(872);
		Ereignisfeld.setLayoutY(24);
		Ereignisfeld.setPrefHeight(417);
		Ereignisfeld.setPrefWidth(313);
		Ereignisfeld.setEditable(false);
		Ereignisfeld.setFont(Font.font("Dialog", 18));
		Ereignisfeld.setWrapText(true);
		root.getChildren().add(Ereignisfeld);
		spielBeenden.setLayoutX(888);
		spielBeenden.setLayoutY(568);
		spielBeenden.setPrefHeight(89);
		spielBeenden.setPrefWidth(289);
		spielBeenden.setOnAction(
				(event) -> {spielBeenden_Action(event);} 
				);
		spielBeenden.setText("Spiel beenden");
		spielBeenden.setFont(Font.font("Dialog", 30));
		root.getChildren().add(spielBeenden);
		line1.setStartX(155);
		line1.setStartY(25);
		line1.setEndX(245);
		line1.setEndY(115);
		line1.setStrokeWidth(2);
		root.getChildren().add(line1);
		line2.setStartX(245);
		line2.setStartY(25);
		line2.setEndX(155);
		line2.setEndY(115);
		line2.setStrokeWidth(2);
		root.getChildren().add(line2);
		line3.setStartX(695);
		line3.setStartY(25);
		line3.setEndX(785);
		line3.setEndY(115);
		line3.setStrokeWidth(2);
		root.getChildren().add(line3);
		line4.setStartX(785);
		line4.setStartY(25);
		line4.setEndX(695);
		line4.setEndY(115);
		line4.setStrokeWidth(2);
		root.getChildren().add(line4);
		line5.setStartX(155);
		line5.setStartY(565);
		line5.setEndX(245);
		line5.setEndY(655);
		line5.setStrokeWidth(2);
		root.getChildren().add(line5);
		line6.setStartX(245);
		line6.setStartY(565);
		line6.setEndX(155);
		line6.setEndY(655);
		line6.setStrokeWidth(2);
		root.getChildren().add(line6);
		line7.setStartX(695);
		line7.setStartY(565);
		line7.setEndX(785);
		line7.setEndY(655);
		line7.setStrokeWidth(2);
		root.getChildren().add(line7);
		line8.setStartX(785);
		line8.setStartY(565);
		line8.setEndX(695);
		line8.setEndY(655);
		line8.setStrokeWidth(2);
		root.getChildren().add(line8);
		line9.setStartX(425);
		line9.setStartY(295);
		line9.setEndX(515);
		line9.setEndY(385);
		line9.setStrokeWidth(2);
		root.getChildren().add(line9);
		line10.setStartX(515);
		line10.setStartY(295);
		line10.setEndX(425);
		line10.setEndY(385);
		line10.setStrokeWidth(2);
		root.getChildren().add(line10);

		// Ende Komponenten

		/*
		 * Hier werden die Linien erzeugt, die die Rechtecke formen, die Felder darstellen
		 */
		for (int x=1; x<=8; x++) {
			Line feldlinie = new Line();
			feldlinie.setStartX(65+(x*90));
			feldlinie.setStartY(-65+(90));
			feldlinie.setEndX(65+(x*90));
			feldlinie.setEndY(655);
			feldlinie.setStrokeWidth(3);
			root.getChildren().add(feldlinie);
		}

		for (int y=1; y<=8; y++) {
			Line feldlinie = new Line();
			feldlinie.setStartX(65+(90));
			feldlinie.setStartY(-65+(y*90));
			feldlinie.setEndX(785);
			feldlinie.setEndY(-65+(y*90));
			feldlinie.setStrokeWidth(3);
			root.getChildren().add(feldlinie);
		}


		/*
		 * Hier werden die Knöpfe und Kreise erstellt die die Mechanik der Felder, bzw. die optische Anzeige darstellen
		 */
		
		erstauswahl = new ToggleButton[7][7];
		zweitauswahl = new ToggleButton[7][7];
		figuren = new Circle[7][7];

		for(int y=0;y<=6;y++) {
			for(int x=0;x<=6;x++) {

				figuren[x][y] = new Circle();
				figuren[x][y].setRadius(35);
				figuren[x][y].setLayoutX(45+65+90+(90*x));
				figuren[x][y].setLayoutY(45-65+90+(90*y));
				if(verwaltung.getFigurtyp(x, y).equals("Russe")) {
					figuren[x][y].setFill(Color.BLACK);
				}
				if(verwaltung.getFigurtyp(x, y).equals("Schwede")) {
					figuren[x][y].setFill(Color.WHITE);
				}
				if(verwaltung.getFigurtyp(x, y).equals("Koenig")) {
					figuren[x][y].setFill(Color.GOLD);
				}
				if(verwaltung.getFigurtyp(x, y).equals("leer")) {
					figuren[x][y].setVisible(false);
				}
				root.getChildren().add(figuren[x][y]);


				erstauswahl[x][y] = new ToggleButton();
				erstauswahl[x][y].setPrefHeight(90);
				erstauswahl[x][y].setPrefWidth(90);
				erstauswahl[x][y].setLayoutX(65+90+(90*x));
				erstauswahl[x][y].setLayoutY(-65+90+(90*y));
				erstauswahl[x][y].setOpacity(0);
				erstauswahl[x][y].setOnAction(
						(event) -> {erstauswahl_Action(event);} 
						);
				root.getChildren().add(erstauswahl[x][y]);

				zweitauswahl[x][y] = new ToggleButton();
				zweitauswahl[x][y].setPrefHeight(90);
				zweitauswahl[x][y].setPrefWidth(90);
				zweitauswahl[x][y].setLayoutX(65+90+(90*x));
				zweitauswahl[x][y].setLayoutY(-65+90+(90*y));
				zweitauswahl[x][y].setOpacity(0);
				zweitauswahl[x][y].setVisible(false);
				zweitauswahl[x][y].setOnAction(
						(event) -> {zweitauswahl_Action(event);} 
						);
				root.getChildren().add(zweitauswahl[x][y]);
			}
		}



		primaryStage.setOnCloseRequest(e -> System.exit(0));
		primaryStage.setTitle("ARD_RI_TAFL_GUI_SPIEL");
		primaryStage.setScene(scene);
		primaryStage.show();

		ZugAusfuehrungMaschine();
	}



	// Anfang Methoden



	public static void main(String[] args) {
		launch(args);
	}
	public void uebergangVorbereitung_Action(Event evt) {
		new ARD_RI_TAFL_GUI_VORBEREITUNG().start(new Stage());
		primaryStage.close();

	}

	public void spielBeenden_Action(Event evt) {
		primaryStage.close();
	}

	public void ZugAusfuehrungMaschine() {

		if(aktivesTeam.equals("schwarz")) {
			if(spielerRusseTesten.equals("einfach") || spielerRusseTesten.equals("schwer")) {
				verwaltung.AmZug(0, "x", 0, 0);
				Ereignisfeld.insertText(0, "Der Computer hat eine Figur von x="+(verwaltung.getspielerschwarzx_Ausgangberechnet()+1)+" y="+(verwaltung.getspielerschwarzy_Ausgangberechnet()+1)
				+" um "+verwaltung.getspielerschwarzfelderberechnet()+" in "+verwaltung.getspielerschwarzrichtungberechnet()+"-Richtung verschoben"+"\n"+"\n");
				aktivesTeam = verwaltung.getaktivesTeam();
				aktualisiereFiguren();
			}
		} else if(aktivesTeam.equals("weiss")) {
			if(spielerSchwedeTesten.equals("einfach") || spielerSchwedeTesten.equals("schwer")) {
				verwaltung.AmZug(0, "x", 0, 0);
				Ereignisfeld.insertText(0, "Der Computer hat eine Figur von x="+(verwaltung.getspielerweissx_Ausgangberechnet()+1)+" y="+(verwaltung.getspielerweissy_Ausgangberechnet()+1)
				+" um "+verwaltung.getspielerweissfelderberechnet()+" in "+verwaltung.getspielerweissrichtungberechnet()+"-Richtung verschoben"+"\n"+"\n");
				
				aktivesTeam = verwaltung.getaktivesTeam();
				aktualisiereFiguren();
			}
		}


	}

	public void zweitauswahl_Action(ActionEvent event) {
		for(int y=0;y<=6;y++) {
			for(int x=0;x<=6;x++) {

				if(zweitauswahl[x][y].isSelected()) {




					if(((x+1) > x_Ausgang || (x+1) < x_Ausgang) && ((y+1) > y_Ausgang || (y+1) < y_Ausgang)) {
						Ereignisfeld.insertText(0, "Bitte bewegen Sie Ihre Figur nur in eine Richtung."+"\n"+"Wählen Sie bitte die Figur, die Sie ziehen möchten erneut aus."+"\n"+"\n");
					}
					else if((x+1) > x_Ausgang || (x+1) < x_Ausgang) {
						richtung = "x";
						felder = (x+1)-x_Ausgang;
						verwaltung.AmZug(felder, richtung, x_Ausgang, y_Ausgang);
						aktivesTeam = verwaltung.getaktivesTeam();
						aktualisiereFiguren();
						//System.out.println("\n"+"felder: "+felder+"\n"+"richtung: "+richtung+"\n"+"x_Ausgang: "+x_Ausgang+"\n"+"y_Ausgang: "+y_Ausgang);
						gameloop();
					}
					else if((y+1) > y_Ausgang || (y+1) < y_Ausgang) {
						richtung = "y";
						felder = (y+1)-y_Ausgang;
						verwaltung.AmZug(felder, richtung, x_Ausgang, y_Ausgang);
						aktivesTeam = verwaltung.getaktivesTeam();
						aktualisiereFiguren();
						//System.out.println("\n"+"felder: "+felder+"\n"+"richtung: "+richtung+"\n"+"x_Ausgang: "+x_Ausgang+"\n"+"y_Ausgang: "+y_Ausgang);
						gameloop();
					} 
					else if((x+1) == x_Ausgang && (y+1) == y_Ausgang) {
						Ereignisfeld.insertText(0, "Sie können Ihre Figur nicht auf der Stelle stehen lassen."+"\n"+"Bitte wählen Sie die Figur, die Sie ziehen möchten erneut aus."+"\n"+"\n");
					}


					zweitauswahl[x][y].setSelected(false);

					for(int i=0;i<=6;i++) {
						for(int z=0;z<=6;z++) {
							erstauswahl[z][i].setVisible(true);
							zweitauswahl[z][i].setVisible(false);
						}
					}
				}

			}
		}
	}

	public void gameloop() {

		if(verwaltung.UeberpruefeSiegWeiss()) {
			Ereignisfeld.insertText(0, "Weiß hat gewonnen!"+"\n"+"Sie können ein neues Spiel starten, indem Sie zum Menü zurückgehen."+"\n"+"\n"+"\n");
			for(int i=0;i<=6;i++) {
				for(int z=0;z<=6;z++) {
					erstauswahl[z][i].setVisible(false);
					zweitauswahl[z][i].setVisible(false);
				}
			}
		} else if(verwaltung.UeberpruefeSiegSchwarz()) {
			Ereignisfeld.insertText(0, "Schwarz hat gewonnen!"+"\n"+"Sie können ein neues Spiel starten, indem Sie zum Menü zurückgehen."+"\n"+"\n"+"\n");
			for(int i=0;i<=6;i++) {
				for(int z=0;z<=6;z++) {
					erstauswahl[z][i].setVisible(false);
					zweitauswahl[z][i].setVisible(false);
				}
			}
		} else {
			ZugAusfuehrungMaschine();



			if(verwaltung.UeberpruefeSiegWeiss()) {
				Ereignisfeld.insertText(0, "Weiß hat gewonnen!"+"\n"+"Sie können ein neues Spiel starten, indem Sie zum Menü zurückgehen."+"\n"+"\n"+"\n");
				for(int i=0;i<=6;i++) {
					for(int z=0;z<=6;z++) {
						erstauswahl[z][i].setVisible(false);
						zweitauswahl[z][i].setVisible(false);
					}
				}
			}
			if(verwaltung.UeberpruefeSiegSchwarz()) {
				Ereignisfeld.insertText(0, "Schwarz hat gewonnen!"+"\n"+"Sie können ein neues Spiel starten, indem Sie zum Menü zurückgehen."+"\n"+"\n"+"\n");
				for(int i=0;i<=6;i++) {
					for(int z=0;z<=6;z++) {
						erstauswahl[z][i].setVisible(false);
						zweitauswahl[z][i].setVisible(false);
					}
				}
			}
		}

	}

	public void erstauswahl_Action(ActionEvent event) {
		for(int y=0;y<=6;y++) {
			for(int x=0;x<=6;x++) {

				if(erstauswahl[x][y].isSelected()) {
					x_Ausgang = x+1;
					y_Ausgang = y+1;

					erstauswahl[x][y].setSelected(false);

					for(int i=0;i<=6;i++) {
						for(int z=0;z<=6;z++) {
							erstauswahl[z][i].setVisible(false);
							zweitauswahl[z][i].setVisible(true);
						}
					}

				}

			}
		}

		System.out.println("Erstauswahl: "+x_Ausgang+" "+y_Ausgang);
	}

	public void aktualisiereFiguren() {
		for(int y=0;y<=6;y++) {
			for(int x=0;x<=6;x++) {

				if(verwaltung.getFigurtyp(x, y).equals("Russe")) {
					figuren[x][y].setFill(Color.BLACK);
					figuren[x][y].setVisible(true);
				}
				if(verwaltung.getFigurtyp(x, y).equals("Schwede")) {
					figuren[x][y].setFill(Color.WHITE);
					figuren[x][y].setVisible(true);
				}
				if(verwaltung.getFigurtyp(x, y).equals("Koenig")) {
					figuren[x][y].setVisible(true);
					figuren[x][y].setFill(Color.GOLD);
				}
				if(verwaltung.getFigurtyp(x, y).equals("leer")) {
					figuren[x][y].setVisible(false);
				}
			}
		}



	}

	// Ende Methoden
}

