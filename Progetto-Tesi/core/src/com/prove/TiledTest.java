package com.prove;

import java.util.HashMap;

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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.game.entities.AnimatedEntity;
import com.game.entities.Entity;
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
	
	AnimatedEntity pupo;
	
    TiledMap tiledMap;
    CameraObject cameraObject;
    TiledMapRenderer tiledMapRenderer;
    
    boolean keyLeftPressed, keyRightPressed;
    
    private World world;
    private Box2DDebugRenderer debugRender;
    private HashMap<String,Entity> entities;
    
    @Override
    public void create() 
    {
        tiledMap = new TmxMapLoader().load("mappa.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        cameraObject = new CameraObject(tiledMap);
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture("idle_right.gif"));
        world = new World(new Vector2(0,0), true);	// Graviti position, sleeping
        debugRender = new Box2DDebugRenderer();
        entities = new HashMap<String, Entity>();
        
        keyLeftPressed = false;
        keyRightPressed = false;
        
        for (MapObject mapObject : tiledMap.getLayers().get("Ground").getObjects())
        	entities.put(mapObject.getName(), (new Entity(world, mapObject, BodyType.StaticBody)));
        
        for (MapObject spawnPoint : tiledMap.getLayers().get("Spawn").getObjects())
        	entities.put(spawnPoint.getName(), (new Entity(world, spawnPoint, BodyType.DynamicBody)));
        	//pupo = new AnimatedEntity(world, spawnPoint, BodyType.DynamicBody);
        
        //pupo.set(sprite);
        
        Gdx.input.setInputProcessor(this);
    }
    
    public void update()
    {
    	if (keyLeftPressed == true)
        	cameraObject.moveLeft();
        if (keyRightPressed == true)
        	cameraObject.moveRight();
        
        world.step(1 / 60f, 6, 2);
        
        cameraObject.update();
        tiledMapRenderer.setView(cameraObject.getCamera());
    }
    
    @Override
    public void render () 
    {	
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
     
        update();
        
        tiledMapRenderer.render();
        
        debugRender.render(world, cameraObject.getCamera().combined);
        
        batch.begin();
        
        //batch.draw(pupo, pupo.getPosition().x, pupo.getPosition().y);
        
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
