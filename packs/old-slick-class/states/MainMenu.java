package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * Classe che definisce il menu principale del gioco
 *
 */

public class MainMenu extends BasicGameState
{
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		g.drawString("Sono il Main Menu", 300, 300);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		game.enterState(State.PlayGame.code());
	}

	@Override
	public int getID()
	{
		return State.MainMenu.code();
	}
	
}
