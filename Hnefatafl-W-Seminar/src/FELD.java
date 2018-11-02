
public class FELD {
	
	/*
	 * Ein Feld ist ein "Kästchen", auf dem eine Figur abgestellt werden kann.
	 * Ein Feld hat eine
	 */
	
	private FIGUR figur;
	
	public FELD(FIGUR figur) {
		this.figur = figur;
	}
	
	
	public FIGUR getfigur() {
		return figur;
	}
	
	public void setfigur(FIGUR figur) {
		this.figur = figur;
	}
	
	public String getFigurtyp() {
		return figur.getFigurtyp();
	}
	
	public String getShortcut() {
		return figur.getShortcut();
	}
	
	public String getFigurtypKategorie() {
		if(getFigurtyp().equals("Russe")) {
			return "schwarz";
		}
		if(getFigurtyp().equals("Schwede") || getFigurtyp().equals("Koenig")) {
			return "weiss";
		}
		return "leer";
	}
	
}
