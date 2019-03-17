package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import graphics.Display;

public class Game implements Runnable
{
	private int height;
	private int width;
	private String title;

	private Display window;
	private Thread thread;
	private boolean isRunning = false;

	private BufferStrategy bs;
	private Graphics g;

	public Game(int width, int height, String title)
	{
		this.height = height;
		this.width = width;
		this.title = title;
	}

	public void run()
	{
		init();

		while(isRunning)
		{
			tick();
			render();
		}
		
		stop();
	}

	private void init()
	{
		window = new Display(width, height, title);		//Creo una finestra per il gioco
	}

	private void tick()
	{

	}

	private void render()
	{
		bs = window.getCanvas().getBufferStrategy();	//Estrazione dei livelli di buffer definiti
		if (bs == null)
		{
			window.getCanvas().createBufferStrategy(3);	//Imposto tre livelli di buffer così da evitare il flickering
			return;
		}
		g = bs.getDrawGraphics();	//Prelevo il buffer per disegnare
		
		//Puliamo lo schermo
		
		g.clearRect(0, 0, width, height);	//Posizione (0,0) e grandezza (width, height)
		
		//Inizio del rendering
		
		g.fillRect(0, 0, width, height);
		g.setColor(Color.blue);
		g.fillRect(50, 50, width/4, height/4);
		g.setColor(Color.white);
		g.fillOval(100, 100, width/4, height/4);
		g.setColor(Color.yellow);
		g.fillRect(200, 200, width/4, height/4);
		
		//Fine del rendering
		bs.show();	//Mostro il buffer disegnato
		g.dispose();	//Elimino il buffer da disegno
		
	}

	public synchronized void start()
	{
		if (isRunning)
			return;

		isRunning = true;
		thread = new Thread(this);
		thread.start();		//Inizio del processo in parallelo
	}

	public synchronized void stop()
	{
		if (!isRunning)
			return;

		isRunning = false;
		try
		{
			thread.join();		//Attesa della morte del thread
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
