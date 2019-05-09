package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter 
{
	SpriteBatch batch;
	Sprite sprite;
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		sprite = new Sprite (new Texture("assets/badlogic.jpg"));
	}

	@Override
	public void render () 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);	//Red, gree, blue, alpha(0 = trasparete, 1 = opaco) 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	//pulisco il buffer dello schermo
		
		batch.begin();
		
		batch.draw(sprite, sprite.getX(), sprite.getY());
		
		batch.end();
	}
	
	@Override
	public void dispose () 
	{
		batch.dispose();
		sprite.getTexture().dispose();
	}
}
