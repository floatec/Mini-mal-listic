	package com.gamelab.mmi;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Mmi extends Game {

	private Screen currentScreen;
	public boolean gameactive = false;
	public Preferences prefs;
	
	private LevelTransporter lt;

	private int currentScreenIndex = 0;
	private ArrayList<ScreenParameters> screens;
	// private String
	// level[]={"data/redGreen.png","data/green.png","data/blue.png"};

	public final static int SCREEN_COUNT = 2;

	@Override
	public void create() {
		lt = new LevelTransporter();
		createLevels();
		prefs = Preferences.userRoot().node(this.getClass().getName());
		setScreen(new SplashScreen(this, "data/Logo.png"));
	}

	public void nextScreen() {
		if(currentScreen instanceof GameScreen) {
			((GameScreen)currentScreen).fillLt();
		}
		screens.get(currentScreenIndex++%screens.size()).setScreen(this);
	}
	
	public void setScreenReturnToMenue() {
		showMenu();
	}

	public void setScreenGameScreen(LevelParameters levelParameters) {
		final GameScreen gs = new GameScreen(this, levelParameters, lt);
		currentScreen = gs;
		gs.setScreenIndex(currentScreenIndex);
		setScreen(currentScreen);
	}

	public void setScreenTutorial(TutorialParameters tutorialParameters) {
		final TutorialScreen ts = new TutorialScreen(this, tutorialParameters.file, tutorialParameters.frames);
		currentScreen = ts;
		ts.setScreenIndex(currentScreenIndex);
		setScreen(ts);
	}

	public void startGame() {
		if (gameactive) {
			setScreen(currentScreen);
		} else {
			gameactive = true;
			currentScreenIndex = 0;
			nextScreen();
		}
	}

	public void continueGame() {
		currentScreenIndex = prefs.getInt("level", -1);
		nextScreen();
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
		screens = new ArrayList<ScreenParameters>();
		screens.add(new TutorialParameters("data/Tutorial1.png", 1));
		screens.add(new TutorialParameters("data/Tutorial2.png", 1));
		screens.add(new TutorialParameters("data/Tutorial3.png", 2));
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
		screens.add(new ReturnToMenueParameters());
	}

	private void createLevel00() {
		final LevelParameters lp = new LevelParameters("data/levels/level01.png", 0.10f,
				0.00f, 0.30f, 1, Player.TOOL_PIXEL, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit);
		screens.add(lp);
	}

	private void createLevel01() {
		final LevelParameters lp = new LevelParameters("data/levels/level02.png", 0.20f,
				0.10f, 0.30f, 1, Player.TOOL_COLOR_SUCKER,
				LevelParameters.walkToolBit | LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit);
		screens.add(lp);
	}

	private void createLevel02() {
		final LevelParameters lp = new LevelParameters("data/levels/level03.png", 0.20f,
				0.10f, 0.30f, 1, Player.TOOL_PIXEL, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.10f,
				0.05f));
		screens.add(lp);
	}

	private void createLevel03() {
		final LevelParameters lp = new LevelParameters("data/levels/level04.png", 0.20f,
				0.10f, 0.30f, 2, Player.TOOL_PIXEL, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.10f,
				0.05f));
		screens.add(lp);
	}

	private void createLevel04() {
		final LevelParameters lp = new LevelParameters("data/levels/level05.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel05() {
		final LevelParameters lp = new LevelParameters("data/levels/level06.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel06() {
		final LevelParameters lp = new LevelParameters("data/levels/level07.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel07() {
		final LevelParameters lp = new LevelParameters("data/levels/level08.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel08() {
		final LevelParameters lp = new LevelParameters("data/levels/level09.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel09() {
		final LevelParameters lp = new LevelParameters("data/levels/level10.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel10() {
		final LevelParameters lp = new LevelParameters("data/levels/level11.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel11() {
		final LevelParameters lp = new LevelParameters("data/levels/level12.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel12() {
		final LevelParameters lp = new LevelParameters("data/levels/level13.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel13() {
		final LevelParameters lp = new LevelParameters("data/levels/level14.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel14() {
		final LevelParameters lp = new LevelParameters("data/levels/level15.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel15() {
		final LevelParameters lp = new LevelParameters("data/levels/level16.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}

	private void createLevel16() {
		final LevelParameters lp = new LevelParameters("data/levels/level17.png", 0.40f,
				0.30f, 1.00f, 4, Player.TOOL_WALK, LevelParameters.walkToolBit
						| LevelParameters.pixelToolBit
						| LevelParameters.colorSuckerToolBit
						| LevelParameters.negatronToolBit
						| LevelParameters.huetralizerToolBit
						| LevelParameters.pixelSwapperToolBit
						| LevelParameters.wetWiperToolBit);
		lp.addEnemy(new EnemyParameters(Enemy.Hipster1Enemy, 0.20f,
				0.10f));
		lp.addEnemy(new EnemyParameters(Enemy.Hipster2Enemy, 0.30f,
				0.20f));
		lp.addEnemy(new EnemyParameters(Enemy.SpiesserClnEnemy, 0.40f,
				0.30f));
		screens.add(lp);
	}


}
