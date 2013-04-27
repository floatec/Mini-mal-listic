package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Map {

	private Pixmap mapPm;
	private PixmapHelper mapPh;
	
	public Map(String mapFile) {
		mapPm = new Pixmap(Gdx.files.internal(mapFile));
		mapPh = new PixmapHelper(mapPm);
	}
	
	public PixmapHelper getMapPh() {
		return mapPh;
	}

}
