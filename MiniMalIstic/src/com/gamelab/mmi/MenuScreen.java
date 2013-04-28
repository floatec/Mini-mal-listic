package com.gamelab.mmi;

import java.awt.List;
import java.sql.BatchUpdateException;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class MenuScreen implements Screen {

	private Mmi game;
	private SpriteBatch batch = new SpriteBatch();
	private Texture texture;
	private Sprite sprite;
	private Button[] buttons = new Button[2];

	private void startgame() {
		game.startGame();
	}

	public MenuScreen(Mmi game) {
		this.game = game;
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		buttons[0] = new Button(Gdx.graphics.getWidth() / 2 - 49, Gdx.graphics.getHeight()-27-80, 95	, 27,
				game.gameactive?"data/Resume.png":"data/Start.png", new ClickEvent() {

					@Override
					public void onClick(int x, int y) {
						startgame();

					}
				}, Button.STATE_ACTIVE, 0);
		buttons[1] = new Button(Gdx.graphics.getWidth() / 2 - 26, Gdx.graphics.getHeight()-27-80-55, 53	, 28,
				"data/End.png", new ClickEvent() {

					@Override
					public void onClick(int x, int y) {
						Gdx.app.exit();

					}
				}, Button.STATE_ACTIVE, 0);

		/*
		 * texture = new Texture(Gdx.files.internal(file));
		 * texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		 * 
		 * TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		 * 
		 * sprite = new Sprite(region); sprite.setSize(0.9f, 0.9f *
		 * sprite.getHeight() / sprite.getWidth());
		 * sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		 * sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		 */
		GameScreenInputHandler gameScreenInputHandler = new GameScreenInputHandler(
				);
		for (Button b : buttons) {
			gameScreenInputHandler.addEvent(b.getOnClick());
		}

		Gdx.input.setInputProcessor(gameScreenInputHandler);

	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		for (Button b : buttons) {
			b.render();
		}

	}

	public void update(float delta) {

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// batch.dispose();
		// texture.dispose();

	}

}
