package com.tantch.Taurel.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tantch.Taurel.B2DVars;
import com.tantch.Taurel.screens.GameScreen;

public class Wall extends Obstacle {

	public Wall(World world, float x, float y, float size,
			OrthographicCamera camera, GameScreen screen,
			boolean vertical) {

		cx = x;
		cy = y;
		SIZE = size;
		this.screen = screen;
		this.camera = camera;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;

		PolygonShape shape = new PolygonShape();
		if (vertical)
			shape.setAsBox(0.2f, SIZE / 2f);
		else
			shape.setAsBox(SIZE / 2f, 0.2f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_OBS;
		fixtureDef.filter.maskBits = B2DVars.BIT_FIBI | B2DVars.BIT_BIRD | B2DVars.BIT_OBS ;
		fixtureDef.restitution = 0f;
		fixtureDef.friction = 1f;
		fixtureDef.density = 6;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData("Wall");
	}

}
