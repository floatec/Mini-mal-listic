package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Tool {
	
	protected Map map;
	protected PixmapHelper pixmapHelper;
	protected float curDistanceUntilDraw;
	
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
		pixmapHelper = new PixmapHelper(map.getMapPh().pixmap, map.getMapPh().sprite, map.getMapPh().texture);
	}
	
	public abstract void draw(Vector2 curPos, Vector2 lastPos, float radius, float distance);
	
	public boolean checkIfRecentlyTouched(int x, int y) {
		return map.getRecentlyTouched(x, y);
	}
	
	public boolean setRecentlyTouched(int x, int y) {
		return map.touchPixel(x, y);
	}
}
