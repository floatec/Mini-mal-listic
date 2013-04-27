package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Player {

	static final int numberOfAnimations = 1;
	static final int numberOfTools = 4;

	private Vector2 pos;
	private Vector2 lockAt = new Vector2(0, 1);

	private float length;
	private int tool;
	private float toolSize;
	private double rotation;
	private float speed;
	private Tool[] tools;
	private Vector2 origin;
	private Circle hitbox;
	private PlayerTexture[] playerTextures;
	private int currentPlayerTexture;
	private Map map;

	public void move(Vector2 wc) {
		this.lockAt = wc.cpy().sub(pos);
		this.length = this.lockAt.len();
		this.lockAt = this.lockAt.nor();
		this.rotation = lockAt.angle();
	}

	public void update(float delta) {
		Vector2 oldPos = pos;	
		
		if (delta * speed < length) {
			pos.add(this.lockAt.cpy().mul(delta * speed));
			this.length = this.length - delta * speed;
			
			tools[tool].draw(pos, oldPos, toolSize, delta * speed);
		} else {
			pos.add(this.lockAt.cpy().mul(this.length));
			this.length = 0;
		}
		playerTextures[currentPlayerTexture].update(delta);
		this.hitbox.set(origin.x, origin.y,
				this.playerTextures[currentPlayerTexture].getFrameHeight());		
	}

	public Circle getHitbox() {
		return hitbox;
	}

	public Player(Vector2 pos, int tool, Map map) {
		this.pos = pos;
		this.origin = pos;
		this.map = map;
			this.tool = tool;
		// Change here
		currentPlayerTexture = 0;

		playerTextures = new PlayerTexture[numberOfAnimations];
		playerTextures[0] = new PlayerTexture(
				"data/Listic-PL-c-w.png", 1, 2, 0.2f);
		
		this.hitbox = new Circle(origin,
				this.playerTextures[currentPlayerTexture].getFrameHeight());
		
		tools = new Tool[numberOfTools];
		tools[0] = new PixelTool(map);
		tools[1] = new ColorSuckerTool(map);
		tools[2] = new NegatronTool(map);
		tools[3] = new PixelSwapperTool(map);
	
		speed = 100.0f;
		toolSize = 20.0f;
		length = 0.0f;
		rotation = 0.0f;
	}

	public void render() {		
		playerTextures[currentPlayerTexture].render((float) rotation, pos.x,
				pos.y, 1.0f);
		
	}

	public void dispose() {
		for (int i = 0; i < playerTextures.length; i++) {
			playerTextures[i].dispose();
		}
	}

	public Texture getTexture() {
		return playerTextures[currentPlayerTexture].getWalkSheet();
	}
}
