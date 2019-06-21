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

/**
 * Classe per la creazione di un menu di game over
 */

public class GameOver extends ChangeListener
{
    public static Stage stage;
    private static boolean visible;
    private AdventureGame game;
    private PlayScreen playScreen;

    private User userData;

    public GameOver(AdventureGame game, PlayScreen playScreen, User userData)
    {
        super();

        visible = false;
        this.game = game;
        this.playScreen = playScreen;
        this.userData = userData;

        drawUI();
    }

    /**
     * Metodo chiamato dal costruttore per disegnare L'interfaccia utente
     */
    private void drawUI()
    {
        // Creo uno stage che mi contenga gli oggetti
        stage = new Stage();
        Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);

        // Creo un table per organizzare gli oggetti
        Table table = new Table();
        table.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
        table.setSize(stageSize.x, stageSize.y);
        table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/gameover/table.png"))));

        // Creo il pulsante per ricominciare la partita
        Texture playagainTexture = new Texture("menu/gameover/playagain.png");
        com.badlogic.gdx.scenes.scene2d.ui.Button playagainButton = new com.badlogic.gdx.scenes.scene2d.ui.Button(new TextureRegionDrawable(new TextureRegion((playagainTexture))));
        playagainButton.setName("playagain");
        playagainButton.addListener(this);

        // Creo il pulsante per tornare al menu di selezione del livello
        Texture exitTexture = new Texture("menu/back.png");
        com.badlogic.gdx.scenes.scene2d.ui.Button exitButton = new com.badlogic.gdx.scenes.scene2d.ui.Button(new TextureRegionDrawable(new TextureRegion((exitTexture))));
        exitButton.setName("exit");
        exitButton.addListener(this);

        // Aggiungo i pulsanti al table
        table.bottom();
        table.add(playagainButton).size(1298 / 6,952 / 9).padBottom(20);
        table.row();
        table.add(exitButton).size(1298 / 6,952 / 9).padBottom(90);

        // Aggiungo il table allo stage
        stage.addActor(table);
    }

    /**
     * Metodo per impostare la visibilita' del menu di game over
     * @param state True per renderlo visibile, False altrimenti
     */
    public static void setVisible(boolean state)
    {
        visible = state;

        if (visible)
            Gdx.input.setInputProcessor(stage);
    }

    /**
     * Metodo per ottenere la visibilità del menu di game over
     * @return True se e' visibile, False altrimenti
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Metodo per ricevere gli eventi di tipo ChangeEvent usati dai pulsanti
     * @param event ChangeEvent
     * @param actor Actor che ha generato l'evento
     */
    @Override
    public void changed(ChangeEvent event, Actor actor)
    {
        stage.dispose();
        playScreen.dispose();

        if (actor.getName().equals("playagain"))
            game.setScreen(new PlayScreen(game, userData));

        else if (actor.getName().equals("exit"))
            game.setScreen(new LevelScreen(game, userData, true));
    }
}
