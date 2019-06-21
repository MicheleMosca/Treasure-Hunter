package com.game.graphics.entities.enemies;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.graphics.entities.Entity;
import com.game.graphics.entities.Player;
import com.game.interfaces.Enemy;

/**
 * Classe che rappresenta l'oggetto spike in gioco
 */

public class Spike extends Entity implements Enemy
{
    private int damage;

    public Spike(World world, MapObject mapObject, BodyDef.BodyType bodyType)
    {
        super(world, mapObject, bodyType);
        damage = 1;
        body.getFixtureList().get(0).setSensor(true);   // tolgo tutte le collisioni standard
    }

    /**
     * Funzione per colpire un determinato target
     * @param target Oggetto da colpire
     */
    @Override
    public void hit(Player target)
    {
        target.takeHit(damage);
    }
}
