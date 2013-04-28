package com.gamelab.mmi;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Map {

	private Pixmap mapOrig;
	private Pixmap mapPm;
	private PixmapHelper mapPh;
	
	private boolean[][] pixelsEverTouched;
	private boolean[][] pixelsRecentlyTouched;
	
	private int touchedPixel;
	
	
	public Map(String mapFile) {
		mapOrig = new Pixmap(Gdx.files.internal(mapFile));
		mapPm = new Pixmap(mapOrig.getWidth(), mapOrig.getHeight(), mapOrig.getFormat());
		mapPm.drawPixmap(mapOrig, 0, 0);
		mapPh = new PixmapHelper(mapPm);
		
		pixelsEverTouched = new boolean[Gdx.graphics.getWidth()][Gdx.graphics.getHeight()];
		pixelsRecentlyTouched = new boolean[Gdx.graphics.getWidth()][Gdx.graphics.getHeight()];
		
		touchedPixel = 0;
	}
	
	public Pixmap getMapOrig() {
		return mapOrig;
	}
	
	public void resetPixelsEverTouched () {
		for(int i = 0; i < pixelsEverTouched.length; i++) {
		    Arrays.fill(pixelsEverTouched[i], false);
		}
		//Arrays.fill(pixelsEverTouched, false);
	}
	
	public void resetPixelsRecentlyTouched () {
		for(int i = 0; i < pixelsRecentlyTouched.length; i++) {
		    Arrays.fill(pixelsRecentlyTouched[i], false);
		}
		//Arrays.fill(pixelsRecentlyTouched, false);
	}
	
	public boolean touchPixel(int x, int y) {
		if (x >= pixelsEverTouched.length || y >= pixelsEverTouched[0].length || y < 0 || x < 0) 
			return false;
		
		if (!pixelsEverTouched[x][y]) {
			touchedPixel++;
		}
		
		pixelsEverTouched[x][y] = true;
		pixelsRecentlyTouched[x][y] = true;
		
		return true;		
	}
	
	public boolean untouchPixel(int x, int y) {
		if (x >= pixelsEverTouched.length || y >= pixelsEverTouched[0].length || y < 0 || x < 0) 
			return false;
		
		if (pixelsEverTouched[x][y]) {
			touchedPixel--;
		}
		
		pixelsEverTouched[x][y] = false;
		pixelsRecentlyTouched[x][y] = false;
		
		return true;		
	}
	
	public boolean getRecentlyTouched(int x, int y) {
		if (x >= pixelsEverTouched.length || y >= pixelsEverTouched[0].length || y < 0 || x < 0) 
			return false;
		
		return pixelsRecentlyTouched[x][y];
		
	}
	
	public boolean getEverTouched(int x, int y) {
		if (x >= pixelsEverTouched.length || y >= pixelsEverTouched[0].length || y < 0 || x < 0) 
			return false;
		return pixelsEverTouched[x][y];
	}
	
	public float getRelativeTouched() {
		return (float) touchedPixel / (float) (pixelsEverTouched.length * pixelsEverTouched[0].length);		
	}
	
	public PixmapHelper getMapPh() {
		return mapPh;
	}

}
