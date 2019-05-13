package com.game.graphics.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.game.AdventureGame;

/**
 * Classe temporanea per utilizzare un player
 * @author Michele
 *
 */

public class Player extends Sprite
{	
	private TextureAtlas textureAtlas;
	private Body body;
	private TextureRegion idle;
	private Vector2 pixelSpriteDimension;
	
	public Player(World world, MapObject mapObject, BodyType bodyType)
	{
		super();
		pixelSpriteDimension = new Vector2(319 / 5, 486 / 5);
		BodyDef bodyDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		textureAtlas = new TextureAtlas("sprites/PlayerIdle.pack");
		setTexture(textureAtlas.findRegion("Idle0").getTexture());
		idle = new TextureRegion(getTexture(), 0, 0, 319, 486);
		setBounds(0, 0, pixelSpriteDimension.x / AdventureGame.pixelPerMeter, pixelSpriteDimension.y / AdventureGame.pixelPerMeter);
		setRegion(idle);
		
		if (mapObject instanceof RectangleMapObject)
		{
			Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
			
			bodyDef.type = bodyType;
			bodyDef.position.set((rectangle.getX() + pixelSpriteDimension.x / 2) / AdventureGame.pixelPerMeter, (rectangle.getY() + pixelSpriteDimension.y /2) / AdventureGame.pixelPerMeter);;
			
			body = world.createBody(bodyDef);
			
			shape.setAsBox((pixelSpriteDimension.x / 2) / AdventureGame.pixelPerMeter, (pixelSpriteDimension.y / 2 ) / AdventureGame.pixelPerMeter);
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}
		update();
	}
	
	public void update()
	{
		setPosition(body.getPosition().x - getWidth() / 2 , body.getPosition(). y - getHeight() / 2);
	}

	public Body getBody()
	{
		return body;
	}

	public void setBody(Body body)
	{
		this.body = body;
	}
}
