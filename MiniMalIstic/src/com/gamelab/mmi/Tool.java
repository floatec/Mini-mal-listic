package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Tool {
	
	protected Map map;
	protected PixmapHelper pixmapHelper;
	protected float curDistanceUntilDraw;
	
	protected float maxToolSize;
	protected int currentPixelsChanged;
	
	//set the Channel to zero where we want to erase
	protected Color color;
	
	public void fixColor (int x, int y) {
		int value =  map.getMapPh().getPixel(x, Gdx.graphics.getHeight() - y);
		
		Color c = new Color(); 
		Color.rgba8888ToColor(c, value);
		if (c.r > c.g)
			if (c.r > c.b)
				this.color = new Color(0,1,1,1);
			else
				this.color = new Color(1,1,0,1);
		else 
			if (c.g > c.b)
				this.color = new Color(1,0,1,1);
			else
				this.color = new Color(1,1,0,1);
	}
	
	public Tool(Map map) {
		color = new Color(1,1,1,1);
		this.map = map;
		this.maxToolSize = 100.f;
		this.currentPixelsChanged = 0;
		pixmapHelper = new PixmapHelper(map.getMapPh().pixmap, map.getMapPh().sprite, map.getMapPh().texture);
	}
	
	public abstract void draw(Vector2 curPos, Vector2 lastPos, float radius, float distance);
	
	public void decreaseToolSize() {
		currentPixelsChanged = 0;
//		currentPixelsChanged /= 2.0f;
	}
	
	public float getRelPixelChanged (){
		return (float) currentPixelsChanged / (float) (Gdx.graphics.getWidth() * Gdx.graphics.getHeight());		
	}
	
	public boolean checkIfRecentlyTouched(int x, int y) {
		return map.getRecentlyTouched(x, y);
	}
	
	public boolean setRecentlyTouched(int x, int y) {
		return map.touchPixel(x, y);
	}
}
