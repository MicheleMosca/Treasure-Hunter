package graphics;

import java.awt.Image;

public class Assets
{
	public static Image playerIdle, playerRun, terrain;

	public static void init()
	{
		SpriteSheet jungleSheet = new SpriteSheet(SpriteLoader.loadSpriteBuffered("/textures/jungleSheet.png"));	//Prelevo il foglio di elementi della jungla
		
		terrain = jungleSheet.crop(0, 192, 160, 32);	//Ritaglio il terreno dal foglio di sprite
		playerIdle = SpriteLoader.loadSprite("/textures/idle.gif");	//Prelevo la sprite di idle del mio personaggio
		playerRun = SpriteLoader.loadSprite("/textures/run.gif");
	}
}
