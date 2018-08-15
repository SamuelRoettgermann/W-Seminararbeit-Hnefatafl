
public class LEER extends FIGUR {
	
	/*
	 * LEER beschreibt den Zustand, wenn ein Feld unbesetzt ist.
	 * Sinn dieser Klasse ist es den null-Wert, oder noch schlimmer ungewollte null-pointer-exceptions zu verhindern.
	 * LEER wird durch den Shortcut - beschrieben
	 */
	
	public LEER() {
		super("leer", "-");
	}

}
