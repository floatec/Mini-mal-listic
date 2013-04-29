package com.gamelab.mmi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.sun.corba.se.impl.presentation.rmi.DynamicAccessPermission;
import com.sun.org.apache.bcel.internal.generic.LNEG;

public abstract class Tool {
	
	protected Map map;
	protected PixmapHelper pixmapHelper;
	protected float curDistanceUntilDraw;
	
	protected float maxToolSize;
	protected int currentPixelsChanged;
	
	protected int currentLevel = 0;
	protected int maxLevel = 0;
	protected float currentExp = 0;
	
	protected static float[] levelCaps = {0.1f, 0.6f, 0.7f, 0.9f};
	protected static float[] growSpeed = {0.35f, 0.45f, 0.65f, 1.0f};
	
	protected float growAdjust = 1.0f;
	
	//set the Channel to zero where we want to erase
	protected Color color;
	
	public void fixColor (int x, int y) {
		int value =  map.getMapPh().getPixel(x, Gdx.graphics.getHeight() - y);
		
		Color c = new Color(); 
		Color.rgba8888ToColor(c, value);
		if (c.r > c.g)
			if (c.r > c.b)
				this.color = new Color(0,1,1,1);
			else
				this.color = new Color(1,1,0,1);
		else 
			if (c.g > c.b)
				this.color = new Color(1,0,1,1);
			else
				this.color = new Color(1,1,0,1);
	}
	
	public Tool(Map map, int currentLevel, int maxLevel, float currentXP, float maxToolSize) {
		color = new Color(1,1,1,1);
		this.map = map;
		this.maxToolSize = maxToolSize;
		this.currentExp = currentXP;
		this.maxLevel = maxLevel;
		this.currentLevel = currentLevel;
		
		this.currentPixelsChanged = 0;
		pixmapHelper = new PixmapHelper(map.getMapPh().pixmap, map.getMapPh().sprite, map.getMapPh().texture);
	}
	
	public abstract void draw(Vector2 curPos, Vector2 lastPos, float radius, float distance);
	
	public float getCurrentXP() {
		return currentExp;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	protected float getDynamicToolSize(float radius) {
		float dynamicToolSize = (float) currentPixelsChanged / (float) (Gdx.graphics.getWidth() * Gdx.graphics.getHeight());
		dynamicToolSize = (float) Math.sqrt(Math.sqrt(dynamicToolSize));
		dynamicToolSize *= growAdjust * growSpeed[currentLevel] * 100.f;
		
		dynamicToolSize = Math.min(dynamicToolSize, maxToolSize);
		
		return dynamicToolSize;
	}
	
	public void increaseXP(int pixels) {
		float xp = (float) pixels / (float) (Gdx.graphics.getWidth() * Gdx.graphics.getHeight());
		
		if (currentLevel < maxLevel) {
			if (currentExp + xp > levelCaps[currentLevel]) {
				currentExp = 0;
				currentLevel++;
				
				System.out.println("Wohoo level up:" + currentLevel);
			} else {
				currentExp += xp;	
				//System.out.println("CurrentXP" + currentExp);			
			}
		}
	}
	
	public void decreaseToolSize() {
		currentPixelsChanged = 0;
//		currentPixelsChanged /= 2.0f;
	}
	
	public float getRelPixelChanged (){
		return (float) currentPixelsChanged / (float) (Gdx.graphics.getWidth() * Gdx.graphics.getHeight());		
	}
	
	public boolean checkIfRecentlyTouched(int x, int y) {
		return map.getRecentlyTouched(x, y);
	}
	
	public boolean setRecentlyTouched(int x, int y) {
		return map.touchPixel(x, y);
	}
}
