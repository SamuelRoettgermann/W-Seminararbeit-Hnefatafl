package ARD_RI_TAFL;

import java.util.ArrayList;

public class ARD_RI_TAFL {

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
	ArrayList<Integer> geschlageneFigurenPosition;
	ArrayList<Integer> geschlageneFigurenTypus;

	//KONSTRUKTOR
	public ARD_RI_TAFL() {
		this.spielfeld = new FELD[7][7];
		Startaufstellung();
		Konsolenausgabe();
		//geschlageneFigurenPosition wird sich nur um die Figuren kümmern die während der Berechnung des "maschinellen Zuges" geschlagen wurden, so dass sie "wiederbelebt" werden können
		geschlageneFigurenPosition = new ArrayList<Integer>(); 
		//geschlageneFigurenTypus wird sich nur um die Figuren kümmern die während der Berechnung des "maschinellen Zuges" geschlagen wurden, so dass sie "wiederbelebt" werden können
		geschlageneFigurenTypus = new ArrayList<Integer>();
	}

	//Stellt die Startaufstellung her
	public void Startaufstellung() {
		for(int x=0; x<=6; x++) {
			for(int y=0; y<=6; y++) {
				this.spielfeld[x][y] = new FELD(new LEER());
			}
		}
		for(int i=2; i<=4; i++) {
			this.spielfeld[i][0] = new FELD(new RUSSE());
			this.spielfeld[i][6] = new FELD(new RUSSE());
			this.spielfeld[0][i] = new FELD(new RUSSE());
			this.spielfeld[6][i] = new FELD(new RUSSE());
			this.spielfeld[i][3] = new FELD(new SCHWEDE());
			this.spielfeld[3][i] = new FELD(new SCHWEDE());
		}
		this.spielfeld[3][1] = new FELD(new RUSSE());
		this.spielfeld[1][3] = new FELD(new RUSSE());
		this.spielfeld[3][5] = new FELD(new RUSSE());
		this.spielfeld[5][3] = new FELD(new RUSSE());

		this.spielfeld[2][2] = new FELD(new SCHWEDE());
		this.spielfeld[2][4] = new FELD(new SCHWEDE());
		this.spielfeld[4][2] = new FELD(new SCHWEDE());
		this.spielfeld[4][4] = new FELD(new SCHWEDE());
		this.spielfeld[3][3] = new FELD(new KOENIG());
	}

	public void Konsolenausgabe() {
		System.out.print("\n");
		System.out.print("yx"+" ");
		for(int x=0; x<=6; x++) {
			System.out.print(x+1+" ");
		}

		System.out.print("\n");

		for(int y=0; y<=6; y++) {
			System.out.print(y+1+"  ");
			for(int x=0; x<=6; x++) {
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

		//Bewegen zurücksetzen
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
	 * Wenn eine Figur während der Berechnung geschlagen wurde, wird diese Methode aufgerufen um die Figur wieder zurückzustellen, ehe das Spielbrett durcheinander "gewürfelt" wird
	 */
	public void SchlagenRueckgaengig() {
		FIGUR tempfig;

		if(geschlageneFigurenTypus.get(geschlageneFigurenTypus.size()-1) == 2) {
			tempfig = new KOENIG();
		}
		else if(geschlageneFigurenTypus.get(geschlageneFigurenTypus.size()-1) == 1) {
			tempfig = new SCHWEDE();
		}
		else {
			tempfig = new RUSSE();
		}

		setFigurtyp(geschlageneFigurenPosition.get(geschlageneFigurenPosition.size()-2), geschlageneFigurenPosition.get(geschlageneFigurenPosition.size()-1), tempfig);

		geschlageneFigurenPosition.remove(geschlageneFigurenPosition.size()-1);
		geschlageneFigurenPosition.remove(geschlageneFigurenPosition.size()-1);
		geschlageneFigurenTypus.remove(geschlageneFigurenTypus.size()-1);	

	}





	/*
	 * UeberpruefeSchlagenGrobMaschine kann nur 
	 */
	public int UeberpruefeSchlagenGrobMaschine(int x_End, int y_End) {

		/*
		 * "dieserFigurtyp" meint eigentlich "dieseFigurtypKategorie". Da die Variable aber so oft benötigt wird, wurde
		 * sie hier vereinfacht nur "dieserFigurtyp" genannt. (Außerdem hatte ich das jetzt schon so gemacht und keinen
		 * Bock mehr das noch mal mit STRG + F alles umzubenennen (Change it in the end if you mind)
		 */

		int geschlageneFiguren = 0;
		String dieserFigurtyp;
		dieserFigurtyp = getFigurtypKategorie(x_End, y_End);




		if(x_End > 1) {
			if(!getFigurtypKategorie(x_End-1, y_End).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End-1,  y_End))) {
				geschlageneFiguren += UeberpruefeSchlagenDetailMaschine(x_End-1, y_End, getFigurtyp(x_End-1, y_End), dieserFigurtyp, "x");
			}
		}

		if(x_End < 5) {
			if(!getFigurtypKategorie(x_End+1, y_End).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End+1,  y_End))) {
				geschlageneFiguren += UeberpruefeSchlagenDetailMaschine(x_End+1, y_End, getFigurtyp(x_End+1, y_End), dieserFigurtyp ,"x");
			}
		}

		if(y_End > 1) {
			if(!getFigurtypKategorie(x_End, y_End-1).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End,  y_End-1))) {
				geschlageneFiguren += UeberpruefeSchlagenDetailMaschine(x_End, y_End-1, getFigurtyp(x_End, y_End-1), dieserFigurtyp, "y");
			}
		}

		if(y_End < 5) {
			if(!getFigurtypKategorie(x_End, y_End+1).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End,  y_End+1))) {
				geschlageneFiguren += UeberpruefeSchlagenDetailMaschine(x_End, y_End+1, getFigurtyp(x_End, y_End+1), dieserFigurtyp, "y");
			}
		}
		return geschlageneFiguren;
	}




	public int UeberpruefeSchlagenDetailMaschine(int x, int y, String figurtypBesiegter, String figurtypKategorieBesieger, String Schlagrichtung) {
		if("Koenig".equals(figurtypBesiegter)) {
			if(x < 6 && x > 0 && y < 6 && y > 0) {
				if((getFigurtypKategorie(x-1, y).equals(figurtypKategorieBesieger) || exklusivfelder(x-1, y)) 
						&& (getFigurtypKategorie(x+1, y).equals(figurtypKategorieBesieger) || exklusivfelder(x+1, y))
						&& (getFigurtypKategorie(x, y+1).equals(figurtypKategorieBesieger) || exklusivfelder(x, y+1)) 
						&& (getFigurtypKategorie(x, y-1).equals(figurtypKategorieBesieger) || exklusivfelder(x, y-1))) {
					setFigurtyp(x, y, new LEER());
					geschlageneFigurenPosition.add(x);
					geschlageneFigurenPosition.add(y);
					geschlageneFigurenTypus.add(2);
					return 1;
				}
			}
		}
		else
		{
			if(Schlagrichtung.equals("x")) {
				if((getFigurtypKategorie(x-1, y).equals(figurtypKategorieBesieger) || randfelder(x-1, y)) 
						&& (getFigurtypKategorie(x+1, y).equals(figurtypKategorieBesieger) || randfelder(x+1, y))) {
					setFigurtyp(x, y, new LEER());
					geschlageneFigurenPosition.add(x);
					geschlageneFigurenPosition.add(y);
					if(figurtypBesiegter.equals("Schwede")) {
						geschlageneFigurenTypus.add(1);
					}
					else {
						geschlageneFigurenTypus.add(0);
					}
					return 1;

				}
			}
			if(Schlagrichtung.equals("y")) {
				if((getFigurtypKategorie(x, y+1).equals(figurtypKategorieBesieger) || randfelder(x, y+1)) 
						&& (getFigurtypKategorie(x, y-1).equals(figurtypKategorieBesieger) || randfelder(x, y-1))) {
					setFigurtyp(x, y, new LEER());
					geschlageneFigurenPosition.add(x);
					geschlageneFigurenPosition.add(y);
					if(figurtypBesiegter.equals("Schwede")) {
						geschlageneFigurenTypus.add(1);
					}
					else {
						geschlageneFigurenTypus.add(0);
					}
					return 1;
				}
			}
		}
		return 0;
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
		if(x==3 && y==3 || x==0 && y==0 || x==0 && y==6 || x==6 && y==0 || x==6 && y==6) {
			return true;
		}
		return false;
	}

	public boolean randfelder(int x, int y) {
		if(x==0 && y==0 || x==0 && y==6 || x==6 && y==0 || x==6 && y==6) {
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

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ARD_RI_TAFL s = new ARD_RI_TAFL();
	}














}

