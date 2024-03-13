package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class Camera {
	public OrthographicCamera camera = null;
	
	private final float SCALE = 1.0f;
	
	public Camera()
	{
		float width = 800;
		float height = 600;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false,width/SCALE, height/SCALE);
	}
	
	public void cameraUpdate(float delta, Vector3 position)
	{
		camera.position.set(position);
		camera.update();
	}
}
