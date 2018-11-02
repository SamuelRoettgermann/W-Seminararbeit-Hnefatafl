
public abstract class ZUG {

	protected String team;
	protected int felderberechnet;
	protected String richtungberechnet;
	protected int x_Ausgangberechnet;
	protected int y_Ausgangberechnet;

	public ZUG(String team) {
		this.team = team;
	}

	public abstract boolean AmZug(int felder, String richtung, int x_Ausgang, int y_Ausgang, FETLAR_HNEFATAFL spiel);

	public abstract boolean GueltigerZug(int felder, String richtung, int x_Ausgang, int y_Ausgang, FETLAR_HNEFATAFL spiel);

	public boolean BewegenMoeglich(int felder, String richtung, int x_Ausgang, int y_Ausgang, FETLAR_HNEFATAFL spiel) {
		if(felder == 0) {
			System.out.print("\n"+"Ihre Figur kann nicht auf der Stelle stehen bleiben");
			return false;
		}
		if(!"x".equals(richtung) && !"y".equals(richtung)) {
			System.out.print("\n"+"Sie müssen ihre Figur in x- oder y-Richtung bewegen");
			return false;
		}

		if(spiel.getFigurtyp(x_Ausgang, y_Ausgang).equals("leer")) {
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
		int ausgang = spiel.getrelativeausgang(x_Faktor, y_Faktor, x_Ausgang, y_Ausgang);

		if(ausgang+felder<=10 && ausgang+felder>=0) {
			for(int Differenz=abweichung; Differenz<=felder || Differenz>=felder; Differenz+=abweichung) {
				if(spiel.getFigurtyp(x_Ausgang+(Differenz*x_Faktor), y_Ausgang+(Differenz*y_Faktor)).equals("leer")) {
					i+=abweichung;
					if(i== felder) {
						if(spiel.exklusivfelder(x_Ausgang+(felder*x_Faktor), y_Ausgang+(felder*y_Faktor))==false){

							return true;

						}
						else
						{
							if(spiel.getFigurtyp(x_Ausgang, y_Ausgang).equals("Koenig")) {  //KOENIG Sonderbehandlung
								return true;
							}
						}
						System.out.println("Ihre Figur konnte nicht bewegt werden."+"\n"+"Bitte ueberpruefen Sie, ob"
								+ " die Laufbahn, sowie das Ziel frei ist, achten Sie darauf das das Ziel noch auf dem Spielbrett"
								+ "\n" + "liegt, oder ob das Ziel ein Exklusivfeld ist, sollten sie nicht den Koenig ziehen.");
						return false;
					}
				}
				
			}
		}

		System.out.println("Ihre Figur konnte nicht bewegt werden."+"\n"+"Bitte ueberpruefen Sie, ob"
				+ " die Laufbahn, sowie das Ziel frei ist, achten Sie darauf das das Ziel noch auf dem Spielbrett"
				+ "\n" + "liegt, oder ob das Ziel ein Exklusivfeld ist, sollten sie nicht den Koenig ziehen.");
		return false;
	}
		
	

	public int RueckgabeFelder() {
		return felderberechnet;
	}
	
	public String RueckgabeRichtung() {
		return richtungberechnet;
	}
	
	public int RueckgabexAusgang() {
		return x_Ausgangberechnet;
	}
	
	public int RueckgabeyAusgang() {
		return y_Ausgangberechnet;
	}

}
