package com.gamelab.mmi;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PixelTool extends Tool {

	public PixelTool(Pixmap pixmap, Sprite sprite, Texture texture) {
		super(pixmap, sprite, texture);
	}
	
	@Override
	public void draw(Vector2 curPos, Vector2 lastPos, float radius,
			float distance) {
		
		int r = (int) radius;
		//for (int x = -r; x <= r; r++) {
		//	for *()			
		//}
		
		Color c = new Color(1,0,0,1);
		
		pixmapHelper.pixmap.setColor(c);
		pixmapHelper.pixmap.fillRectangle(0, 0, 100, 100);
		
		pixmapHelper.reload();
	}
}
