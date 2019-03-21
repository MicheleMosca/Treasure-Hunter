package graphics;

import java.awt.Image;

public class Assets
{
	public static Image playerIdleRight, playerRunRight, playerIdleLeft, playerRunLeft, terrain;
	public static Image background;

	public static void init()
	{
		SpriteSheet jungleSheet = new SpriteSheet(SpriteLoader.loadSpriteBuffered("/textures/jungleSheet.png"));	//Prelevo il foglio di elementi della jungla
		
		terrain = jungleSheet.crop(0, 192, 160, 32);	//Ritaglio il terreno dal foglio di sprite
		playerIdleRight = SpriteLoader.loadSprite("/textures/idle_right.gif");	//Prelevo la sprite di idle del mio personaggio
		playerIdleLeft = SpriteLoader.loadSprite("/textures/idle_left.gif");
		playerRunRight = SpriteLoader.loadSprite("/textures/run_right.gif");
		playerRunLeft = SpriteLoader.loadSprite("/textures/run_left.gif");
		background = SpriteLoader.loadSprite("/textures/background.png");
	}
}
