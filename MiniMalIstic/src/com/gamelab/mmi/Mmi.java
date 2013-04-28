package com.gamelab.mmi;

import java.util.prefs.Preferences;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Mmi extends Game {
	private GameScreen gameScreen;
	public boolean gameactive=false;
	public Preferences prefs;
	
	private int currentScreen=0;
	private String level[]={"data/level1.png","data/level2.png","data/level3.png"};
	//private String level[]={"data/redGreen.png","data/green.png","data/blue.png"};
	
	public final static int SCREEN_COUNT=2;
	@Override
	public void create() {
		prefs= Preferences.userRoot().node(this.getClass().getName());
		setScreen(new SplashScreen(this, "data/Logo.png"));
	}
	
	public void nextLevel(){
		this.gameScreen=new GameScreen(this, level[currentScreen++%level.length]);
		gameScreen.setLevel(currentScreen);
		setScreen(gameScreen);
	}
	
	public void startGame() {
		if(gameactive){
			setScreen(gameScreen);
		}else{
			gameactive=true;
		currentScreen=0;
		this.gameScreen=new GameScreen(this, level[currentScreen++%level.length]);
		Screen tut3=new TutorialScrenn(this, "data/Tutorial3.png", 2, gameScreen);
		Screen tut2=new TutorialScrenn(this, "data/Tutorial2.png", 1, tut3);
		Screen tut1=new TutorialScrenn(this, "data/Tutorial1.png", 1, tut2);
		
		setScreen(tut1);
		}
	}
	public void continueGame() {
		this.gameScreen=new GameScreen(this, level[currentScreen++%level.length]);
		gameScreen.setLevel(prefs.getInt("level", 0));
		setScreen(gameScreen);
	}
	
	public void showMenu(){
		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
