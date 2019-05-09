package com.prove;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.game.graphics.CameraObject;

/**
 * 
 * Classe di prova per test su una tiled map
 *
 */

public class TiledTest extends ApplicationAdapter implements InputProcessor 
{
    Texture img;
    TiledMap tiledMap;
    CameraObject cameraObject;
    TiledMapRenderer tiledMapRenderer;
    boolean keyLeftPressed, keyRightPressed;
    
    @Override
    public void create () 
    {
        keyLeftPressed = false;
        keyRightPressed = false;

        tiledMap = new TmxMapLoader().load("assets/mappa.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        cameraObject = new CameraObject(tiledMap);
        Gdx.input.setInputProcessor(this);
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
        System.out.println(cameraObject.getCamera().position.x + " " + cameraObject.getCamera().position.y);
        tiledMapRenderer.setView(cameraObject.getCamera());
        tiledMapRenderer.render();
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
