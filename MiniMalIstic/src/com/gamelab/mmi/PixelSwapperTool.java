package com.gamelab.mmi;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PixelSwapperTool extends Tool {

	public PixelSwapperTool(Map map) {
		super(map);
	}

	@Override
	public void draw(Vector2 curPos, Vector2 lastPos, float radius,
			float distance) {
		curDistanceUntilDraw -= distance;
		
		if (curDistanceUntilDraw > 0) return;
		
		curDistanceUntilDraw = 0.8f*radius;
		
		int PixelRadius = (int)(radius/2.0f);
		
		int r = (int) radius;
		
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
					
					int dx = (int) ((Math.random() - 0.5f) * 2 * PixelRadius);
					int dy = (int) ((Math.random() - 0.5f) * 2 * PixelRadius);
					
					int pX2 = pX + dx;
					int pY2 = pY + dy;
					int value2 = pixmapHelper.pixmap.getPixel(pX2, Gdx.graphics.getHeight() - pY2);
					
					pixmapHelper.pixmap.drawPixel(pX, Gdx.graphics.getHeight() -pY, value2);
					pixmapHelper.pixmap.drawPixel(pX2, Gdx.graphics.getHeight() -pY2, value);
				}							
			}			
		}
		
		pixmapHelper.reload();		
	}

}
