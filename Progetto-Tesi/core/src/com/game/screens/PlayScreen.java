package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.game.AdventureGame;
import com.game.graphics.CameraObject;
import com.game.graphics.entities.Entity;
import com.game.graphics.entities.Player;

/**
 * 
 * Screen che rappresenta il gioco in riproduzione
 *
 */

public class PlayScreen implements Screen
{
	private AdventureGame game;
	
	private CameraObject camera;
	
	private TmxMapLoader mapLoader;
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer mapRender;
	
	private World world;
	private Box2DDebugRenderer debugRender;
	
	private Player player;
	
	public PlayScreen(AdventureGame game)
	{
		this.game = game;
		
		// Carico la mappa
		mapLoader = new TmxMapLoader();
		tiledMap = mapLoader.load("maps/level1/level1.tmx");
		mapRender = new OrthogonalTiledMapRenderer(tiledMap, 1 / AdventureGame.pixelPerMeter);
		
		// creazione di una camera che seguirà il player
		camera = new CameraObject(tiledMap);
		
		// Inizializzo il mondo e il debug render
		world = new World(new Vector2 (0,-10), true);
		debugRender = new Box2DDebugRenderer();
		
		// Inserisco nel mondo tutti gli oggetti di tipo ground della mappa con le rispettive collisioni
		for (MapObject mapObject : tiledMap.getLayers().get("Collision").getObjects())
        	new Entity(world, mapObject, BodyType.StaticBody);
		
		player = new Player(world, tiledMap.getLayers().get("Spawn").getObjects().get(0), BodyType.DynamicBody);
	}
	
	public void handleInput()
	{
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
		{
			player.getBody().applyLinearImpulse(new Vector2(0, 4f), player.getBody().getWorldCenter(), true);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.D) && (player.getBody().getLinearVelocity().x <= 2))
		{
			player.getBody().applyLinearImpulse(new Vector2(0.1f, 0), player.getBody().getWorldCenter(), true);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.A) && (player.getBody().getLinearVelocity().x >= -2))
		{
			player.getBody().applyLinearImpulse(new Vector2(-0.1f, 0), player.getBody().getWorldCenter(), true);
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1))
		{
			if (Gdx.graphics.isFullscreen())
				Gdx.graphics.setWindowedMode(Gdx.graphics.getDisplayMode().width, Gdx.graphics.getDisplayMode().height);
			else
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		}
	}
	
	public void update(float delta)
	{
		handleInput();
		
		world.step(1 / 60f,  6, 2);
		
		camera.followThisTarget(player);
		
		player.update();
		
		camera.update();
		mapRender.setView(camera);
	}

	@Override
	public void render(float delta)
	{
		update(delta);
		
		// Pulisco il buffer dello schermo
		Gdx.gl.glClearColor(0, 0, 0, 1);	//Red, gree, blue, alpha(0 = trasparete, 1 = opaco) 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Renderizzo la mappa
		mapRender.render();
		
		// Renderizzo gli oggetti del mondo per debug
		debugRender.render(world, camera.combined);
		
		// Impongo al batch di proiettare tutto sulla camera 
		game.batch.setProjectionMatrix(camera.combined);
		
		// Inizio i desegni sulla camera
		game.batch.begin();
		
		player.draw(game.batch);
		
		game.batch.end();
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
		world.dispose();
		mapRender.dispose();
		tiledMap.dispose();
		debugRender.dispose();
	}

}
