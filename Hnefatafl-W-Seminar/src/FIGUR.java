
public abstract class FIGUR {
	//ALLGEMEIN
	
	/*
	 * Abstrakte Oberklasse für alle Figuren
	 * Enthält, für die Objektorientierte Umsetzung, nur den Figurtyp.
	 * Ursprünglich geplant war auch die Angabe von x- und y-Positionen  
	 */

	//ATTRIBUTE

	private String figurtyp;
	private String shortcut;
	//private int posX;
	//private int posY;

	//KONSTRUKTOR
	public FIGUR(String figurtyp, String shortcut){
		this.figurtyp = figurtyp;
		this.shortcut = shortcut;
		//this.posX = posX;
		//this.posY = posY;
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