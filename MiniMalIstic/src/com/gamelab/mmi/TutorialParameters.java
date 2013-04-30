package com.gamelab.mmi;

public class TutorialParameters extends ScreenParameters {

	public final String file;
	public final int frames;
	public final boolean save;

	public TutorialParameters(final String file, final int frames, final boolean save) {
		this.file = file;
		this.frames = frames;
		this.save = save;
	}
	
	@Override
	public void setScreen(Mmi mmi) {
		mmi.setScreenTutorial(this);
	}

	
	
}
