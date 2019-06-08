package com.game.graphics.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.graphics.entities.MovableAnimatedEntity;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Classe che deinisce la struttura dell'entità giocabile
 * @author Michele
 *
 */

public abstract class Player extends MovableAnimatedEntity
{
    private boolean isLeft;
    private boolean isAlive;

    private static boolean isSliding;
    private Timer sliderTimer;

    private int health;

    public Player(World world, MapObject mapObject, BodyDef.BodyType bodyType, String textureAtlasPath, String textureRegionName, Vector2 textureDimension)
    {
        super(world, mapObject, bodyType, textureAtlasPath, textureRegionName, textureDimension);

        isLeft = false;
        isAlive = true;
        isSliding = false;
        health = 1;
    }

    /**
     * Mostra lo stato di salute del player
     * @return Ritorna True se il playe è ancora vivo
     */
    public boolean isAlive()
    {
        return isAlive;
    }

    /**
     * Indica se il player è in fase di sliding
     * @return Ritorna True se è in stato di sliding
     */
    public boolean isSliding()
    {
        return isSliding;
    }

    /**
     * Imposta lo stato di slinding del player
     * @param sliding True per mettere il player in stato di sliding
     */
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && (getVelocity().y == 0) && getState() != AnimationState.Slide)
            moveUp();

        if (getVelocity().x == 0 && isSliding)
        {
            isSliding = false;
            sliderTimer.cancel();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT) && getVelocity().x != 0 && getState() != AnimationState.Jump)
        {
            isSliding = true;
            sliderTimer = new Timer();
            sliderTimer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    Player.isSliding = false;
                }
            }, 1800);
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
        if (!isAlive)
            return AnimationState.Dead;

        if (isSliding)
            return AnimationState.Slide;

        if (body.getLinearVelocity().y != 0)
            return AnimationState.Jump;

        if (body.getLinearVelocity().x > 0f || Gdx.input.isKeyPressed(Input.Keys.D))
        {
            isLeft = false;
            return AnimationState.Run;
        }
        else if (body.getLinearVelocity().x < -0f || Gdx.input.isKeyPressed(Input.Keys.A))
        {
            isLeft = true;
            return AnimationState.Run;
        }

        return AnimationState.Idle;
    }

    /**
     * Mostra il numero delle vite del player
     * @return numero delle vite
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Funzione che esprime l'azione da compiere appena si è preso danno
     * @param damage valore del danno preso
     */
    public void takeHit(int damage)
    {
        if (health - damage <= 0)
        {
            System.out.println("Game Over!");
            isAlive = false;
            return;
        }

        health -= damage;
    }
}
