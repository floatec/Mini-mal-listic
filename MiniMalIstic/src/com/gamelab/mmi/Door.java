package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
	private float animationTime;
	private int frameWidth;
	private int frameHeight;
	public static final float SIZE=64.0f;
	public static final float BORDER=28.0f;
	
	public Door(Mmi game) {
		this.texture= new Texture(Gdx.files.internal("data/door.png"));
		this.texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		final int row = 1;
		final int col = 2;
		frameWidth = texture.getWidth() / col;
		frameHeight = texture.getHeight() / row;
		
		final TextureRegion[][] tmp = TextureRegion.split(texture,
				frameWidth, frameHeight);
		this.walkframes = new TextureRegion[col * row];
		int index = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				walkframes[index++] = tmp[i][j];
			}
		}
		final float frameDuration = 0.60f;
		animation = new Animation(frameDuration, walkframes);
		this.sb = new SpriteBatch();
		animationTime = 0;
		
		hitbox = new Rectangle(pos.x+BORDER,pos.y+BORDER,SIZE-BORDER,SIZE-BORDER);
	}
	
	public void update(float delta) {
		this.animationTime += delta;
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
/*
 			sb.begin();
			sb.draw(texture,pos.x,pos.y);
			sb.end();
*/
			currentFrame = animation.getKeyFrame(animationTime, true);
			sb.begin();
			sb.draw(currentFrame, pos.x, pos.y);
			sb.end();
		}
	}
	

}
