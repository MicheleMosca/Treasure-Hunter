package graphics;

import java.awt.image.BufferedImage;

public class Assets
{
	public static BufferedImage playerIdle, terrain;

	public static void init()
	{
		SpriteSheet jungleSheet = new SpriteSheet(SpriteLoader.loadSprite("/textures/jungleSheet.png"));	//Prelevo il foglio di elementi della jungla
		
		terrain = jungleSheet.crop(0, 192, 160, 32);	//Ritaglio il terreno dal foglio di sprite
		playerIdle = SpriteLoader.loadSprite("/textures/idle.gif");	//Prelevo la sprite di idle del mio personaggio
	}
}
