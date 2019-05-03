package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

import game.AdventureGame;

public class Main
{

	public static void main(String[] args)
	{
		AppGameContainer frameContainer;
		try
		{
			Game game = new AdventureGame("Adventure Game");
			frameContainer = new AppGameContainer(game);
			frameContainer.setDisplayMode(800, 600, false);
			frameContainer.setTargetFrameRate(59);
			frameContainer.start();
			
			game.init(frameContainer);
		} 
		catch (SlickException e)
		{
			System.out.println(e);
			System.exit(1);
		}
	}

}
