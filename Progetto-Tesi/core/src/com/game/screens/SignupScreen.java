package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import java.io.IOException;

/**
 * Classe per la crazione del menu di sign up del gioco
 */

public class SignupScreen extends ChangeListener implements Screen
{
    private AdventureGame game;

    private Stage stage;
    private Texture background;

    private Label warningLabel;
    private TextField usernameField;
    private TextField passwordField;

    public SignupScreen(final AdventureGame game)
    {
        this.game = game;

        stage = new Stage();
        Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);

        background = new Texture("menu/background.png");

        Table table = new Table();
        table.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
        table.setSize(stageSize.x, stageSize.y);
        table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/signup/table.png"))));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.RED;
        warningLabel = new Label("", labelStyle);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture("menu/field.png")));
        textFieldStyle.fontColor = Color.LIGHT_GRAY;
        textFieldStyle.font = new BitmapFont();

        usernameField = new TextField("", textFieldStyle);
        usernameField.setMessageText("Inserisci un Username");
        usernameField.setAlignment(1);

        passwordField = new TextField("", textFieldStyle);
        passwordField.setMessageText("Inserire la Password");
        passwordField.setAlignment(1);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);

        Texture signupTexture = new Texture("menu/signup/signup.png");

        Button signupButton = new Button(new TextureRegionDrawable(new TextureRegion(signupTexture)));
        signupButton.setName("signup");
        signupButton.addListener(this);

        Texture closeTexture = new Texture("menu/close.png");

        Button exitButton = new Button(new TextureRegionDrawable(new TextureRegion((closeTexture))));
        exitButton.setName("exit");
        exitButton.addListener(this);
        exitButton.setSize(214 / 5, 215 / 5);
        exitButton.setPosition(table.getX() + table.getWidth() - (exitButton.getWidth()*2),
                table.getY() + table.getHeight() - exitButton.getHeight() - 10);

        Texture backTexture = new Texture("menu/prew.png");

        Button backButton = new Button(new TextureRegionDrawable(new TextureRegion((backTexture))));
        backButton.setName("back");
        backButton.addListener(this);
        backButton.setSize(214 / 5, 215 / 5);
        backButton.setPosition(table.getX() + backButton.getWidth(),
                table.getY() + backButton.getHeight());

        table.add(warningLabel).padBottom(10);
        table.row();
        table.add(usernameField).size(300,50).padBottom(20);
        table.row();
        table.add(passwordField).size(300,50).padBottom(25);
        table.row();
        table.add(signupButton).size(1298 / 6,952 / 10).padBottom(15);

        stage.addActor(table);
        stage.addActor(exitButton);
        stage.addActor(backButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void changed(ChangeEvent event, Actor actor)
    {
        if(actor.getName().equals("signup"))
            sendToServer();
        else if (actor.getName().equals("exit"))
            Gdx.app.exit();
        else if (actor.getName().equals("back"))
        {
            dispose();
            game.setScreen(new LoginScreen(game));
        }
    }

    private void sendToServer()
    {
        if(usernameField.getText().equals("") || passwordField.getText().equals(""))
        {
            warningLabel.setText("Username e Password non validi!");
            return;
        }

        String reply = null;
        try
        {
            reply = new ClientResource("http://" + AdventureGame.serverIP + ":4444/checkUser?username=" + usernameField.getText() +
                    "").get().getText();

            if (reply.equals("true"))
            {
                // Username disponibile
                reply = new ClientResource("http://" + AdventureGame.serverIP + ":4444/addUser?username=" + usernameField.getText() +
                        "&password=" + passwordField.getText() + "").get().getText();

                if (reply.equals("true"))
                {
                    dispose();
                    game.setScreen(new LoginScreen(game));
                }
                else
                    warningLabel.setText("Errore inserimento del nuovo utente");
            }
            else
                warningLabel.setText("Username non disponibile");
        } catch (ResourceException e)
        {
            warningLabel.setText("Server non disponibile, riprovare piu' tardi");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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

        // Se si preme invio nella sezionione di SIGNUP allora automaticamente sarà rilevato il tasto di signup
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            sendToServer();
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
