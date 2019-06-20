package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.game.screens.SplashScreen;

/**
 * Classe principale del gioco, la prima ad essere instanziata e quella che decide quale screen avviare
 */

public class AdventureGame extends Game
{
	public static final String gameTitle = "Treasure Hunter";
	public static final int worldWidth = 1920;
	public static final int worldHeight = 1080;
	public static final float pixelPerMeter = 100;
	public static final boolean fullScreenOnStart = true;
	public static final String serverIP = "micheleserver.dlinkddns.com";

	public SpriteBatch batch;

	public BitmapFont defaultFont;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		defaultFont = createFont(30);

		setScreen(new SplashScreen(this));
	}

	/**
	 * Metodo per creare un nuovo font con il tema del gioco
	 * @param size Stabilisce la grandezza del font
	 * @return Ritorna una BitmapFont
	 */
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
