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

/**
 * Classe per la creazione di uno SplashScreen (a fine animazione passera' alla schermata di login)
 */

public class SplashScreen implements Screen, ActionListener
{
    private AdventureGame game;
    private Sprite logo;
    private float time;
    private float alphaValue;

    private boolean reverseTime;    // se True inizia la dissolvenza
    private boolean reverseLock;    // se True il logo non inizia la dissolvenza
    private Timer reverseTimer;     // timer che mi stabilesce per quanto tempo il logo deve rimanere fermo

    public SplashScreen(AdventureGame game)
    {
        this.game = game;

        logo = new Sprite(new Texture("menu/logo2.png"));
        logo.setAlpha(0f);
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

    /**
     * Metodo per aggiornare gli oggetti in scena
     * @param delta tempo trascorso tra un frame ed un altro
     */
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

    /**
     * Metodo chiamato dalla funzione update quando bisogna iniziare il timer prima che il logo
     * entri in dissolvenza
     */
    private void waitBeforeReverse()
    {
        reverseTimer = new Timer(1500, this);
        reverseTimer.start();
    }

    /**
     * Metodo che mi riceve gli eventi di tipo ActionEvent, non appena il timer finisce inizio la dissolvenza
     * @param e ActionEvent
     */
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
        Gdx.gl.glClearColor(255, 255, 255, 1);	//Red, gree, blue, alpha(0 = trasparete, 1 = opaco)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        logo.draw(game.batch);  // disegno il logo

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
        logo.getTexture().dispose();
    }

}
