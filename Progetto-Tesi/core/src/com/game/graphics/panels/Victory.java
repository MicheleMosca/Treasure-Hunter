package com.game.graphics.panels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.AdventureGame;
import com.game.User;
import com.game.screens.LevelScreen;
import com.game.screens.PlayScreen;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.util.List;

/**
 * Menu di Vittoria, appare sopra il gioco messo in pausa
 */

public class Victory extends ChangeListener
{
    public static Stage stage;
    private static boolean visible;
    private AdventureGame game;
    private PlayScreen playScreen;
    private Label coinLabel;
    private Label timeLabel;

    private User userData;

    public Victory(AdventureGame game, PlayScreen playScreen, User userData)
    {
        super();

        visible = false;
        this.game = game;
        this.playScreen = playScreen;
        this.userData = userData;

        drawUI();
    }

    /**
     * Metodo che serve al costruttore per disegnare la user interface
     */
    private void drawUI()
    {
        // Creo uno stage e le dimensioni che avrà sopra il gioco in pausa
        stage = new Stage();
        Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);

        // Creo un table per contenere tutti gli oggetti del menu
        Table table = new Table();
        table.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
        table.setSize(stageSize.x, stageSize.y);
        table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/victory/table.png"))));

        // Creo un table per contenere i bottoni dei menu (mi serve per posizionarli in orizzontale)
        Table tableButtons = new Table();

        // Texture e bottone di PLAY AGAIN
        Texture playagainTexture = new Texture("menu/gameover/playagain.png");
        com.badlogic.gdx.scenes.scene2d.ui.Button playagainButton = new com.badlogic.gdx.scenes.scene2d.ui.Button(new TextureRegionDrawable(new TextureRegion((playagainTexture))));
        playagainButton.setName("playagain");
        playagainButton.addListener(this);

        // Texture e bottone di EXIT
        Texture exitTexture = new Texture("menu/main/exit.png");
        com.badlogic.gdx.scenes.scene2d.ui.Button exitButton = new com.badlogic.gdx.scenes.scene2d.ui.Button(new TextureRegionDrawable(new TextureRegion((exitTexture))));
        exitButton.setName("exit");
        exitButton.addListener(this);

        // Aggiungo i bottoni al tableButtons
        tableButtons.add(playagainButton).size(1298 / 6,952 / 9).padRight(30);
        tableButtons.add(exitButton).size(1298 / 6,952 / 9);

        // Creo un table per contenere le label della score
        Table tableScore = new Table();

        // Definisco lo style che devono avere le label
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.defaultFont;
        labelStyle.fontColor = Color.WHITE;

        // Label per i coin
        Label coinTextLabel = new Label("Coins: ", labelStyle);
        coinLabel = new Label("0", labelStyle);

        // Label per il timer
        Label timeTextLabel = new Label("Time: ", labelStyle);
        timeLabel = new Label("0:0", labelStyle);

        // Aggiungo le label al table delle label
        tableScore.add(coinTextLabel).padRight(20);
        tableScore.add(coinLabel).padRight(80);
        tableScore.add(timeTextLabel).padRight(20);
        tableScore.add(timeLabel);

        // Aggiungo i due table al table principale dello stage
        table.add(tableScore).padBottom(55);
        table.row();
        table.add(tableButtons);

        // Aggiungo il table principale allo stage
        stage.addActor(table);
    }

    /**
     * Metodo per settare in modo statico la visibilità del menu di vittoria
     * @param state
     */
    public static void setVisible(boolean state)
    {
        visible = state;

        if (visible)
            Gdx.input.setInputProcessor(stage);
    }

    /**
     * Metodo per spedire al server i dati del record appena ottenuto
     * @param scoreCoins
     * @param scoreTime
     */
    public void sendRecord(int scoreCoins, List<Integer> scoreTime)
    {
        coinLabel.setText(scoreCoins);
        timeLabel.setText(scoreTime.get(0) + ":" + scoreTime.get(1));

        try
        {
            String reply = new ClientResource("http://" + AdventureGame.serverIP +
                    ":4444/updateRecord?username=" + userData.getUsername() +
                    "&livello=" + userData.getLevelSelected() + "&coins=" + scoreCoins +
                    "&time=" + scoreTime.get(0) + ":" + scoreTime.get(1) + "").get().getText();

            if (!Boolean.valueOf(reply))
                System.out.println("Errore: Update fallito!");  //TEMPORANEO
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        userData.addNewLevel(userData.getLevelSelected());
    }

    /**
     * Metodo per controllare la visibilità del menu di vittoria
     * @return
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Metodo per ricevere gli eventi dai bottoni
     * @param event
     * @param actor
     */
    @Override
    public void changed(ChangeEvent event, Actor actor)
    {
        stage.dispose();
        playScreen.dispose();

        if (actor.getName().equals("playagain"))
            game.setScreen(new PlayScreen(game, userData));

        else if (actor.getName().equals("exit"))
            game.setScreen(new LevelScreen(game, userData));
    }
}
