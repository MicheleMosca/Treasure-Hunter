package com.game.graphics.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.graphics.panels.Victory;
import com.game.screens.PlayScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che serve ad instanziare una chest di fine livello
 */

public class Chest extends AnimatedEntity implements ActionListener
{
    // Inserisco i parametri che di default avrà il personaggio
    private static final String defaultTextureAtlasPath = "sprites/Chest/Idle.atlas";
    private static final String defaultTextureRegionName = "Idle0";

    private AnimationState state;
    private Timer openTimer;

    private PlayScreen playScreen;

    public Chest(World world, MapObject spawnPoint, PlayScreen playScreen)
    {
        super(world, spawnPoint, BodyDef.BodyType.StaticBody, defaultTextureAtlasPath, defaultTextureRegionName,
                new Vector2(128 / 2, 128 / 2), AnimationState.Idle);

        this.playScreen = playScreen;

        animations.get(AnimationState.Idle).setPlayMode(Animation.PlayMode.NORMAL);
        body.getFixtureList().get(0).setSensor(true);   // tolgo tutte le collisioni standard

        createAnimation(AnimationState.Open, "sprites/Chest/Open.atlas", new Vector2(128 / 2, 128 / 2), Animation.PlayMode.NORMAL);
        animations.get(AnimationState.Open).setFrameDuration(0.3f);
        state = AnimationState.Idle;
    }

    /**
     * Ottendo l'azione attuale della chest
     * @return AnimationState contenente lo state inerente all'azione svolta
     */
    @Override
    protected AnimationState getState()
    {
        return state;
    }

    /**
     * Metodo per terminare il livello attuale
     */
    public void endLevel()
    {
        state = AnimationState.Open;
        openTimer = new Timer(700, this);
        openTimer.start();
    }

    /**
     * Metodo per ricevere gli eventi di tipo ActionEvent, serve per aspettare che la chest finisca l'animazione
     * @param e ActionEvent che rappresenta l'evento
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == openTimer)
        {
            openTimer.stop();
            Victory.setVisible(true);
            playScreen.setGameOnPause(true);
        }
    }
}
