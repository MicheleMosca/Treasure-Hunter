package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.AdventureGame;
import com.game.User;

/**
 * 
 * Classe per il men� principale del gioco
 *
 */

public class MainMenuScreen implements Screen
{
	private static final int Width = 200; //80
	private static final int Height = 100;
	private static final int PlayPositionX = 500;
	private static final int PlayPositionY = 400;
	private static final int ExitPositionX = 500;
	private static final int ExitPositionY = 200;

	private AdventureGame game;
	private User user;

	private Stage stage;
	private Texture background;
	
	public MainMenuScreen(final AdventureGame game, User user)
	{
		this.game = game;
		this.user = user;
		
		//texture del background
		background = new Texture("menu/background.png");
		
		//carico l'immagine del pulsante EXIT
	    Button ExitButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/main/exit.png"))));
	    
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
	    Button PlayButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/main/play.png"))));
	    
	    PlayButton.setSize(Width, Height);
	    PlayButton.setPosition(PlayPositionX, PlayPositionY);
	    PlayButton.addListener(new ChangeListener()
		{
			@Override
			public void changed (ChangeEvent event, Actor actor)
			{
				dispose();
				game.setScreen(new LevelScreen(game));
			}
		});
	    
	    Table table= new Table();
	    table.setPosition(Gdx.graphics.getWidth()/2 -  800 / 2  , Gdx.graphics.getHeight()/2 - 1457 / 4);
	    table.setSize(800, 1457 / 2);
	    
	    table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/main/table.png"))));
	    table.add(PlayButton).size(240,180).pad(20);
	    table.row();
	    table.add(ExitButton).size(240,180).pad(20);
	    
	    stage=new Stage(); 
	    stage.addActor(table);

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
		stage.getBatch().draw(background, 0, 0);
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
		stage.dispose();
		background.dispose();
	}
	
}
