package com.game.graphics.entities.players;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.graphics.entities.Player;
import com.game.screens.PlayScreen;

/**
 * Classe che definisce il player Carl
 */

public class Carl extends Player
{
    // Inserisco i parametri che di default avrà il personaggio
    private static final String defaultTextureAtlasPath = "sprites/Carl/Idle/Idle.atlas";
    private static final String defaultTextureRegionName = "Idle0";

    public Carl(World world, MapObject spawnPoint, PlayScreen playScreen)
    {
        super(world, spawnPoint, BodyDef.BodyType.DynamicBody, defaultTextureAtlasPath, defaultTextureRegionName,
                new Vector2(64 , 98), playScreen);

        initCarl();
    }

    private void initCarl()
    {
        // Creo le animazioni di Carl
        createAnimation(AnimationState.Run, "sprites/Carl/Run/Run.atlas",
                new Vector2(83, 101), Animation.PlayMode.LOOP);
        createAnimation(AnimationState.Jump, "sprites/Carl/Jump/Jump.atlas",
                new Vector2(81, 107), Animation.PlayMode.NORMAL);
        createAnimation(AnimationState.Dead, "sprites/Carl/Dead/Dead.atlas",
                new Vector2(118, 96), Animation.PlayMode.NORMAL);
        createAnimation(AnimationState.Slide, "sprites/Carl/Slide/Slide.atlas",
                new Vector2(79, 78), Animation.PlayMode.NORMAL);
    }
}
