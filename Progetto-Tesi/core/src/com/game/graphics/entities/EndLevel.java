package com.game.graphics.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;

public class EndLevel extends AnimatedEntity
{
    public EndLevel(World world, MapObject mapObject, BodyDef.BodyType bodyType, String textureAtlasPath, String textureRegionName, Vector2 textureDimension, AnimationState animationName)
    {
        super(world, mapObject, bodyType, textureAtlasPath, textureRegionName, textureDimension, animationName);
    }

    public void gotFinish()
    {
        System.out.println("Victory!");
    }
}
