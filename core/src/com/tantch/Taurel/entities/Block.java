package com.tantch.Taurel.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tantch.Taurel.B2DVars;
import com.tantch.Taurel.screens.MovementTestScreen;

public class Block extends Obstacle {

	public Block(World world, float x, float y, float size,
			OrthographicCamera camera, MovementTestScreen screen) {

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
		shape.setAsBox(SIZE / 2f, SIZE / 2f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_OBS;
		fixtureDef.filter.maskBits = B2DVars.BIT_FIBI | B2DVars.BIT_CANNON;
		fixtureDef.restitution = .8f;
		fixtureDef.friction = .8f;
		fixtureDef.density = 6;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData("block");
	}

}
