package states;

/**
 * 
 * Classe statica che definisce gli id degli stati del gioco
 *
 */

public enum State
{
	MainMenu(0),
	PlayGame(1);
	
	private int code;
	
	State(int code) {
		this.code = code;
	}
	
	public int code() {
		return code;
	}
}
