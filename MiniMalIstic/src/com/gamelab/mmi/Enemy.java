package com.gamelab.mmi;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
	
	private static final int numberOfEnemies = 3;
	private static final int numberOfEnemyTextures = 2*numberOfEnemies;
	public static final int Hipster1Enemy = 0;
	public static final int Hipster2Enemy = 1;
	public static final int SpiesserEnemy = 2;
	
	public static final int enemyEraseTool = 0;
	
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
			
			Vector2 headPos = new Vector2(46, 16);
			
			Vector2 deltaBrush = new Vector2(42, 47).sub(headPos);
			
			deltaBrush.rotate((float) this.rotation + 180);
					
			tools[tool].draw(pos.cpy().add(deltaBrush), oldPos, toolSize, delta * speed);
		} else {
			pos.add(this.lockAt.cpy().mul(this.length));
			this.length = 0;
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

	public void render() {		
		playerTextures[currentPlayerTexture].render((float) rotation, pos.x,
				pos.y, 1.0f);
		
	}

	public Enemy(Vector2 pos, int tool, Map map) {
		this.pos = pos;
		this.origin = pos;
		this.map = map;
		this.tool = tool;
		currentPlayerTexture = 2 * tool + 1;;

		playerTextures = new PlayerTexture[numberOfEnemyTextures];
		createTextureForTool(Hipster1Enemy, "data/Hipster1-w.png");
		createTextureForTool(Hipster2Enemy, "data/Hipster2-w.png");
		
		this.hitbox = new Circle(origin,
				this.playerTextures[currentPlayerTexture].getFrameHeight()/2);
		
		tools = new Tool[numberOfEnemies];
		tools[Hipster1Enemy] = new EnemyEraseTool(map);
		tools[Hipster2Enemy] = new EnemyEraseTool(map);
		tools[SpiesserEnemy] = new EnemyEraseTool(map);
	
		speed = 100.0f;
		toolSize = 20.0f;
		length = 0.0f;
		rotation = 0.0f;
	}
	
	private void createTextureForTool(int _tool, String texture) {
		playerTextures[2 * _tool] = new PlayerTexture(
				texture, 1, 2, 0.2f);
		playerTextures[2 * _tool + 1] = new PlayerTexture(
				texture, 1, 2, 10.0f);		
	}
	
	public void dispose() {
		for (int i = 0; i < playerTextures.length; i++) {
			playerTextures[i].dispose();
		}
	}


}
