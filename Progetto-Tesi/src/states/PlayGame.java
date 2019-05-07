package states;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * Classe che rappresenta il gioco avviato
 *
 */

public class PlayGame extends BasicGameState
{
	HashMap<String, Image> objects;
	int x,y,scale;

	public PlayGame()
	{
		super();
		objects = new HashMap<String, Image>();
		x=0;
		y=0;
		scale = 3;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		objects.put("Player", new Image("res/sprites/idle_right.gif"));
		x=0;
		y=container.getHeight() - objects.get("Player").getHeight()*scale;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		objects.get("Player").draw(x, y,3);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		if(x < container.getWidth() - objects.get("Player").getWidth()*3)
			x++;
	}

	@Override
	public int getID()
	{
		return State.PlayGame;
	}

}
