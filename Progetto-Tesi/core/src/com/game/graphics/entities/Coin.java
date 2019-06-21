package com.game.graphics.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;

/**
 * Classe che serve ad instanziare una moneta nel gioco
 */

public class Coin extends AnimatedEntity
{
    // Inserisco i parametri che di default avrà il coin
    private static final String defaultTextureAtlasPath = "sprites/Coin/Idle.atlas";
    private static final String defaultTextureRegionName = "Idle0";

    public Coin(World world, MapObject spawnPoint)
    {
        super(world, spawnPoint, BodyDef.BodyType.StaticBody, defaultTextureAtlasPath, defaultTextureRegionName, new Vector2(30, 30), AnimationState.Idle);

        body.getFixtureList().get(0).setSensor(true);   // tolgo tutte le collisioni standard
    }

}
