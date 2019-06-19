package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.game.screens.LoginScreen;
import com.game.screens.MainMenuScreen;
import com.game.screens.PlayScreen;

/**
 * 
 * Classe principale del gioco, la prima ad essere instanziata e quella che decide quale screen avviare
 *
 */

public class AdventureGame extends Game
{
	public static final int worldWidth = 1920; //1280
	public static final int worldHeight = 1080;	//720
	public static final float pixelPerMeter = 100;
	public static final boolean fullScreenOnStart = true;
	public static final String serverIP = "localhost";

	public SpriteBatch batch;
	private User user;

	public BitmapFont defaultFont;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		defaultFont = createFont(22);

		setScreen(new MainMenuScreen(this, new User("pippo", 0))); //LoginScreen
	}

	public BitmapFont createFont(int size)
	{
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/SHOWG.TTF"));
		FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.size = size;
		fontParameter.color = Color.WHITE;
		BitmapFont font = generator.generateFont(fontParameter);
		generator.dispose();

		return font;
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
