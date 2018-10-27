
public abstract class ZUG {
	
	protected String aktivesTeam;
	protected FETLAR_HNEFATAFL aktuellesSpiel;
	
	public ZUG(String aktivesTeam, FETLAR_HNEFATAFL aktuellesSpiel) {
		this.aktivesTeam = aktivesTeam;
		this.aktuellesSpiel = aktuellesSpiel;
	}
	
	public abstract FETLAR_HNEFATAFL AmZug(int felder, String richtung, int x_Ausgang, int y_Ausgang);
	
	public abstract boolean GueltigerZug(int felder, String richtung, int x_Ausgang, int y_Ausgang);
	
}
