package com.game.graphics.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class AnimatedEntity extends Entity
{
	private Vector2 position;
	
	public AnimatedEntity(World world, MapObject spawnPoint, BodyType bodyType)
	{
		super(world, spawnPoint, bodyType);
		
		position = new Vector2(((RectangleMapObject) spawnPoint).getRectangle().x , ((RectangleMapObject) spawnPoint).getRectangle().y);
			
	}

	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}
	
	
}
