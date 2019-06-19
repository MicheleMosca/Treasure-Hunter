package com.game.graphics.panels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.AdventureGame;
import com.game.User;
import com.game.screens.LevelScreen;
import com.game.screens.PlayScreen;

public class Pause extends ChangeListener
{
    public static Stage stage;
    private static boolean visible;
    private AdventureGame game;
    private PlayScreen playScreen;

    private User userData;

    public Pause(AdventureGame game, PlayScreen playScreen, User userData)
    {
        super();

        visible = false;
        this.game = game;
        this.playScreen = playScreen;
        this.userData = userData;

        stage = new Stage();
        Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);

        Table table = new Table();
        table.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
        table.setSize(stageSize.x, stageSize.y);
        table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/pause/table.png"))));

        Texture resumeTexture = new Texture("menu/pause/resume.png");

        com.badlogic.gdx.scenes.scene2d.ui.Button resumeButton = new com.badlogic.gdx.scenes.scene2d.ui.Button(new TextureRegionDrawable(new TextureRegion((resumeTexture))));
        resumeButton.setName("resume");
        resumeButton.addListener(this);

        Texture exitTexture = new Texture("menu/back.png");

        com.badlogic.gdx.scenes.scene2d.ui.Button exitButton = new com.badlogic.gdx.scenes.scene2d.ui.Button(new TextureRegionDrawable(new TextureRegion((exitTexture))));
        exitButton.setName("exit");
        exitButton.addListener(this);

        table.bottom();
        table.add(resumeButton).size(1298 / 6,952 / 9).padBottom(20);
        table.row();
        table.add(exitButton).size(1298 / 6,952 / 9).padBottom(90);

        stage.addActor(table);
    }

    public static void setVisible(boolean state)
    {
        visible = state;

        if (visible)
            Gdx.input.setInputProcessor(stage);
    }

    public boolean isVisible()
    {
        return visible;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor)
    {
        if (actor.getName().equals("resume"))
        {
            setVisible(false);
            playScreen.setGameOnPause(false);
            Gdx.input.setInputProcessor(null);
        }

        else if (actor.getName().equals("exit"))
        {
            stage.dispose();
            playScreen.dispose();
            game.setScreen(new LevelScreen(game, userData, true));
        }
    }
}
