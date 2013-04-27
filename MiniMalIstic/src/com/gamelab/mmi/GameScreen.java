package com.gamelab.mmi;

import java.awt.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
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

public class GameScreen implements Screen {

	private GameScreenInputHandler gameScreenInputHandler;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private Player player;
	private Door door;
	private Random rand = new Random();
	private Map map;
		
	
	public GameScreen( String file) {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		map = new Map(file);
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		door=new Door();
		door.activate(new Vector2(Math.abs(rand.nextInt())%(w-Door.SIZE),Math.abs(rand.nextInt())%(w-Door.SIZE)));
		
		texture = new Texture(Gdx.files.internal(file));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		
		player = new Player(new Vector2(w / 2, h / 2), 3, map);
		
		gameScreenInputHandler = new GameScreenInputHandler(this, player);
		Gdx.input.setInputProcessor(gameScreenInputHandler);
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		

		batch.begin();

		
		map.getMapPh().sprite.draw(batch);	
		batch.end();
		
		
		player.render();
		door.render();
	}
	
	public void update(float delta){
		player.update(delta);
		if(door.isActive()&&Intersector.overlapCircleRectangle(player.getHitbox(),door.getHitbox())){
		System.out.println("win win win");
		}
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
		batch.dispose();
		texture.dispose();
		
		player.dispose();
	}

}
