package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sun.tools.javac.util.Position;

public class Door {
	private Texture texture;
	private SpriteBatch sb=new SpriteBatch();
	private Sprite sprite;
	private Animation animation;
	private TextureRegion[] walkframes;
	private TextureRegion currentFrame;
	private Vector2 pos=new Vector2(0,0);
	private Rectangle hitbox;
	private boolean active=false;
	public static final int SIZE=64;
	
	public Door() {
		this.texture= new Texture(Gdx.files.internal("data/door.png"));
		hitbox = new Rectangle(pos.x,pos.y,SIZE,SIZE);
	}
	
	public void activate(Vector2 pos) {
		System.out.println(pos);
		this.pos=pos;
		hitbox =new Rectangle(pos.x,pos.y,SIZE,SIZE);
		active=true;
	}
	public void deactivate() {
		active=false;
	}
	public boolean isActive() {
		return active;
	}
	
	 public Rectangle getHitbox() {
		return hitbox;
	}
	
	public void render() {
		if(active){
			sb.begin();
			sb.draw(texture,pos.x,pos.y);
			sb.end();
		}
	}

}
