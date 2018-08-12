
public class FETLAR_HNEFATAFL {

	/* 
	 * Aufbau des Spielfeldes durch Zusammenstellen einer Matrix von FELDERn
	 * 
	 */

	private FELD spielfeld[][];

	public FETLAR_HNEFATAFL() {
		this.spielfeld = new FELD[11][11];
		Startaufstellung();
	}

	/*
	 * BENÖTIGTES: 
	 * 1. Initialisierung des Spielfeldes (Startposition herstellen)   			CHECK
	 * 2. Ausgabe zur Überprüfung (einfach per Konsole) 						CHECK
	 * 3. Einfaches Bewegen (ohne alle Kontrollen) muss möglich sein			CHECK
	 * 4. Überprüfen beim Bewegen												CHECK
	 * 5. Figuren müssen geschlagen werden können
	 * 6. Sieg muss festgestellt werden können
	 */

	//Stellt die Startaufstellung her
	public void Startaufstellung() {
		for(int x=0; x<=10; x++) {
			for(int y=0; y<=10; y++) {
				this.spielfeld[x][y] = new FELD(new LEER());
			}
		}
		for(int i=3; i<=7; i++) {
			this.spielfeld[i][0] = new FELD(new RUSSE());
			this.spielfeld[i][10] = new FELD(new RUSSE());
			this.spielfeld[0][i] = new FELD(new RUSSE());
			this.spielfeld[10][i] = new FELD(new RUSSE());
			this.spielfeld[i][5] = new FELD(new SCHWEDE());
			this.spielfeld[5][i] = new FELD(new SCHWEDE());
		}
		this.spielfeld[5][1] = new FELD(new RUSSE());
		this.spielfeld[1][5] = new FELD(new RUSSE());
		this.spielfeld[5][9] = new FELD(new RUSSE());
		this.spielfeld[9][5] = new FELD(new RUSSE());
		this.spielfeld[4][4] = new FELD(new SCHWEDE());
		this.spielfeld[4][6] = new FELD(new SCHWEDE());
		this.spielfeld[6][4] = new FELD(new SCHWEDE());
		this.spielfeld[6][6] = new FELD(new SCHWEDE());
		this.spielfeld[5][5] = new FELD(new KOENIG());
	}

	public void Konsolenausgabe() {
		System.out.print("yx"+" ");
		for(int x=0; x<=8; x++) {
			System.out.print(x+1+" ");
		}
		System.out.print("10");
		System.out.print("11");
		System.out.print("\n");

		for(int y=0; y<=8; y++) {
			System.out.print(y+1+"  ");
			for(int x=0; x<=10; x++) {
				System.out.print(getShortcut(x, y)+" ");
			}
			System.out.print("\n");
		}

		for(int y=9; y<=10; y++) {
			System.out.print(y+1+" ");
			for(int x=0; x<=10; x++) {
				System.out.print(getShortcut(x, y)+" ");
			}
			System.out.print("\n");
		}
	}

	public void Bewegen(int felder, String richtung, int x_Ausgang, int y_Ausgang) {
		if(felder == 0) {
			System.out.print("\n"+"Ihre Figur kann nicht auf der Stelle stehen bleiben");
			return;
		}
		if(!"x".equals(richtung) && !"y".equals(richtung)) {
			System.out.print("\n"+"Sie müssen ihre Figur in x- oder y-Richtung bewegen");
			return;
		}

		x_Ausgang--;
		y_Ausgang--;
		int i = 0;
		int abweichung = felder/Math.abs(felder);
		int x_Faktor = 0;
		int y_Faktor = 0;

		if(richtung.equals("x")) {
			x_Faktor = 1;
		}
		else
		{
			y_Faktor = 1;
		}
		int ausgang = getrelativeausgang(x_Faktor, y_Faktor, x_Ausgang, y_Ausgang);

		if(ausgang+felder<=10 && ausgang+felder>=0) {
			for(int Differenz=1; Differenz<=felder; Differenz+=abweichung) {
				if(getFigurtyp(x_Ausgang+(Differenz*x_Faktor), y_Ausgang+(Differenz*y_Faktor)).equals("leer")) {
					i++;
					if(i== felder) {
						if(exklusivfelder(x_Ausgang+(felder*x_Faktor), y_Ausgang+(felder*y_Faktor))==false){
							FIGUR tempFigur = this.spielfeld[x_Ausgang][y_Ausgang].getfigur();
							setFigurtyp(x_Ausgang, y_Ausgang, new LEER()); //Altes Feld wird leer
							this.spielfeld[x_Ausgang+(felder*x_Faktor)][y_Ausgang+(felder*y_Faktor)].setfigur(tempFigur);
							//UeberpruefeSchlagen(x_Ausgang+(felder*x_Faktor), y_Ausgang+(felder*y_Faktor), shortcut);
							return;

						}
						else
						{
							if(getFigurtyp(x_Ausgang, y_Ausgang).equals("Koenig")) {  //KOENIG Sonderbehandlung
								FIGUR tempFigur = this.spielfeld[x_Ausgang][y_Ausgang].getfigur();
								this.spielfeld[x_Ausgang][y_Ausgang].setfigur(new LEER()); //Altes Feld wird leer
								this.spielfeld[x_Ausgang+(felder*x_Faktor)][y_Ausgang+(felder*y_Faktor)].setfigur(tempFigur);
								//UeberpruefeSchlagen(x_Ausgang+(felder*x_Faktor), y_Ausgang+(felder*y_Faktor), shortcut);
								if(exklusivfelder(x_Ausgang+(felder*x_Faktor), y_Ausgang+(felder*y_Faktor)) && x_Ausgang+(felder*x_Faktor)!=6 && y_Ausgang+(felder*y_Faktor)!=6) {
									System.out.print("WEISS GEWINNT!"+"\n"+"Der König hat ein Eckfeld erreicht");
									return;
								}
							}
						}
					}
				}
			}
		}

			System.out.println("Ihre Figur konnte nicht bewegt werden."+"\n"+"Bitte ueberpruefen Sie, ob"
					+ " die Laufbahn, sowie das Ziel frei ist, achten Sie darauf das das Ziel noch auf dem Spielbrett"
					+ " liegt und ob das Ziel ein Exklusivfeld ist, sollten sie nicht den Koenig ziehen");
	}


	/*
	 * NOCH UNFERTIG MUSS MÖGLICHKEIT BIETEN FÜR BEIDE SEITEN ZU FUNKTIONIEREN (NICHT NUR WEISS)
	 * AUSSERDEM MUSS DER CODE GEKÜRZT WERDEN (SCHWARZ/WEISS, TÜRME/KOENIG, End 2/End 10, x/y)
	 */
	public void UeberpruefeSchlagen(int x_End, int y_End, String figurtyp) {
		
		int schwarzFaktor = 0;
		int weissFaktor = 0;
		
		if("Russe".equals(figurtyp)) {
			schwarzFaktor = 1;
		}
		if("Schwede".equals(figurtyp) || "Koenig".equals(figurtyp)) {
			weissFaktor = 1;
		}
		
		if(x_End > 2) {
			if(getFigurtyp(x_End-1, y_End).equals("Russe")) {
				if(getFigurtyp(x_End-2, y_End).equals("Schwede") || getFigurtyp(x_End+2, y_End).equals("Koenig")) {
					setFigurtyp(x_End-1, y_End, new LEER());
				}
			}
		}
		if(x_End < 10) {
			if(getFigurtyp(x_End+1, y_End).equals("Russe")) {
				if(getFigurtyp(x_End+2, y_End).equals("Schwede") || getFigurtyp(x_End+2, y_End).equals("Koenig")) {
					setFigurtyp(x_End+1, y_End, new LEER());
				}
			}
		}
		if(y_End > 2) {
			if(getFigurtyp(x_End, y_End-1).equals("Russe")) {
				if(getFigurtyp(x_End, y_End-2).equals("Schwede") || getFigurtyp(x_End-2, y_End).equals("Koenig")) {
					setFigurtyp(x_End, y_End-1, new LEER());
				}
			}
		}
		if(y_End < 10) {
			if(getFigurtyp(x_End, y_End+1).equals("Russe")) {
				if(getFigurtyp(x_End, y_End+2).equals("Schwede") || getFigurtyp(x_End, y_End+2).equals("Koenig")) {
					setFigurtyp(x_End, y_End+1, new LEER());
				}
			}
		}
	}

	public boolean exklusivfelder(int x, int y) {
		if(x==6 && y==6 || x==1 && y==1 || x==1 && y==11 || x==11 && y==1 || x==11 && y==11) {
			return true;
		}
		return false;
	}

	public String getShortcut(int x, int y) {
		return this.spielfeld[x][y].getShortcut();
	}

	public String getFigurtyp(int x, int y) {
		return this.spielfeld[x][y].getFigurtyp();
	}
	
	public void setFigurtyp(int x, int y, FIGUR figur) {
		this.spielfeld[x][y].setfigur(figur);
	}
	
	public FIGUR getFigur(int x, int y) {
		return this.spielfeld[x][y].getfigur();
	}
 
	public static void main(String[] args) {
		FETLAR_HNEFATAFL testfeld = new FETLAR_HNEFATAFL();
		testfeld.Bewegen(2, "x", 6, 2);
		testfeld.Konsolenausgabe();
	}





















	public int getrelativeausgang(int x_Faktor, int y_Faktor, int x_Ausgang, int y_Ausgang) {
		return (x_Faktor*x_Ausgang)+(y_Faktor*y_Ausgang);
	}








}

