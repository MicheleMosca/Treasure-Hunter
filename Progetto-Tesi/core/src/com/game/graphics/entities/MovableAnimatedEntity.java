package com.game.graphics.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.interfaces.Movable;

/**
 * Classe che definisce un elemento del gioco avente una texture un corpo con collisioni e metodi per muoversi all'interno del mondo di gioco
 */

public class MovableAnimatedEntity extends AnimatedEntity implements Movable
{
	MovableAnimatedEntity(World world, MapObject mapObject, BodyType bodyType, String textureAtlasPath, String textureRegionName, Vector2 textureDimension)
	{
		super(world, mapObject, bodyType, textureAtlasPath, textureRegionName, textureDimension, AnimationState.Idle);
	}

	/**
	 * Metodo per ottenere la posizione dell'oggetto animato in gioco
	 * @return Vetcor2 contenente le coordinate (x,y)
	 */
	@Override
	public Vector2 getPosition()
	{
		return body.getPosition();
	}

	/**
	 * Metodo per ottenere il vettore velocita' dell'oggetto animato
	 * @return Vector2 contenente le coordianate del vettore velocita' (x,y)
	 */
	@Override
	public Vector2 getVelocity()
	{
		return body.getLinearVelocity();
	}

	/**
	 * Muove l'ggetto a destra
	 */
	@Override
	public void moveRight()
	{
		if (getVelocity().x <= 2.25f)
			body.applyLinearImpulse(new Vector2(0.1f, 0), body.getWorldCenter(), true);
	}

	/**
	 * Muove l'ggetto a sinistra
	 */
	@Override
	public void moveLeft()
	{
		if (getVelocity().x >= -2.25f)
			body.applyLinearImpulse(new Vector2(-0.1f, 0), body.getWorldCenter(), true);
	}

	/**
	 * Muove l'ggetto in alto
	 */
	@Override
	public void moveUp()
	{
		body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);
	}

	/**
	 * Muove l'ggetto in basso
	 */
	@Override
	public void moveDown()
	{
		body.applyLinearImpulse(new Vector2(0, -4f), body.getWorldCenter(), true);
	}

}
