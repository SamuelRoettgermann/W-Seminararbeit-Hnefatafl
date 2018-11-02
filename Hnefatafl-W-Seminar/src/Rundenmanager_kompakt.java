
public class Rundenmanager_kompakt {

	private FETLAR_HNEFATAFL spiel;
	private String aktivesTeam;

	Rundenmanager_kompakt() {
		spiel = new FETLAR_HNEFATAFL();
		aktivesTeam = "schwarz";
		spiel.Konsolenausgabe();
	}

	public void Bewegen(int felder, String richtung, int x_Ausgang, int y_Ausgang) {
		if(spiel.getFigurtypKategorie(x_Ausgang, y_Ausgang).equals(aktivesTeam)) {
			if(spiel.BewegenMoeglich(felder, richtung, x_Ausgang, y_Ausgang)) {
				spiel.Bewegen(felder, richtung, x_Ausgang, y_Ausgang);
				WechselAktivesTeam();
				spiel.Konsolenausgabe();
				System.out.println("\n"+"Es wurde gezogen.");
				System.out.println("Jetzt ist "+aktivesTeam+" am Zug");
				System.out.println("_________________________________________________________");
				return;
			}
		}
		System.out.println("\n"+"Es wurde nicht gezogen. Bitte führen sie die Methode noch einmal aus und überprüfen Sie ihre Eingaben");
	}

	private void WechselAktivesTeam() {
		if(aktivesTeam.equals("schwarz")) {
			aktivesTeam = "weiss";
		}
		else
		{
			aktivesTeam = "schwarz";
		}
	}
	
	public static void main(String[] args) {
		Rundenmanager_kompakt manager = new Rundenmanager_kompakt();
		manager.Bewegen(1, "y", 5, 1);
		manager.Bewegen(1, "y", 7, 1);
		manager.Bewegen(2, "x", 7, 5);
	}
	
}
