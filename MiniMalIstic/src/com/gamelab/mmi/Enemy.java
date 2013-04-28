package com.gamelab.mmi;

public class Enemy {
	
	private static final int numberOfEnemyTools = 1;
	
	public static final int enemyEraseTool = 0;
	
	protected Tool tools[];
	
	public Enemy(Map map) {
		tools = new Tool[numberOfEnemyTools];
		tools[enemyEraseTool] = new EnemyEraseTool(map);
	}
	

}
