package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.AdventureGame;
import com.game.Enum.AnimationState;
import com.game.User;
import com.game.graphics.CameraObject;
import com.game.graphics.CollisionDetector;
import com.game.graphics.Hud;
import com.game.graphics.panels.GameOver;
import com.game.graphics.WorldCreator;
import com.game.graphics.entities.AnimatedEntity;
import com.game.graphics.entities.MovableAnimatedEntity;
import com.game.graphics.entities.Player;
import com.game.graphics.panels.Pause;
import com.game.graphics.panels.Victory;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Screen che rappresenta il gioco in riproduzione
 *
 */

public class PlayScreen implements Screen
{
	private AdventureGame game;
	
	private CameraObject camera;

	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer mapRender;
	
	private World world;
	private Box2DDebugRenderer debugRender;
	private List<AnimatedEntity> gameObjects;

	private float currentTime;
	private List<Integer> scoreTime;
	private int scoreCoins;

	private AnimatedEntity player;

	private Hud hud;
	private GameOver gameOverScreen;
	private Victory victoryScreen;
	private Pause pauseScreen;

	private Stage tutorialStage;
	private AnimationState tutorialState;

	private boolean gameOnPause;

	private User userData;

	public PlayScreen(AdventureGame game, User userData)
	{
		this.game = game;
		this.userData = userData;

		gameOnPause = false;
		// Carico la mappa
		TmxMapLoader mapLoader = new TmxMapLoader();
		tiledMap = mapLoader.load("maps/level" + userData.getLevelSelected() +
				"/level" + userData.getLevelSelected() + ".tmx");
		mapRender = new OrthogonalTiledMapRenderer(tiledMap, 1 / AdventureGame.pixelPerMeter);
		
		// creazione di una camera che seguirà il player
		camera = new CameraObject(tiledMap);
		
		// Inizializzo il mondo e il debug render
		world = new World(new Vector2 (0,-10), true);
		debugRender = new Box2DDebugRenderer();

		// Instanzio tutti gli oggetti di gioco all'interno del mondo
		WorldCreator worldCreator = new WorldCreator(this);
		gameObjects = worldCreator.initWorld(tiledMap, world);

		// Imposto il ContactListener per gli oggetti all'interno del mondo
		world.setContactListener(new CollisionDetector(this));

		// Inizializzo l'hud di gioco
		hud = new Hud(game);

		// Inizializzo gli elementi che costituiscono il punteggio e il tempo che in giocatore impiega
		currentTime = 0;
		scoreTime = new ArrayList<Integer>(2);
		scoreTime.add(0, 0);
		scoreTime.add(1,0);
		scoreCoins = 0;

		// Ricerco al'interno degli oggetti di gioco qual è il player
		for (AnimatedEntity object : gameObjects)
		{
			if (object instanceof Player)
				player = object;
		}

		// Inizializzo il GameOver screen
		gameOverScreen = new GameOver(game, this, userData);

		// Inizializzo il Victory screen
		victoryScreen = new Victory(game, this, userData);

		// Inizializzo il Pause screen
		pauseScreen = new Pause(game, this, userData);

		// Inizializzo nulle la label e la texture del tutorial
		tutorialStage = null;

		// Se e' il tutorial aggiungo come muoversi all'inizio
		if (userData.getLevelSelected() == 0)
			initTutorial("To move use", "A or D", AnimationState.Idle);

		// Imposto come input processor null per togliere i comandi di imput agli screen
		// (le azioni di input a livello di schermo non sono previste per il gioco, solo input da tastiera)
		Gdx.input.setInputProcessor(null);
	}

	private void handleInput(float deltaTime)
	{
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
		{
			gameOnPause = true;
			Pause.setVisible(true);
		}
	}

	private void timerCount(float deltatime)
	{
		currentTime += deltatime;

		if (currentTime >= 1)
		{
			if (scoreTime.get(1) == 59)
			{
				scoreTime.add(0, scoreTime.get(0) + 1);
				scoreTime.add(1, 0);
			}
			else
				scoreTime.add(1 , scoreTime.get(1) + 1);
			currentTime = 0;
			
			hud.setcountdownlabel(scoreTime.get(0) + ":" + scoreTime.get(1));
		}
	}
	
	private void update(float deltaTime)
	{
		handleInput(deltaTime);

		timerCount(deltaTime);

		world.step(1 / 60f,  6, 2);

		for (AnimatedEntity object : gameObjects)
			object.update(deltaTime);
		
		camera.update();

		camera.followThisTarget((MovableAnimatedEntity) player);
		mapRender.setView(camera);
		camera.update();
	}

	@Override
	public void render(float deltaTime)
	{
	    if (!gameOnPause)
		    update(deltaTime);
		
		// Pulisco il buffer dello schermo
		Gdx.gl.glClearColor(0, 0, 0, 1);	//Red, gree, blue, alpha(0 = trasparete, 1 = opaco) 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Renderizzo la mappa
		mapRender.render();
		
		// Renderizzo gli oggetti del mondo per debug
		//debugRender.render(world, camera.combined);
		
		// Impongo al batch di proiettare tutto sulla camera 
		game.batch.setProjectionMatrix(camera.combined);

		// Disegno tutti i componenti dell'hud
		hud.draw();

		// mostro il tutorial se e' presente
		if (tutorialStage != null)
			tutorialStage.draw();

		// Inizio i desegni sulla camera
		game.batch.begin();

		// Disegno tutte le Sprite degli oggetti del mondo
		for (AnimatedEntity object : gameObjects)
			object.getSprite().draw(game.batch);

		// Termino i disegni sulla camera
		game.batch.end();

		// Visualizzo il menu di game over oppure quello di victory o quello di pausa
		if (pauseScreen.isVisible())
			Pause.stage.draw();
		else if (gameOverScreen.isVisible())
			GameOver.stage.draw();
		else if (victoryScreen.isVisible())
			Victory.stage.draw();
	}

	public void initTutorial(String text, String command, AnimationState tutorialState)
	{
		tutorialStage = new Stage();
		this.tutorialState = tutorialState;

		Table table = new Table();
		table.top().padTop(200);
		table.setFillParent(true);

		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = game.createFont(50);

		Label textLabel = new Label(text, labelStyle);
		Label commandLabel = new Label(command, labelStyle);

		table.add(textLabel);
		table.row();
		table.add(commandLabel).padTop(10);

		tutorialStage.addActor(table);
	}

	public AnimationState getTutorialState()
	{
		return tutorialState;
	}

	public void setTutorialStageNull()
	{
		tutorialStage = null;
	}

	public void removeBodyFromWorld(AnimatedEntity entity)
	{
		entity.getSprite().getTexture().dispose();
		world.step(0,0,0);
		world.destroyBody(entity.getBody());
		gameObjects.remove(entity);
	}

	public void addCoin()
	{
		scoreCoins++;
		hud.setscorevaluelabel(""+scoreCoins);
	}

	public boolean isGameOnPause()
	{
		return gameOnPause;
	}

	public void setGameOnPause(boolean gameOnPause)
	{
		this.gameOnPause = gameOnPause;

		if (gameOnPause && victoryScreen.isVisible())
			victoryScreen.sendRecord(scoreCoins, scoreTime);
	}

	@Override
	public void resize(int width, int height)
	{
		camera.resize(width, height);
	}

	@Override
	public void pause()
	{

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
	public void show()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose()
	{
		for (AnimatedEntity object : gameObjects)
			object.getSprite().getTexture().dispose();
		world.dispose();
		mapRender.dispose();
		tiledMap.dispose();
		debugRender.dispose();
	}
}
