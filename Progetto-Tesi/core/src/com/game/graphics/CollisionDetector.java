package com.game.graphics;

import com.badlogic.gdx.physics.box2d.*;
import com.game.graphics.entities.*;
import com.game.interfaces.Enemy;
import com.game.screens.PlayScreen;

/**
 * Classe che si occupa di gestire le collisioni tra gli oggetti in gioco
 */

public class CollisionDetector implements ContactListener
{
    private PlayScreen game;

    public CollisionDetector(PlayScreen game)
    {
        this.game = game;
    }

    /**
     * Inizio del contatto tra due figure
     * @param contact contiene le informazioni sul contatto avvenuto tra le due figure
     */
    @Override
    public void beginContact(Contact contact)
    {
        Object receiver = contact.getFixtureA().getBody().getUserData();
        Object sender = contact.getFixtureB().getBody().getUserData();

        if (receiver instanceof Enemy && sender instanceof Player)
            ((Enemy) receiver).hit((Player) sender);

        else if (receiver instanceof Coin && sender instanceof Player)
        {
            game.addCoin();
            game.removeBodyFromWorld((Coin) receiver);
        }

        else if (receiver instanceof Chest && sender instanceof Player)
            ((Chest) receiver).endLevel();

        else if (receiver instanceof TutorialObject && sender instanceof Player)
            if (!((TutorialObject) receiver).isVisualized())
                ((TutorialObject) receiver).showTutorial();
    }

    @Override
    public void endContact(Contact contact)
    {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {

    }
}
