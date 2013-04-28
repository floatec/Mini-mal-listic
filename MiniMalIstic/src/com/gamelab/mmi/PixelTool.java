package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PixelTool extends Tool {

	public PixelTool(Map map) {
		super(map);
	}
	
	@Override
	public void draw(Vector2 curPos, Vector2 lastPos, float radius,
			float distance) {
		
		curDistanceUntilDraw -= distance;
		
		if (curDistanceUntilDraw > 0) return;
		
		curDistanceUntilDraw = 1.8f*radius;
		
		int PixelRadius = (int)(radius/2.0f);
		
		int r = (int) radius;
		for (int x = -r; x <= r; x+= PixelRadius) {
			for (int y = -r; y <= r; y+= PixelRadius) {
				if (x * x + y * y <= r * r) {
					int pX = (int) (curPos.x + x);
					int pY = (int) (curPos.y + y);
					drawAbstractPixel(pX, pY, PixelRadius);
				}							
			}			
		}
		
		pixmapHelper.reload();
	}
	
	private void drawAbstractPixel(int _x, int _y, int pixelRadius) {
		Color c = getAverageArroundPixel(_x, _y, pixelRadius);
		
		pixmapHelper.pixmap.setColor(c);
		pixmapHelper.pixmap.fillRectangle(_x, Gdx.graphics.getHeight() - _y, 2 * pixelRadius, 2 * pixelRadius);
	}
	
	private Color getAverageArroundPixel(int _x, int _y, int pixelRadius) {
			Color average = new Color();
			Color.rgba8888ToColor(average, 0);
			average.a = 1;

			for (int x = _x-pixelRadius; x <= _x+pixelRadius; x++) {
					for (int y = _y-pixelRadius; y <= _y+pixelRadius; y++) {
						int value = pixmapHelper.pixmap.getPixel(x, Gdx.graphics.getHeight() - y);
						Color valColor = new Color();
						Color.rgba8888ToColor(valColor, value);
						average.r += valColor.r;
						average.g += valColor.g;
						average.b += valColor.b;
				}			
			}
			float num = ((2*pixelRadius)+1) * ((2*pixelRadius)+1);
			average.r /= num;
			average.g /= num;
			average.b /= num;
			return average;
		} 
}
