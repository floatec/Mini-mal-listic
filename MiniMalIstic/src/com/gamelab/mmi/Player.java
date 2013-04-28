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

	public static final int numberOfTools = 7;
	public static final int numberOfAnimations = 2 * numberOfTools;
	public static final int TOOL_PIXEL=0x0;
	public static final int TOOL_COLOR_SUCKER=0x1;
	public static final int TOOL_NEGATRON=0x2;
	public static final int TOOL_PIXEL_SWAPPER=0x3;
	public static final int TOOL_HUETRALIZER=0x4;
	public static final int TOOL_WETWIPER=0x5;
	public static final int TOOL_WALK=0x6;
	
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

	public void setTool(int tool) {
		this.tool = tool;
		currentPlayerTexture = 2 * tool + 1;
	}
	
	public void move(Vector2 wc) {
		this.lockAt = wc.cpy().sub(pos);
		this.length = this.lockAt.len();
		this.lockAt = this.lockAt.nor();
		this.rotation = lockAt.angle();
	}
	
	public void newTarget(Vector2 target) {
		tools[tool].fixColor((int) target.x, (int) target.y);
	}

	public Vector2 getPos() {
		return pos;
	}
	
	public void update(float delta) {
		Vector2 oldPos = pos;	
		
		if (delta * speed < length) {
			pos.add(this.lockAt.cpy().mul(delta * speed));
			this.length = this.length - delta * speed;
			
			Vector2 headPos = new Vector2(46, 16);
			
			Vector2 deltaBrush = new Vector2(42, 47).sub(headPos);
			
			deltaBrush.rotate((float) this.rotation + 180);
					
			tools[tool].draw(pos.cpy().add(deltaBrush), oldPos, toolSize, delta * speed);
		} else {
			pos.add(this.lockAt.cpy().mul(this.length));
			this.length = 0;
			map.resetPixelsRecentlyTouched();
		}
		
		if (this.length > 0) {
			currentPlayerTexture = 2 * tool;			
		} else {
			currentPlayerTexture = 2 * tool + 1;
			playerTextures[currentPlayerTexture].resetAnimationTime();
		}
		
		playerTextures[currentPlayerTexture].update(delta);
		this.hitbox.set(origin.x, origin.y,
				this.playerTextures[currentPlayerTexture].getFrameHeight());		
	}

	public Circle getHitbox() {
		return hitbox;
	}

	private void createTextureForTool(int _tool, String texture) {
		playerTextures[2 * _tool] = new PlayerTexture(
				texture, 1, 2, 0.2f);
		playerTextures[2 * _tool + 1] = new PlayerTexture(
				texture, 1, 2, 10.0f);		
	}
	
	public Player(Vector2 pos, int tool, Map map) {
		this.pos = pos;
		this.origin = pos;
		this.map = map;
			this.tool = tool;
		// Change here
		currentPlayerTexture = 0;

		playerTextures = new PlayerTexture[numberOfAnimations];
		createTextureForTool(TOOL_PIXEL, "data/Listic-PL-c-w-big.png");
		createTextureForTool(TOOL_HUETRALIZER, "data/Listic-HT-c-w-big.png");
		createTextureForTool(TOOL_COLOR_SUCKER, "data/Listic-CS-c-w-big.png");
		createTextureForTool(TOOL_PIXEL_SWAPPER, "data/Listic-PS-c-w-big.png");
		createTextureForTool(TOOL_NEGATRON, "data/Listic-NT-c-w-big.png");
		createTextureForTool(TOOL_WETWIPER, "data/Listic-WW-c-w-big.png");
		createTextureForTool(TOOL_WALK, "data/Listic-SansPencil-c-w-big.png");
		
		this.hitbox = new Circle(origin,
				this.playerTextures[currentPlayerTexture].getFrameHeight()/2);
		
		tools = new Tool[numberOfTools];
		tools[TOOL_PIXEL] = new PixelTool(map);
		tools[TOOL_HUETRALIZER] = new HuetralizerTool(map);
		tools[TOOL_COLOR_SUCKER] = new ColorSuckerTool(map);
		tools[TOOL_PIXEL_SWAPPER] = new PixelSwapperTool(map);
		tools[TOOL_NEGATRON] = new NegatronTool(map);
		tools[TOOL_WETWIPER] = new WetWiperTool(map);
		tools[TOOL_WALK] = new WalkTool(map);
	
		speed = 100.0f;
		toolSize = 20.0f;
		length = 0.0f;
		rotation = 0.0f;
	}

	public void render() {		
		playerTextures[currentPlayerTexture].render((float) rotation, pos.x,
				pos.y, 1.0f, 46, 16);
		
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
