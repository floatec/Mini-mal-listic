package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {

	static final int numberOfAnimations = 1;

	private Vector2 pos;
	private Vector2 lockAt = new Vector2(0,1);

	private float length;
	private int tool;
	private float toolSize;
	private double rotation;
	private float speed;
	private PlayerTexture[] playerTextures;
	private int currentPlayerTexture;

	public void move(Vector2 wc) {		
		this.lockAt = wc.cpy().sub(pos);
		this.length = this.lockAt.len();
		this.lockAt = this.lockAt.nor();
		this.rotation = lockAt.angle();
	}

	public void update(float delta) {
		if (delta * speed < length) {
			pos.add(this.lockAt.cpy().mul(delta * speed));
			this.length = this.length - delta * speed;
		} else {
			pos.add(this.lockAt.cpy().mul(this.length));
			this.length = 0;
		}
		playerTextures[currentPlayerTexture].update(delta);

	}

	public Player(Vector2 pos, int tool) {
		this.pos = pos;
		this.tool = tool;
		//Change here
		currentPlayerTexture = 0;
		
		playerTextures = new PlayerTexture[numberOfAnimations];
		playerTextures[0] = new PlayerTexture(
				"data/sprite_animation_frames_1.png", 5, 6, 0.01f);
		speed = 100.0f;
		toolSize = 1.0f;
		length = 0.0f;
		rotation = 0.0f;
	}

	public void render() {
		playerTextures[currentPlayerTexture].render((float) rotation, pos.x, pos.y, 1.0f);
	}
	
	public void dispose() {
		for (int i = 0; i < playerTextures.length; i++) {
			playerTextures[i].dispose();
		}
	}
}