package com.game.graphics.entities.players;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.AdventureGame;
import com.game.Enum.AnimationState;
import com.game.graphics.entities.MovableAnimatedEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe di specializzazione dei vari personaggi di gioco
 * @author Michele
 *
 */

public class Carl extends MovableAnimatedEntity
{
    // Inserisco i parametri che di default avrà il personaggio
    private static final String defaultTextureAtlasPath = "sprites/Carl/Idle/Idle.atlas";
    private static final String defaultTextureRegionName = "Idle0";
    private boolean isLeft;

    public Carl(World world, MapObject spawnPoint)
    {
        super(world, spawnPoint, BodyDef.BodyType.DynamicBody, defaultTextureAtlasPath, defaultTextureRegionName,
                new Vector2(64 , 98));

        isLeft = false;

        initCarl();
    }

    private void initCarl()
    {
        // Creo le animazioni di Carl
        createAnimation(AnimationState.Run, "sprites/Carl/Run/Run.atlas", "Run0",
                new Vector2(83, 101));
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
        if (isLeft)
            getSprite().setFlip(true, false);
    }

    @Override
    protected AnimationState getState()
    {
        if (body.getLinearVelocity().x > 0.1f)
        {
            isLeft = false;
            return AnimationState.Run;
        }

        if (body.getLinearVelocity().x < -0.1f)
        {
            isLeft = true;
            return AnimationState.Run;
        }

        return AnimationState.Idle;
    }
}
