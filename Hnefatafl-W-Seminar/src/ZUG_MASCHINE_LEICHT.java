

public class ZUG_MASCHINE_LEICHT extends ZUG {

	int anzahl = 0;


	public ZUG_MASCHINE_LEICHT(String team) {
		super(team);
		felderberechnet = 0;
		richtungberechnet = "x";
		x_Ausgangberechnet = 0;
		y_Ausgangberechnet = 0;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see ZUG#AmZug(int, java.lang.String, int, int, ARD_RI_TAFL)
	 * 
	 * Schwarz maximiert immer und weiß minimiert immer.
	 * Es wird jeweils anders gestartet, damit die richtigen Züge gespeichert werden können.
	 * Natürlich könnte man die max/min-Auswahl auch Variabel machen, allerdings ist das nur unnötiger Rechenaufwand, der auch vermieden werden kann.
	 */
	public boolean AmZug(int felder, String richtung, int x_Ausgang, int y_Ausgang, ARD_RI_TAFL spiel) {
		if(team.equals("schwarz")) {
			max(2, spiel);
		}
		else {
			min(2, spiel);
		}
		return false;
	}

	@Override
	public boolean GueltigerZug(int felder, String richtung, int x_Ausgang, int y_Ausgang, ARD_RI_TAFL spiel) {
		if(spiel.getFigurtypKategorie(x_Ausgang, y_Ausgang).equals(team)) {
			return super.BewegenMoeglich(felder, richtung, x_Ausgang, y_Ausgang, spiel);
		}
		return false;
	}

	public void Minimax(int tiefe, int maxTiefe, ARD_RI_TAFL spiel) {
		if(tiefe == maxTiefe || UeberpruefeSieg(spiel)) {

		}
	}

	/*
	 * Noch fehlt die Auswertung des Zuges, sowie die Möglichkeit den Scheiß Zug zu speichern
	 * EDIT: Der Scheiß Zug kann jetzt hoffentlich gespeichert werden
	 */

	int max(int depth, ARD_RI_TAFL spiel) {
		if(depth == 0 || UeberpruefeSieg(spiel)) {
			return Auswertung(spiel);
		}
		int maxWert = -1000;
		int value;


		for(int x = 0; x <= 6; x++) {
			for(int y = 0; y <= 6; y++) {
				if(spiel.getFigurtyp(x, y).equals("Russe")) {
					for(int xn = x-1; xn >= 0; xn--) {
						if(spiel.getFigurtyp(xn, y).equals("leer")) {
							if(spiel.exklusivfelder(x+(xn-x), y) == false) {
								spiel.Bewegen(xn-x, "x", x, y);
								if(spiel.UeberpruefeSchlagenGrobMaschine(x+(xn-x), y)) {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(xn-x, "x", x, y);
									spiel.SchlagenRueckgaengig();
								}
								else {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(xn-x, "x", x, y);
								}
								if(value >= maxWert) {
									maxWert = value;
									felderberechnet = xn-x;
									richtungberechnet = "x";
									x_Ausgangberechnet = x;
									y_Ausgangberechnet = y;

								}
							}
						}
					}
					for(int xp = x+1; xp <= 6; xp++) {
						if(spiel.getFigurtyp(xp, y).equals("leer")) {
							if(spiel.exklusivfelder(x+(xp-x), y) == false) {
								spiel.Bewegen(xp-x, "x", x, y);
								if(spiel.UeberpruefeSchlagenGrobMaschine(x+(xp-x), y)) {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(xp-x, "x", x, y);
									spiel.SchlagenRueckgaengig();
								}
								else {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(xp-x, "x", x, y);
								}
								if(value >= maxWert) {
									maxWert = value;
									felderberechnet = xp-x;
									richtungberechnet = "x";
									x_Ausgangberechnet = x;
									y_Ausgangberechnet = y;
								}
							}
						}
					}
					for(int yn = y-1; yn >= 0; yn--) {
						if(spiel.getFigurtyp(x, yn).equals("leer")) {
							if(spiel.exklusivfelder(x, y+(yn-y)) == false) {
								spiel.Bewegen(yn-y, "x", x, y);
								if(spiel.UeberpruefeSchlagenGrobMaschine(x, y+(yn-y))) {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(yn-y, "x", x, y);
									spiel.SchlagenRueckgaengig();
								}
								else {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(yn-y, "x", x, y);
								}
								if(value >= maxWert) {
									maxWert = value;
									felderberechnet = yn-y;
									richtungberechnet = "x";
									x_Ausgangberechnet = x;
									y_Ausgangberechnet = y;
								}
							}
						}
					}
					for(int yp = y+1; yp <= 6; yp++) {
						if(spiel.getFigurtyp(x, yp).equals("leer")) {
							if(spiel.exklusivfelder(x, y+(yp-y)) == false) {
								spiel.Bewegen(yp-y, "x", x, y);
								if(spiel.UeberpruefeSchlagenGrobMaschine(x, y+(yp-y))) {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(yp-y, "x", x, y);
									spiel.SchlagenRueckgaengig();
								}
								else {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(yp-y, "x", x, y);
								}
								if(value >= maxWert) {
									maxWert = value;
									felderberechnet = yp-y;
									richtungberechnet = "x";
									x_Ausgangberechnet = x;
									y_Ausgangberechnet = y;
								}
							}
						}
					}
				}
			}
		}
		return maxWert;
	}

	int min(int depth, ARD_RI_TAFL spiel) {
		if(depth == 0 || UeberpruefeSieg(spiel)) {
			return Auswertung(spiel);
		}
		int minWert = 1000;
		int value;


		for(int x = 0; x <= 6; x++) {
			for(int y = 0; y <= 6; y++) {
				if(spiel.getFigurtyp(x, y).equals("Schwede")) {
					for(int xn = x-1; xn >= 0; xn--) {
						if(spiel.getFigurtyp(xn, y).equals("leer")) {
							if(spiel.exklusivfelder(x+(xn-x), y) == false) {
								spiel.Bewegen(xn-x, "x", x, y);
								if(spiel.UeberpruefeSchlagenGrobMaschine(x+(xn-x), y)) {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(xn-x, "x", x, y);
									spiel.SchlagenRueckgaengig();
								}
								else {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(xn-x, "x", x, y);
								}									
								if(value <= minWert) {
									minWert = value;
									felderberechnet = xn-x;
									richtungberechnet = "x";
									x_Ausgangberechnet = x;
									y_Ausgangberechnet = y;
								}
							}
						}
					}
					for(int xp = x+1; xp <= 6; xp++) {
						if(spiel.getFigurtyp(xp, y).equals("leer")) {
							if(spiel.exklusivfelder(x+(xp-x), y) == false) {
								spiel.Bewegen(xp-x, "x", x, y);
								if(spiel.UeberpruefeSchlagenGrobMaschine(x+(xp-x), y)) {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(xp-x, "x", x, y);
									spiel.SchlagenRueckgaengig();
								}
								else {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(xp-x, "x", x, y);
								}
								if(value <= minWert) {
									minWert = value;
									felderberechnet = xp-x;
									richtungberechnet = "x";
									x_Ausgangberechnet = x;
									y_Ausgangberechnet = y;
								}
							}
						}
					}
					for(int yn = x-1; yn >= 0; yn--) {
						if(spiel.getFigurtyp(x, yn).equals("leer")) {
							if(spiel.exklusivfelder(x, y+(yn-y)) == false) {
								spiel.Bewegen(yn-y, "x", x, y);
								if(spiel.UeberpruefeSchlagenGrobMaschine(x, y+(yn-y))) {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(yn-y, "x", x, y);
									spiel.SchlagenRueckgaengig();
								}
								else {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(yn-y, "x", x, y);
								}
								if(value <= minWert) {
									minWert = value;
									felderberechnet = yn-y;
									richtungberechnet = "x";
									x_Ausgangberechnet = x;
									y_Ausgangberechnet = y;
								}
							}
						}
					}
					for(int yp = x+1; yp <= 6; yp++) {
						if(spiel.getFigurtyp(x, yp).equals("leer")) {
							if(spiel.exklusivfelder(x, y+(yp-y)) == false) {
								spiel.Bewegen(yp-y, "x", x, y);
								if(spiel.UeberpruefeSchlagenGrobMaschine(x, y+(yp-y))) {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(yp-y, "x", x, y);
									spiel.SchlagenRueckgaengig();
								}
								else {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(yp-y, "x", x, y);
								}
								if(value <= minWert) {
									minWert = value;
									felderberechnet = yp-y;
									richtungberechnet = "x";
									x_Ausgangberechnet = x;
									y_Ausgangberechnet = y;
								}
							}
						}
					}
				}




				if(spiel.getFigurtyp(x, y).equals("Koenig")) {
					for(int xn = x-1; xn >= 0; xn--) {
						if(spiel.getFigurtyp(xn, y).equals("leer")) {
							if(spiel.exklusivfelder(x+(xn-x), y) == false) {
								spiel.Bewegen(xn-x, "x", x, y);
								if(spiel.UeberpruefeSchlagenGrobMaschine(x+(xn-x), y)) {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(xn-x, "x", x, y);
									spiel.SchlagenRueckgaengig();
								}
								else {
									value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(xn-x, "x", x, y);
								}									
								if(value <= minWert) {
									minWert = value;
									felderberechnet = xn-x;
									richtungberechnet = "x";
									x_Ausgangberechnet = x;
									y_Ausgangberechnet = y;
								}
							}
						}
					}
					for(int xp = x+1; xp <= 6; xp++) {
						if(spiel.getFigurtyp(xp, y).equals("leer")) {
							spiel.Bewegen(xp-x, "x", x, y);
							if(spiel.UeberpruefeSchlagenGrobMaschine(x+(xp-x), y)) {
								value = max(depth - 1, spiel);
								spiel.BewegenRueckgaengig(xp-x, "x", x, y);
								spiel.SchlagenRueckgaengig();
							}
							else {
								value = max(depth - 1, spiel);
								spiel.BewegenRueckgaengig(xp-x, "x", x, y);
							}
							if(value <= minWert) {
								minWert = value;
								felderberechnet = xp-x;
								richtungberechnet = "x";
								x_Ausgangberechnet = x;
								y_Ausgangberechnet = y;
							}
						}
					}
					for(int yn = x-1; yn >= 0; yn--) {
						if(spiel.getFigurtyp(x, yn).equals("leer")) {
							spiel.Bewegen(yn-y, "x", x, y);
							if(spiel.UeberpruefeSchlagenGrobMaschine(x, y+(yn-y))) {
								value = max(depth - 1, spiel);
								spiel.BewegenRueckgaengig(yn-y, "x", x, y);
								spiel.SchlagenRueckgaengig();
							}
							else {
								value = max(depth - 1, spiel);
								spiel.BewegenRueckgaengig(yn-y, "x", x, y);
							}
							if(value <= minWert) {
								minWert = value;
								felderberechnet = yn-y;
								richtungberechnet = "x";
								x_Ausgangberechnet = x;
								y_Ausgangberechnet = y;
							}
						}
					}
					for(int yp = x+1; yp <= 6; yp++) {
						if(spiel.getFigurtyp(x, yp).equals("leer")) {
							spiel.Bewegen(yp-y, "x", x, y);
							if(spiel.UeberpruefeSchlagenGrobMaschine(x, y+(yp-y))) {
								value = max(depth - 1, spiel);
								spiel.BewegenRueckgaengig(yp-y, "x", x, y);
								spiel.SchlagenRueckgaengig();
							}
							else {
								value = max(depth - 1, spiel);
								spiel.BewegenRueckgaengig(yp-y, "x", x, y);
							}
							if(value <= minWert) {
								minWert = value;
								felderberechnet = yp-y;
								richtungberechnet = "x";
								x_Ausgangberechnet = x;
								y_Ausgangberechnet = y;
							}
						}
					}
				}
			}
		}
		return minWert;
	}


	//Schwarz maximiert immer und weiß minimiert immer
	public int Auswertung(ARD_RI_TAFL spiel) {
		int auswertung = 0;





		if(UeberpruefeSiegWeiss(spiel)) {
			auswertung = -1000;
		}
		if(UeberpruefeSiegSchwarz(spiel)) {
			auswertung = 1000;
		}



		return auswertung;

	}


	/*
	 * Hier werden alle möglichen Züge für das aktive Team berechnet

	public ArrayList<Method> ErzeugeMoeglicheZuege(String aktivesTeam, FETLAR_HNEFATAFL spiel) {
		ArrayList<Method> alleMoeglichenZuege = new ArrayList<Method>();
		for(int x = 0; x <= 10; x++) {
			for(int y = 0; y <= 10; y++) {
				if(spiel.getFigurtypKategorie(x, y).equals(aktivesTeam)) {
					for(int xn = x-1; xn >= 0; xn--) {
						if(spiel.getFigurtyp(xn, y).equals("leer")) {

						}
					}
					for(int xp = x+1; xp <= 10; xp++) {
						if(spiel.getFigurtyp(xp, y).equals("leer")) {

						}
					}
					for(int yn = x-1; yn >= 0; yn--) {
						if(spiel.getFigurtyp(yn, y).equals("leer")) {

						}
					}
					for(int yp = x+1; yp <= 10; yp++) {
						if(spiel.getFigurtyp(yp, y).equals("leer")) {

						}
					}
				}
			}
		}
	}
	 */




	public int MoeglicheZuegeAnzahl(ARD_RI_TAFL spiel, String aktivesTeam, int tiefe) {

		if(tiefe == 0) {
			anzahl++;
			return anzahl;
		}

		for(int x = 0; x <= 6; x++) {
			for(int y = 0; y <= 6; y++) {
				if(spiel.getFigurtypKategorie(x, y).equals(aktivesTeam)) {
					for(int xn = x-1; xn >= 0; xn--) {
						if(spiel.getFigurtyp(xn, y).equals("leer")) {
							MoeglicheZuegeAnzahl(spiel, aktivesTeam, tiefe-1);

						}
					}
					for(int xp = x+1; xp <= 6; xp++) {
						if(spiel.getFigurtyp(xp, y).equals("leer")) {
							MoeglicheZuegeAnzahl(spiel, aktivesTeam, tiefe-1);

						}
					}
					for(int yn = x-1; yn >= 0; yn--) {
						if(spiel.getFigurtyp(x, yn).equals("leer")) {
							MoeglicheZuegeAnzahl(spiel, aktivesTeam, tiefe-1);

						}
					}
					for(int yp = x+1; yp <= 6; yp++) {
						if(spiel.getFigurtyp(x, yp).equals("leer")) {
							MoeglicheZuegeAnzahl(spiel, aktivesTeam, tiefe-1);

						}
					}
				}
			}
		}
		return anzahl;
	}



	public boolean UeberpruefeSieg(ARD_RI_TAFL spiel) {

		if(UeberpruefeSiegWeiss(spiel)==true) {
			return true;
		}

		return UeberpruefeSiegSchwarz(spiel);



	}

	private boolean UeberpruefeSiegWeiss(ARD_RI_TAFL spiel) {
		if(spiel.getFigurtyp(0, 0).equals("Koenig") || spiel.getFigurtyp(0, 6).equals("Koenig") || spiel.getFigurtyp(6, 0).equals("Koenig") || spiel.getFigurtyp(6, 6).equals("Koenig")) { 
			//Prüft ob der König sich auf einem Eckfeld befindet, wenn ja, gewinnt weiß
			return true;
		}
		return false;
	}

	private boolean UeberpruefeSiegSchwarz(ARD_RI_TAFL spiel) {

		for(FELD[] f : spiel.getSpielfeld()) {
			for(FELD g : f)
			{
				if(g.getFigurtyp().equals("Koenig")) {
					return false;
				}
			}
		}


		return true;

	}

	public String WechselAktivesTeam(String aktivesTeam) {
		if(aktivesTeam.equals("schwarz")) {
			return "weiss";
		}
		else {
			return "schwarz";
		}
	}

	public static void main(String[] args) {
		ZUG_MASCHINE_LEICHT s = new ZUG_MASCHINE_LEICHT("schwarz");
		ARD_RI_TAFL p = new ARD_RI_TAFL();
		System.out.println("\n"+s.MoeglicheZuegeAnzahl(p, "schwarz", 1));
		System.out.println("---------");
		s.anzahl = 0;
		System.out.println(s.MoeglicheZuegeAnzahl(p, "weiss", 1));

	}

}
