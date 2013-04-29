package com.gamelab.mmi;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
	
	private float relativeRed;
	private float relativeGreen;
	private float relativeBlue;	
	
	public Map(String mapFile) {
		mapOrig = new Pixmap(Gdx.files.internal(mapFile));
		mapPm = new Pixmap(mapOrig.getWidth(), mapOrig.getHeight(), mapOrig.getFormat());
		mapPm.drawPixmap(mapOrig, 0, 0);
		mapPh = new PixmapHelper(mapPm);
		
		pixelsEverTouched = new boolean[Gdx.graphics.getWidth()][Gdx.graphics.getHeight()];
		pixelsRecentlyTouched = new boolean[Gdx.graphics.getWidth()][Gdx.graphics.getHeight()];
		
		touchedPixel = 0;
		
		relativeRed = 0.333f;
		relativeGreen = 0.333f;
		relativeBlue = 0.333f;
	}
	
	public float getRelRed() {
		return relativeRed;
	}
	public float getRelGreen() {
		return relativeGreen;
	}
	public float getRelBlue() {
		return relativeBlue;
	}
	
	
	public void calcRelativeColors() {
		float countRed = 0;
		float countGreen = 0;
		float countBlue = 0;
		
		for (int x = 0; x < mapOrig.getWidth() ; x++) {
			float rowSum = 0;
			for (int y = 0; y < mapOrig.getHeight(); y++) {
				int value = mapOrig.getPixel(x, y);
				
				Color c = new Color();
				Color.rgba8888ToColor(c, value);
				
				rowSum += c.r + c.g + c.b;
				
				countRed += c.r;
				countGreen += c.g;
				countBlue += c.b;
			}
			countRed /= rowSum;
			countGreen /= rowSum;
			countBlue /= rowSum;
		}		
		float sum = countRed + countGreen + countBlue;
		
		relativeRed = countRed / sum;
		relativeGreen = countGreen / sum;
		relativeBlue = countBlue / sum;		
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
