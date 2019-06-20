package com.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.AdventureGame;

/**
 * 
 * Classe per l'HUD del gioco, verrà avviato insieme al PlayScreen
 *
 */

public class Hud extends Stage
{
    private AdventureGame game;

	private Label timeValueLabel;
	private Label scoreValueLabel;
	
    public Hud(AdventureGame game)
    {
        super();

        this.game = game;
        drawUI();
    }

    /**
     * Metodo chiamato dal costruttore per disegnare L'interfaccia utente del login
     */
    private void drawUI()
    {
        // Creo un table per contenere tutti gli oggetti da mostrare
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        // Creo lo style che le label devono avere
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.defaultFont;
        labelStyle.fontColor = Color.WHITE;

        // Creo la texture per mostrare i coin ottenuti e la label per mostrare il tempo trascorso
        Texture texture = new Texture("sprites/Coin/Coin.png");
        Label timelabel = new Label("Timer", labelStyle);
        Image coin=new Image(texture);

        // Creo le label aggiornabili dove inserire le informazioni
        timeValueLabel= new Label("0:0", labelStyle);
        scoreValueLabel = new Label("0", labelStyle);

        // Aggiungo gli oggetti al table
        table.add(timelabel).expandX().padTop(10);
        table.add(coin).expandX().padTop(10);
        table.row();
        table.add(timeValueLabel);
        table.add(scoreValueLabel);

        // Inserisco il table all'interno dello stage(hud)
        addActor(table);
    }

    /**
     * Metodo per aggiornare il tempo trascorso
     * @param timeValue Tempo trascorso
     */
    public void setTimeValueLabel(String timeValue)
    {
    	timeValueLabel.setText(timeValue);
    }

    /**
     * Metodo per aggiornare la score
     * @param score numero di coin presi
     */
    public void setScoreValueLabel(String score)
    {
    	scoreValueLabel.setText(score);
    }
  
}