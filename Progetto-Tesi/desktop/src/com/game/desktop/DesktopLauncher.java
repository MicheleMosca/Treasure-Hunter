package com.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.AdventureGame;

/**
 * 
 * Classe che serve da laucher per il gioco da ambiente Desktop,
 * contiene tutte le impostazioni del frame che verr� lanciato
 *
 */

public class DesktopLauncher 
{
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
		
		configuration.title = "Adventure Game";
		configuration.width = AdventureGame.worldWidth;//LwjglApplicationConfiguration.getDesktopDisplayMode().width;
		configuration.height = AdventureGame.worldHeight;//LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		configuration.foregroundFPS = 60;
		configuration.fullscreen = false;
		
		new LwjglApplication(new AdventureGame(), configuration);
	}
}
