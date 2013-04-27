package com.gamelab.mmi;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Tool {
	
	protected Map map;
	protected PixmapHelper pixmapHelper;
	protected float curDistanceUntilDraw;
	
	public Tool(Map map) {
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
