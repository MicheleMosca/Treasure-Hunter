package com.game.graphics.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.game.interfaces.Movable;

/**
 * Classe che definisce un elemento del gioco avente una texture un corpo con collisioni e metodi per muoversi all'interno del mondo di gioco
 * @author Michele
 *
 */

public class MovableAnimatedEntity extends TexturedEntity implements Movable
{

	public MovableAnimatedEntity(World world, MapObject mapObject, BodyType bodyType, String textureAtlasPath,
								 String textureRegionName, Vector2 textureDimension)
	{
		super(world, mapObject, bodyType, textureAtlasPath, textureRegionName, textureDimension);
	}

	@Override
	public void moveRight()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveLeft()
	{
		// TODO Auto-generated method stub
		
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
