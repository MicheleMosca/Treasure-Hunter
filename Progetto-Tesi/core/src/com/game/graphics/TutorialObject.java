package com.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Enum.AnimationState;
import com.game.graphics.entities.Entity;
import com.game.screens.PlayScreen;

public class TutorialObject extends Entity
{
    private PlayScreen playScreen;
    private String text;
    private String command;
    private AnimationState tutorialState;
    private boolean visualized;

    public TutorialObject(World world, MapObject mapObject, BodyDef.BodyType bodyType,
                          PlayScreen playScreen, String text, String command, AnimationState tutorialState)
    {
        super(world, mapObject, bodyType);

        this.playScreen = playScreen;
        this.text = text;
        this.command = command;
        this.tutorialState = tutorialState;

        visualized = false;
        body.getFixtureList().get(0).setSensor(true);   // tolgo tutte le collisioni standard
    }

    public boolean isVisualized()
    {
        return visualized;
    }

    public void showTutorial()
    {
        visualized = true;
        playScreen.initTutorial(text, command, tutorialState);
    }
}
