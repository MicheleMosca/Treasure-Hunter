package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

import states.GameState;

/**
 * 
 * Creo una frame con all'interno il la definizione di tutti gli stati del gioco
 *
 */

public class Main extends AppGameContainer
{

	public Main(Game game) throws SlickException
	{
		super(game);
		
		setDisplayMode(800, 600, false);
		setTargetFrameRate(59);
		start();
	}

	public static void main(String[] args)
	{
		try
		{
			new Main(new GameState("Adventure Game"));
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

}
