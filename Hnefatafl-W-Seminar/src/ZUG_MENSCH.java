
//import java.util.Scanner;

public class ZUG_MENSCH extends ZUG {

	public ZUG_MENSCH(String Team) {
		super(Team);
		//AmZug(HIER MUSS IRGENDEIN LISTENER REIN DAMIT MAN DAS JETZT ERST ABFRAGEN KANN)
	}

	@Override
	public boolean AmZug(int felder, String richtung, int x_Ausgang, int y_Ausgang, ARD_RI_TAFL spiel) {
		
		x_Ausgang--;
		y_Ausgang--;
		
		if(GueltigerZug(felder, richtung, x_Ausgang, y_Ausgang, spiel)) {
			felderberechnet = felder;
			richtungberechnet = richtung;
			x_Ausgangberechnet = x_Ausgang;
			y_Ausgangberechnet = y_Ausgang;
			return true;
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
	
	






}
