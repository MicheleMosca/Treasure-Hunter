package com.game.interfaces;

/**
 * 
 * Interfaccia che permette ad un entità di seguire un altro tipo di entità
 *
 * @param <T>
 */

public interface Follower<T>
{
	public void followThisTarget (T target);
}
