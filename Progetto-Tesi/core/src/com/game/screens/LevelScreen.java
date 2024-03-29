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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.AdventureGame;
import com.game.User;

/**
 * Classe per la selezione del livello
 */

public class LevelScreen extends ChangeListener implements Screen
{
	private Stage stage;
	private Texture background;

	User userData;
	protected AdventureGame game;

	public LevelScreen (AdventureGame game, User userData, boolean allowTutorialButton)
	{
		super();

		this.userData = userData;
		this.game = game;
		
		game.music.setCurrentTrack(0);
        game.music.playMusic();
		drawUI(allowTutorialButton);
	}

	/**
	 * Metodo chiamato dal costruttore per disegnare L'interfaccia utente del login
	 */
	private void drawUI(boolean allowTutorialButton)
	{
		Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);

		//texture del background
		background = new Texture("menu/background.png");	//menu/level_select/Sky.png

		Image Lock = new Image(new Texture("menu/level_select/lockbtnw.png"));

		//carico l'immagine dei pulsanti
		Button Level1Button = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/level_select/btn1.png"))));
		Button Level2Button = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/level_select/btn2.png"))));
		Button PrewButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("menu/level_select/prew.png"))));

		//listener per il bottone livello 1
		Level1Button.setName("level1");
		Level1Button.addListener(this);

		//listener per il bottone livello2
		Level2Button.setName("level2");
		Level2Button.addListener(this);

		//listener per il bottone PREW
		PrewButton.setName("prew");
		PrewButton.addListener(this);

		//init table1
		Table table1 = new Table();

		table1.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
		table1.setSize(stageSize.x, stageSize.y);

		table1.bottom().left().pad(40);
		table1.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/level_select/bg3.png"))));

		table1.add(Level1Button).expandY().padLeft(140).padTop(100);

		if (userData.getLastLevel() < 1)
			table1.add(Lock).expandY().padLeft(10).padTop(100);
		else
			table1.add(Level2Button).expandY().padLeft(10).padTop(100);

		table1.row();
		table1.add(PrewButton).size(80,80).left();

		stage=new Stage();
		stage.addActor(table1);

		// Se True allora inseriro' nel menu anche il pulsante per il tutorial
		if (allowTutorialButton)
		{
			Button tutorialButton = new Button(new TextureRegionDrawable(new TextureRegion(
					new Texture("menu/level_select/tutorial.png"))));
			tutorialButton.setName("tutorial");
			tutorialButton.addListener(this);
			tutorialButton.setSize(1298 / 9,952 / 11);
			tutorialButton.setPosition(table1.getX() + (table1.getWidth() / 3),
					table1.getY() + 37);
			stage.addActor(tutorialButton);
		}

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
		dispose();

		if (actor.getName().equals("level1"))
		{
			userData.selectLevel(1);
			game.setScreen(new PlayScreen(game, userData));
		}
		else if (actor.getName().equals("level2"))
		{
			userData.selectLevel(2);
			game.setScreen(new PlayScreen(game, userData));
		}
		else if (actor.getName().equals("tutorial"))
		{
			userData.selectLevel(0);
			game.setScreen(new PlayScreen(game, userData));
		}
		else if (actor.getName().equals("prew"))
			game.setScreen(new MainMenuScreen(game, userData));
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
