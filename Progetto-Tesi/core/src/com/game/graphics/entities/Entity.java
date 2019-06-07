package com.game.graphics.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.game.AdventureGame;

/**
 * 
 * Classe che definisce un entità del gioco con corpo, il quale è affetto da collisioni da parte di altri elementi di gioco
 *
 */

public class Entity
{
	protected Body body;
	
	public Entity(Body body)
	{
		this.body = body;
		body.setUserData(this);
	}

	public Entity(World world, MapObject mapObject, BodyType bodyType)
	{
		initBody(world, mapObject, bodyType);
		body.setUserData(this);
	}

	public Entity(World world, MapObject mapObject, BodyType bodyType, Vector2 bodyDimension)
	{
		initBodyWithDimension(world, mapObject, bodyType, bodyDimension);
		body.setUserData(this);
	}
	
	private void initBody(World world, MapObject mapObject, BodyType bodyType)
	{
		BodyDef bodyDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();		
		
		if (mapObject instanceof RectangleMapObject)
		{
			Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
			
			bodyDef.type = bodyType;
			bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / AdventureGame.pixelPerMeter,
					(rectangle.getY() + rectangle.getHeight() /2) / AdventureGame.pixelPerMeter);
			
			body = world.createBody(bodyDef);
			
			shape.setAsBox((rectangle.getWidth() / 2) / AdventureGame.pixelPerMeter, (rectangle.getHeight() / 2) / AdventureGame.pixelPerMeter);
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}
	}

	private void initBodyWithDimension(World world, MapObject mapObject, BodyType bodyType, Vector2 bodyDimension)
	{
		BodyDef bodyDef = new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();

		if (mapObject instanceof RectangleMapObject)
		{
			PolygonShape shape = new PolygonShape();
			Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();

			bodyDef.type = bodyType;
			bodyDef.position.set((rectangle.getX() + bodyDimension.x / 2) / AdventureGame.pixelPerMeter,
					(rectangle.getY() + bodyDimension.y /2) / AdventureGame.pixelPerMeter);

			body = world.createBody(bodyDef);

			shape.setAsBox((bodyDimension.x / 2) / AdventureGame.pixelPerMeter,
					(bodyDimension.y / 2 ) / AdventureGame.pixelPerMeter);
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}
		else if (mapObject instanceof EllipseMapObject)
		{
			CircleShape shape = new CircleShape();
			Ellipse ellipse = ((EllipseMapObject) mapObject).getEllipse();

			bodyDef.type = bodyType;
			bodyDef.position.set((ellipse.x + bodyDimension.x / 2) / AdventureGame.pixelPerMeter,
					(ellipse.y + bodyDimension.y /2) / AdventureGame.pixelPerMeter);

			body = world.createBody(bodyDef);

			// Setto il raggio in base alla diagonale della mia sprite
			shape.setRadius( (float) Math.sqrt( Math.pow((bodyDimension.x / 2) / AdventureGame.pixelPerMeter , 2) /2 +
					Math.pow((bodyDimension.y / 2 ) / AdventureGame.pixelPerMeter, 2) /2) );
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}
	}

	protected void resetBodyShape(Vector2 previousBodyDimension, Vector2 bodyDimension)
	{
		float positionY = 0;

		if (previousBodyDimension.y > bodyDimension.y)
			positionY = body.getPosition().y - (((previousBodyDimension.y - bodyDimension.y) / 2) / AdventureGame.pixelPerMeter);
		else
			positionY = body.getPosition().y + (((bodyDimension.y - previousBodyDimension.y) / 2) / AdventureGame.pixelPerMeter);

		Shape shape = body.getFixtureList().get(0).getShape();
		if (shape instanceof PolygonShape)
		{
			((PolygonShape) shape).setAsBox((bodyDimension.x / 2) / AdventureGame.pixelPerMeter,
					(bodyDimension.y / 2 ) / AdventureGame.pixelPerMeter);
		}
		else if (shape instanceof CircleShape)
		{
			((CircleShape) shape).setRadius( (float) Math.sqrt( Math.pow((bodyDimension.x / 2) / AdventureGame.pixelPerMeter , 2) /2 +
					Math.pow((bodyDimension.y / 2 ) / AdventureGame.pixelPerMeter, 2) /2) );
		}

		body.setTransform(body.getPosition().x, positionY, 0);
	}

}