package com.gamelab.mmi;

public class TutorialParameters extends ScreenParameters {

	public final String file;
	public final int frames;

	public TutorialParameters(final String file, final int frames) {
		this.file = file;
		this.frames = frames;
	}
	
	@Override
	public void setScreen(Mmi mmi) {
		mmi.setScreenTutorial(this);
	}

	
	
}
