package com.game.interfaces;

import com.badlogic.gdx.math.Vector2;

/**
 * 
 * Interfaccia che server per implementare i metodi di movimento per un oggetto
 *
 */

public interface Movable
{
	// metodo che restituisce la posizione in cordinate
	public Vector2 getPosition();

	// metodo che restituisce la velocità del corpo
	public float getVelocity();

	// metodi per muovere il corpo nelle quattro principali direzioni
	public void moveRight();
	public void moveLeft();
	public void moveUp();
	public void moveDown();
}
