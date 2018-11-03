
public class VERWALTUNG {

	private ARD_RI_TAFL spiel;
	private String aktivesTeam;
	private ZUG spielerweiss, spielerschwarz;

	public VERWALTUNG(ZUG spielerschwarz, ZUG spielerweiss) {
		this.spiel = new ARD_RI_TAFL();
		this.aktivesTeam = "schwarz";
		this.spielerweiss = spielerweiss;
		this.spielerschwarz = spielerschwarz;
	}

	public void AmZug(int felder, String richtung, int x_Ausgang, int y_Ausgang) {
		int x_End;
		int y_End;
		if(aktivesTeam.equals("schwarz")) {
			if(spielerschwarz.AmZug(felder, richtung, x_Ausgang, y_Ausgang, spiel)) {
				spiel.Bewegen(spielerschwarz.RueckgabeFelder(), spielerschwarz.RueckgabeRichtung(), spielerschwarz.RueckgabexAusgang(), spielerschwarz.RueckgabeyAusgang());
				if(spielerschwarz.RueckgabeRichtung().equals("x")) {
					x_End = spielerschwarz.RueckgabexAusgang()+spielerschwarz.RueckgabeFelder();
					y_End = spielerschwarz.RueckgabeyAusgang();
				}
				else {
					x_End = spielerschwarz.RueckgabexAusgang();
					y_End = spielerschwarz.RueckgabeyAusgang()+spielerschwarz.RueckgabeFelder();
				}
				UeberpruefeSchlagenGrob(x_End, y_End);
				WechselAktivesTeam();
			}
		}

		else if(aktivesTeam.equals("weiss")) {
			if(spielerweiss.AmZug(felder, richtung, x_Ausgang, y_Ausgang, spiel)) {
				spiel.Bewegen(spielerweiss.RueckgabeFelder(), spielerweiss.RueckgabeRichtung(), spielerweiss.RueckgabexAusgang(), spielerweiss.RueckgabeyAusgang());
				if(spielerweiss.RueckgabeRichtung().equals("x")) {
					x_End = spielerweiss.RueckgabexAusgang()+spielerweiss.RueckgabeFelder();
					y_End = spielerweiss.RueckgabeyAusgang();
				}
				else {
					x_End = spielerweiss.RueckgabexAusgang();
					y_End = spielerweiss.RueckgabeyAusgang()+spielerweiss.RueckgabeFelder();
				}
				UeberpruefeSchlagenGrob(x_End, y_End);
				WechselAktivesTeam();
			}
		}
		
		else {
			System.out.println("\n"+"Figur des falschen Teams wurde bewegt. Bitte Figur des richtigen Teams bewegen");
		}
		spiel.Konsolenausgabe();
		
		

	}




	public void UeberpruefeSieg() {

		for(FELD[] f : spiel.getSpielfeld()) {
			for(FELD g : f)
			{
				if(g.getFigurtyp().equals("Koenig")) {
					return;
				}
			}
		}

		if(getFigurtyp(0, 0).equals("Koenig") || getFigurtyp(0, 8).equals("Koenig") || getFigurtyp(8, 0).equals("Koenig") || getFigurtyp(8, 8).equals("Koenig")) { 
			//Prüft ob der König sich auf einem Eckfeld befindet, wenn ja, gewinnt weiß (Sollte aus der Bewegen Methode rausgezogen werden)
			System.out.print("WEISS GEWINNT!"+"\n"+"Der König hat ein Eckfeld erreicht");
			System.exit(0);
		}
		System.out.println("SCHWARZ GEWINNT!"+"\n"+"Der König wurde geschlagen");
		System.exit(0);

	}

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

		if(x_End < 5) {
			if(!getFigurtypKategorie(x_End+1, y_End).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End+1,  y_End))) {
				UeberpruefeSchlagenDetail(x_End+1, y_End, getFigurtyp(x_End+1, y_End), dieserFigurtyp ,"x");
			}
		}

		if(y_End > 1) {
			if(!getFigurtypKategorie(x_End, y_End-1).equals(dieserFigurtyp) && !"leer".equals(getFigurtypKategorie(x_End,  y_End-1))) {
				UeberpruefeSchlagenDetail(x_End, y_End-1, getFigurtyp(x_End, y_End-1), dieserFigurtyp, "y");
			}
		}

		if(y_End < 5) {
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
				//König wurde geschlagen
			}
		}
		else
		{
			if(Schlagrichtung.equals("x")) {
				if((getFigurtypKategorie(x-1, y).equals(figurtypKategorieBesieger) || spiel.randfelder(x-1, y)) 
						&& (getFigurtypKategorie(x+1, y).equals(figurtypKategorieBesieger) || spiel.randfelder(x+1, y))) {
					setFigurtyp(x, y, new LEER());
				}
			}
			if(Schlagrichtung.equals("y")) {
				if((getFigurtypKategorie(x, y+1).equals(figurtypKategorieBesieger) || spiel.randfelder(x, y+1)) 
						&& (getFigurtypKategorie(x, y-1).equals(figurtypKategorieBesieger) || spiel.randfelder(x, y-1))) {
					setFigurtyp(x, y, new LEER());
				}
			}
		}
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

	public String getFigurtyp(int x, int y) {
		return this.spiel.getFigurtyp(x, y);
	}

	public void setFigurtyp(int x, int y, FIGUR figur) {
		this.spiel.setFigurtyp(x, y, figur);
	}

	public void WechselAktivesTeam() {
		if(aktivesTeam.equals("schwarz")) {
			aktivesTeam = "weiss";
		}
		else {
			aktivesTeam = "schwarz";
		}
	}



	public static void main(String[] args) {
		ZUG spielerschwarztest = new ZUG_MENSCH("schwarz");
		ZUG spielerweisstest = new ZUG_MENSCH("weiss");
		VERWALTUNG testspiel = new VERWALTUNG(spielerschwarztest, spielerweisstest);

		testspiel.AmZug(1, "y", 7, 5); //R
		testspiel.AmZug(2, "x", 5, 5); //S
		testspiel.AmZug(3, "x", 4, 2); //R
		testspiel.AmZug(-1, "x", 3, 3); //S
		testspiel.AmZug(2, "y", 2, 4); //R


	}



	/*

	public boolean BewegenMoeglich(int felder, String richtung, int x_Ausgang, int y_Ausgang) {
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
		int ausgang = spiel.getrelativeausgang(x_Faktor, y_Faktor, x_Ausgang, y_Ausgang);

		if(ausgang+felder<=10 && ausgang+felder>=0) {
			for(int Differenz=1; Differenz<=felder; Differenz+=abweichung) {
				if(getFigurtyp(x_Ausgang+(Differenz*x_Faktor), y_Ausgang+(Differenz*y_Faktor)).equals("leer")) {
					i++;
					if(i== felder) {
						if(spiel.exklusivfelder(x_Ausgang+(felder*x_Faktor), y_Ausgang+(felder*y_Faktor))==false){

							return true;

						}
						else
						{
							if(getFigurtyp(x_Ausgang, y_Ausgang).equals("Koenig")) {  //KOENIG Sonderbehandlung
								return true;
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

	 */

}
