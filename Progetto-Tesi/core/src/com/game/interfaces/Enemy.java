package com.game.interfaces;

import com.game.graphics.entities.Player;

/**
 * Interaccia che implementa il metodi per far sì che l'oggetto sia un enemy
 */

public interface Enemy
{
    /**
     * Funzione per colpire un determinato target
     * @param target Oggetto da colpire
     */
    public void hit(Player target);
}
