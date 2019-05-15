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

public class Pippo extends MovableAnimatedEntity
{
    private static final String textureAtlasPath = "sprites/PlayerIdle.pack";
    private static final String textureRegionName = "Idle0";
    private static final Vector2 textureDimension = new Vector2(319, 486);

    public Pippo(World world, MapObject spawnPoint)
    {
        super(world, spawnPoint, BodyDef.BodyType.DynamicBody, textureAtlasPath, textureRegionName, textureDimension);
    }
}
