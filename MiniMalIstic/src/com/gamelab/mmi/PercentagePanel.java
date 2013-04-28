package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class PercentagePanel{

	private Texture texture;
	private TextureRegion[] tr;
	private SpriteBatch sb = new SpriteBatch();
	private int offset;
	
	private BitmapFont font;
	
	private float percentage;
	
	//int charWidth, int charHeight, String texFile
	
	public PercentagePanel() {
		font = new BitmapFont(Gdx.files.internal("data/fonts/percentage.fnt"), false);
//		this.texture = new Texture(texFile);
//		this.offset = 0;
//
//		int rows = this.texture.getHeight() / charHeight;
//		int cols = this.texture.getWidth() / charWidth;
//		
//		TextureRegion[][] tmp = TextureRegion.split(texture, charWidth, charHeight);
//		this.tr = new TextureRegion[rows * cols];
//		
//		int index = 0;
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < cols; j++) {
//				tr[index++] = tmp[i][j];
//			}
//		}
		
	}
	
	public void render(String text, int x, int y) {
		sb.begin();
		font.draw(sb, text, x, y);
		sb.end();
//		sb.begin();
//		for (int i = 0; i < chars.length; i++) {
//			sb.draw(tr[chars[i]], 0, 0);
//		}
//		sb.end();
	}	
}
