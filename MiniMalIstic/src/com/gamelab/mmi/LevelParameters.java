package com.gamelab.mmi;

import java.util.ArrayList;

public class LevelParameters {
	
	public static final int pixelToolBit = 1 << Player.TOOL_PIXEL;
	public static final int colorSuckerToolBit = 1 << Player.TOOL_COLOR_SUCKER;
	public static final int negatronToolBit = 1 << Player.TOOL_NEGATRON;
	public static final int pixelSwapperToolBit = 1 << Player.TOOL_PIXEL_SWAPPER;
	public static final int huetralizerToolBit = 1 << Player.TOOL_HUETRALIZER;
	public static final int wetWiperToolBit = 1 << Player.TOOL_WETWIPER;
	public static final int walkToolBit = 1 << Player.TOOL_WALK;
	public static final int allToolsMask = pixelToolBit | colorSuckerToolBit
			| negatronToolBit | pixelSwapperToolBit | huetralizerToolBit
			| wetWiperToolBit | walkToolBit;

	private final ArrayList<EnemyParameters> enemies;

	public final String file;
	public final float doorSpawn;
	public final float doorDespawn;
	public final float toolMaxRadius;
	public final int toolMaxLevel;
	public final int firstTool;
	public final int toolFlags;
	public final int toolCount;

	public LevelParameters(final String file, final float doorSpawn,
			final float doorDespawn, final float toolMaxRadius,
			final int toolMaxLevel, final int firstTool, final int toolFlags) {
		this.enemies = new ArrayList<EnemyParameters>();
		this.file = file;
		this.doorSpawn = doorSpawn;
		this.doorDespawn = doorDespawn;
		this.toolMaxRadius = toolMaxRadius;
		this.toolMaxLevel = toolMaxLevel;
		this.firstTool = firstTool;
		this.toolFlags = toolFlags&allToolsMask;
		this.toolCount = countFlags(); 
	}
	
	private int countFlags() {
		int out = 0;
		if(getPixelTool()) {
			out++;
		}
		if(getColorSuckerTool()) {
			out++;
		}
		if(getNegatronTool()) {
			out++;
		}
		if(getHuetralizerTool()) {
			out++;
		}
		if(getPixelSwapperTool()) {
			out++;
		}
		if(getWalkTool()) {
			out++;
		}
		if(getWetWiperTool()) {
			out++;
		}
		return out;
	}

	public void addEnemy(EnemyParameters enemy) {
		enemies.add(enemy);
	}

	public int getNumberOfEnemys() {
		return enemies.size();
	}

	public EnemyParameters getEnemyParameters(int index) {
		return enemies.get(index);
	}

	public boolean getPixelTool() {
		return (toolFlags & pixelToolBit) != 0;
	}

	public boolean getColorSuckerTool() {
		return (toolFlags & colorSuckerToolBit) != 0;
	}

	public boolean getNegatronTool() {
		return (toolFlags & negatronToolBit) != 0;
	}

	public boolean getPixelSwapperTool() {
		return (toolFlags & pixelSwapperToolBit) != 0;
	}

	public boolean getHuetralizerTool() {
		return (toolFlags & huetralizerToolBit) != 0;
	}

	public boolean getWetWiperTool() {
		return (toolFlags & wetWiperToolBit) != 0;
	}

	public boolean getWalkTool() {
		return (toolFlags & walkToolBit) != 0;
	}

}
