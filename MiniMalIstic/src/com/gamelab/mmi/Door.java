package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

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
	public static final float SIZE=64.0f;
	public static final float BORDER=28.0f;
	
	public Door(Mmi game) {
		this.texture= new Texture(Gdx.files.internal("data/door.png"));
		hitbox = new Rectangle(pos.x+BORDER,pos.y+BORDER,SIZE-BORDER,SIZE-BORDER);
	}
	
	public void activate(Vector2 pos) {
		System.out.println(pos);
		this.pos=pos;
		hitbox =new Rectangle(pos.x+BORDER,pos.y+BORDER,SIZE-BORDER,SIZE-BORDER);
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
