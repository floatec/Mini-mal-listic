package com.gamelab.mmi;

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
		pixmapHelper.pixmap.drawPixel(0, 0, 0xff888888);
		pixmapHelper.pixmap.drawPixel(0, 1, 0xff888888);
		pixmapHelper.pixmap.drawPixel(0, 2, 0xff888888);
		pixmapHelper.pixmap.drawPixel(0, 3, 0xff888888);
		pixmapHelper.pixmap.drawPixel(0, 4, 0xff888888);
		pixmapHelper.pixmap.drawPixel(0, 5, 0xff888888);
		pixmapHelper.pixmap.drawPixel(0, 6, 0xff888888);
		pixmapHelper.update();
	}
}
