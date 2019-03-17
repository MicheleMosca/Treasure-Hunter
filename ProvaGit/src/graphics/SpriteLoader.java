package graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteLoader
{
	public static BufferedImage loadSprite(String path)
	{
		try
		{
			return ImageIO.read(SpriteLoader.class.getResource(path));	//Caricamento della texture dalla cartella di risorse
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
}
