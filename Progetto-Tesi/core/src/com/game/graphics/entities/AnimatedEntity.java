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

import java.util.HashMap;
import java.util.Vector;

/**
 * Classe che definisce un elemento del gioco avente una texture e un animazione legata al proprio corpo affetto
 * da collisioni da parte di altri elementi di gioco
 */

public class AnimatedEntity extends Entity
{
	// parametri di default per una semplice sprite all'inizio
	private HashMap<AnimationState, TextureAtlas> textureAtlas;
	private Sprite sprite;
	private HashMap<String, TextureRegion> textureRegions;

	// attributi per la gestione delle animazioni
	private AnimationState currentState;
	private AnimationState previousState;
	private HashMap<AnimationState, Animation<TextureRegion>> animations;

	// dimensioni delle texture inerenti alle animazioni
	private HashMap<AnimationState, Vector2> texturesDimension;

	private float stateTimer;

	public AnimatedEntity(World world, MapObject mapObject, BodyType bodyType, String textureAtlasPath,
						  String textureRegionName, Vector2 textureDimension, AnimationState animationName)
	{
		super(world, mapObject, bodyType, textureDimension);

		textureRegions = new HashMap<String, TextureRegion>();
		sprite = new Sprite();
		textureAtlas = new HashMap<AnimationState, TextureAtlas>();
		animations = new HashMap<AnimationState, Animation<TextureRegion>>();
		texturesDimension = new HashMap<AnimationState, Vector2>();

		textureAtlas.put(animationName, new TextureAtlas(textureAtlasPath));
		currentState = AnimationState.Idle;
		previousState = AnimationState.Idle;
		stateTimer = 0;
		texturesDimension.put(animationName, textureDimension);

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

		createAnimation(animationName, "" , textureRegionName, textureDimension, Animation.PlayMode.LOOP);
	}

	protected void createAnimation(AnimationState animationName, String textureAtlasPath, String textureRegionName,
								   Vector2 textureDimension, Animation.PlayMode animationMode)
	{
		if (textureAtlas.containsKey(animationName) == false)
		{
			textureAtlas.put(animationName, new TextureAtlas(textureAtlasPath));
			texturesDimension.put(animationName, textureDimension);
		}

		animations.put(animationName, new Animation<TextureRegion>(0.1f, textureAtlas.get(animationName).getRegions(),
				animationMode));
	}

	private TextureRegion getFrame(float deltaTime)
	{
		currentState = getState();

		// Avanzamento dei frame di animazione inerenti alla stesso stato
		if (currentState == previousState)
			stateTimer += deltaTime;
		else
		{
			stateTimer = 0;

			// sovrascrivo la texture della sprite con quella dello stato corrispondente (alla prima regione)
			sprite.setTexture(textureAtlas.get(currentState).getRegions().get(0).getTexture());

			// imposto le dimensioni della sprite in base alla texture inserita
			sprite.setBounds(0, 0, texturesDimension.get(currentState).x / AdventureGame.pixelPerMeter,
					texturesDimension.get(currentState).y / AdventureGame.pixelPerMeter);

			// importo le dimensioni del body in base alla texture
			resetBodyShape(texturesDimension.get(previousState), texturesDimension.get(currentState));
		}

		// prelevo la regione della texture che mi interessa in base allo stateTimer
		TextureRegion region = animations.get(currentState).getKeyFrame(stateTimer);

		previousState = currentState;
		return region;
	}

	protected AnimationState getState()
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
