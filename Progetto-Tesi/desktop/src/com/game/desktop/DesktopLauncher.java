package com.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.Game;
import com.prove.TiledTest;

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
		LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
		
		configuration.title = "Adventure Game";
		configuration.width = 1280;
		configuration.height = 720;
		
		new LwjglApplication(new TiledTest(), configuration);
	}
}
