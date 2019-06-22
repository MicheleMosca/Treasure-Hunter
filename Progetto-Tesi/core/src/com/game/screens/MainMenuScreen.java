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
	private AdventureGame game;
	private User userData;

	private Stage stage;
	private Texture background;
	
	private Button muteButton;
	
	MainMenuScreen(AdventureGame game, User userData)
	{
		this.game = game;
		this.userData = userData;

		game.music.setCurrentTrack(0);
        game.music.playMusic();
		drawUI();
	}

	/**
	 * Metodo chiamato dal costruttore per disegnare L'interfaccia utente del login
	 */
	private void drawUI()
	{
		stage = new Stage();
		Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);

		Table table= new Table();
		table.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
		table.setSize(stageSize.x, stageSize.y);
		table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/main/table2.png"))));

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

		// Definisco il pulsante per effettuare il logout
		Button logoutButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/prew.png"))));
		logoutButton.setName("logout");
		logoutButton.addListener(this);
		logoutButton.setSize(214 / 5, 215 / 5);
		logoutButton.setPosition(table.getX() + logoutButton.getWidth(),
				table.getY() + logoutButton.getHeight());
		
		// Definisco il pulsante per mettere muto
		if (game.music.isMute())
			muteButton=new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/sound_off.png"))));
		else
			muteButton=new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/sound.png"))));
		
		muteButton.setName("mute");
		muteButton.addListener(this);
		muteButton.setSize(214 / 5, 215 / 5);
		muteButton.setPosition(table.getX() + table.getWidth() - muteButton.getWidth() * 2,
				table.getY() + table.getHeight() - muteButton.getHeight() - 10);
		
		table.add(PlayButton).size(1298 / 7,952 / 11).padBottom(20);
		table.row();
		table.add(rankingButton).size(1298 / 7,952 / 11).padBottom(20);
		table.row();
		table.add(ExitButton).size(1298 / 7,952 / 11);

		stage.addActor(table);
		stage.addActor(logoutButton);
		stage.addActor(muteButton);

		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * Metodo per ricevere gli eventi di tipo ChangeEvent usati dai pulsanti
	 * @param event ChangeEvent
	 * @param actor Actor che ha generato l'evento
	 */
	@Override
	public void changed(ChangeEvent event, Actor actor)
	{
		if (actor.getName().equals("exit"))
		{
			Gdx.app.exit();
			return;
		}
		
		if (actor.getName().equals("mute"))
		{
			if(game.music.isMute())
			{
				game.music.setMute(false);
				muteButton.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("menu/sound.png")));
			}
			else
			{
				game.music.setMute(true);
				muteButton.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("menu/sound_off.png")));
			}
			
			return;
		}

		dispose();

		if (actor.getName().equals("play"))
			game.setScreen(new LevelScreen(game, userData, true));
		else if (actor.getName().equals("ranking"))
			game.setScreen(new RankingLevelScreen(game, userData));
		else if (actor.getName().equals("logout"))
			game.setScreen(new LoginScreen(game));
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
	public void show()
	{
		// TODO Auto-generated method stub
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
