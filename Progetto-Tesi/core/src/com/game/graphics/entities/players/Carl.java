package com.game.graphics.entities.players;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.graphics.entities.MovableAnimatedEntity;

/**
 * Classe di specializzazione dei vari personaggi di gioco
 * @author Michele
 *
 */

public class Carl extends MovableAnimatedEntity
{
    private static final String textureAtlasPath = "sprites/Carl/Idle/Idle.atlas";
    private static final String textureRegionName = "Idle0";
    private static final Vector2 textureDimension = new Vector2(64, 98);

    public Carl(World world, MapObject spawnPoint)
    {
        super(world, spawnPoint, BodyDef.BodyType.DynamicBody, textureAtlasPath, textureRegionName, textureDimension);
    }
}
