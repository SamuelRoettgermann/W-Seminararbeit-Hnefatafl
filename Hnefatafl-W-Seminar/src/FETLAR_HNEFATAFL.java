

public class FETLAR_HNEFATAFL {

	/* 
	 * Aufbau des Spielfeldes durch Zusammenstellen einer Matrix von FELDERn
	 * 
	 */

	/*
	 * BENÖTIGTES: 
	 * 1. Initialisierung des Spielfeldes (Startposition herstellen)   			CHECK
	 * 2. Ausgabe zur Überprüfung (einfach per Konsole) 						CHECK
	 * 3. Einfaches Bewegen (ohne alle Kontrollen) muss möglich sein			CHECK
	 * 4. Überprüfen beim Bewegen												CHECK
	 * 5. Figuren müssen geschlagen werden können								CHECK
	 * 6. Sieg muss festgestellt werden können									CHECK
	 * 7. Abwechselndes Bewegen muss sichergestellt werden können
	 *    (evtl erst in GUI-Klasse implementieren)				
	 */

	//ATTRIBUTE

	private FELD spielfeld[][];

	//KONSTRUKTOR
	public FETLAR_HNEFATAFL() {
		this.spielfeld = new FELD[11][11];
		Startaufstellung();
		Konsolenausgabe();
	}

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
		System.out.print("\n");
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
		System.out.println("\n"+"_________________________________");
	}


	public void Bewegen(int felder, String richtung, int x_Ausgang, int y_Ausgang) {

		int x_Faktor = 0;
		int y_Faktor = 0;

		if(richtung.equals("x")) {
			x_Faktor = 1;
		}
		else
		{
			y_Faktor = 1;
		}

		FIGUR tempFigur = getFigur(x_Ausgang, y_Ausgang); //temporäre Figur wird erstellt die eine Kopie der verschobenen Figur ist
		setFigurtyp(x_Ausgang, y_Ausgang, new LEER()); //Altes Feld wird leer
		setFigurtyp(x_Ausgang+(felder*x_Faktor), y_Ausgang+(felder*y_Faktor), tempFigur); //Kopie der Figur wird auf das Zielfeld gestellt
			
	}
	
	public void BewegenRueckgaengig(int felder, String richtung, int x_Ausgang, int y_Ausgang) {
		
		if(richtung.equals("x")) {
			x_Ausgang = x_Ausgang + felder;
		}
		else {
			y_Ausgang = y_Ausgang + felder;
		}
		felder = -felder;
		Bewegen(felder, richtung, x_Ausgang, y_Ausgang);
	}

	


	/*
	 * NOCH UNFERTIG MUSS MÖGLICHKEIT BIETEN FÜR BEIDE SEITEN ZU FUNKTIONIEREN (NICHT NUR WEISS)
	 * AUSSERDEM MUSS DER CODE GEKÜRZT WERDEN (SCHWARZ/WEISS (CHECK), TÜRME/KOENIG, End 2/End 10, x/y)
	 * EDIT: TÜRME/KOENIG End 2/End 10 VERKÜRZUNG NICHT SO WIRKLICH MÖGLICH, WEIL SOWIESO ALLES GEPRÜFT WERDEN MUSS
	 * EVTL. WENN LINKS/OBEN/RECHTS/UNTEN GEGNER DANN PRÜFE DIE 4 FELDER DARUMHERUM (WENN KÖNIG DANN MÜSSEN ALLE 4
	 * BESETZT SEIN, WENN TURM DANN NUR 2 GEGENÜBERLIEGENDE)
	 * x/y VERKÜRZUNG AUCH NICHT WIRKLICH MÖGLICH, WEIL IMMER NOCH ALLES GEPRÜFT WERDEN MUSS
	 * EVTL. ARBEITEN MIT VARIABLEN DIE DIE X/Y KOORDINATEN DER ZU PRÜFENDEN FIGUR BESCHREIBT UND DANN ÜBERPRÜFUNG
	 * MIT +1/-1 etc.
	 *
	public void UeberpruefeSchlagen(int x_End, int y_End) {

		String dieserFigurtyp;

		dieserFigurtyp = getFigurtypKategorie(x_End, y_End);



		if(x_End > 2) {
			if(!getFigurtypKategorie(x_End-1, y_End).equals(dieserFigurtyp)) {
				if(getFigurtypKategorie(x_End-2, y_End).equals(dieserFigurtyp)) {
					setFigurtyp(x_End-1, y_End, new LEER());
				}
			}
		}
		if(x_End < 10) {
			if(!getFigurtypKategorie(x_End+1, y_End).equals(dieserFigurtyp)) {
				if(getFigurtypKategorie(x_End+2, y_End).equals(dieserFigurtyp)) {
					setFigurtyp(x_End+1, y_End, new LEER());
				}
			}
		}
		if(y_End > 2) {
			if(!getFigurtypKategorie(x_End, y_End-1).equals(dieserFigurtyp)) {
				if(getFigurtypKategorie(x_End, y_End-2).equals(dieserFigurtyp)) {
					setFigurtyp(x_End, y_End-1, new LEER());
				}
			}
		}
		if(y_End < 10) {
			if(!getFigurtypKategorie(x_End, y_End+1).equals(dieserFigurtyp)) {
				if(getFigurtypKategorie(x_End, y_End+2).equals(dieserFigurtyp)) {
					setFigurtyp(x_End, y_End+1, new LEER());
				}
			}
		}
	}

	 */

	

	public boolean exklusivfelder(int x, int y) {
		if(x==5 && y==5 || x==0 && y==0 || x==0 && y==10 || x==10 && y==0 || x==10 && y==10) {
			return true;
		}
		return false;
	}
	
	public boolean randfelder(int x, int y) {
		if(x==0 && y==0 || x==0 && y==10 || x==10 && y==0 || x==10 && y==10) {
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

	public String getFigurtypKategorie(int x, int y) {
		return this.spielfeld[x][y].getFigurtypKategorie();
	}




	public int getrelativeausgang(int x_Faktor, int y_Faktor, int x_Ausgang, int y_Ausgang) {
		return (x_Faktor*x_Ausgang)+(y_Faktor*y_Ausgang);
	}

	public FELD[][] getSpielfeld() {
		return spielfeld;

	}

	








}

