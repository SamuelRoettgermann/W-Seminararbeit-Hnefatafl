
public class ZUG_MENSCH extends ZUG {
	
	public ZUG_MENSCH(String aktivesTeam, FETLAR_HNEFATAFL aktuellesSpiel) {
		super(aktivesTeam, aktuellesSpiel);
		//AmZug(HIER MUSS IRGENDEIN LISTENER REIN DAMIT MAN DAS JETZT ERST ABFRAGEN KANN)
	}

	@Override
	public FETLAR_HNEFATAFL AmZug(int felder, String richtung, int x_Ausgang, int y_Ausgang) {
		FETLAR_HNEFATAFL altesSpiel = aktuellesSpiel;
		if(GueltigerZug(felder, richtung, x_Ausgang, y_Ausgang)) {
			return aktuellesSpiel;
		}
		return altesSpiel;
		

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
