package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.AdventureGame;

/**
 * Classe per la crazione del menu di sign up del gioco
 */

public class SignupScreen extends ChangeListener implements Screen
{
    private Stage stage;
    private Label warningLabel;
    private Button signupButton;

    public SignupScreen(final AdventureGame game)
    {
        stage = new Stage();
        Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);

        Table table = new Table();
        table.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
        table.setSize(stageSize.x, stageSize.y);
        table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/signup/table.png"))));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.RED;
        warningLabel = new Label("", labelStyle);
        warningLabel.setText("Username non disponibile");

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture("menu/field.png")));
        textFieldStyle.fontColor = Color.LIGHT_GRAY;
        textFieldStyle.font = new BitmapFont();

        TextField usernameField = new TextField("", textFieldStyle);
        usernameField.setMessageText("Inserisci un Username");
        usernameField.setAlignment(1);

        TextField passwordField = new TextField("", textFieldStyle);
        passwordField.setMessageText("Inserire la Password");
        passwordField.setAlignment(1);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);

        Texture signupTexture = new Texture("menu/signup/signup.png");

        signupButton = new Button(new TextureRegionDrawable(new TextureRegion(signupTexture)));
        signupButton.setName("signup");
        signupButton.addListener(this);

        table.add(warningLabel).padBottom(10);
        table.row();
        table.add(usernameField).size(300,50).padBottom(20);
        table.row();
        table.add(passwordField).size(300,50).padBottom(25);
        table.row();
        table.add(signupButton).size(1298 / 6,952 / 10).padBottom(15);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void changed(ChangeEvent event, Actor actor)
    {
        if(actor.getName().equals(signupButton.getName()))
            warningLabel.setText("signup");
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
        stage.getBatch().draw(new Texture("menu/background.png"), 0, 0);
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
    }
}
