package com.game.graphics.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.game.AdventureGame;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Entity extends Sprite
{
	private Body body;

	public Entity(World world, MapObject mapObject, BodyType bodyType)
	{
		super();
		BodyDef bodyDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();		
		
		if (mapObject instanceof RectangleMapObject)
		{
			Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
			
			bodyDef.type = bodyType;
			bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / AdventureGame.pixelPerMeter, (rectangle.getY() + rectangle.getHeight() /2) / AdventureGame.pixelPerMeter);
			
			body = world.createBody(bodyDef);
			
			shape.setAsBox((rectangle.getWidth() / 2) / AdventureGame.pixelPerMeter, (rectangle.getHeight() / 2) / AdventureGame.pixelPerMeter);
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}
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
