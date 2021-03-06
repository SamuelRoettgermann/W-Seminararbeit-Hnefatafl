package ARD_RI_TAFL;


public abstract class FIGUR {
	//ALLGEMEIN
	
	/*
	 * Abstrakte Oberklasse f�r alle Figuren
	 * Enth�lt, f�r die Objektorientierte Umsetzung, nur den Figurtyp.
	 * Urspr�nglich geplant war auch die Angabe von x- und y-Positionen  
	 */

	//ATTRIBUTE

	private String figurtyp;
	private String shortcut;

	//KONSTRUKTOR
	public FIGUR(String figurtyp, String shortcut){
		this.figurtyp = figurtyp;
		this.shortcut = shortcut;
	}

	//METHODEN
	
	public String getFigurtyp() {
		return figurtyp;
	}
	
	public String getShortcut() {
		return shortcut;
	}
	
	
	/*
	public int getposX() {
		return posX;
	}
	
	public int getposY() {
		return posY;
	}
	
	public void setposX(int posX) {
		this.posX = posX;
	}
	
	public void setposY(int posY) {
		this.posY = posY;
	}
	*/
	
}