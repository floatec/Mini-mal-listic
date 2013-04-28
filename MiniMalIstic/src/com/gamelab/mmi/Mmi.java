package com.gamelab.mmi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Mmi extends Game {
	private GameScreen gameScreen;
	public boolean gameactive=false;
	
	private int currentScreen=0;
	private String level[]={"data/level1.png","data/level2.png","data/level3.png"};
	public final static int SCREEN_COUNT=2;
	@Override
	public void create() {
		setScreen(new SplashScreen(this, "data/Logo.png"));
	}
	
	public void nextLevel(){
		this.gameScreen=new GameScreen(this, level[currentScreen++%level.length]);
		setScreen(gameScreen);
	}
	
	public void startGame() {

		currentScreen=0;
		this.gameScreen=new GameScreen(this, level[currentScreen++%level.length]);
		Screen tut3=new TutorialScrenn(this, "data/Tutorial3.png", 2, gameScreen);
		Screen tut2=new TutorialScrenn(this, "data/Tutorial2.png", 1, tut3);
		Screen tut1=new TutorialScrenn(this, "data/Tutorial1.png", 1, tut2);
		
		setScreen(tut1);
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
