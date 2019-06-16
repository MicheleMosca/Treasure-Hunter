package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.AdventureGame;

/**
 * 
 * Classe per il menù principale del gioco
 *
 */

public class MainMenuScreen implements Screen
{
	private static final int Width = 80;
	private static final int Height = 80;
	private static final int PlayPositionX=500;
	private static final int PlayPositionY=400;
	private static final int ExitPositionX=500;
	private static final int ExitPositionY=200;
	
	private Stage stage;
	private Texture Background;
	
	public MainMenuScreen(final AdventureGame game)
	{
		//texture del pulsante PLAY
		Texture Play= new Texture("menu/play.png");
		//texture del pulsante EXIT
		Texture Exit= new Texture("menu/close_2.png");
		//texture del background
		Background = new Texture("menu/background.png");
		
		//carico l'immagine del pulsante EXIT
	    Button ExitButton = new Button(new TextureRegionDrawable(new TextureRegion(Exit)));
	    
	    ExitButton.setSize(Width, Height);
	    ExitButton.setPosition(ExitPositionX, ExitPositionY);
	    ExitButton.addListener(new ChangeListener()
		{
			@Override
			public void changed (ChangeEvent event, Actor actor)
			{
				Gdx.app.exit();
			}
		});
	    
	    //caruco l'immagine del pulsante PLAY
	    Button PlayButton = new Button(new TextureRegionDrawable(new TextureRegion(Play)));
	    
	    PlayButton.setSize(Width, Height);
	    PlayButton.setPosition(PlayPositionX, PlayPositionY);
	    PlayButton.addListener(new ChangeListener()
		{
			@Override
			public void changed (ChangeEvent event, Actor actor)
			{
				game.setScreen(new PlayScreen(game));
			}
		});
	    
	    
	    stage=new Stage();
	    stage.addActor(PlayButton);
	    stage.addActor(ExitButton);

	    Gdx.input.setInputProcessor(stage);

	}
	
	@Override
	public void show()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta)
	{
		// Pulisco il buffer dello schermo
		Gdx.gl.glClearColor(0, 0, 0, 1);	//Red, gree, blue, alpha(0 = trasparete, 1 = opaco) 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//inserire nello stage il background
		stage.getBatch().begin();
		stage.getBatch().draw(Background, 0, 0);
		stage.getBatch().end();

		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}
	
}
