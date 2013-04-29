package com.gamelab.mmi;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class HuetralizerTool extends Tool {

	private static int getCurrentLevel(LevelTransporter lt) {
		return lt!=null?lt.huetralizerToolXp.currentLevel:0;
	}
	
	private static float getCurrentXP(LevelTransporter lt) {
		return lt!=null?lt.huetralizerToolXp.currentXp:0;
	}
	
	public HuetralizerTool(LevelParameters lp, Map map, LevelTransporter lt) {
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

		curDistanceUntilDraw = (float) (0.4f * Math.sqrt(dynamicToolSize));
		
		int r = (int) dynamicToolSize;
		for (int x = -r; x <= r; x++) {
			for (int y = -r; y <= r; y++) {
				if (x * x + y * y <= r * r) {
					int pX = (int) (curPos.x + x);
					int pY = (int) (curPos.y + y);
					if (!map.getRecentlyTouched(pX, pY)) {
						currentPixelsChanged += 1;
					}
					
					map.touchPixel(pX, pY);
					
					int value = pixmapHelper.pixmap.getPixel(pX, Gdx.graphics.getHeight() - pY);
					Color valColor = new Color();
					Color.rgba8888ToColor(valColor, value);
					float v = Math.max(valColor.r, Math.max(valColor.g, valColor.b));
					
					pixmapHelper.pixmap.drawPixel(pX, Gdx.graphics.getHeight() -pY, v>=0.5f?0xffffffff:0x000000ff);				
				}							
			}			
		}
		
		increaseXP(currentPixelsChanged - oldPixelsChanged);
		
		pixmapHelper.reload();
	}

}
