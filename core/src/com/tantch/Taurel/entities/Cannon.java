package com.tantch.Taurel.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tantch.Taurel.B2DVars;

public class Cannon {

	public Body body;
	private World world;
	private Fixture fixture;
	private float cx, cy;

	public Cannon(float x, float y, float ang, World world) {

		cx = x;
		cy = y;
		this.world = world;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = ang;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(2, 3, new Vector2(0, 1.5f), 0);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_CANNON;
		fixtureDef.filter.maskBits = B2DVars.BIT_FIBI | B2DVars.BIT_OBS
				| B2DVars.BIT_CANNON;
		fixtureDef.restitution = 0.01f;
		fixtureDef.friction = 1f;
		fixtureDef.density = 40;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData("Cannon");

		

	}

	public void fireArrow() {
		float ang = (float) (body.getAngle() + Math.PI / 2);

		float x = (float) (Math.cos(ang) * 4);
		float y = (float) (Math.sin(ang) * 4);

		new Arrow(cx + x, cy + y, world, ang);
	}

}
