package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class PercentagePanel{

	private Texture textureCircle;
	private Texture textureSettings;
	private int x;
	private int y;
	private SpriteBatch sb = new SpriteBatch();
	
	private BitmapFont font;
		
	public PercentagePanel() {
		font = new BitmapFont(Gdx.files.internal("data/fonts/percentage.fnt"), false);
		textureSettings = new Texture(Gdx.files.internal("data/Circle-Settings.png"));
		textureCircle = new Texture(Gdx.files.internal("data/Circle-Percentage.png"));		
	}
	
	public void render(String text) {
		
		 x = Gdx.graphics.getWidth() - 110;
		 y = Gdx.graphics.getHeight() - 16;
		
		sb.begin();
		sb.draw(textureCircle, x - 16, Gdx.graphics.getHeight() - 59);
		sb.draw(textureSettings, x + 65, Gdx.graphics.getHeight() - 43);
		
		font.draw(sb, text, x, y);
		sb.end();

	}	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
