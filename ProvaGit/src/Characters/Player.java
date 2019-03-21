package Characters;

import java.awt.Image;

import graphics.Assets;

public class Player
{
	private Image[] sprite;
	private int spriteIndex;
	private int xPosition;
	private int yPosition;
	private boolean isRight;

	public Player(int xPosition, int yPosition)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		isRight = true;

		sprite = new Image[4];
		sprite[0] = Assets.playerIdleRight;
		sprite[1] = Assets.playerIdleLeft;
		sprite[2] = Assets.playerRunRight;
		sprite[3] = Assets.playerRunLeft;
	}

	public void idle()
	{
		if (isRight)
			setSpriteIndex(0);
		else
			setSpriteIndex(1);
	}

	public void runRight()
	{
		setSpriteIndex(2);
		setxPosition(getxPosition() + 1);
		isRight = true;
	}
	
	public void runLeft()
	{
		setSpriteIndex(3);
		setxPosition(getxPosition() - 1);
		isRight = false;
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
