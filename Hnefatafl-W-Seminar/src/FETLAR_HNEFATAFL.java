
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
	 * 3. Einfaches Bewegen (ohne alle Kontrollen) muss möglich sein			
	 * 4. Kommunikation zwischen den Feldern muss möglich sein
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
	
	public void Bewegen(int felder, String richtung, int x_Ausgang, int y_Ausgang, String shortcut) {
		if(felder == 0) {
			System.out.print("\n");
			System.out.print("Ihre Figur kann nicht auf der Stelle stehen bleiben");
		}
	}
	
	public String getShortcut(int x, int y) {
		return this.spielfeld[x][y].getShortcut();
	}
	
	public String getFigurtyp(int x, int y) {
		return this.spielfeld[x][y].getFigurtyp();
	}
	
	public static void main(String[] args) {
		FETLAR_HNEFATAFL testfeld = new FETLAR_HNEFATAFL();
		testfeld.Konsolenausgabe();
		testfeld.getShortcut(0,0);
	}
}

