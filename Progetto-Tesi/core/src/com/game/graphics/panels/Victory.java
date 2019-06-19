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
import javafx.scene.control.Tab;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.util.List;

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

        stage = new Stage();
        Vector2 stageSize = new Vector2(1298 / 2, 952 / 2);

        Table table = new Table();
        table.setPosition(((float) Gdx.graphics.getWidth() /2) - (stageSize.x /2), ((float) Gdx.graphics.getHeight() /2) - (stageSize.y /2));
        table.setSize(stageSize.x, stageSize.y);
        table.background(new TextureRegionDrawable(new TextureRegion(new Texture("menu/victory/table.png"))));

        Table tableButtons = new Table();

        Texture playagainTexture = new Texture("menu/gameover/playagain.png");

        com.badlogic.gdx.scenes.scene2d.ui.Button playagainButton = new com.badlogic.gdx.scenes.scene2d.ui.Button(new TextureRegionDrawable(new TextureRegion((playagainTexture))));
        playagainButton.setName("playagain");
        playagainButton.addListener(this);

        Texture exitTexture = new Texture("menu/main/exit.png");

        com.badlogic.gdx.scenes.scene2d.ui.Button exitButton = new com.badlogic.gdx.scenes.scene2d.ui.Button(new TextureRegionDrawable(new TextureRegion((exitTexture))));
        exitButton.setName("exit");
        exitButton.addListener(this);

        tableButtons.add(playagainButton).size(1298 / 6,952 / 9).padRight(30);
        tableButtons.add(exitButton).size(1298 / 6,952 / 9);

        Table tableScore = new Table();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = createFont();
        labelStyle.fontColor = Color.WHITE;

        Label coinTextLabel = new Label("Coins: ", labelStyle);
        coinLabel = new Label("0", labelStyle);

        Label timeTextLabel = new Label("Time: ", labelStyle);
        timeLabel = new Label("0:0", labelStyle);

        tableScore.add(coinTextLabel).padRight(20);
        tableScore.add(coinLabel).padRight(80);
        tableScore.add(timeTextLabel).padRight(20);
        tableScore.add(timeLabel);

        table.add(tableScore).padBottom(55);
        table.row();
        table.add(tableButtons);

        stage.addActor(table);
    }

    private BitmapFont createFont()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/SHOWG.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 25;
        fontParameter.color = Color.WHITE;
        BitmapFont font = generator.generateFont(fontParameter);
        generator.dispose();

        return font;
    }

    public static void setVisible(boolean state)
    {
        visible = state;

        if (visible)
            Gdx.input.setInputProcessor(stage);
    }

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

    public boolean isVisible()
    {
        return visible;
    }

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
