package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class ToolSoundController {

	private Sound[] toolSounds;
	
	private long[] soundID;
	
	private int currentTool;
	private boolean isPlaying;
	
	private float volume;
	
	public ToolSoundController(int initTool) {
		currentTool = initTool;
		isPlaying = false;
		
		toolSounds = new Sound[Player.numberOfTools];
		soundID = new long[Player.numberOfTools];
		
		toolSounds[Player.TOOL_PIXEL] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pixelizer.mp3"));
		toolSounds[Player.TOOL_PIXEL_SWAPPER] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pixelswapper.mp3"));
		toolSounds[Player.TOOL_COLOR_SUCKER] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/colorsucker.mp3"));
		toolSounds[Player.TOOL_HUETRALIZER] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/huetralizer.mp3"));
		toolSounds[Player.TOOL_NEGATRON] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/negatron.mp3"));
		toolSounds[Player.TOOL_WETWIPER] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/wetwiper.mp3"));
		toolSounds[Player.TOOL_WALK] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pixelizer.mp3"));
		
		volume = 0.05f;
		
		for (int i = 0; i < Player.numberOfTools; i++)
			soundID[i] = 0;
	}
	
	public void changeTool(int tool) {
		stopSound();		
		currentTool = tool;		
	}
	
	public void startSound() {
		isPlaying = true;
		soundID[currentTool] = toolSounds[currentTool].play(volume);      // plays the sound a second time, this is treated as a different instance
		toolSounds[currentTool].setLooping(soundID[currentTool], true);       // keeps the sound looping
	}
	
	public void changeVolume(float _volume) {
		toolSounds[currentTool].setVolume(soundID[currentTool], Math.max(_volume, 0.01f));
		volume = _volume;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public void stopSound() {
		isPlaying = false;
		toolSounds[currentTool].stop(soundID[currentTool]);
	}
	
	public void dispose() {
		for (int i = 0; i < toolSounds.length; i++) {
			toolSounds[i].stop(soundID[currentTool]);
			toolSounds[i].dispose();
		}
		
	}

}
