package com.gamelab.mmi;

import sun.net.www.content.text.plain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class GameScreenInputHandler implements InputProcessor {

	private GameScreen gameScreen;
	private Player player;
	
	public GameScreenInputHandler(GameScreen _screen, Player pl) {
				this.gameScreen = _screen;
				player = pl;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//System.out.println(screenX+ " " + screenY + "\n");
		
		player.move(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		player.move(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));	
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
