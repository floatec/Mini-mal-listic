package com.gamelab.mmi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicController {
	private ArrayList<Music> musicRed;
	private ArrayList<Music> musicGreen;
	private ArrayList<Music> musicBlue;
	
	private float relVolRed;
	private float relVolGreen;
	private float relVolBlue;
	
	private static float volumeUpdateStep = 0.5f;
	private float curTime;
	
	private static float[] volumeSteps = {0.15f, 0.3f, 0.45f};
		
	public boolean needVolumeUpdate(float delta) {
		curTime += delta;
		
		if (curTime > volumeUpdateStep) {
			curTime = 0;
			return true;
		}
		
		return false;		
	}
	
	public void setRelVolumes(float red, float green, float blue) {
		relVolRed = red;
		relVolGreen = green;
		relVolBlue = blue;		
	}
	
	public MusicController() {
		curTime = 0;
		
		relVolRed = 1.0f;
		relVolGreen = 1.0f;
		relVolBlue = 1.0f;
		
		musicRed = new ArrayList<Music>();
		musicGreen = new ArrayList<Music>();
		musicBlue = new ArrayList<Music>();
		
		addRedMusicFile("data/audio/red1_fire.mp3");
		addRedMusicFile("data/audio/red2_steam.mp3");
		addRedMusicFile("data/audio/red3_cauldron.mp3");
		addRedMusicFile("data/audio/red4_synth.mp3");

		addGreenMusicFile("data/audio/green1_forest.mp3");
		addGreenMusicFile("data/audio/green2_wind.mp3");
		addGreenMusicFile("data/audio/green3_sheep.mp3");
		addGreenMusicFile("data/audio/green4_synth.mp3");

		addBlueMusicFile("data/audio/blue1_snow.mp3");
		addBlueMusicFile("data/audio/blue2_raintock.mp3");
		addBlueMusicFile("data/audio/blue3_icesteps.mp3");
		addBlueMusicFile("data/audio/blue4_synth.mp3");

		java.util.Collections.shuffle(musicRed);
		java.util.Collections.shuffle(musicGreen);
		java.util.Collections.shuffle(musicBlue);
	}
	
	public void addRedMusicFile(String file) {
		Music music = Gdx.audio.newMusic(Gdx.files.internal(file));
		musicRed.add(music);
	}
	
	public void addGreenMusicFile(String file) {
		Music music = Gdx.audio.newMusic(Gdx.files.internal(file));
		musicGreen.add(music);
	}
	
	public void addBlueMusicFile(String file) {
		Music music = Gdx.audio.newMusic(Gdx.files.internal(file));
		musicBlue.add(music);
	}
	
	public void updateVolumes(float percentage) {
		if (percentage < volumeSteps[0]) {
			float vol = 1.0f - percentage / volumeSteps[0];
			musicRed.get(0).setVolume(vol * relVolRed);
			musicGreen.get(0).setVolume(vol * relVolGreen);	
			musicBlue.get(0).setVolume(vol * relVolBlue);		
		} else if (percentage < volumeSteps[1]) {
			float vol = 1.0f - percentage / volumeSteps[1];
			musicRed.get(1).setVolume(vol * relVolRed);
			musicGreen.get(1).setVolume(vol * relVolGreen);	
			musicBlue.get(1).setVolume(vol * relVolBlue);			
		} else if (percentage < volumeSteps[2]) {
			float vol = 1.0f - percentage / volumeSteps[2];
			musicRed.get(2).setVolume(vol * relVolRed);
			musicGreen.get(2).setVolume(vol * relVolGreen);
			musicBlue.get(2).setVolume(vol * relVolBlue);	
		}		
	}
	
	public void startRed(float initVol) {
		relVolRed = initVol;
		
		for (Iterator<Music> it = musicRed.iterator(); it.hasNext();) {
			Music music = (Music) it.next();
			
			music.setLooping(true);
			music.setVolume(relVolRed);
			music.play();
		}
	}
	
	public void startGreen(float initVol) {
		relVolGreen = initVol;
		
		for (Iterator<Music> it = musicGreen.iterator(); it.hasNext();) {
			Music music = (Music) it.next();
			
			music.setLooping(true);
			music.setVolume(relVolGreen);
			music.play();
		}
	}
	
	public void startBlue(float initVol) {
		relVolBlue = initVol;
		
		for (Iterator<Music> it = musicBlue.iterator(); it.hasNext();) {
			Music music = (Music) it.next();
			
			music.setLooping(true);
			music.setVolume(relVolBlue);
			music.play();
		}
	}
	
	public void dispose() {
		for (Iterator<Music> it = musicRed.iterator(); it.hasNext();) {
			Music music = (Music) it.next();
			music.stop();
			music.dispose();
		}
		for (Iterator<Music> it = musicGreen.iterator(); it.hasNext();) {
			Music music = (Music) it.next();
			music.stop();
			music.dispose();
		}
		for (Iterator<Music> it = musicBlue.iterator(); it.hasNext();) {
			Music music = (Music) it.next();
			music.stop();
			music.dispose();
		}
		
	}
}
