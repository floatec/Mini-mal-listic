package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class ToolSoundController {

	private Sound[] toolSounds;
	
	private long[] soundID;
	
	private int currentTool;
	private boolean isPlaying;
	
	public ToolSoundController(int initTool) {
		currentTool = initTool;
		isPlaying = false;
		
		toolSounds = new Sound[Player.numberOfTools];
		soundID = new long[Player.numberOfTools];
		
		toolSounds[Player.TOOL_PIXEL] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pixelizer.mp3"));
		toolSounds[Player.TOOL_PIXEL_SWAPPER] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pixelizer.mp3"));
		toolSounds[Player.TOOL_COLOR_SUCKER] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/colorsucker.mp3"));
		toolSounds[Player.TOOL_HUETRALIZER] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/huetralizer.mp3"));
		toolSounds[Player.TOOL_NEGATRON] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pixelizer.mp3"));
		toolSounds[Player.TOOL_WETWIPER] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pixelizer.mp3"));
		toolSounds[Player.TOOL_WALK] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/pixelizer.mp3"));
		
		for (int i = 0; i < Player.numberOfTools; i++)
			soundID[i] = 0;
	}
	
	public void changeTool(int tool) {
		stopSound();		
		currentTool = tool;		
	}
	
	public void startSound() {
		isPlaying = true;
		soundID[currentTool] = toolSounds[currentTool].play(1.0f);      // plays the sound a second time, this is treated as a different instance
		toolSounds[currentTool].setLooping(soundID[currentTool], true);       // keeps the sound looping
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public void stopSound() {
		isPlaying = false;
		toolSounds[currentTool].stop(soundID[currentTool]);
	}

}
