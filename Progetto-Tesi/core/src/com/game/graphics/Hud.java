package com.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud extends Stage
{
	Label countdownlabel;
	Label timelabel;
	Label scorelabel;
	Label scorevaluelabel;
	
    public Hud()
    {
        super();

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timelabel = new Label("TIMER: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countdownlabel= new Label("0:0",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scorelabel=new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scorevaluelabel = new Label("0",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        
 
        table.add(timelabel).expandX().padTop(10);
        table.add(scorelabel).expandX().padTop(10);
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