package com.game.graphics.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.AdventureGame;
import com.game.Enum.AnimationState;
import com.game.graphics.Panels.GameOver;
import com.game.screens.PlayScreen;

public class Chest extends AnimatedEntity
{
    // Inserisco i parametri che di default avrà il personaggio
    private static final String defaultTextureAtlasPath = "sprites/Chest/Idle.atlas";
    private static final String defaultTextureRegionName = "Idle0";

    public Chest(World world, MapObject spawnPoint)
    {
        super(world, spawnPoint, BodyDef.BodyType.StaticBody, defaultTextureAtlasPath, defaultTextureRegionName,
                new Vector2(128 / 2, 128 / 2), AnimationState.Idle);

        animations.get(AnimationState.Idle).setFrameDuration(2.5f);
        body.getFixtureList().get(0).setSensor(true);   // tolgo tutte le collisioni standard
    }

    public void endLevel()
    {
        PlayScreen.gameOnPause = true;
        // Victory start menu
    }
}
