package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.screens.PlayScreen;

/**
 * 
 * Classe principale del gioco, la prima ad essere instanziata e quella che decide quale screen avviare
 *
 */

public class AdventureGame extends Game
{
	public final float virtualWidth = 1280;
	public final float virtualHeight = 720;
	public SpriteBatch batch;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
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
