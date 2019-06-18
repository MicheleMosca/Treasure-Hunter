package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.game.AdventureGame;
import com.game.User;

/**
/**
 * 
 * Classe per la selezione del livello 
 *
 */


public class LevelScreen implements Screen
{

	private Stage stage;
	private Texture background;
	private User user;

	public LevelScreen (final AdventureGame game)
	{
		Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);
		
		//texture del background
		background = new Texture("menu/level_select/Sky.png");
		
		Image Lock = new Image(new Texture("menu/level_select/lockbtnw.png"));
		
	    //carico l'immagine dei pulsanti 
	    Button Level1Button = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/level_select/btn1.png"))));
	    Button Level2Button = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/level_select/btn2.png"))));
	    Button PrewButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/level_select/prew.png"))));
	    
	    //listener per il bottone livello 1
	    Level1Button.addListener(new ChangeListener()
		{
			@Override
			public void changed (ChangeEvent event, Actor actor)
			{
				dispose();
				game.setScreen(new PlayScreen(game));
			}
		});
	    
	    //listener per il bottone livello2
	    Level2Button.addListener(new ChangeListener()
		{
			@Override
			public void changed (ChangeEvent event, Actor actor)
			{
				dispose();
				game.setScreen(new PlayScreen(game));
			}
		});
	    
	  //listener per il bottone PREW
	    PrewButton.addListener(new ChangeListener()
		{
			@Override
			public void changed (ChangeEvent event, Actor actor)
			{
				dispose();
				game.setScreen(new MainMenuScreen(game, user));
			}
		});
	    
	    //init table1
	    Table table1 = new Table();
	    
	    table1.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
        table1.setSize(stageSize.x, stageSize.y);
	    
	    table1.bottom().left().pad(40);
	    table1.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/level_select/bg3.png"))));
	    
	    table1.add(Level1Button).expandY().padLeft(140).padTop(100);
	    
	    if(0>=1)
	    	table1.add(Level2Button).expandY().padLeft(10).padTop(100);
	    else
	    	table1.add(Lock).expandY().padLeft(10).padTop(100);
	    
	    table1.row();
	    table1.add(PrewButton).size(80,80).left();
        
	    stage=new Stage();
	    stage.addActor(table1);

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
