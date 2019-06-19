package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.AdventureGame;
import com.game.User;

public class RankingScreen extends ChangeListener implements Screen
{
    private AdventureGame game;
    private User userData;

    private Stage stage;
    private Texture background;

    private int level;

    public RankingScreen(AdventureGame game, User userData, int level)
    {
        this.game = game;
        this.userData = userData;
        this.level = level;

        drawUI();
    }

    private void drawUI()
    {
        // Creo uno stage e le dimensioni di tale stage
        stage = new Stage();
        Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);

        // Texture del background
        background = new Texture("menu/level_select/Sky.png");

        Table table = new Table();
        table.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
        table.setSize(stageSize.x, stageSize.y);
        if (level == 1)
            table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/ranking/table1.png"))));
        else if (level == 2)
            table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/ranking/table2.png"))));

        Texture closeTexture = new Texture("menu/close.png");
        Button exitButton = new Button(new TextureRegionDrawable(new TextureRegion((closeTexture))));
        exitButton.setName("exit");
        exitButton.addListener(this);
        exitButton.setSize(214 / 5, 215 / 5);
        exitButton.setPosition(table.getX() + table.getWidth() - (exitButton.getWidth()*2),
                table.getY() + table.getHeight() - exitButton.getHeight() - 10);

        // Creo un table per contenere le label della score
        Table tableScoreText = new Table();

        // Definisco lo style che devono avere le label
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.createFont(30);
        labelStyle.fontColor = Color.WHITE;

        // Label per il testo username
        Label usernameTextLabel = new Label("username", labelStyle);

        // Label per il testo coins
        Label coinsTextLabel = new Label("coins", labelStyle);

        // Label per il testo time
        Label timeTextLabel = new Label("time", labelStyle);

        // Aggiungo le label di testo al table
        tableScoreText.add(usernameTextLabel).padRight(50);
        tableScoreText.add(coinsTextLabel).padRight(50);
        tableScoreText.add(timeTextLabel);

        // Aggiungo il table dei testi al table principale
        table.top();
        table.add(tableScoreText).padTop(100);
        table.row();

        stage.addActor(table);
        stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void changed(ChangeEvent event, Actor actor)
    {
        dispose();

        if (actor.getName().equals("exit"))
            game.setScreen(new RankingLevelScreen(game, userData));
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        // Pulisco il buffer dello schermo
        Gdx.gl.glClearColor(0, 0, 0, 1);	//Red, gree, blue, alpha(0 = trasparete, 1 = opaco)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //inserire nello stage il background
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();

        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        stage.dispose();
        background.dispose();
    }
}
