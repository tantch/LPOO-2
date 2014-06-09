package com.tantch.Taurel.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tantch.Taurel.B2DVars;

public class Cannon {

	public Body body;
	private World world;
	private int frame;
	private Fixture fixture;
	
	
	Sprite cannonSprite;

	public Cannon(float x, float y, float ang, World world,Texture cannonText) {

		
		this.world = world;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(x, y);
		bodyDef.angle = ang;
		CircleShape shape =  new CircleShape();
		shape.setRadius(3);

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
		cannonSprite = new Sprite(cannonText);
		cannonSprite.setSize(6, 6);
		//cannonSprite.setOrigin(body.getLocalCenter().x+3,body.getLocalCenter().y+3);
		cannonSprite.setOriginCenter();
		updateSprite();
		body.setUserData(cannonSprite);
		

	}
	public void updateSprite() {
		cannonSprite.setRegion(31,18, 100, 100);
		cannonSprite.rotate(500);
	}

	public void fireArrow(Texture arrowText) {
		float ang = (float) (body.getAngle() + Math.PI / 2);

		float x = (float) (Math.cos(ang) * 4);
		float y = (float) (Math.sin(ang) * 4);

		new Arrow(body.getPosition().x + x, body.getPosition().y + y, world, ang,arrowText);
	}

}
