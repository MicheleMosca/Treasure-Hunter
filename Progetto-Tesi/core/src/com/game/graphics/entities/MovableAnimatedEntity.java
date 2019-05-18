package com.game.graphics.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.interfaces.Movable;

/**
 * Classe che definisce un elemento del gioco avente una texture un corpo con collisioni e metodi per muoversi all'interno del mondo di gioco
 * @author Michele
 *
 */

public class MovableAnimatedEntity extends AnimatedEntity implements Movable
{

	public MovableAnimatedEntity(World world, MapObject mapObject, BodyType bodyType, String textureAtlasPath,
								 String textureRegionName, Vector2 textureDimension)
	{
		super(world, mapObject, bodyType, textureAtlasPath, textureRegionName, textureDimension, AnimationState.Idle);
	}

	@Override
	public Vector2 getPosition()
	{
		return body.getPosition();
	}

	@Override
	public Vector2 getVelocity()
	{
		return body.getLinearVelocity();
	}

	@Override
	public void moveRight()
	{
		if (getVelocity().x <= 2.25f)
			body.applyLinearImpulse(new Vector2(0.1f, 0), body.getWorldCenter(), true);
	}

	@Override
	public void moveLeft()
	{
		if (getVelocity().x >= -2.25f)
			body.applyLinearImpulse(new Vector2(-0.1f, 0), body.getWorldCenter(), true);
	}

	@Override
	public void moveUp()
	{
		body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);
	}

	@Override
	public void moveDown()
	{
		// TODO Auto-generated method stub
		
	}

}
