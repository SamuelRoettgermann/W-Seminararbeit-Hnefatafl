package ARD_RI_TAFL;

import java.util.ArrayList;

public class TABLUT_HNEFATAFL {

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
	public TABLUT_HNEFATAFL() {
		this.spielfeld = new FELD[9][9];
		Startaufstellung();
		Konsolenausgabe();
		//geschlageneFigurenPosition wird sich nur um die Figuren kümmern die während der Berechnung des "maschinellen Zuges" geschlagen wurden, so dass sie "wiederbelebt" werden können
		geschlageneFigurenPosition = new ArrayList<Integer>(); 
		//geschlageneFigurenTypus wird sich nur um die Figuren kümmern die während der Berechnung des "maschinellen Zuges" geschlagen wurden, so dass sie "wiederbelebt" werden können
		geschlageneFigurenTypus = new ArrayList<Integer>();
	}

	//Stellt die Startaufstellung her
	public void Startaufstellung() {
		for(int x=0; x<=8; x++) {
			for(int y=0; y<=8; y++) {
				this.spielfeld[x][y] = new FELD(new LEER());
			}
		}
		for(int i=3; i<=5; i++) {
			this.spielfeld[i][0] = new FELD(new RUSSE());
			this.spielfeld[i][8] = new FELD(new RUSSE());
			this.spielfeld[0][i] = new FELD(new RUSSE());
			this.spielfeld[8][i] = new FELD(new RUSSE());
			this.spielfeld[i][4] = new FELD(new SCHWEDE());
			this.spielfeld[4][i] = new FELD(new SCHWEDE());
		}
		this.spielfeld[4][1] = new FELD(new RUSSE());
		this.spielfeld[1][4] = new FELD(new RUSSE());
		this.spielfeld[4][7] = new FELD(new RUSSE());
		this.spielfeld[7][4] = new FELD(new RUSSE());
		
		this.spielfeld[4][2] = new FELD(new SCHWEDE());
		this.spielfeld[2][4] = new FELD(new SCHWEDE());
		this.spielfeld[6][4] = new FELD(new SCHWEDE());
		this.spielfeld[4][6] = new FELD(new SCHWEDE());
		this.spielfeld[4][4] = new FELD(new KOENIG());
	}

	public void Konsolenausgabe() {
		System.out.print("\n");
		System.out.print("yx"+" ");
		for(int x=0; x<=8; x++) {
			System.out.print(x+1+" ");
		}
		
		System.out.print("\n");

		for(int y=0; y<=8; y++) {
			System.out.print(y+1+"  ");
			for(int x=0; x<=8; x++) {
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
		
		geschlageneFigurenPosition.remove(geschlageneFigurenPosition.size()-1);
		geschlageneFigurenPosition.remove(geschlageneFigurenPosition.size()-1);
		geschlageneFigurenTypus.remove(geschlageneFigurenTypus.size()-1);
		
		setFigurtyp(geschlageneFigurenPosition.get(geschlageneFigurenPosition.size()-2), geschlageneFigurenPosition.get(geschlageneFigurenPosition.size()-1), tempfig);
		
	}

	
	
	

	public boolean UeberpruefeSchlagenGrobMaschine(int x_End, int y_End) {

		/*
		 * "dieserFigurtyp" meint eigentlich "dieseFigurtypKategorie". Da die Variable aber so oft benötigt wird, wurde
		 * sie hier vereinfacht nur "dieserFigurtyp" genannt. (Außerdem hatte ich das jetzt schon so gemacht und keinen
		 * Bock mehr das noch mal mit STRG + F alles umzubenennen (Change it in the end if you mind)
		 */

		String dieserFigurtyp;
		dieserFigurtyp = getFigurtypKategorie(x_End, y_End);



		if(x_End > 1) {
			if(!getFigurtypKategorie(x_End-1, y_End).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End-1,  y_End))) {
				return UeberpruefeSchlagenDetailMaschine(x_End-1, y_End, getFigurtyp(x_End-1, y_End), dieserFigurtyp, "x");
			}
		}

		if(x_End < 9) {
			if(!getFigurtypKategorie(x_End+1, y_End).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End+1,  y_End))) {
				return UeberpruefeSchlagenDetailMaschine(x_End+1, y_End, getFigurtyp(x_End+1, y_End), dieserFigurtyp ,"x");
			}
		}

		if(y_End > 1) {
			if(!getFigurtypKategorie(x_End, y_End-1).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End,  y_End-1))) {
				return UeberpruefeSchlagenDetailMaschine(x_End, y_End-1, getFigurtyp(x_End, y_End-1), dieserFigurtyp, "y");
			}
		}

		if(y_End < 9) {
			if(!getFigurtypKategorie(x_End, y_End+1).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End,  y_End+1))) {
				return UeberpruefeSchlagenDetailMaschine(x_End, y_End+1, getFigurtyp(x_End, y_End+1), dieserFigurtyp, "y");
			}
		}
		return false;
	}


	
	
	public boolean UeberpruefeSchlagenDetailMaschine(int x, int y, String figurtypBesiegter, String figurtypKategorieBesieger, String Schlagrichtung) {
		if("Koenig".equals(figurtypBesiegter)) {
			if(getFigurtypKategorie(x-1, y).equals(figurtypKategorieBesieger) && getFigurtypKategorie(x+1, y).equals(figurtypKategorieBesieger)
					&& getFigurtypKategorie(x, y+1).equals(figurtypKategorieBesieger) && getFigurtypKategorie(x, y-1).equals(figurtypKategorieBesieger)) {
				setFigurtyp(x, y, new LEER());
				geschlageneFigurenPosition.add(x);
				geschlageneFigurenPosition.add(y);
				geschlageneFigurenTypus.add(2);
				return true;
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
					return true;
					
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
					return true;
				}
			}
		}
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

	

	public boolean exklusivfelder(int x, int y) {
		if(x==4 && y==4 || x==0 && y==0 || x==0 && y==8 || x==8 && y==0 || x==8 && y==8) {
			return true;
		}
		return false;
	}
	
	public boolean randfelder(int x, int y) {
		if(x==0 && y==0 || x==0 && y==8 || x==8 && y==0 || x==8 && y==8) {
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
		
	}

	

	

	








}

