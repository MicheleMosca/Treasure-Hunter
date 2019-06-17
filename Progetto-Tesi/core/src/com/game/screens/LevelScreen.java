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
		//texture del pulsante level1
		Texture Level1= new Texture("menu/level_select/1.png");
		
		//texture del pulsante level2
		Texture Level2= new Texture("menu/level_select/2.png");
		
		//texture del background
		background = new Texture("menu/level_select/Sky.png");
		
		//texture del pulsante per tornare al menu principale
		Texture Prew= new Texture("menu/level_select/prew.png");
		
		//background table1
		Texture tab =new Texture("menu/level_select/bg.png");
		
		//Scritta select level
		Texture StrLevel = new Texture("menu/level_select/header.png");
		
		//background table2
		Texture tab1=new Texture("menu/level_select/table2.png");
		
		//Header level select
		Image Header = new Image(StrLevel);
		
		//stelle
		Image Star = new Image(new Texture("menu/level_select/star_1.png"));
		
		//carico l'immagine del pulsante PREW
	    Button PrewButton = new Button(new TextureRegionDrawable(new TextureRegion(Prew)));
	    
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
	    
	    //carico l'immagine dei pulsanti dei livelli 
	    Button Level1Button = new Button(new TextureRegionDrawable(new TextureRegion(Level1)));
	    Button Level2Button = new Button(new TextureRegionDrawable(new TextureRegion(Level2)));
	    
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
	    
	    //init table1
	    Table table1 = new Table();
	    
	    table1.setPosition(Gdx.graphics.getWidth()/2 - 1307 / 4 , Gdx.graphics.getHeight()/2 - 1457 / 4);
	    table1.setSize(1307 / 2, 1457 / 2);
	    
	    table1.top().pad(40);
	    table1.background(new TextureRegionDrawable(new TextureRegion(tab)));
	    table1.add(Header).size(1003/2,396/2);
	    table1.row();
	    
	    //init table2
	    Table table2 = new Table();
	    table2.pad(20);
	    table2.background(new TextureRegionDrawable(new TextureRegion(tab1)));
	    
	    //init table3
	    Table table3 =new Table();
	    table3.pad(20);
	    table3.add(Level1Button).expandY().pad(50);
	    table3.add(Level2Button).expandY().pad(50);
	    
	    table2.add(table3);
	    table2.row();
	    table2.add(Star);
	   
	    table1.add(table2);
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
