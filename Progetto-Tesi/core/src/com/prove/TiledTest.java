package com.prove;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.game.graphics.CameraObject;

/**
 * 
 * Classe di prova per test su una tiled map
 *
 */

public class TiledTest extends ApplicationAdapter implements InputProcessor 
{
	SpriteBatch batch;
	Sprite sprite;
	
	Rectangle spawnPoint, ground;
	float position;
	
    TiledMap tiledMap;
    CameraObject cameraObject;
    TiledMapRenderer tiledMapRenderer;
    
    boolean keyLeftPressed, keyRightPressed;
    
    @Override
    public void create () 
    {
        tiledMap = new TmxMapLoader().load("assets/mappa.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        cameraObject = new CameraObject(tiledMap);
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture("assets/idle_right.gif"));
        
        keyLeftPressed = false;
        keyRightPressed = false;
        
        Gdx.input.setInputProcessor(this);
        
        for (MapObject obj : tiledMap.getLayers().get(1).getObjects().getByType(RectangleMapObject.class))
        {
        	spawnPoint = ((RectangleMapObject) obj).getRectangle();
        }
        
        for (MapObject obj : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
        	ground = ((RectangleMapObject) obj).getRectangle();
        }
        
        position = spawnPoint.y;
        System.out.println(position + " | " + ground.y);
    }

    public void update()
    {
    	if (position > ground.y + ground.height)
    		position -= 2;			//Fa ancora schifo adesso sistemo tutto
    }
    
    @Override
    public void render () 
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if (keyLeftPressed == true)
        	cameraObject.moveLeft();
        if (keyRightPressed == true)
        	cameraObject.moveRight();
        
        cameraObject.update();
        tiledMapRenderer.setView(cameraObject.getCamera());
        tiledMapRenderer.render();
        
        update();
        
        batch.begin();
        
        batch.draw(sprite, spawnPoint.x, position);
        
        batch.end();
    }

    @Override
	public void dispose()
	{
		super.dispose();
		batch.dispose();
		tiledMap.dispose();
		sprite.getTexture().dispose();
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
		cameraObject.resize();
	}

	@Override
    public boolean keyDown(int keycode) 
    {
    	if(keycode == Input.Keys.LEFT)
            keyLeftPressed = true;
        if(keycode == Input.Keys.RIGHT)
        	keyRightPressed = true;
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }

    @Override
    public boolean keyUp(int keycode) 
    {
    	if(keycode == Input.Keys.LEFT)
    		keyLeftPressed = false;
    	if(keycode == Input.Keys.RIGHT)
        	keyRightPressed = false;
        
        return false;
    }

    @Override
    public boolean keyTyped(char character) 
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
