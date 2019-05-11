package com.game.interfaces;

/**
 * 
 * Interfaccia che permette ad un entit� di seguire un altro tipo di entit�
 *
 * @param <T>
 */

public interface Follower<T>
{
	public void followThisTarget (T target);
}
