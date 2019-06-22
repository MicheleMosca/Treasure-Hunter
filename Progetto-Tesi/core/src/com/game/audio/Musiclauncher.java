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
	
	public boolean isMute()
	{
		return mute;
	}

	public void setMute(boolean mute)
	{
		this.mute = mute;
		
		if (mute)
			stopMusic();
		else
			playMusic();
	}

	public void playMusic()
	{
		if (mute)
			return;
		
		if (music.get(currentTrack).isPlaying() && currentTrack == 0)
			return;
		
		for ( Music musicObject : music)
		{
			//if(music.get(currentTrack) != musicObject && musicObject.isPlaying())
				musicObject.stop();
		}
		
		music.get(currentTrack).play();
		
	}
	
	public void stopMusic()
	{
		if (music.get(currentTrack).isPlaying())
			music.get(currentTrack).stop();
	}
	
	public void setCurrentTrack(int trackIndex)
	{
		currentTrack= trackIndex;
	}
}
