package com.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.game.interfaces.Movable;

/**
 * 
 * Classe che instanzia una camera basata sulle dimensioni del frame e che non oltrepassa le dimensioni
 * della tiled map
 *
 */

public class CameraObject implements Movable
{
	private OrthographicCamera camera;
	private TiledMap tiledMap;
	
	private Vector2 cameraMinPosition;
    private Vector2 cameraMaxPosition;
	
	private float frameWidth;
    private float frameHeight;
    
    private float shift;

	public CameraObject(TiledMap tiledMap)
	{
		this.tiledMap = tiledMap;
		
		camera = new OrthographicCamera();
		cameraMinPosition = new Vector2();
		cameraMaxPosition = new Vector2();
		frameWidth = Gdx.graphics.getWidth();
		frameHeight = Gdx.graphics.getHeight();
		shift = 3.0f;
		
        camera.setToOrtho(false, frameWidth, frameHeight);
        camera.update();
        
        calculateMaxAndMinPosition();
	}
	
	public void resize()
	{
		frameWidth = Gdx.graphics.getWidth();
		frameHeight = Gdx.graphics.getHeight();
		camera.setToOrtho(false, frameWidth, frameHeight);
        camera.update();
        calculateMaxAndMinPosition();
	}
	
	private void calculateMaxAndMinPosition()
	{
		int mapWidth = tiledMap.getProperties().get("width", Integer.class);
		int mapHeight = tiledMap.getProperties().get("height", Integer.class);
		
		int tilePixelWidth = tiledMap.getProperties().get("tilewidth", Integer.class);
		int tilePixelHeight = tiledMap.getProperties().get("tileheight", Integer.class);
		
		int mapPixelWidth = mapWidth * tilePixelWidth;
		int mapPixelHeight = mapHeight * tilePixelHeight;
		
		cameraMinPosition.set(camera.position.x, camera.position.y);
		cameraMaxPosition.set((camera.position.x + mapPixelWidth) - frameWidth, (camera.position.y + mapPixelHeight) - frameHeight);
	}

	public TiledMap getTiledMap()
	{
		return tiledMap;
	}

	public void setTiledMap(TiledMap tiledMap)
	{
		this.tiledMap = tiledMap;
	}

	public float getShift()
	{
		return shift;
	}

	public void setShift(float shift)
	{
		this.shift = shift;
	}

	public void update()
	{
		camera.update();
	}

	public OrthographicCamera getCamera()
	{
		return camera;
	}

	public void setCamera(OrthographicCamera camera)
	{
		this.camera = camera;
	}

	@Override
	public void moveRight()
	{
		if (camera.position.x < cameraMaxPosition.x)
			camera.translate(shift, 0);
	}

	@Override
	public void moveLeft()
	{
		if (camera.position.x > cameraMinPosition.x)
			camera.translate(shift * (-1), 0);
	}
	
	@Override
	public void moveUp()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveDown()
	{
		// TODO Auto-generated method stub
		
	}
}
