package com.tantch.Taurel.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tantch.Taurel.B2DVars;

public class BtCan {
	private World world;
	public Body body;
	private Fixture fixture;
	
	public BtCan(World world, float x, float y) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;
		this.world=world;
		PolygonShape shape = new PolygonShape();

		shape.setAsBox(1f, 0.2f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_PICKUP;
		fixtureDef.filter.maskBits = B2DVars.BIT_FIBI;
		fixtureDef.restitution = 0.8f;
		fixtureDef.friction = .8f;
		fixtureDef.density = 3;
		fixtureDef.isSensor=true;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);

		fixture.setUserData("Button");
	}

}
