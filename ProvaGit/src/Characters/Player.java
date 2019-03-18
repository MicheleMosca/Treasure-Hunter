package Characters;

import java.awt.Image;

import graphics.Assets;

public class Player
{
	private Image[] sprite;
	private int spriteIndex;
	private int xPosition;
	private int yPosition;

	public Player(int xPosition, int yPosition)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		spriteIndex = 0;

		sprite = new Image[2];
		sprite[0] = Assets.playerIdleRight;
		sprite[1] = Assets.playerRunRight;
	}

	public void idle()
	{
		setSpriteIndex(0);
	}

	public void run()
	{
		setSpriteIndex(1);
		setxPosition(getxPosition() + 1);
	}
	
	public Image render()
	{
		return sprite[spriteIndex];
	}

	public int getSpriteIndex()
	{
		return spriteIndex;
	}

	public void setSpriteIndex(int spriteIndex)
	{
		this.spriteIndex = spriteIndex;
	}

	public int getxPosition()
	{
		return xPosition;
	}

	public void setxPosition(int xPosition)
	{
		this.xPosition = xPosition;
	}

	public int getyPosition()
	{
		return yPosition;
	}

	public void setyPosition(int yPosition)
	{
		this.yPosition = yPosition;
	}
}
