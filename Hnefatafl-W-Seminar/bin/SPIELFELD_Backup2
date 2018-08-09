
public class SPIELFELD {
	// ALLGEMEINE INFORMATIONEN

	/* 
	 * Der Wert -1 beschreibt, dass ein Feld leer ist.
	 * Der Wert 0 beschreibt, dass ein Feld von einem Russen (schwarz) besetzt ist.
	 * Der Wert 1 beschreibt, dass ein Feld von einem Schweden (weiss) besetzt ist.
	 * Der Wert 2 beschreibt, dass ein Feld vom Koenig (weisser Koenig) besetzt ist.
	 */

	//ATTRIBUTE

	int spielfeld[][];
	/* 
	 * spielfeldgroesse muss nicht festgelegt werden, da diese bei Fetlar-Hnefatafl immer gleich ist. 
	 * Da sich bei anderen Spielfeldgrößen nicht nur die Größe der Matrix, sondern auch die
	 * Startaufstellung ändern würde, wird die Option hier nicht gegeben.
	 * Die Fetlar-Hnefatafl-Standardgroesse ist 11, daher die Zahl.
	 */

	//KONSTRUKTOR

	//Initialisiert das Spielfeld und stellt die Standard Fetlar-Hnefatafl Startaufstellung wieder her
	public SPIELFELD(){
		this.spielfeld = new int[12][12];
		Startaufstellung();
	}

	//METHODEN

	//Gibt den Figurentyp wieder der auf einem eingegebenen Feld steht.
	//Wenn das Feld leer ist wird dementsprechend "-1" zurückgegeben
	public int getFieldValue(int x, int y) {
		return spielfeld[x][y];
	}

	//Prüft auf "Exklusivfelder" (Felder die nur vom König betreten werden können)
	public boolean Exklusivfelder(int x, int y) {
		if(x==6 && y==6 || x==1 && y==1 || x==1 && y==11 || x==11 && y==1 || x==11 && y==11) {
			return true;
		}
		return false;
	}




	//Stellt die Startaufstellung her
	public void Startaufstellung() {
		for(int x=1; x<12; x++) {
			for(int y=1; y<12; y++) {
				this.spielfeld[x][y] = -1;
			}
		}
		for(int i=4; i<=8; i++) {
			this.spielfeld[i][1] = 0;
			this.spielfeld[i][11] = 0;
			this.spielfeld[1][i] = 0;
			this.spielfeld[11][i] = 0;
			this.spielfeld[i][6] = 1;
			this.spielfeld[6][i] = 1;
		}
		this.spielfeld[6][2] = 0;
		this.spielfeld[2][6] = 0;
		this.spielfeld[6][10] = 0;
		this.spielfeld[10][6] = 0;
		this.spielfeld[5][5] = 1;
		this.spielfeld[5][7] = 1;
		this.spielfeld[7][5] = 1;
		this.spielfeld[7][7] = 1;
		this.spielfeld[6][6] = 2;
	}

	//public boolean FreieFahrt()

	/*
	 *  Die Methode Bewegen ueberprueft nicht, ob der Stein der angegeben wurde an diesen Koordinaten
	 *  existiert. Diese Abfrage waere in diesem Stadium zum Testen evtl. hilfreich, ist aber fuer
	 *  das Endprodukt irrelevant, da die Daten ja eh erst durch eine andere Methode an diese
	 *  weitergegeben werden, weshalb die Korrektheit der Daten nicht noch einmal von dieser Methode
	 *  gewaehrleistet werden muss.
	 */
	public void Bewegen(int Felder, String Richtung, int x_Ausgangswert, int y_Ausgangswert, int figurtyp) {

		if(Felder==0) {
			System.out.println("Ihre Figur kann nicht auf der Stelle stehen bleiben");
		}

		int i = 0;

		if(Felder>0) {
			if(Richtung.equals("x")) {
				if(x_Ausgangswert+Felder<=11 && x_Ausgangswert+Felder>=1) {
					for(int Differenz=0; Differenz<=Felder; Differenz++) {
						if(this.spielfeld[x_Ausgangswert+Differenz][y_Ausgangswert] == -1) {
							i--;
							if(i== -1*Felder) {
								if(Exklusivfelder(x_Ausgangswert+Felder, y_Ausgangswert)==false){
									this.spielfeld[x_Ausgangswert][y_Ausgangswert] = -1; //Altes Feld wird leer
									this.spielfeld[x_Ausgangswert+Felder][y_Ausgangswert] = figurtyp;
									UeberpruefeSchlagen(x_Ausgangswert+Felder, y_Ausgangswert, figurtyp);

								}
								else
								{
									if(figurtyp==2) {  //KOENIG Sonderbehandlung
										this.spielfeld[x_Ausgangswert][y_Ausgangswert] = -1; //Altes Feld wird leer
										this.spielfeld[x_Ausgangswert+Felder][y_Ausgangswert] = figurtyp;
										UeberpruefeSchlagen(x_Ausgangswert+Felder, y_Ausgangswert, figurtyp);
										if(Exklusivfelder(x_Ausgangswert+Felder, y_Ausgangswert) && x_Ausgangswert+Felder!=6 && y_Ausgangswert!=6) {
											System.out.print("WEISS GEWINNT!"+"\n"+"Der König hat ein Eckfeld erreicht");
											return;
										}
									}
								}
							}
						}
					}
				}
				else
				{
					System.out.println("Ihre Figur konnte nicht bewegt werden."+"\n"+"Bitte ueberpruefen Sie, ob"
							+ " die Laufbahn, sowie das Ziel frei ist, achten Sie darauf das das Ziel noch auf dem Spielbrett"
							+ " liegt und ob das Ziel ein Exklusivfeld ist, sollten sie nicht den Koenig ziehen");
				}
			}
			if(Richtung.equals("y")) {
				if(y_Ausgangswert+Felder<=11 && y_Ausgangswert+Felder>=1) {
					for(int Differenz=0; Differenz<=Felder; Differenz++) {
						if(this.spielfeld[x_Ausgangswert][y_Ausgangswert+Differenz] == -1) {
							i--;
							if(i== -1*Felder) {
								if(Exklusivfelder(x_Ausgangswert, y_Ausgangswert+Felder)==false){
									this.spielfeld[x_Ausgangswert][y_Ausgangswert] = -1; //Altes Feld wird leer
									this.spielfeld[x_Ausgangswert][y_Ausgangswert+Felder] = figurtyp;
								}
								else
								{
									if(figurtyp==2) {  //KOENIG Sonderbehandlung
										this.spielfeld[x_Ausgangswert][y_Ausgangswert] = -1; //Altes Feld wird leer
										this.spielfeld[x_Ausgangswert][y_Ausgangswert+Felder] = figurtyp;
										if(Exklusivfelder(x_Ausgangswert, y_Ausgangswert+Felder) && x_Ausgangswert!=6 && y_Ausgangswert+Felder!=6) {
											System.out.print("WEISS GEWINNT!"+"\n"+"Der König hat ein Eckfeld erreicht");
											return;
										}
									}
								}
							}
						}
					}
				}
				else
				{
					System.out.println("Ihre Figur konnte nicht bewegt werden."+"\n"+"Bitte ueberpruefen Sie, ob"
							+ " die Laufbahn, sowie das Ziel frei ist, achten Sie darauf das das Ziel noch auf dem Spielbrett"
							+ " liegt und ob das Ziel ein Exklusivfeld ist, sollten sie nicht den Koenig ziehen");
				}
			}
		}
		if(Felder<0) {
			if(Richtung.equals("x")) {
				if(x_Ausgangswert+Felder<=11 && x_Ausgangswert+Felder>=1) {
					for(int Differenz=0; Differenz>=Felder; Differenz--) {
						if(this.spielfeld[x_Ausgangswert+Differenz][y_Ausgangswert] == -1) {
							i--;
							if(i== -1*Felder) {
								if(Exklusivfelder(x_Ausgangswert+Felder, y_Ausgangswert)==false){
									this.spielfeld[x_Ausgangswert][y_Ausgangswert] = -1; //Altes Feld wird leer
									this.spielfeld[x_Ausgangswert+Felder][y_Ausgangswert] = figurtyp;
									UeberpruefeSchlagen(x_Ausgangswert+Felder, y_Ausgangswert, figurtyp);

								}
								else
								{
									if(figurtyp==2) {  //KOENIG Sonderbehandlung
										this.spielfeld[x_Ausgangswert][y_Ausgangswert] = -1; //Altes Feld wird leer
										this.spielfeld[x_Ausgangswert+Felder][y_Ausgangswert] = figurtyp;
										UeberpruefeSchlagen(x_Ausgangswert+Felder, y_Ausgangswert, figurtyp);
										if(Exklusivfelder(x_Ausgangswert+Felder, y_Ausgangswert) && x_Ausgangswert+Felder!=6 && y_Ausgangswert!=6) {
											System.out.print("WEISS GEWINNT!"+"\n"+"Der König hat ein Eckfeld erreicht");
											return;
										}
									}
								}
							}
						}
					}
				}
				else
				{
					System.out.println("Ihre Figur konnte nicht bewegt werden."+"\n"+"Bitte ueberpruefen Sie, ob"
							+ " die Laufbahn, sowie das Ziel frei ist, achten Sie darauf das das Ziel noch auf dem Spielbrett"
							+ " liegt und ob das Ziel ein Exklusivfeld ist, sollten sie nicht den Koenig ziehen");
				}
			}
			if(Richtung.equals("y")) {
				if(y_Ausgangswert+Felder<=11 && y_Ausgangswert+Felder>=1) {
					for(int Differenz=0; Differenz>=Felder; Differenz--) {
						if(this.spielfeld[x_Ausgangswert][y_Ausgangswert+Differenz] == -1) {
							i--;
							if(i== -1*Felder) {
								if(Exklusivfelder(x_Ausgangswert, y_Ausgangswert+Felder)==false){
									this.spielfeld[x_Ausgangswert][y_Ausgangswert] = -1; //Altes Feld wird leer
									this.spielfeld[x_Ausgangswert][y_Ausgangswert+Felder] = figurtyp;
								}
								else
								{
									if(figurtyp==2) {  //KOENIG Sonderbehandlung
										this.spielfeld[x_Ausgangswert][y_Ausgangswert] = -1; //Altes Feld wird leer
										this.spielfeld[x_Ausgangswert][y_Ausgangswert+Felder] = figurtyp;
										if(Exklusivfelder(x_Ausgangswert, y_Ausgangswert+Felder) && x_Ausgangswert!=6 && y_Ausgangswert+Felder!=6) {
											System.out.print("WEISS GEWINNT!"+"\n"+"Der König hat ein Eckfeld erreicht");
											return;
										}
									}
								}
							}

						}
					}
				}
				else
				{
					System.out.println("Ihre Figur konnte nicht bewegt werden."+"\n"+"Bitte ueberpruefen Sie, ob"
							+ " die Laufbahn, sowie das Ziel frei ist, achten Sie darauf das das Ziel noch auf dem Spielbrett"
							+ " liegt und ob das Ziel ein Exklusivfeld ist, sollten sie nicht den Koenig ziehen");
				}
			}
		}

		

	}

	public void Konsolenausgabe() {
		System.out.print("yx"+"  ");
		for(int x=1; x<=9; x++) {
		System.out.print(" "+x+"  ");
		}
		System.out.print("10"+"  ");
		System.out.print("11"+"  ");
		System.out.print("\n");
		
		for(int y=1; y<=9; y++) {
			System.out.print(" "+y+"  ");
			for(int x=1; x<=11; x++) {
				if(this.spielfeld[x][y]<0) {
					System.out.print(this.spielfeld[x][y]+"  ");
				}
				else
				{
					System.out.print(" "+this.spielfeld[x][y]+"  ");
				}

			}
			System.out.print("\n");
		}
		for(int y=10; y<=11; y++) {
			System.out.print(y+"  ");
			for(int x=1; x<=11; x++) {
				if(this.spielfeld[x][y]<0) {
					System.out.print(this.spielfeld[x][y]+"  ");
				}
				else
				{
					System.out.print(" "+this.spielfeld[x][y]+"  ");
				}

			}
			System.out.print("\n");
		}
	}

	//void Schlagen
	public void UeberpruefeSchlagen(int x_Endwert, int y_Endwert, int Figurentyp) {
		//Wichtige Eigenschaften fürs Schlagen: Ist ist der x-/y-Wert =0 oder =10, weil dann darf
		//nicht weiter nach oben/unten/links/rechts prüfen, da sonst der Wert out-of-bounds wäre.
		//Weiterhin muss man schauen, welche Figuren überhaupt geschlagen werden sollen (Figurentyp)
		if(Figurentyp == 0) {
			UeberpruefeSchlagenSchwarz(x_Endwert, y_Endwert);
		}
		if(Figurentyp == 1 || Figurentyp == 2) {
			UeberpruefeSchlagenWeiss(x_Endwert, y_Endwert);
		}
	}
	
	public void UeberpruefeSchlagenSchwarz(int x_Endwert, int y_Endwert){
		if(x_Endwert > 2) {
			if(this.spielfeld[x_Endwert-1][y_Endwert] == 1) {
				if(this.spielfeld[x_Endwert-2][y_Endwert] == 0) {
					this.spielfeld[x_Endwert-1][y_Endwert] = -1;
				}
			}
			if(this.spielfeld[x_Endwert-1][y_Endwert] == 2) {
				if(this.spielfeld[x_Endwert-2][y_Endwert] == 0 && this.spielfeld[x_Endwert][y_Endwert-1] == 0 && this.spielfeld[x_Endwert][y_Endwert+1] == 0) {
					this.spielfeld[x_Endwert-1][y_Endwert] = -1;
					System.out.println("SCHWARZ GEWINNT!"+"\n"+"Der König wurde geschlagen");
					return;
				}
			}
		}
		if(x_Endwert < 10) {
			if(this.spielfeld[x_Endwert+1][y_Endwert] == 1) {
				if(this.spielfeld[x_Endwert+2][y_Endwert] == 0) {
					this.spielfeld[x_Endwert+1][y_Endwert] = -1;
				}
			}
			if(this.spielfeld[x_Endwert+1][y_Endwert] == 2) {
				if(this.spielfeld[x_Endwert+2][y_Endwert] == 0 && this.spielfeld[x_Endwert][y_Endwert-1] == 0 && this.spielfeld[x_Endwert][y_Endwert+1] == 0) {
					this.spielfeld[x_Endwert+1][y_Endwert] = -1;
					System.out.println("SCHWARZ GEWINNT!"+"\n"+"Der König wurde geschlagen");
					return;
				}
			}
		}
		if(y_Endwert > 2) {
			if(this.spielfeld[x_Endwert][y_Endwert-1] == 1) {
				if(this.spielfeld[x_Endwert][y_Endwert-2] == 0) {
					this.spielfeld[x_Endwert][y_Endwert-1] = -1;
				}
			}
			if(this.spielfeld[x_Endwert][y_Endwert-1] == 2) {
				if(this.spielfeld[x_Endwert][y_Endwert-2] == 0 && this.spielfeld[x_Endwert-1][y_Endwert] == 0 && this.spielfeld[x_Endwert-1][y_Endwert] == 0) {
					this.spielfeld[x_Endwert][y_Endwert-1] = -1;
					System.out.println("SCHWARZ GEWINNT!"+"\n"+"Der König wurde geschlagen");
					return;
				}
			}
		}
		if(y_Endwert < 10) {
			if(this.spielfeld[x_Endwert][y_Endwert+1] == 1) {
				if(this.spielfeld[x_Endwert][y_Endwert+2] == 0) {
					this.spielfeld[x_Endwert][y_Endwert+1] = -1;
				}
			}
			if(this.spielfeld[x_Endwert][y_Endwert+1] == 2) {
				if(this.spielfeld[x_Endwert][y_Endwert+2] == 0 && this.spielfeld[x_Endwert-1][y_Endwert] == 0 && this.spielfeld[x_Endwert-1][y_Endwert] == 0) {
					this.spielfeld[x_Endwert][y_Endwert+1] = -1;
					System.out.println("SCHWARZ GEWINNT!"+"\n"+"Der König wurde geschlagen");
					return;
				}
			}
		}
	}
	
	public void UeberpruefeSchlagenWeiss(int x_Endwert, int y_Endwert) {
		if(x_Endwert > 2) {
			if(this.spielfeld[x_Endwert-1][y_Endwert] == 0) {
				if(this.spielfeld[x_Endwert-2][y_Endwert] == 1 || this.spielfeld[x_Endwert+2][y_Endwert] == 2) {
					this.spielfeld[x_Endwert-1][y_Endwert] = -1;
				}
			}
		}
		if(x_Endwert < 10) {
			if(this.spielfeld[x_Endwert+1][y_Endwert] == 0) {
				if(this.spielfeld[x_Endwert+2][y_Endwert]  == 1 || this.spielfeld[x_Endwert+2][y_Endwert] == 2) {
					this.spielfeld[x_Endwert+1][y_Endwert] = -1;
				}
			}
		}
		if(y_Endwert > 2) {
			if(this.spielfeld[x_Endwert][y_Endwert-1] == 0) {
				if(this.spielfeld[x_Endwert][y_Endwert-2] == 1 || this.spielfeld[x_Endwert-2][y_Endwert] == 2) {
					this.spielfeld[x_Endwert][y_Endwert-1] = -1;
				}
			}
		}
		if(y_Endwert < 10) {
			if(this.spielfeld[x_Endwert][y_Endwert+1] == 0) {
				if(this.spielfeld[x_Endwert][y_Endwert+2] == 1 || this.spielfeld[x_Endwert][y_Endwert+2] == 2) {
					this.spielfeld[x_Endwert][y_Endwert+1] = -1;
				}
			}
		}
	}
	
	//Prüft ob ein Spieler gewonnen hat
	public boolean TesteSieg() {
		//Prüfung für den Sieg von Weiß(Schweden[König ist auf einem Eckfeld])
		
		
		//Prüfung für den Sieg von Schwarz(Russen[König wurde geschlagen
		
		return false;
	}

	public static void main(String[] args) {
		SPIELFELD spielfeldTest = new SPIELFELD();
		spielfeldTest.Konsolenausgabe();
	}

}

