
public abstract class FIGUR {
	//ALLGEMEIN

	//ATTRIBUTE

	private int figurtyp;
	private int posX;
	private int posY;

	//KONSTRUKTOR
	public FIGUR(int figurtyp, int posX, int posY){
		this.figurtyp = figurtyp;
		this.posX = posX;
		this.posY = posY;
	}

	//METHODEN
	
	public int getFigurtyp() {
		return figurtyp;
	}
	
	public int getposX() {
		return posX;
	}
	
	public int getposY() {
		return posY;
	}
	
	public void setposX(int newposX) {
		this.posX = newposX;
	}
	
	public void setposY(int newposY) {
		this.posY = newposY;
	}
}