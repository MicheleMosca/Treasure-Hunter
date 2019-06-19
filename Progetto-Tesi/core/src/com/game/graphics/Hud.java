package com.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.AdventureGame;
import sun.font.TrueTypeFont;

/**
 * 
 * Classe per HUD del gioco, verr� avviato insieme al PlayScreen
 *
 */

public class Hud extends Stage
{
    private AdventureGame game;

	private Label countdownlabel;
	private Label scorevaluelabel;
	
    public Hud(AdventureGame game)
    {
        super();

        this.game = game;

        drawUI();
    }

    private void drawUI()
    {
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.defaultFont;
        labelStyle.fontColor = Color.WHITE;

        Texture texture = new Texture("sprites/Coin/Coin.png");
        Label timelabel = new Label("TIMER: ", labelStyle);
        countdownlabel= new Label("0:0", labelStyle);
        Image coin=new Image(texture);
        scorevaluelabel = new Label("0", labelStyle);

        table.add(timelabel).expandX().padTop(10);
        table.add(coin).expandX().padTop(10);
        table.row();
        table.add(countdownlabel);
        table.add(scorevaluelabel);

        addActor(table);
    }
    
    public void setcountdownlabel(String countdown)
    {
    	countdownlabel.setText(countdown);
    }
    
    public void setscorevaluelabel(String score)
    {
    	scorevaluelabel.setText(score);
    }
  
}