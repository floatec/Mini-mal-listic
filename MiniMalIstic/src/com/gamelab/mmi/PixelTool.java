package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PixelTool extends Tool {

	private static int getCurrentLevel(LevelTransporter lt) {
		return lt!=null?lt.pixelToolXp.currentLevel:0;
	}
	
	private static float getCurrentXP(LevelTransporter lt) {
		return lt!=null?lt.pixelToolXp.currentXp:0;
	}
	
	public PixelTool(LevelParameters lp, Map map, LevelTransporter lt) {
		super(lp, map, getCurrentLevel(lt), getCurrentXP(lt));
//		this.maxToolSize *= 0.75f;
		this.growAdjust = 0.55f;
	}
	
	@Override
	public void draw(Vector2 curPos, Vector2 lastPos, float radius,
			float distance) {
		
		float dynamicToolSize = getDynamicToolSize(radius);
		dynamicToolSize = Math.max(dynamicToolSize, radius);
		
		int oldPixelsChanged = currentPixelsChanged;
		
		curDistanceUntilDraw -= distance;
		
		if (curDistanceUntilDraw > 0) return;
		
		curDistanceUntilDraw = 0.8f*dynamicToolSize;
		
		int PixelRadius = (int)(dynamicToolSize/2.0f);
		
		int r = (int) dynamicToolSize;
		for (int x = -r; x <= r; x+= PixelRadius) {
			for (int y = -r; y <= r; y+= PixelRadius) {
				if (x * x + y * y <= r * r) {
					int pX = (int) (curPos.x + x);
					int pY = (int) (curPos.y + y);
					if (!map.getRecentlyTouched(pX, pY)) {
						currentPixelsChanged += drawAbstractPixel(pX, pY, PixelRadius);
					}
				}							
			}			
		}
		
		increaseXP((int) (growAdjust * (currentPixelsChanged - oldPixelsChanged)));
		
		pixmapHelper.reload();
	}
	
	private int drawAbstractPixel(int _x, int _y, int pixelRadius) {
		int pixelsChanged = 0;
		
		Color c = getAverageArroundPixel(_x, _y, pixelRadius);
		
		Pixmap pm = pixmapHelper.pixmap;
		pm.setColor(c);
		for (int x = _x-pixelRadius; x < _x + pixelRadius; x++) {
			for (int y = _y-pixelRadius; y < _y + pixelRadius; y++) {
				pm.drawPixel(x, Gdx.graphics.getHeight()-y);
				pixelsChanged += 1;
				map.touchPixel(x, y);
			}			
		}
		return pixelsChanged;
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
