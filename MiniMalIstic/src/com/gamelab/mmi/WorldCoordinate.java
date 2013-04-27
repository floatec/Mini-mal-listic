package com.gamelab.mmi;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class WorldCoordinate extends Coordinates {

	public WorldCoordinate(int x, int y) {
		super(x, y);
	}

	public WorldCoordinate cameraToWorld(Camera cam, Coordinates cor){
		//cam.invProjectionView.mulVec(mat, vec)
		
	return new WorldCoordinate(cor.getX(), cor.getY());
	}
}
