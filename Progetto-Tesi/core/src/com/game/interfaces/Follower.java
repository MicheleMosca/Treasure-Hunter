package com.game.interfaces;

/**
 * Interfaccia che permette ad un entità di seguire un altro tipo di entità
 * @param <T> Tipoligia di entita' che si vuole seguire
 */

public interface Follower<T>
{
	void followThisTarget (T target);
}
