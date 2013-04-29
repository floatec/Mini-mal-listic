package com.gamelab.mmi;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class HuetralizerTool extends Tool {

	public HuetralizerTool(Map map) {
		super(map);
	}

	@Override
	public void draw(Vector2 curPos, Vector2 lastPos, float radius,
			float distance) {
		float dynamicToolSize = getDynamicToolSize(radius);
		dynamicToolSize = Math.max(dynamicToolSize, radius);
		
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
		
		pixmapHelper.reload();
	}

}
