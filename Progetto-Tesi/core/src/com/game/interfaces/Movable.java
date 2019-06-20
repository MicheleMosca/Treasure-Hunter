package com.game.interfaces;

import com.badlogic.gdx.math.Vector2;

/**
 * Interfaccia che server per implementare i metodi di movimento per un oggetto
 */

public interface Movable
{
	// metodo che restituisce la posizione in cordinate
	Vector2 getPosition();

	// metodo che restituisce la velocità del corpo
	Vector2 getVelocity();

	// metodi per muovere il corpo nelle quattro principali direzioni
	void moveRight();
	void moveLeft();
	void moveUp();
	void moveDown();
}
