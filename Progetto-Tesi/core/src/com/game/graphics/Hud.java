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

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud extends Stage
{
	Label countdownlabel;
	Label timelabel;
    Image coin;
	Label scorevaluelabel;
    Texture texture;
	
    public Hud()
    {
        super();

        Table table = new Table();
        table.top().left().pad(50);
        table.setFillParent(true);

        texture=new Texture("sprites/Coin/Coin.png");
        timelabel = new Label("TIMER: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countdownlabel= new Label("0:0",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        coin=new Image(texture);
        scorevaluelabel = new Label("0",new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        
        table.add(timelabel).expandX().padTop(10);
        table.add(coin).expandX().padTop(10);
        table.row();
        table.add(countdownlabel);

        table.add(coin);

        table.add(scorevaluelabel);
        table.row().padTop(20).padLeft(20);
        table.add(timelabel);
        table.add(countdownlabel).padLeft(10);

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