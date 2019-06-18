package com.game.graphics.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.graphics.panels.GameOver;
import com.game.graphics.panels.Victory;
import com.game.screens.PlayScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        createAnimation(AnimationState.Open, "sprites/Chest/Open.atlas",
                "Open0", new Vector2(128 / 2, 128 / 2), Animation.PlayMode.NORMAL);
        animations.get(AnimationState.Open).setFrameDuration(0.3f);
        state = AnimationState.Idle;
    }

    @Override
    protected AnimationState getState()
    {
        return state;
    }

    public void endLevel()
    {
        state = AnimationState.Open;
        openTimer = new Timer(700, this);
        openTimer.start();
    }

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
