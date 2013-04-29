package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class ColorSuckerTool extends Tool {

	public ColorSuckerTool(Map map, int currentLevel, int maxLevel, float currentXP, float maxToolSize) {
		super(map, currentLevel, maxLevel, currentXP, maxToolSize);
	}
	
	@Override
	public void draw(Vector2 curPos, Vector2 lastPos, float radius,
			float distance) {
		
		float dynamicToolSize = getDynamicToolSize(radius);
		dynamicToolSize = Math.max(dynamicToolSize, radius);

		int oldPixelsChanged = currentPixelsChanged;
		
		curDistanceUntilDraw -= distance;
		
		if (curDistanceUntilDraw > 0) return;

		curDistanceUntilDraw = (float) (0.4f * Math.sqrt(dynamicToolSize));
		
		int r = (int) dynamicToolSize;
		for (int x = -r; x <= r; x++) {
			for (int y = -r; y <= r; y++) {
				if (x * x + y * y <= r * r) {
					int pX = (int) (curPos.x + x);
					int pY = (int) (curPos.y + y);
					if (map.getRecentlyTouched(pX, pY)) {
						continue;
					}
					map.touchPixel(pX, pY);
					
					int value = pixmapHelper.pixmap.getPixel(pX, Gdx.graphics.getHeight() - pY);
					Color valColor = new Color();
					Color.rgba8888ToColor(valColor, value);
					valColor.mul(this.color);
					
					currentPixelsChanged += 1;
					pixmapHelper.pixmap.drawPixel(pX, Gdx.graphics.getHeight() -pY, Color.rgba8888(valColor));
				}							
			}			
		}
		increaseXP(currentPixelsChanged - oldPixelsChanged);
		
		pixmapHelper.reload();
	}

}
