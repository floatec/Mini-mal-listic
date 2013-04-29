package com.gamelab.mmi;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PixelSwapperTool extends Tool {

	private static int getCurrentLevel(LevelTransporter lt) {
		return lt!=null?lt.pixelSwappeToolXp.currentLevel:0;
	}
	
	private static float getCurrentXP(LevelTransporter lt) {
		return lt!=null?lt.pixelSwappeToolXp.currentXp:0;
	}
	
	public PixelSwapperTool(LevelParameters lp, Map map, LevelTransporter lt) {
		super(lp, map, getCurrentLevel(lt), getCurrentXP(lt));
	}

	@Override
	public void draw(Vector2 curPos, Vector2 lastPos, float radius,
			float distance) {
		float dynamicToolSize = getDynamicToolSize(radius);
		dynamicToolSize = Math.max(dynamicToolSize, radius);

		int oldPixelsChanged = currentPixelsChanged;
		
		curDistanceUntilDraw -= distance;
		
		if (curDistanceUntilDraw > 0) return;

		curDistanceUntilDraw = (float) (0.4f *  Math.sqrt(dynamicToolSize));
		
		int PixelRadius = (int)(dynamicToolSize/2.0f);
		
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
					
					int dx = (int) ((Math.random() - 0.5f) * 2 * PixelRadius);
					int dy = (int) ((Math.random() - 0.5f) * 2 * PixelRadius);
					
					int pX2 = pX + dx;
					int pY2 = pY + dy;
					int value2 = pixmapHelper.pixmap.getPixel(pX2, Gdx.graphics.getHeight() - pY2);
					
					currentPixelsChanged +=1;
					pixmapHelper.pixmap.drawPixel(pX, Gdx.graphics.getHeight() -pY, value2);
					pixmapHelper.pixmap.drawPixel(pX2, Gdx.graphics.getHeight() -pY2, value);
				}							
			}			
		}

		increaseXP(currentPixelsChanged - oldPixelsChanged);
		
		pixmapHelper.reload();
	}

}
