package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * Classe che definisce tutti gli stati del gioco
 *
 */

public class GameState extends StateBasedGame
{

	public GameState(String name)
	{
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{
		this.addState(new MainMenu());
		this.addState(new PlayGame());
		
		enterState(State.MainMenu.code());
	}

}
