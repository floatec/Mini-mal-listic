package com.gamelab.mmi;

import com.badlogic.gdx.Game;

public class Mmi extends Game {
	private GameScreen[] gameScreen;
	private int currentScreen=0;
	public final static int SCREEN_COUNT=2;
	@Override
	public void create() {
		gameScreen=new GameScreen[SCREEN_COUNT];
		gameScreen[0] = new GameScreen("data/level1.png");
		gameScreen[1] = new GameScreen("data/level2.png");
		setScreen(gameScreen[0]);
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
