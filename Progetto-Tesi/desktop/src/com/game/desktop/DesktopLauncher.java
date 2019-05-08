package com.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.Game;
import com.game.TiledTest;

/**
 * 
 * Classe che serve da laucher per il gioco da ambiente Desktop,
 * contiene tutte le impostazioni del frame che verrà lanciato
 *
 */

public class DesktopLauncher 
{
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 1280;
		config.height = 720;
		
		new LwjglApplication(new Game(), config);
	}
}
