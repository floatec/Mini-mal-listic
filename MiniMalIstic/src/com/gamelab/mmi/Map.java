package com.gamelab.mmi;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Map {

	private Pixmap mapPm;
	private PixmapHelper mapPh;
	
	private boolean[][] pixelsEverTouched;
	private boolean[][] pixelsRecentlyTouched;
	
	private int touchedPixel;
	
	
	public Map(String mapFile) {
		mapPm = new Pixmap(Gdx.files.internal(mapFile));
		mapPh = new PixmapHelper(mapPm);
		
		pixelsEverTouched = new boolean[Gdx.graphics.getWidth()][Gdx.graphics.getHeight()];
		pixelsRecentlyTouched = new boolean[Gdx.graphics.getWidth()][Gdx.graphics.getHeight()];
		
		touchedPixel = 0;
	}
	
	public void resetPixelsEverTouched () {
		Arrays.fill(pixelsEverTouched, false);
	}
	
	public void resetPixelsRecentlyTouched () {
		Arrays.fill(pixelsRecentlyTouched, false);
	}
	
	public boolean touchPixel(int x, int y) {
		if (x > pixelsEverTouched.length || x > pixelsEverTouched[0].length) 
			return false;
		
		if (!pixelsEverTouched[x][y]) {
			touchedPixel++;
		}
		
		pixelsEverTouched[x][y] = true;
		pixelsRecentlyTouched[x][y] = true;
		
		return true;		
	}
	
	public boolean getRecentlyTouched(int x, int y) {
		if (x > pixelsEverTouched.length || x > pixelsEverTouched[0].length) 
			return false;
		
		return pixelsRecentlyTouched[x][y];
		
	}
	
	public float getRelativeTouched() {
		return (float) touchedPixel / (float) (pixelsEverTouched.length * pixelsEverTouched[0].length);		
	}
	
	public PixmapHelper getMapPh() {
		return mapPh;
	}

}
