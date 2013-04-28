package com.gamelab.mmi;

import com.badlogic.gdx.Game;

public class Mmi extends Game {
	private GameScreen gameScreen;
	public boolean gameactive=false;
	
	private int currentScreen=0;
	private String level[]={"data/level1.png","data/level2.png","data/level3.png"};
	//private String level[]={"data/redGreen.png","data/green.png","data/blue.png"};
	
	public final static int SCREEN_COUNT=2;
	@Override
	public void create() {
		setScreen(new SplashScreen(this, "data/Logo.png"));
	}
	
	public void nextLevel(){
		this.gameScreen=new GameScreen(this, level[currentScreen++%level.length]);
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
