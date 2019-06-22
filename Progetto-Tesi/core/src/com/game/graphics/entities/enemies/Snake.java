package com.game.graphics.entities.enemies;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.graphics.entities.AnimatedEntity;
import com.game.graphics.entities.Player;
import com.game.interfaces.Enemy;
import com.game.screens.PlayScreen;

/**
 * Classe per implementare un serpente nel gioco
 */

public class Snake extends AnimatedEntity implements Enemy
{

	private static final String defaultTextureAtlasPath = "sprites/Snake/Idle/Idle.atlas";
	private static final String defaultTextureRegionName = "Idle0";
	private int damage;
	private PlayScreen playScreen;
	
	public Snake(World world, MapObject spawnPoint, BodyType bodyType, PlayScreen playScreen)
	{
		 super(world, spawnPoint, bodyType, defaultTextureAtlasPath, defaultTextureRegionName,
	                new Vector2(64 , 98), AnimationState.Idle);
		 
		 body.getFixtureList().get(0).setSensor(true);
		 this.playScreen = playScreen;
		 
		 damage=1;
	}

	/**
	 * Metodo per aggiornare la posizione del serprente in gioco
	 * @param deltaTime tempo trascorso tra un frame ed un altro
	 */
	@Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);

		if (playScreen.getPlayer().getBody().getPosition().x > body.getPosition().x)
			getSprite().setFlip(true, false);
	}

	/**
	 * Funzione per colpire un determinato target
	 * @param target Oggetto da colpire
	 */
	@Override
	public void hit(Player target)
	{
		 target.takeHit(damage);
	}

	
}
