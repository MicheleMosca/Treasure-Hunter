package com.game.graphics.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.game.AdventureGame;
import com.game.Enum.AnimationState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe che definisce un elemento del gioco avente una texture e un animazione legata al proprio corpo affetto
 * da collisioni da parte di altri elementi di gioco
 */

public class AnimatedEntity extends Entity
{
	// parametri di default per una semplice sprite all'inizio
	protected HashMap<AnimationState, TextureAtlas> textureAtlas;
	protected Sprite sprite;
	protected HashMap<String, TextureRegion> textureRegions;
	protected Vector2 textureDimension;

	protected AnimationState currentState;
	protected AnimationState previousState;
	protected HashMap<AnimationState, Animation<TextureRegion>> animations;

	private float stateTimer;

	public AnimatedEntity(World world, MapObject mapObject, BodyType bodyType, String textureAtlasPath,
						  String textureRegionName, Vector2 textureDimension, AnimationState animationName)
	{
		super(world, mapObject, bodyType, textureDimension);

		this.textureDimension = textureDimension;

		textureRegions = new HashMap<String, TextureRegion>();
		sprite = new Sprite();
		textureAtlas = new HashMap<AnimationState, TextureAtlas>();
		animations = new HashMap<AnimationState, Animation<TextureRegion>>();

		textureAtlas.put(animationName, new TextureAtlas(textureAtlasPath));
		currentState = AnimationState.Idle;
		previousState = AnimationState.Idle;
		stateTimer = 0;

		initSprite(textureRegionName, textureDimension, animationName);
	}

	private void initSprite(String textureRegionName, Vector2 textureDimension, AnimationState animationName)
	{
		sprite.setTexture(textureAtlas.get(animationName).findRegion(textureRegionName).getTexture());
		textureRegions.put(textureRegionName, new TextureRegion(sprite.getTexture(),
				0, 0, (int)textureDimension.x, (int)textureDimension.y));
		sprite.setBounds(0, 0, textureDimension.x / AdventureGame.pixelPerMeter,
				textureDimension.y / AdventureGame.pixelPerMeter);
		sprite.setRegion(textureRegions.get(textureRegionName));

		createAnimation(animationName, "" , textureRegionName);
	}

	private void createAnimation(AnimationState animationName, String textureAtlasPath, String textureRegionName)
	{
		if (textureAtlas.containsKey(animationName) == false)
			textureAtlas.put(animationName, new TextureAtlas(textureAtlasPath));

		animations.put(animationName, new Animation<TextureRegion>(0.1f, textureAtlas.get(animationName).getRegions(), Animation.PlayMode.LOOP));
	}

	private TextureRegion getFrame(float deltaTime)
	{
		currentState = getState();
		TextureRegion region = animations.get(currentState).getKeyFrame(stateTimer);

		// Avanzamento dei frame di animazione inerenti alla stesso stato
		if (currentState == previousState)
			stateTimer += deltaTime;
		else
			stateTimer = 0;
		previousState = currentState;
		return region;
	}

	private AnimationState getState()
	{
		return AnimationState.Idle;
	}

	public void update(float deltaTime)
	{
		sprite.setRegion(getFrame(deltaTime));
		sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2 , body.getPosition(). y - sprite.getHeight() / 2);
	}

	public Sprite getSprite()
	{
		return sprite;
	}
}
