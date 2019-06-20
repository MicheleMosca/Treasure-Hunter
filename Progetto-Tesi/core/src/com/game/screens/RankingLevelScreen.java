package com.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.AdventureGame;
import com.game.User;

/**
 * Classe per la creazione del menu di selezione del livello per mostrare tale classifica
 */

public class RankingLevelScreen extends LevelScreen
{
    RankingLevelScreen(AdventureGame game, User userData)
    {
        super(game, userData, false);
    }

    /**
     * Metodo per ricevere gli eventi di tipo ChangeEvent usati dai pulsanti
     * @param event ChangeEvent
     * @param actor Actor che ha generato l'evento
     */
    @Override
    public void changed(ChangeEvent event, Actor actor)
    {
        dispose();

        if (actor.getName().equals("level1"))
            game.setScreen(new RankingScreen(game, userData, 1));
        else if (actor.getName().equals("level2"))
            game.setScreen(new RankingScreen(game, userData, 2));
        else if (actor.getName().equals("prew"))
            game.setScreen(new MainMenuScreen(game, userData));
    }
}
