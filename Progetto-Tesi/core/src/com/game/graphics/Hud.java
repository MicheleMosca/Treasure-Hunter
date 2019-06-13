package com.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud extends Stage
{
    public Hud()
    {
        super();

        Table table = new Table();
        table.top().left();
        table.setFillParent(true);

        Label time = new Label("Timer: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(time).pad(50);

        addActor(table);
    }
}
