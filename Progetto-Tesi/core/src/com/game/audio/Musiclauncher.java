package com.game.audio;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


/**
 * Classe che rappresenta l'audio del gioco
 */

public class Musiclauncher 
{
	private ArrayList<Music> music;
	private int currentTrack;
	private boolean mute;

	public Musiclauncher()
	{
		mute = false;
		music = new ArrayList<Music>();
		currentTrack=0;
		music.add(Gdx.audio.newMusic(Gdx.files.internal("music/main.mp3")));
		music.add(Gdx.audio.newMusic(Gdx.files.internal("music/level1.mp3")));
		music.add(Gdx.audio.newMusic(Gdx.files.internal("music/level2.mp3")));
		
		for ( Music musicObject : music)
		{
			musicObject.setLooping(true);
			musicObject.setVolume(0.1f);
		}
	}

	/**
	 * Metodo che mi indica se l'audio e' mutato
	 * @return True se l'audio e' mutato, False altrimenti
	 */
	public boolean isMute()
	{
		return mute;
	}

	/**
	 * Metodo per mette l'audio in mute
	 * @param mute True se vogliamo settare l'audio mute, False altrimenti
	 */
	public void setMute(boolean mute)
	{
		this.mute = mute;
		
		if (mute)
			stopMusic();
		else
			playMusic();
	}

	/**
	 * Metodo per iniziare la canzone corrente
	 */
	public void playMusic()
	{
		if (mute)
			return;
		
		if (music.get(currentTrack).isPlaying() && currentTrack == 0)
			return;
		
		for ( Music musicObject : music)
		{
			musicObject.stop();
		}
		
		music.get(currentTrack).play();
	}

	/**
	 * Metodo per fermare la canzone corrente
	 */
	public void stopMusic()
	{
		if (music.get(currentTrack).isPlaying())
			music.get(currentTrack).stop();
	}

	/**
	 * Metodo per selezionare qual' e' la traccia corrente
	 * @param trackIndex Indice della canzone corrente
	 */
	public void setCurrentTrack(int trackIndex)
	{
		currentTrack= trackIndex;
	}
}
