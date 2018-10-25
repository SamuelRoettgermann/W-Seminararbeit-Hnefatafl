
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
	}

	public boolean Bewegen(int felder, String richtung, int x_Ausgang, int y_Ausgang) {
		if(felder == 0) {
			System.out.print("\n"+"Ihre Figur kann nicht auf der Stelle stehen bleiben");
			return false;
		}
		if(!"x".equals(richtung) && !"y".equals(richtung)) {
			System.out.print("\n"+"Sie müssen ihre Figur in x- oder y-Richtung bewegen");
			return false;
		}
		
		x_Ausgang--;
		y_Ausgang--;
		
		if(getFigurtyp(x_Ausgang, y_Ausgang).equals("leer")) {
			System.out.print("\n" + "Ups. Sie haben sich wohl mit den Koordinaten vertan. An dieser Stelle steht nämlich keine Figur");
			return false;
		}
			
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
							UeberpruefeSchlagenGrob(x_Ausgang+(felder*x_Faktor), y_Ausgang+(felder*y_Faktor));
							return true;

						}
						else
						{
							if(getFigurtyp(x_Ausgang, y_Ausgang).equals("Koenig")) {  //KOENIG Sonderbehandlung
								FIGUR tempFigur = this.spielfeld[x_Ausgang][y_Ausgang].getfigur(); //temporäre Figur wird erstellt die die eine Kopie der verschobenen Figur ist
								this.spielfeld[x_Ausgang][y_Ausgang].setfigur(new LEER()); //Altes Feld wird leer
								this.spielfeld[x_Ausgang+(felder*x_Faktor)][y_Ausgang+(felder*y_Faktor)].setfigur(tempFigur);
								UeberpruefeSchlagenGrob(x_Ausgang+(felder*x_Faktor), y_Ausgang+(felder*y_Faktor));
								if(exklusivfelder(x_Ausgang+(felder*x_Faktor), y_Ausgang+(felder*y_Faktor)) && x_Ausgang+(felder*x_Faktor)!=6 
								&& y_Ausgang+(felder*y_Faktor)!=6) {
									System.out.print("WEISS GEWINNT!"+"\n"+"Der König hat ein Eckfeld erreicht");
									return true;
								}
							}
						}
					}
				}
			}
		}

			System.out.println("Ihre Figur konnte nicht bewegt werden."+"\n"+"Bitte ueberpruefen Sie, ob"
					+ " die Laufbahn, sowie das Ziel frei ist, achten Sie darauf das das Ziel noch auf dem Spielbrett"
					+ "\n" + "liegt, oder ob das Ziel ein Exklusivfeld ist, sollten sie nicht den Koenig ziehen.");
			return false;
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
	
	public void UeberpruefeSchlagenGrob(int x_End, int y_End) {

		/*
		 * "dieserFigurtyp" meint eigentlich "dieseFigurtypKategorie". Da die Variable aber so oft benötigt wird, wurde
		 * sie hier vereinfacht nur "dieserFigurtyp" genannt. (Außerdem hatte ich das jetzt schon so gemacht und keinen
		 * Bock mehr das noch mal mit STRG + F alles umzubenennen (Change it in the end if you mind)
		 */
		
		String dieserFigurtyp;
		dieserFigurtyp = getFigurtypKategorie(x_End, y_End);
		
		
		
		if(x_End > 1) {
			if(!getFigurtypKategorie(x_End-1, y_End).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End-1,  y_End))) {
				UeberpruefeSchlagenDetail(x_End-1, y_End, getFigurtyp(x_End-1, y_End), dieserFigurtyp, "x");
			}
		}
		
		if(x_End < 9) {
			if(!getFigurtypKategorie(x_End+1, y_End).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End+1,  y_End))) {
				UeberpruefeSchlagenDetail(x_End+1, y_End, getFigurtyp(x_End+1, y_End), dieserFigurtyp ,"x");
			}
		}
		
		if(y_End > 1) {
			if(!getFigurtypKategorie(x_End, y_End-1).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End,  y_End-1))) {
				UeberpruefeSchlagenDetail(x_End, y_End-1, getFigurtyp(x_End, y_End-1), dieserFigurtyp, "y");
			}
		}
		
		if(y_End < 9) {
			if(!getFigurtypKategorie(x_End, y_End+1).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End,  y_End+1))) {
				UeberpruefeSchlagenDetail(x_End, y_End+1, getFigurtyp(x_End, y_End+1), dieserFigurtyp, "y");
			}
		}
	}
	
	
	public void UeberpruefeSchlagenDetail(int x, int y, String figurtypBesiegter, String figurtypKategorieBesieger, String Schlagrichtung) {
		if("Koenig".equals(figurtypBesiegter)) {
			if(getFigurtypKategorie(x-1, y).equals(figurtypKategorieBesieger) && getFigurtypKategorie(x+1, y).equals(figurtypKategorieBesieger)
			&& getFigurtypKategorie(x, y+1).equals(figurtypKategorieBesieger) && getFigurtypKategorie(x, y-1).equals(figurtypKategorieBesieger)) {
				setFigurtyp(x, y, new LEER());
			}
		}
		else
		{
			if(Schlagrichtung.equals("x")) {
				if(getFigurtypKategorie(x-1, y).equals(figurtypKategorieBesieger) && getFigurtypKategorie(x+1, y).equals(figurtypKategorieBesieger)) {
					setFigurtyp(x, y, new LEER());
				}
			}
			if(Schlagrichtung.equals("y")) {
				if(getFigurtypKategorie(x, y+1).equals(figurtypKategorieBesieger) && getFigurtypKategorie(x, y-1).equals(figurtypKategorieBesieger)) {
					setFigurtyp(x, y, new LEER());
				}
			}
		}
	}

	public boolean exklusivfelder(int x, int y) {
		if(x==5 && y==5 || x==0 && y==0 || x==0 && y==10 || x==10 && y==0 || x==10 && y==10) {
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
		if(getFigurtyp(x, y).equals("Russe")) {
			return "schwarz";
		}
		if(getFigurtyp(x, y).equals("Schwede") || getFigurtyp(x, y).equals("Koenig")) {
			return "weiss";
		}
		return "leer";
	}
 






















	public int getrelativeausgang(int x_Faktor, int y_Faktor, int x_Ausgang, int y_Ausgang) {
		return (x_Faktor*x_Ausgang)+(y_Faktor*y_Ausgang);
	}








}

