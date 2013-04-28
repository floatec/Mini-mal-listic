package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerTexture {
	private Texture walkSheet;
	private SpriteBatch sb;
	private Animation animation;
	private TextureRegion[] walkframes;
	private TextureRegion currentFrame;
	private float animationTime;
	
	private int frameWidth;
	private int frameHeight;
	
	public int getFrameHeight() {
		return frameHeight;
	}
	
	public void update(float delta) {
		this.animationTime += delta;
	}

	public PlayerTexture(String file, int row, int col, float frameDuration) {
		walkSheet = new Texture(Gdx.files.internal(file));
		walkSheet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		frameWidth = walkSheet.getWidth() / col;
		frameHeight = walkSheet.getHeight() / row;
		
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				frameWidth, frameHeight);
		this.walkframes = new TextureRegion[col * row];
		int index = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				walkframes[index++] = tmp[i][j];
			}
		}
		animation = new Animation(frameDuration, walkframes);
		this.sb = new SpriteBatch();
		this.animationTime = 0;
	}
	
	public void render(float rotation, float x, float y, float scale) {
		currentFrame = animation.getKeyFrame(animationTime, true);
		sb.begin();
		sb.draw(currentFrame, x, y, 46, 16, frameWidth, frameHeight, scale, scale, rotation + 90, true);
		sb.end();
	}

	public void resetAnimationTime() {
		this.animationTime = 0;
	}
	
	public void dispose() {
		walkSheet.dispose();
		sb.dispose();
	}
	
	public Texture getWalkSheet() {
		return walkSheet;
	}
}
