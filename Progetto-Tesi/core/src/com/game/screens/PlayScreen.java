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
import com.game.AdventureGame;
import com.game.graphics.CameraObject;
import com.game.graphics.CollisionDetector;
import com.game.graphics.Hud;
import com.game.graphics.WorldCreator;
import com.game.graphics.entities.AnimatedEntity;
import com.game.graphics.entities.MovableAnimatedEntity;
import com.game.graphics.entities.Player;
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
	
	public PlayScreen(AdventureGame game)
	{
		this.game = game;

		// Carico la mappa
		TmxMapLoader mapLoader = new TmxMapLoader();
		tiledMap = mapLoader.load("maps/level1/level1.tmx");
		mapRender = new OrthogonalTiledMapRenderer(tiledMap, 1 / AdventureGame.pixelPerMeter);
		
		// creazione di una camera che seguirà il player
		camera = new CameraObject(tiledMap);
		
		// Inizializzo il mondo e il debug render
		world = new World(new Vector2 (0,-10), true);
		debugRender = new Box2DDebugRenderer();

		// Instanzio tutti gli oggetti di gioco all'interno del mondo
		gameObjects = WorldCreator.initWorld(tiledMap, world);

		// Imposto il ContactListener per gli oggetti all'interno del mondo
		world.setContactListener(new CollisionDetector(this));

		// Inizializzo l'hud di gioco
		hud = new Hud();

		currentTime = 0;
		scoreTime = new ArrayList<Integer>(2);
		scoreTime.add(0, 0);
		scoreTime.add(1,0);
		scoreCoins = 0;

		for (AnimatedEntity object : gameObjects)
		{
			if (object instanceof Player)
				player = object;
		}

		Gdx.input.setInputProcessor(null);
	}

	private void handleInput(float deltaTime)
	{
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1))
		{
			if (Gdx.graphics.isFullscreen())
				Gdx.graphics.setWindowedMode(Gdx.graphics.getDisplayMode().width, Gdx.graphics.getDisplayMode().height);
			else
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
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
	}

	@Override
	public void render(float deltaTime)
	{
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

		// Inizio i desegni sulla camera
		game.batch.begin();

		// Disegno tutte le Sprite degli oggetti del mondo
		for (AnimatedEntity object : gameObjects)
			object.getSprite().draw(game.batch);

		// Termino i disegni sulla camera
		game.batch.end();
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

	@Override
	public void resize(int width, int height)
	{
		camera.resize(width, height);
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
