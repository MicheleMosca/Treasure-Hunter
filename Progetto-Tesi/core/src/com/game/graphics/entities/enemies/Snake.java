package com.game.graphics.entities.enemies;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.graphics.entities.AnimatedEntity;
import com.game.graphics.entities.Player;
import com.game.interfaces.Enemy;

public class Snake extends AnimatedEntity implements Enemy
{

	private static final String defaultTextureAtlasPath = "sprites/Snake/Idle/Idle.atlas";
	private static final String defaultTextureRegionName = "Idle0";
	private int damage;

	
	public Snake(World world, MapObject spawnPoint, BodyType bodyType)
	{
		 super(world, spawnPoint, bodyType, defaultTextureAtlasPath, defaultTextureRegionName,
	                new Vector2(64 , 98), AnimationState.Idle);
		 
		 body.getFixtureList().get(0).setSensor(true);
		 
		 damage=1;
	}

	@Override
	public void hit(Player target)
	{
		 target.takeHit(damage);
	}

	
}
