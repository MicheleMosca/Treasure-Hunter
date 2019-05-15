package com.game.graphics.entities;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.game.AdventureGame;
import com.badlogic.gdx.physics.box2d.World;

/**
 * 
 * Classe non considerata
 */

public class TexturedEntity extends Entity
{
	protected TextureAtlas textureAtlas;
	protected Sprite sprite;
	protected HashMap<String, TextureRegion> textureRegions;

	public TexturedEntity(World world, MapObject mapObject, BodyType bodyType, String textureAtlasPath, String textureRegionName, Vector2 textureDimension)
	{
		super(world, mapObject, bodyType);
		textureRegions = new HashMap<String, TextureRegion>();
		initSprite(textureAtlasPath, textureRegionName, textureDimension);
	}

	private void initSprite(String textureAtlasPath, String textureRegionName, Vector2 textureDimension)
	{
		textureAtlas = new TextureAtlas(textureAtlasPath);
		sprite.setTexture(textureAtlas.findRegion(textureRegionName).getTexture());
		textureRegions.put(textureRegionName, new TextureRegion(sprite.getTexture(), 0, 0, textureDimension.x, textureDimension.y));
		sprite.setBounds(0, 0, textureDimension.x / AdventureGame.pixelPerMeter, textureDimension.y / AdventureGame.pixelPerMeter);
		sprite.setRegion(textureRegions.get(textureRegionName));

		update();
	}

	public void update()
	{
		sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2 , body.getPosition(). y - sprite.getHeight() / 2);
	}

}
