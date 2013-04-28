package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class EnemyEraseTool extends Tool {

	private Pixmap mapOrig;
	
	public EnemyEraseTool(Map map) {
		super(map);
		mapOrig = map.getMapOrig();
	}

	@Override
	public void draw(Vector2 curPos, Vector2 lastPos, float radius,
			float distance) {
		curDistanceUntilDraw -= distance;
		
		if (curDistanceUntilDraw > 0) return;
		
		curDistanceUntilDraw = 1.0f;
		
		int r = (int) radius;
		for (int x = -r; x <= r; x++) {
			for (int y = -r; y <= r; y++) {
				if (x * x + y * y <= r * r) {
					int pX = (int) (curPos.x + x);
					int pY = (int) (curPos.y + y);
					if (!map.getEverTouched(pX, pY)) {
						continue;
					}
					map.untouchPixel(pX, pY);
					
					pixmapHelper.pixmap.drawPixel(pX, Gdx.graphics.getHeight() -pY, mapOrig.getPixel(pX, Gdx.graphics.getHeight() - pY));
				}							
			}			
		}
		
		pixmapHelper.reload();
	}
	
}
