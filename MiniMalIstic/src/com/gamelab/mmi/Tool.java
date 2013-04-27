package com.gamelab.mmi;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Tool {
	
	protected PixmapHelper pixmapHelper;
	
	public Tool(Pixmap pixmap, Sprite sprite, Texture texture) {
		pixmapHelper = new PixmapHelper(pixmap, sprite, texture);
	}
	
	public abstract void draw(Vector2 curPos, Vector2 lastPos, float radius, float distance);
	
}
