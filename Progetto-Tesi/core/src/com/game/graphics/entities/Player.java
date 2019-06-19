package com.game.graphics.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.graphics.panels.GameOver;
import com.game.screens.PlayScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe che deinisce la struttura dell'entità giocabile
 * @author Michele
 *
 */

public abstract class Player extends MovableAnimatedEntity implements ActionListener
{
    private boolean isLeft;
    private boolean isAlive;

    private boolean isSliding;
    private Timer sliderTimer;
    private Timer deadTimer;

    private int health;

    private PlayScreen playScreen;

    public Player(World world, MapObject mapObject, BodyDef.BodyType bodyType, String textureAtlasPath,
                  String textureRegionName, Vector2 textureDimension, PlayScreen playScreen)
    {
        super(world, mapObject, bodyType, textureAtlasPath, textureRegionName, textureDimension);

        this.playScreen = playScreen;

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
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == sliderTimer)
        {
            isSliding = false;
            sliderTimer.stop();
        }
        else if (e.getSource() == deadTimer)
        {
            playScreen.setGameOnPause(true);
            GameOver.setVisible(true);
            deadTimer.stop();
        }
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
        {
            if (playScreen.getTutorialState() == AnimationState.Jump)
                playScreen.setTutorialStageNull();

            moveUp();
        }

        if (getVelocity().x == 0 && isSliding)
        {
            isSliding = false;
            sliderTimer.stop();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT) && getVelocity().x != 0
                && getState() != AnimationState.Jump && !isSliding)
        {
            if (playScreen.getTutorialState() == AnimationState.Slide)
                playScreen.setTutorialStageNull();

            isSliding = true;
            sliderTimer = new Timer(1600, this);
            sliderTimer.start();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
            if (playScreen.getTutorialState() == AnimationState.Idle)
                playScreen.setTutorialStageNull();

            moveRight();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
            if (playScreen.getTutorialState() == AnimationState.Idle)
                playScreen.setTutorialStageNull();

            moveLeft();
        }
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
        health -= damage;

        if (health <= 0)
        {
            deadTimer = new Timer(650, this);
            deadTimer.start();
            isAlive = false;
        }
    }
}
