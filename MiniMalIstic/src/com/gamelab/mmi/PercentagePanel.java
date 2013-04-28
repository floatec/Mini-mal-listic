package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class PercentagePanel{

	private Texture textureCircle;
	private Texture textureSettings;
	private SpriteBatch sb = new SpriteBatch();
	
	private BitmapFont font;
		
	public PercentagePanel() {
		font = new BitmapFont(Gdx.files.internal("data/fonts/percentage.fnt"), false);
		textureSettings = new Texture(Gdx.files.internal("data/Circle-Settings.png"));
		textureCircle = new Texture(Gdx.files.internal("data/Circle-Percentage.png"));		
	}
	
	public void render(String text) {
		
		int x = Gdx.graphics.getWidth() - 200;
		int y = Gdx.graphics.getHeight() - 16;
		
		sb.begin();
		sb.draw(textureCircle, x - 16, Gdx.graphics.getHeight() - 59);
		sb.draw(textureSettings, x + 100, Gdx.graphics.getHeight() - 43);
		
		font.draw(sb, text, x, y);
		sb.end();

	}	
}
