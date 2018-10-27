
public class ZUG_MASCHINE extends ZUG {

	public ZUG_MASCHINE(String aktivesTeam, FETLAR_HNEFATAFL spiel) {
		super(aktivesTeam, spiel);
	}

	@Override
	public FETLAR_HNEFATAFL AmZug(int felder, String richtung, int x_Ausgang, int y_Ausgang) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean GueltigerZug(int felder, String richtung, int x_Ausgang, int y_Ausgang) {
		if(aktuellesSpiel.getFigurtypKategorie(x_Ausgang, y_Ausgang).equals(aktivesTeam)) {
			if(aktuellesSpiel.Bewegen(felder, richtung, x_Ausgang, y_Ausgang)) {
				return true;
			}
		}
		return false;
	}

}
