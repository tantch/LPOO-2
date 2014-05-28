package com.tantch.Taurel.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tantch.Taurel.B2DVars;
import com.tantch.Taurel.screens.MovementTestScreen;

public abstract class Obstacle {

	public Body body;
	Fixture fixture;
	MovementTestScreen screen;
	float SIZE = 1;
	float screenW, screenH;
	OrthographicCamera camera;
	float cx, cy;
	

}
