package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import Characters.Player;
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

	private Player player;
	private int numTerrain;

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
		int timer = 0;
		int ticks = 0;

		while(isRunning)
		{
			nowTime = System.nanoTime();
			deltaTime += (nowTime - lastTime) / timePerTick;	//Differenza di tempo
			timer += nowTime - lastTime;
			lastTime = nowTime;

			if (deltaTime >= 1)
			{
				tick();
				render();
				
				ticks++;
				deltaTime--;
			}
			
			if (timer >= 1000000000)	//Se sono arrivato a un secondo mostro quanti tick ho effettuato
			{
				System.out.println("" + ticks + " fps");
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	private void init()
	{
		window = new Display(width, height, title);		//Creo una finestra per il gioco
		Assets.init();
		player = new Player(0, 0);
		numTerrain = 0;
	}

	private void tick()		//Fase di aggiornamento delle variabili
	{
		player.run();
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
		
		g.setColor(java.awt.Color.black);
		g.fillRect(0, 0, window.getWidth(), window.getWidth());
		g.drawImage(player.render(), player.getxPosition()-1, window.getHeight() - Assets.terrain.getHeight(null) * scale - player.render().getHeight(null) * scale + (2 * scale), player.render().getWidth(null) * scale, player.render().getHeight(null) * scale, null);
		while ( numTerrain <= ( window.getWidth()) / Assets.terrain.getWidth(null) )
		{
			g.drawImage(Assets.terrain, numTerrain * Assets.terrain.getWidth(null), window.getHeight() - Assets.terrain.getHeight(null) * scale, Assets.terrain.getWidth(null) * scale, Assets.terrain.getHeight(null) * scale, null);
			numTerrain++;
		}
		
		numTerrain = 0;

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
