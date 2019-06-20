package com.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.AdventureGame;

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
		
		configuration.title = AdventureGame.gameTitle;
		configuration.width = AdventureGame.worldWidth;
		configuration.height = AdventureGame.worldHeight;
		configuration.foregroundFPS = 60;
		configuration.fullscreen = AdventureGame.fullScreenOnStart;
		
		new LwjglApplication(new AdventureGame(), configuration);
	}
}
