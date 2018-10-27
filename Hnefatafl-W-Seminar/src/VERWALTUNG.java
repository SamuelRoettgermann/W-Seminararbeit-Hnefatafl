
public class VERWALTUNG {

	private FETLAR_HNEFATAFL spiel;
	private String aktivesTeam;
	private String spielerweiss, spielerschwarz;

	public VERWALTUNG(String aktivesTeam, String spielerweiss, String spielerschwarz) {
		this.spiel = new FETLAR_HNEFATAFL();
		this.aktivesTeam = aktivesTeam;
		this.spielerweiss = spielerweiss;
		this.spielerschwarz = spielerschwarz;
	}

	public void ZugAusfuehren() {
		if(aktivesTeam.equals("weiss")) {
			if(spielerweiss.equals("Mensch")) {
				new ZUG_MENSCH(aktivesTeam, spiel);
			}
			else
			{
				new ZUG_MASCHINE(aktivesTeam, spiel);
			}
		}
		else
		{
			if(spielerschwarz.equals("Mensch")) {
				new ZUG_MENSCH(aktivesTeam, spiel);
			}
			else
			{
				new ZUG_MASCHINE(aktivesTeam, spiel);
			}
		}
	}

}
