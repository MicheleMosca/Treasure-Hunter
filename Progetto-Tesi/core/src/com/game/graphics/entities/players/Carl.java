package com.game.graphics.entities.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.graphics.entities.MovableAnimatedEntity;

import java.util.Timer;
import java.util.TimerTask;


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
    private boolean isAlive;

    private static boolean isSliding;
    private Timer sliderTimer;

    public Carl(World world, MapObject spawnPoint)
    {
        super(world, spawnPoint, BodyDef.BodyType.DynamicBody, defaultTextureAtlasPath, defaultTextureRegionName,
                new Vector2(64 , 98));

        isLeft = false;
        isAlive = true;
        isSliding = false;

        initCarl();
    }

    private void initCarl()
    {
        // Creo le animazioni di Carl
        createAnimation(AnimationState.Run, "sprites/Carl/Run/Run.atlas", "Run0",
                new Vector2(83, 101), Animation.PlayMode.LOOP);
        createAnimation(AnimationState.Jump, "sprites/Carl/Jump/Jump.atlas", "Jump0",
                new Vector2(81, 107), Animation.PlayMode.NORMAL);
        createAnimation(AnimationState.Dead, "sprites/Carl/Dead/Dead.atlas", "Dead0",
                new Vector2(118, 120), Animation.PlayMode.NORMAL);
        createAnimation(AnimationState.Slide, "sprites/Carl/Slide/Slide.atlas", "Slide0",
                new Vector2(79, 78), Animation.PlayMode.NORMAL);
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public boolean isSliding()
    {
        return isSliding;
    }

    public void setSliding(boolean sliding)
    {
        isSliding = sliding;
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
        inputHandler();
        if (isLeft)
            getSprite().setFlip(true, false);
    }

    private void inputHandler()
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_9))
            isAlive = false;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && (getVelocity().y == 0))
            moveUp();

        if (getVelocity().x == 0 && isSliding == true)
        {
            isSliding = false;
            sliderTimer.cancel();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT) && (getVelocity().x != 0))
        {
            isSliding = true;
            sliderTimer = new Timer();
            sliderTimer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    Carl.isSliding = false;
                }
            }, 1000);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            moveRight();
        else if (Gdx.input.isKeyPressed(Input.Keys.A))
            moveLeft();
        else
            body.setLinearVelocity(0, getVelocity().y);
    }

    @Override
    protected AnimationState getState()
    {
        if (isAlive == false)
            return AnimationState.Dead;

        if (isSliding == true)
            return AnimationState.Slide;

        if (body.getLinearVelocity().y != 0)
            return AnimationState.Jump;

        if (body.getLinearVelocity().x > 0f)
        {
            isLeft = false;
            return AnimationState.Run;
        }
        else if (body.getLinearVelocity().x < -0f)
        {
            isLeft = true;
            return AnimationState.Run;
        }

        return AnimationState.Idle;
    }
}
