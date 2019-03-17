package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SpriteLoader
{
	public static Image loadSprite(String path)
	{
		try
		{
			return new ImageIcon(SpriteLoader.class.getResource(path)).getImage();	//Caricamento della texture dalla cartella di risorse
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	public static BufferedImage loadSpriteBuffered(String path)
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
