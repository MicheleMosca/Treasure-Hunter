package com.game.graphics;

import com.badlogic.gdx.physics.box2d.*;
import com.game.graphics.entities.Entity;
import com.game.graphics.entities.Player;
import com.game.interfaces.Enemy;

/**
 * Classe che si occupa di gestire le collisioni tra gli oggetti in gioco
 */

public class CollisionDetector implements ContactListener
{
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
