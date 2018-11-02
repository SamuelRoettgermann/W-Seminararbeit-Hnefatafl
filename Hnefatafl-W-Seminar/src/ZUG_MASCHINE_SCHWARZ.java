
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ZUG_MASCHINE_SCHWARZ extends ZUG {

	public ZUG_MASCHINE_SCHWARZ(String team) {
		super(team);
	}

	@Override
	public boolean AmZug(int felder, String richtung, int x_Ausgang, int y_Ausgang, FETLAR_HNEFATAFL spiel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean GueltigerZug(int felder, String richtung, int x_Ausgang, int y_Ausgang, FETLAR_HNEFATAFL spiel) {
		if(spiel.getFigurtypKategorie(x_Ausgang, y_Ausgang).equals(team)) {
			return super.BewegenMoeglich(felder, richtung, x_Ausgang, y_Ausgang, spiel);
		}
		return false;
	}

	public void Minimax(int tiefe, int maxTiefe, FETLAR_HNEFATAFL spiel) {
		if(tiefe == maxTiefe || UeberpruefeSieg(spiel)) {

		}
	}


	int max(int depth, FETLAR_HNEFATAFL spiel) {
		String aktivesTeam = "schwarz";
		if(depth == 0 || UeberpruefeSieg(spiel)) {
			return eval(spiel, spieler);
		}
		int maxWert = -1000;


		for(int x = 0; x <= 10; x++) {
			for(int y = 0; y <= 10; y++) {
				if(spiel.getFigurtypKategorie(x, y).equals(aktivesTeam)) {
					for(int xn = x-1; xn >= 0; xn--) {
						if(spiel.getFigurtyp(xn, y).equals("leer")) {
							spiel.Bewegen(xn-x, "x", x, y);
							int value = max(depth - 1, spiel);
							spiel.BewegenRueckgaengig(xn-x, "x", x, y);
							if(value > maxWert) {
								maxWert = value;
							}
						}
						for(int xp = x+1; xp <= 10; xp++) {
							if(spiel.getFigurtyp(xp, y).equals("leer")) {
								spiel.Bewegen(xp-x, "x", x, y);
								int value = max(depth - 1, spiel);
								spiel.BewegenRueckgaengig(xp-x, "x", x, y);
								if(value > maxWert) {
									maxWert = value;
								}
							}
							for(int yn = x-1; yn >= 0; yn--) {
								if(spiel.getFigurtyp(yn, y).equals("leer")) {
									spiel.Bewegen(yn-x, "x", x, y);
									int value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(yn-x, "x", x, y);
									if(value > maxWert) {
										maxWert = value;
									}
								}
								for(int yp = x+1; yp <= 10; yp++) {
									if(spiel.getFigurtyp(yp, y).equals("leer")) {
										spiel.Bewegen(yp-x, "x", x, y);
										int value = max(depth - 1, spiel);
										spiel.BewegenRueckgaengig(yp-x, "x", x, y);
										if(value > maxWert) {
											maxWert = value;
										}
									}
								}
							}
						}

						/*
						pos.makeMove(m);
						int value = max(pos, depth - 1);
						pos.undoMove(m);
						if(value < minWert) {
						minWert = value;
						}
						 */
					}
				}
			}
		}
		return maxWert;
	}

	int min(int depth, FETLAR_HNEFATAFL spiel) {
		String aktivesTeam = "weiss";
		if(depth == 0 || UeberpruefeSieg(spiel)) {
			return eval(spiel, gegner);
		}
		int minWert = 1000000000;


		for(int x = 0; x <= 10; x++) {
			for(int y = 0; y <= 10; y++) {
				if(spiel.getFigurtypKategorie(x, y).equals(aktivesTeam)) {
					for(int xn = x-1; xn >= 0; xn--) {
						if(spiel.getFigurtyp(xn, y).equals("leer")) {
							spiel.Bewegen(xn-x, "x", x, y);
							int value = max(depth - 1, spiel);
							spiel.BewegenRueckgaengig(xn-x, "x", x, y);
							if(value < minWert) {
								minWert = value;
							}
						}
						for(int xp = x+1; xp <= 10; xp++) {
							if(spiel.getFigurtyp(xp, y).equals("leer")) {
								spiel.Bewegen(xp-x, "x", x, y);
								int value = max(depth - 1, spiel);
								spiel.BewegenRueckgaengig(xp-x, "x", x, y);
								if(value < minWert) {
									minWert = value;
								}
							}
							for(int yn = x-1; yn >= 0; yn--) {
								if(spiel.getFigurtyp(yn, y).equals("leer")) {
									spiel.Bewegen(yn-x, "x", x, y);
									int value = max(depth - 1, spiel);
									spiel.BewegenRueckgaengig(yn-x, "x", x, y);
									if(value < minWert) {
										minWert = value;
									}
								}
								for(int yp = x+1; yp <= 10; yp++) {
									if(spiel.getFigurtyp(yp, y).equals("leer")) {
										spiel.Bewegen(yp-x, "x", x, y);
										int value = max(depth - 1, spiel);
										spiel.BewegenRueckgaengig(yp-x, "x", x, y);
										if(value < minWert) {
											minWert = value;
										}
									}
								}
							}
						}

						/*
						pos.makeMove(m);
						int value = max(pos, depth - 1);
						pos.undoMove(m);
						if(value < minWert) {
						minWert = value;
						}
						 */
					}
				}
			}
		}
		return minWert;
	}


	public int Auswertung(FETLAR_HNEFATAFL)
	
	
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
	
	
	

	public void MoeglicheZuegeAnzahl(FETLAR_HNEFATAFL spiel, String aktivesTeam) {
		int anzahl = 0;

		for(int x = 0; x <= 10; x++) {
			for(int y = 0; y <= 10; y++) {
				if(spiel.getFigurtypKategorie(x, y).equals(aktivesTeam)) {
					for(int xn = x-1; xn >= 0; xn--) {
						if(spiel.getFigurtyp(xn, y).equals("leer")) {
							anzahl++;
						}
					}
					for(int xp = x+1; xp <= 10; xp++) {
						if(spiel.getFigurtyp(xp, y).equals("leer")) {
							anzahl++;
						}
					}
					for(int yn = x-1; yn >= 0; yn--) {
						if(spiel.getFigurtyp(yn, y).equals("leer")) {
							anzahl++;
						}
					}
					for(int yp = x+1; yp <= 10; yp++) {
						if(spiel.getFigurtyp(yp, y).equals("leer")) {
							anzahl++;
						}
					}
				}
			}
		}
		System.out.println("\n"+anzahl);
	}
	
	

	public boolean UeberpruefeSieg(FETLAR_HNEFATAFL spiel) {

		if(spiel.getFigurtyp(0, 0).equals("Koenig") || spiel.getFigurtyp(0, 10).equals("Koenig") || spiel.getFigurtyp(10, 0).equals("Koenig") || spiel.getFigurtyp(10, 10).equals("Koenig")) { 
			//Prüft ob der König sich auf einem Eckfeld befindet, wenn ja, gewinnt weiß
			return true;
		}

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
		ZUG_MASCHINE_SCHWARZ s = new ZUG_MASCHINE_SCHWARZ("schwarz");
		FETLAR_HNEFATAFL p = new FETLAR_HNEFATAFL();
		s.MoeglicheZuegeAnzahl(p, "schwarz");
		System.out.println("---------");
		s.MoeglicheZuegeAnzahl(p, "weiss");
	}

}
