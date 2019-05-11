package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.AdventureGame;

/**
 * 
 * Screen che rappresenta il gioco in riproduzione
 *
 */

public class PlayScreen implements Screen
{
	private AdventureGame game;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private TmxMapLoader mapLoader;
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer mapRender;
	
	public PlayScreen(AdventureGame game)
	{
		this.game = game;
		
		// creazione di una camera che seguirà il player
		camera = new OrthographicCamera();
		
		// Carico la mappa
		mapLoader = new TmxMapLoader();
		tiledMap = mapLoader.load("mappa.tmx");
		mapRender = new OrthogonalTiledMapRenderer(tiledMap);
		
		// creazione di un FillViewport per mantenere le dimensioni dell'aspect ratio in base alla dimensione dello schermo
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		
		// Posiziono la camera al centro della mia viewport
		camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
	}
	
	@Override
	public void show()
	{
		// TODO Auto-generated method stub
		
	}
	
	public void handleInput()
	{
		if (Gdx.input.isTouched())
		{
			Gdx.graphics.setWindowedMode(Gdx.graphics.getDisplayMode().width, Gdx.graphics.getDisplayMode().height);
		}
	}
	
	public void update(float delta)
	{
		handleInput();
		
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
		
		// Impongo al batch di proiettare tutto sulla camera 
		game.batch.setProjectionMatrix(camera.combined);
		
		// Inizio i desegni sulla camera
		game.batch.begin();
		
		game.batch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		// Aggiorno il viewport con le nuove dimensioni dello schermo
		viewport.update(width, height);
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
