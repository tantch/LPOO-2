package com.tantch.Taurel.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.tantch.Taurel.screens.GameScreen;

public abstract class Obstacle {

	public Body body;
	Fixture fixture;
	GameScreen screen;
	float SIZE = 1;
	float screenW, screenH;
	OrthographicCamera camera;
	float cx, cy;
	Sprite cloudSprite;
	

}
