package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor.SetterOnlyReflection;

public class Enemy {
	
	private static final int numberOfEnemies = 4;
	private static final int numberOfEnemyTextures = 2*numberOfEnemies;
	public static final int Hipster1Enemy = 0;
	public static final int Hipster2Enemy = 1;
	public static final int SpiesserClnEnemy = 2;
	public static final int SpiesserFlwEnemy = 3;
	
	public static final int aiDefault = 0;
	public static final int aiMove = 1;
	public static final int aiIdle = 2;
	
	public static final int enemyEraseTool = 0;
	
	private Vector2 pos;
	private Vector2 lockAt = new Vector2(0, 1);

	private float length;
	private int enemy;
	private float toolSize;
	private double rotation;
	private float speed;
	private Tool[] tools;
	private Vector2 origin;
	private Circle hitbox;
	private PlayerTexture[] playerTextures;
	private int currentPlayerTexture;
	private Map map;
	private int aiPhase;
	private Player player;
	private float spiesserChaseRadius;
	private float spiesserChaseRadiusSq;
	private float idleRadius;
	private float idleRadiusSq;
	private float slothDist;
	private float enemyRadius;
	private boolean active;
	
	public void move(Vector2 wc) {
		this.lockAt = wc.cpy().sub(pos);
		this.length = this.lockAt.len();
		this.lockAt = this.lockAt.nor();
		this.rotation = lockAt.angle();
	}
	
	public void activate(Vector2 pos) {
		this.pos = pos;
		this.active = true;
	}
	
	public void deactivate() {
		this.active = false;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void update(float delta) {
		if(!active) {
			return;
		}
		ai();
		
		Vector2 oldPos = pos;	
		
		if (delta * speed < length) {
			pos.add(this.lockAt.cpy().mul(delta * speed));
			this.length = this.length - delta * speed;
			
			Vector2 headPos = new Vector2(28, 32);
			
			Vector2 deltaBrush = new Vector2(44, 32).sub(headPos);
			
			deltaBrush.rotate((float) this.rotation + 180);
					
			tools[enemy].draw(pos.cpy().add(deltaBrush), oldPos, toolSize, delta * speed);
		} else {
			pos.add(this.lockAt.cpy().mul(this.length));
			this.length = 0;
		}
		
		if (this.length > 0) {
			currentPlayerTexture = 2 * enemy;			
		} else {
			currentPlayerTexture = 2 * enemy + 1;
			playerTextures[currentPlayerTexture].resetAnimationTime();
		}
		
		playerTextures[currentPlayerTexture].update(delta);
		this.hitbox.set(pos.x, pos.y,enemyRadius);		
	}
	
	public void setSpiesserChaseRadius(float radius) {
		spiesserChaseRadius = radius;
		spiesserChaseRadiusSq = radius*radius;
	}
	
	public void setIdleRadius(float radius) {
		idleRadius = radius;
		idleRadiusSq = radius*radius;
	}

	public Circle getHitbox() {
		return hitbox;
	}

	public void render() {		
		if(!active) {
			return;
		}
		playerTextures[currentPlayerTexture].render((float) rotation, pos.x,
				pos.y, 1.0f, 28, 32);
		
	}

	public Enemy(Vector2 pos, int enemy, Map map, Player player) {
		this.map = map;
		this.player = player;
		this.pos = pos;
		this.origin = pos;
		
		setIdleRadius(300.0f);
		setSpiesserChaseRadius(200.0f);

		playerTextures = new PlayerTexture[numberOfEnemyTextures];

		createTextureForTool(Hipster1Enemy, "data/Hipster-big-w.png");
		createTextureForTool(Hipster2Enemy, "data/Kunststudentin-big-w.png");
		createTextureForTool(SpiesserFlwEnemy, "data/Spiesser-big-w.png");
		createTextureForTool(SpiesserClnEnemy, "data/Spiesser-big-w.png");
	
		tools = new Tool[numberOfEnemies];
		tools[Hipster1Enemy] = new EnemyEraseTool(map);
		tools[Hipster2Enemy] = new WalkTool(map);
		tools[SpiesserFlwEnemy] = new WalkTool(map);
		tools[SpiesserClnEnemy] = new EnemyEraseTool(map);
	
		toolSize = 32.0f;
		length = 0.0f;
		rotation = 0.0f;
		enemyRadius = 16.0f;
		this.hitbox = new Circle(origin,enemyRadius);
		setEnemy(enemy);
	}
	
	private void ai() {
		switch (enemy) {
		case Hipster1Enemy:
			cleanAi();
			break;

		case Hipster2Enemy:
			followAi();
			break;

		case SpiesserFlwEnemy:
			spiesserFlwAi();
			break;

		case SpiesserClnEnemy:
			spiesserClnAi();
			break;

		default:
			break;
		}
	}
	
	private Vector2 searchBlock(int radius) {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		int t = 0; 
		int outX = 0;
		int outY = 0;
		int sqDist = w*w + h*h;
		int minY = 0;
		int maxY = h-1;
		int minX = 0;
		int maxX = w-1;
		boolean found = false;
		int posX = (int) pos.x;
		int posY = (int) pos.y;
		int diffX = 0;
		int diffY = 0;

		if(radius>0) {
			t = posY-radius;
			if(t>0) {
				minY = t;
			}
			t = posY+radius;
			if(t<h-1) {
				maxY = t;
			}
			t = posX-radius;
			if(t>0) {
				minX = t;
			}
			t = posX+radius;
			if(t<w-1) {
				maxX = t;
			}
		}
		
		for(int i=minY; i<maxY; i++) {
			for(int j=minX; j<maxX; j++) {
				diffY = i-posY;
				diffX = j-posX;
				t = diffY*diffY+diffX*diffX; 
				if((t<sqDist)&&map.getEverTouched(j, i)) {
					outX = j;
					outY = i;
					sqDist = t;
					found = true;
				}
			}
		}
		return found?new Vector2(outX, outY):null;
	}
	
	private Vector2 searchTouched() {
		Vector2 out = searchBlock(80);
		if(out!=null) {
			return out;
		}
		out = searchBlock(240);
		if(out!=null) {
			return out;
		}
		return searchBlock(0);
	}
	
	private void cleanAi() {
		switch (aiPhase) {
		case aiDefault:
			Vector2 found = searchTouched();
			if(found!=null) {
				Vector2 v = pos.cpy().sub(found);
				if(v.len2()<1) {
					found.x += (float) (4*2*(Math.random()-0.5)*toolSize);
					found.y += (float) (4*2*(Math.random()-0.5)*toolSize);
				}
				move(found);
				aiPhase = aiMove;
			}
			break;
		case aiMove:
			if(length==0.0f) {
				aiPhase = aiDefault;
			}
			break;
		default:
			aiPhase = aiDefault;
			break;
		}
	}
	
	private void followAi() {
		switch (aiPhase) {
		case aiDefault:
			move(player.getPos());
			aiPhase = aiMove;
			break;
		case aiMove:
			if(length>slothDist||length==0.0f) {
				aiPhase = aiDefault;
			}
			break;
		case aiIdle:
			if(player.getPos().cpy().sub(pos).len2()>=idleRadiusSq) {
				aiPhase = aiDefault;
			}
			break;
		default:
			aiPhase = aiDefault;
			break;
		}
	}
	
	private void spiesserFlwAi() {
		Vector2 diff = player.getPos().cpy().sub(pos);
		if(diff.len2()>spiesserChaseRadiusSq) {
			setEnemy(SpiesserClnEnemy);
			cleanAi();
		} else {
			followAi();
		}
	}
	
	private void spiesserClnAi() {
		Vector2 diff = player.getPos().cpy().sub(pos);
		if(diff.len2()<spiesserChaseRadiusSq) {
			setEnemy(SpiesserFlwEnemy);
			followAi();
		} else {
			cleanAi();
		}
	}

	public void setEnemy(int enemy) {
		switch (enemy) {
		case Hipster1Enemy:
			speed = 80.0f;
			break;

		case Hipster2Enemy:
			speed = 90.0f;
			slothDist = 100.0f;
			break;

		case SpiesserClnEnemy:
			speed = 60.0f;
			break;

		case SpiesserFlwEnemy:
			speed = 70.0f;
			slothDist = 50.0f;
			break;

		default:
			return;
		}
		aiPhase = aiDefault;
		this.enemy = enemy;
		currentPlayerTexture = 2 * enemy + 1;
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

	public void collide() {
		if(!active) {
			return;
		}
		switch (enemy) {
		case Hipster2Enemy:
		case SpiesserFlwEnemy:
			if(Intersector.overlapCircles(player.getHitbox(), hitbox)) {
				aiPhase = aiIdle;
				length = 0;
				player.decreaseToolSize();
			}
			break;

		default:
			break;
		}
	}


}
