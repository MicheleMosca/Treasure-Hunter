package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class SpriteSheet
{
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet)
	{
		this.sheet = sheet;
	}
	
	public Image crop(int x, int y, int width, int height)
	{
		return (Image) sheet.getSubimage(x, y, width, height);		//Ritaglio il foglio di sprite nella sprite desiderata
	}
}
