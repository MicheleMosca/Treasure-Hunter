package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.AdventureGame;

public class MainMenuScreen implements Screen
{
	public static final int Width = 80;
	public static final int Height = 80;
	public static final int PlayPositionX=500;
	public static final int PlayPositionY=400;
	public static final int ExitPositionX=500;
	public static final int ExitPositionY=200;
	
	private Stage stage;
	
	private Button ExitButton;
	private Button PlayButton;
	
	Texture Background; 
	
	private AdventureGame game;
	
	public MainMenuScreen(final AdventureGame game)
	{
		this.game=game;
		//texture del pulsante PLAY
		Texture Play= new Texture("menu/play.png");
		//texture del pulsante EXIT
		Texture Exit= new Texture("menu/close_2.png");
		//texture del background
		Background = new Texture("menu/background.png");
		
		
		//carico l'immagine del pulsante EXIT
	    ExitButton = new Button(new TextureRegionDrawable(new TextureRegion(Exit)));
	    
	    ExitButton.setSize(Width, Height);
	    ExitButton.setPosition(ExitPositionX, ExitPositionY);
	    ExitButton.addListener(new ClickListener()
	    		{
	    			public void clicked(InputEvent event, float x, float y)
	    			{
	    				Gdx.app.exit();
	    			}
	    		});
	    
	    //caruco l'immagine del pulsante PLAY
	    PlayButton = new Button(new TextureRegionDrawable(new TextureRegion(Play)));
	    
	    PlayButton.setSize(Width, Height);
	    PlayButton.setPosition(PlayPositionX, PlayPositionY);
	    PlayButton.addListener(new ClickListener()
	    		{
	    			public void clicked(InputEvent event, float x, float y)
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
		
		game.batch.begin();
		
		//inserire nello stage il background
		stage.getBatch().begin();
		stage.getBatch().draw(Background, 0, 0);
		stage.getBatch().end();
		
		stage.draw();
		
		game.batch.end();
			
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
