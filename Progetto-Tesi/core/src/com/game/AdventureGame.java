package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.screens.LoginScreen;
import com.game.screens.MainMenuScreen;
import com.game.screens.SignupScreen;


/**
 * 
 * Classe principale del gioco, la prima ad essere instanziata e quella che decide quale screen avviare
 *
 */

public class AdventureGame extends Game
{
	public static final int worldWidth = 1280;
	public static final int worldHeight = 720;
	public static final float pixelPerMeter = 100;
	public static final boolean fullScreenOnStart = true;
	public static final String serverIP = "192.168.43.166";
	
	public SpriteBatch batch;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		setScreen(new LoginScreen(this));
	}

	@Override
	public void render () 
	{
		super.render();
	}
	
	@Override
	public void dispose () 
	{
		batch.dispose();
	}
}
