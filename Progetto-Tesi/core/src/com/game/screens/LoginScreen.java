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
import com.game.User;
import org.json.JSONObject;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import java.io.IOException;

/**
 * Classe per la crazione del menu di login del gioco
 */

public class LoginScreen extends ChangeListener implements Screen
{
    private AdventureGame game;

    private Stage stage;
    private Texture background;

    private Label warningLabel;
    private Button loginButton;
    private Button signupButton;
    private TextField usernameField;
    private TextField passwordField;

    public LoginScreen(final AdventureGame game)
    {
        this.game = game;

        stage = new Stage();
        Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);

        background = new Texture("menu/background.png");

        Table table = new Table();
        table.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
        table.setSize(stageSize.x, stageSize.y);
        table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/login/table.png"))));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.RED;
        warningLabel = new Label("", labelStyle);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture("menu/field.png")));
        textFieldStyle.fontColor = Color.LIGHT_GRAY;
        textFieldStyle.font = new BitmapFont();

        usernameField = new TextField("", textFieldStyle);
        usernameField.setMessageText("Username");
        usernameField.setAlignment(1);

        passwordField = new TextField("", textFieldStyle);
        passwordField.setMessageText("Password");
        passwordField.setAlignment(1);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);

        Texture loginTexture = new Texture("menu/login/login.png");

        loginButton = new Button(new TextureRegionDrawable(new TextureRegion(loginTexture)));
        loginButton.setName("login");
        loginButton.addListener(this);

        Texture signupTexture = new Texture("menu/signup/signup.png");

        signupButton = new Button(new TextureRegionDrawable(new TextureRegion(signupTexture)));
        signupButton.setName("signup");
        signupButton.addListener(this);

        Texture closeTexture = new Texture("menu/close.png");

        Button exitButton = new Button(new TextureRegionDrawable(new TextureRegion((closeTexture))));
        exitButton.setName("exit");
        exitButton.addListener(this);
        exitButton.setSize(214 / 5, 215 / 5);
        exitButton.setPosition(table.getX() + table.getWidth() - (exitButton.getWidth()*2),
                table.getY() + table.getHeight() - exitButton.getHeight() - 10);

        table.add(warningLabel).padBottom(10);
        table.row();
        table.add(usernameField).size(300,50).padBottom(20);
        table.row();
        table.add(passwordField).size(300,50).padBottom(25);
        table.row();
        table.add(loginButton).size(1298 / 8,952 / 14).padBottom(15);
        table.row();
        table.add(signupButton).size(1298 / 11,952 / 16);

        stage.addActor(table);
        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void changed(ChangeEvent event, Actor actor)
    {
        if (actor.getName().equals(loginButton.getName()))
        {
            if(usernameField.getText().equals("") || passwordField.getText().equals(""))
            {
                warningLabel.setText("Username e Password non validi!");
                return;
            }

            String reply = null;
            try
            {
                reply = new ClientResource("http://localhost:4444/checkUser?username=" + usernameField.getText() +
                        "&password=" + passwordField.getText() + "").get().getText();

                if (reply.equals("true"))
                {
                    // effettuare il check dei livelli dell'utente e poi creare l'oggetto utente
                    reply = new ClientResource("http://localhost:4444/getLastLevel?username=" + usernameField.getText() +
                            "").get().getText();

                    dispose();
                    game.setScreen(new MainMenuScreen(game, new User(usernameField.getText(), Integer.valueOf(reply))));
                }
                else
                    warningLabel.setText("Username e Password non validi!");
            }
            catch (ResourceException e)
            {
                warningLabel.setText("Server non disponibile, riprovare piu' tardi");
            }
            catch (IOException e)
            {
                warningLabel.setText("Server non disponibile, riprovare più tardi");
            }
        }
        else if(actor.getName().equals(signupButton.getName()))
        {
            dispose();
            game.setScreen(new SignupScreen(game));
        }
        else  if (actor.getName().equals("exit"))
            Gdx.app.exit();
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
    }
}
