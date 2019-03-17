package graphics;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display
{
	private int height;
	private int width;
	private String title;
	
	private JFrame frame;
	private Canvas canvas;
	
	public Display(int width, int height, String title)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		
		createDisplay();
	}
	
	private void createDisplay()
	{
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);		//Imposto la posizione della finestra al centro dello schermo
		frame.setVisible(true);		//Rendo visibile la finestra
		
		canvas = new Canvas();
		Dimension dim = new Dimension(width, height);
		canvas.setMinimumSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setPreferredSize(dim);
		
		frame.add(canvas);
		frame.pack();		//Includo il canvas all'interno della finestra
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public Canvas getCanvas()
	{
		return canvas;
	}
}
