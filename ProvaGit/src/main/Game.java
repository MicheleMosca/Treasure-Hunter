package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import graphics.Assets;
import graphics.Display;

public class Game implements Runnable
{
	private int height;
	private int width;
	private String title;
	private final int scale = 2;

	private Display window;
	private Thread thread;
	private boolean isRunning = false;

	private BufferStrategy bs;
	private Graphics g;

	private int xPlayerPos;

	public Game(int width, int height, String title)
	{
		this.height = height;
		this.width = width;
		this.title = title;
	}

	public void run()
	{
		init();

		int fps = 60;	//Frame per Second
		double timePerTick = 1000000000 / fps;		//Un bilione di nanosecondi (un secondo) / frame per second
		double deltaTime = 0;
		long nowTime;
		long lastTime = System.nanoTime();	//Tempo attuale in clock

		while(isRunning)
		{
			nowTime = System.nanoTime();
			deltaTime += (nowTime - lastTime) / timePerTick;	//Differenza di tempo
			lastTime = nowTime;

			if (deltaTime >= 1)
			{
				tick();
				render();
				
				deltaTime--;
			}
		}

		stop();
	}

	private void init()
	{
		window = new Display(width, height, title);		//Creo una finestra per il gioco
		Assets.init();
		xPlayerPos = 0;
	}

	private void tick()		//Fase di aggiornamento delle variabili
	{
		xPlayerPos++;
	}

	private void render()	//Fase di disegno degli oggetti
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

		g.drawImage(Assets.playerIdle, xPlayerPos-1, window.getHeight() - Assets.terrain.getHeight() * scale - Assets.playerIdle.getHeight() * scale + (2 * scale), Assets.playerIdle.getWidth() * scale, Assets.playerIdle.getHeight() * scale, null);
		g.drawImage(Assets.terrain, 0, window.getHeight() - Assets.terrain.getHeight() * scale, Assets.terrain.getWidth() * scale, Assets.terrain.getHeight() * scale, null);

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
			System.exit(1);
		}
	}
}
