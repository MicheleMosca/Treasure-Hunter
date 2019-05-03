package game;

import java.util.HashMap;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AdventureGame extends BasicGame
{
	HashMap<String, Image> objects;

	public AdventureGame(String title)
	{
		super(title);
		objects = new HashMap<String, Image>();
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		objects.get("Player").draw(100, 100);
	}

	@Override
	public void init(GameContainer container) throws SlickException
	{
		objects.put("Player", new Image("res/sprites/idle_right.gif"));
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException
	{
		
	}

}
