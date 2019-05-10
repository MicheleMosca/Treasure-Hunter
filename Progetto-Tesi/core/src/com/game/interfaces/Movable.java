package com.game.interfaces;

import com.badlogic.gdx.math.Vector3;

/**
 * 
 * Interfaccia che server per implementare i metodi di movimento per un oggetto
 *
 */

public interface Movable
{
	public Vector3 position();
	
	public void moveRight();
	public void moveLeft();
	public void moveUp();
	public void moveDown();
}
