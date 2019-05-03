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
	int x,y;

	public AdventureGame(String title)
	{
		super(title);
		objects = new HashMap<String, Image>();
		x=0;
		y=0;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		objects.get("Player").draw(x, y,3);
	}

	@Override
	public void init(GameContainer container) throws SlickException
	{
		objects.put("Player", new Image("res/sprites/idle_right.gif"));
		x=0;
		y=container.getHeight() - objects.get("Player").getHeight()*3;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException
	{
		if(x < container.getWidth() - objects.get("Player").getWidth()*3)
			x++;
	}

}
