package com.game.interfaces;

/**
 * Interfaccia che permette ad un entit� di seguire un altro tipo di entit�
 * @param <T> Tipoligia di entita' che si vuole seguire
 */

public interface Follower<T>
{
	void followThisTarget (T target);
}
