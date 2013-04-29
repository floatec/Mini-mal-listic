package com.gamelab.mmi;

import java.util.prefs.Preferences;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Mmi extends Game {
	public static final int numberOfLevels = 17;

	private GameScreen gameScreen;
	public boolean gameactive = false;
	public Preferences prefs;

	private int currentScreen = 0;
	private LevelParameters levels[];
	// private String
	// level[]={"data/redGreen.png","data/green.png","data/blue.png"};

	public final static int SCREEN_COUNT = 2;

	@Override
	public void create() {
		createLevels();
		prefs = Preferences.userRoot().node(this.getClass().getName());
		setScreen(new SplashScreen(this, "data/Logo.png"));
	}

	public void nextLevel() {
		this.gameScreen = new GameScreen(this, levels[currentScreen++
				% levels.length]);
		gameScreen.setLevel(currentScreen);
		setScreen(gameScreen);
	}

	public void startGame() {
		if (gameactive) {
			setScreen(gameScreen);
		} else {
			gameactive = true;
			currentScreen = 0;
			this.gameScreen = new GameScreen(this, levels[currentScreen++
					% levels.length]);
			Screen tut3 = new TutorialScrenn(this, "data/Tutorial3.png", 2,
					gameScreen);
			Screen tut2 = new TutorialScrenn(this, "data/Tutorial2.png", 1,
					tut3);
			Screen tut1 = new TutorialScrenn(this, "data/Tutorial1.png", 1,
					tut2);

			setScreen(tut1);
		}
	}

	public void continueGame() {
		currentScreen = prefs.getInt("level", -1);
		this.gameScreen = new GameScreen(this, levels[currentScreen++
				% levels.length]);
		gameScreen.setLevel(currentScreen);
		setScreen(gameScreen);
	}

	public void showMenu() {
		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	private void createLevels() {
		levels = new LevelParameters[numberOfLevels];
		createLevel00();
		createLevel01();
		createLevel02();
		createLevel03();
		createLevel04();
		createLevel05();
		createLevel06();
		createLevel07();
		createLevel08();
		createLevel09();
		createLevel10();
		createLevel11();
		createLevel12();
		createLevel13();
		createLevel14();
		createLevel15();
		createLevel16();
	}

	private void createLevel00() {
		levels[0] = new LevelParameters("data/levels/level01.png", 0.10f,
				0.00f, 0.30f, 1, Player.TOOL_PIXEL, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit);
	}

	private void createLevel01() {
		levels[1] = new LevelParameters("data/levels/level02.png", 0.20f,
				0.10f, 0.30f, 1, Player.TOOL_COLOR_SUCKER,
				LevelParameters.walkToolBit | LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit);
	}

	private void createLevel02() {
		levels[2] = new LevelParameters("data/levels/level03.png", 0.20f,
				0.10f, 0.30f, 1, Player.TOOL_PIXEL, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit);
		levels[2].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.10f,
				0.05f));
	}

	private void createLevel03() {
		levels[3] = new LevelParameters("data/levels/level04.png", 0.20f,
				0.10f, 0.30f, 2, Player.TOOL_PIXEL, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit);
		levels[3].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.10f,
				0.05f));
	}

	private void createLevel04() {
		levels[4] = new LevelParameters("data/levels/level05.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[4].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[4].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[4].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel05() {
		levels[5] = new LevelParameters("data/levels/level06.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[5].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[5].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[5].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel06() {
		levels[6] = new LevelParameters("data/levels/level07.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[6].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[6].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[6].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel07() {
		levels[7] = new LevelParameters("data/levels/level08.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[7].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[7].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[7].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel08() {
		levels[8] = new LevelParameters("data/levels/level09.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[8].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[8].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[8].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel09() {
		levels[9] = new LevelParameters("data/levels/level10.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[9].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[9].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[9].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel10() {
		levels[10] = new LevelParameters("data/levels/level11.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[10].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[10].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[10].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel11() {
		levels[11] = new LevelParameters("data/levels/level12.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[11].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[11].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[11].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel12() {
		levels[12] = new LevelParameters("data/levels/level13.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[12].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[12].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[12].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel13() {
		levels[13] = new LevelParameters("data/levels/level14.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[13].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[13].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[13].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel14() {
		levels[14] = new LevelParameters("data/levels/level15.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[14].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[14].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[14].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel15() {
		levels[15] = new LevelParameters("data/levels/level16.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[15].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[15].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[15].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

	private void createLevel16() {
		levels[16] = new LevelParameters("data/levels/level17.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		levels[16].addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		levels[16].addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		levels[16].addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
	}

}
