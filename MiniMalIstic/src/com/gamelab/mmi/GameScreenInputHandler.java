package com.gamelab.mmi;

import java.util.ArrayList;

import sun.net.www.content.text.plain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class GameScreenInputHandler implements InputProcessor {
	
	private GameScreen gameScreen;
	private Player player;
	private ArrayList<ClickEvent> events=new ArrayList<ClickEvent>();
	
	public GameScreenInputHandler(GameScreen _screen, Player pl) {
				this.gameScreen = _screen;
				player = pl;
	}
	
	public void addEvent(ClickEvent e){
		events.add(e);
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
		if(screenY+105<Gdx.graphics.getHeight())
		player.move(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));		
		for (int i = 0; i < events.size(); i++) {
			events.get(i).onClick(screenX, screenY);
		}
		
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
