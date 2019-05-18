package com.game.graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.AdventureGame;
import com.game.interfaces.Follower;
import com.game.interfaces.Movable;

/**
 * 
 * Classe che instanzia una camera basata sulle dimensioni del frame e che non oltrepassa le dimensioni
 * della tiled map
 *
 */

public class CameraObject extends OrthographicCamera implements Movable, Follower<Movable>
{
	private TiledMap tiledMap;
	private Viewport viewport;
	
	private Vector2 cameraMinPosition;
    private Vector2 cameraMaxPosition;
    
    private float shift;

	public CameraObject(TiledMap tiledMap)
	{
		super();
		this.tiledMap = tiledMap;
		
		// definisco i vettori corrispondendi alla minima e massima posizione che la camera può avere
		cameraMinPosition = new Vector2();
		cameraMaxPosition = new Vector2();
		
		// creazione di un FitViewport per mantenere le dimensioni dell'aspect ratio in base alla dimensione dello schermo
		viewport = new FitViewport(AdventureGame.worldWidth / AdventureGame.pixelPerMeter, AdventureGame.worldHeight / AdventureGame.pixelPerMeter, this);
		
		// Posiziono la camera al centro della mia viewport
		position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
		
		// decido di quanto si può spostare la camera da sola
		shift = 3;
        
        calculateMaxAndMinPosition();
	}
	
	public void resize(int width, int height)
	{
		viewport.update(width, height);
	}
	
	/**
	 * Metodo per calcolare la minima e massima posizione che la camera può avere in base alla tiledmap
	 */
	private void calculateMaxAndMinPosition()
	{
		// Prelevo le dimensioni della mappa in tile
		int mapWidth = tiledMap.getProperties().get("width", Integer.class);
		int mapHeight = tiledMap.getProperties().get("height", Integer.class);
		
		// Prelevo la dimensione di un singolo tile
		int tilePixelWidth = tiledMap.getProperties().get("tilewidth", Integer.class);
		int tilePixelHeight = tiledMap.getProperties().get("tileheight", Integer.class);
		
		// Ricavo la dimensione della mappa da tile in pixel
		int mapPixelWidth = mapWidth * tilePixelWidth;
		int mapPixelHeight = mapHeight * tilePixelHeight;
		
		// Definisco i confini della mappa
		cameraMinPosition.set(position.x, position.y);
		cameraMaxPosition.set(((position.x + mapPixelWidth) - AdventureGame.worldWidth) / AdventureGame.pixelPerMeter, ((position.y + mapPixelHeight) - AdventureGame.worldHeight) / AdventureGame.pixelPerMeter);
	}

	public TiledMap getTiledMap()
	{
		return tiledMap;
	}

	public void setTiledMap(TiledMap tiledMap)
	{
		this.tiledMap = tiledMap;
	}

	/**
	 * Metodo che serve per definire di quanto la camera si può spostare autonomamente
	 * @param shift spostamento
	 */
	public void setVelocity(float shift)
	{
		this.shift = shift;
	}

	@Override
	public Vector2 getPosition()
	{
		return new Vector2(position.x, position.y);
	}

	@Override
	public Vector2 getVelocity()
	{
		return null;
	}

	@Override
	public void moveRight()
	{
		if (position.x < cameraMaxPosition.x)
			translate(shift, 0);
	}

	@Override
	public void moveLeft()
	{
		if (position.x > cameraMinPosition.x)
			translate(shift * (-1), 0);
	}
	
	@Override
	public void moveUp()
	{
		if (position.y < cameraMaxPosition.y)
			translate(0, shift);
		
	}

	@Override
	public void moveDown()
	{
		if (position.y > cameraMinPosition.y)
			translate(0, shift * (-1));
	}

	/**
	 * Metodo che permette alla camera di seguire un target Entity passato come parametro
	 */
	@Override
	public void followThisTarget(Movable target)
	{
		if (target.getPosition().x > cameraMinPosition.x && target.getPosition().x < cameraMaxPosition.x)
			position.x = target.getPosition().x;
	}
}
