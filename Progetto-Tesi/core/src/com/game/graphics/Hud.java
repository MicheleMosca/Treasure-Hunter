package com.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * 
 * Classe per HUD del gioco, verrà avviato insieme al PlayScreen
 *
 */

public class Hud extends Stage
{
	private Label countdownlabel;
	private Label scorevaluelabel;
	
    public Hud()
    {
        super();

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Texture texture = new Texture("sprites/Coin/Coin.png");
        Label timelabel = new Label("TIMER: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countdownlabel= new Label("0:0",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Image coin=new Image(texture);
        scorevaluelabel = new Label("0",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

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