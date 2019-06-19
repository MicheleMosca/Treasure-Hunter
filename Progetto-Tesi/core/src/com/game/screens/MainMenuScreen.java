package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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
 * Classe per il menù principale del gioco
 *
 */

public class MainMenuScreen extends ChangeListener implements Screen
{
	private static final int Width = 200; //80
	private static final int Height = 100;
	private static final int PlayPositionX = 500;
	private static final int PlayPositionY = 400;
	private static final int ExitPositionX = 500;
	private static final int ExitPositionY = 200;

	private AdventureGame game;
	private User userData;

	private Stage stage;
	private Texture background;
	
	public MainMenuScreen(AdventureGame game, User userData)
	{
		this.game = game;
		this.userData = userData;

		drawUI();
	}

	private void drawUI()
	{
		Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);

		// Texture del background
		background = new Texture("menu/background.png");

		// Carico l'immagine del pulsante EXIT
		Button ExitButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/main/exit.png"))));
		ExitButton.setName("exit");
		ExitButton.addListener(this);

		// Carico l'immagine del pulsante PLAY
		Button PlayButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/main/play.png"))));
		PlayButton.setName("play");
		PlayButton.addListener(this);

		// Definisco il pulsante per raggiugere la schermata di selezione del livello per mostrare la relativa classifica
		Button rankingButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/main/ranking.png"))));
		rankingButton.setName("ranking");
		rankingButton.addListener(this);

		Table table= new Table();
		table.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
		table.setSize(stageSize.x, stageSize.y);
		table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/login/table.png"))));

		table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/main/table2.png"))));

		table.add(PlayButton).size(1298 / 7,952 / 11).padBottom(20);
		table.row();
		table.add(rankingButton).size(1298 / 7,952 / 11).padBottom(20);
		table.row();
		table.add(ExitButton).size(1298 / 7,952 / 11);

		stage=new Stage();
		stage.addActor(table);

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void changed(ChangeEvent event, Actor actor)
	{
		if (actor.getName().equals("play"))
		{
			dispose();
			game.setScreen(new LevelScreen(game, userData));
		}
		else if (actor.getName().equals("ranking"))
		{
			dispose();
			game.setScreen(new RankingLevelScreen(game, userData));
		}
		else if (actor.getName().equals("exit"))
			Gdx.app.exit();
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
