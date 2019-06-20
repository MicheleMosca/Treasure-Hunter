package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.AdventureGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen implements Screen, ActionListener
{
    private AdventureGame game;
    private Texture background;
    private Sprite logo;
    private float time;
    private float alphaValue;
    private boolean reverseTime;
    private boolean reverseLock;
    private Timer reverseTimer;

    public SplashScreen(AdventureGame game)
    {
        this.game = game;

        background = new Texture("menu/background.png");
        logo = new Sprite(new Texture("menu/logo.png"));
        logo.setAlpha(0f);
        logo.setScale(0.3f);
        logo.setPosition(((float) Gdx.graphics.getWidth() / 2) - logo.getWidth()/2,
                ((float) Gdx.graphics.getHeight() / 2) - logo.getHeight()/2);
        time = 0;
        alphaValue = 0;
        reverseTime = false;
        reverseLock = true;
    }

    @Override
    public void show()
    {

    }

    private void update(float delta)
    {
        time += delta;

        if (time >= 0.01)
        {
            time = 0;
            if (alphaValue < 0.9 && !reverseTime)
                alphaValue += 0.01;

            else if (alphaValue >= 0.9 && !reverseTime)
            {
                reverseTime = true;
                waitBeforeReverse();
            }

            logo.setAlpha(alphaValue);

            if (reverseTime && !reverseLock)
            {
                alphaValue -= 0.01;
                if (alphaValue <= 0)
                {
                    dispose();
                    game.setScreen(new LoginScreen(game));
                }
            }
        }
    }

    private void waitBeforeReverse()
    {
        reverseTimer = new Timer(1500, this);
        reverseTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == reverseTimer)
        {
            reverseTimer.stop();
            reverseLock = false;
        }
    }

    @Override
    public void render(float delta)
    {
        update(delta);

        // Pulisco il buffer dello schermo
        Gdx.gl.glClearColor(0, 0, 0, 1);	//Red, gree, blue, alpha(0 = trasparete, 1 = opaco)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(background, 0,0);
        logo.draw(game.batch);

        game.batch.end();
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
        background.dispose();
        logo.getTexture().dispose();
    }

}
