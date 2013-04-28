package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button {
	public static final int STATE_DISABLED = 0x2;
	public static final int STATE_INACTIVE = 0x1;
	public static final int STATE_ACTIVE = 0x0;
	private SpriteBatch sb = new SpriteBatch();
	private ClickEvent onClick;
	private ClickEvent event;
	private Rectangle hitbox;
	private Texture texture;
	private TextureRegion[] tr;
	private Sprite sprite;
	private int state;
	private int offset;

	public int getState() {
		return state;
	}
	
	
	public Button(int x, int y, int w, int h, String img, ClickEvent event,
			int state, int spriteRow)
	{
		this(x,  y,w,  h,  img,  event,
				 state, spriteRow,3);
	}
	public Button(int x, int y, int w, int h, String img, ClickEvent event,
			int state, int spriteRow,int statecount) {
		this.event = event;
		this.texture = new Texture(Gdx.files.internal(img));
		this.offset = spriteRow * statecount;
		this.state = state;
		int col = this.texture.getWidth() / w;
		int row = this.texture.getHeight() / h;
		TextureRegion[][] tmp = TextureRegion.split(texture, w, h);
		this.tr = new TextureRegion[row * col];
		int index = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tr[index++] = tmp[i][j];
			}
		}

		hitbox = new Rectangle(x, y, w, h);
		this.onClick = new ClickEvent() {

			@Override
			public void onClick(int x, int y) {
				if (Intersector.intersectRectangles(hitbox, new Rectangle(x,
						Gdx.graphics.getHeight() - y, 1, 1))) {
					callback(x, y);

				}

			}
		};

	}

	public void setState(int state) {
		this.state = state;
	}

	public ClickEvent getOnClick() {
		return onClick;
	}

	private void callback(int x, int y) {
		if (state != Button.STATE_DISABLED)
			event.onClick(x, y);
	}

	public void render() {
		sb.begin();
		sb.draw(tr[offset + state], hitbox.x, hitbox.y);
		sb.end();
	}

}
