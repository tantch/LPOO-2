package com.tantch.Taurel.entities;

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

public class Arrow {
	public Body body;
	private Fixture fixture;
	private float movementForce = 10000;
	private Sprite arrowSprite;
	private int frame;

	public Arrow(float x, float y, World world, float ang,Texture arrowText) {



		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = false;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1.4f, 0.1f);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_OBS;
		fixtureDef.filter.maskBits = B2DVars.BIT_FIBI | B2DVars.BIT_CANNON | B2DVars.BIT_OBS;
		fixtureDef.restitution = 0.1f;
		fixtureDef.friction = .8f;
		fixtureDef.density = 3;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData("Stick");
		 shape = new PolygonShape();
		shape.setAsBox(0.13f, 0.13f,new Vector2(1.57f,0),0);
		
		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_OBS;
		fixtureDef.filter.maskBits = B2DVars.BIT_FIBI | B2DVars.BIT_CANNON | B2DVars.BIT_OBS;
		fixtureDef.restitution = 0.1f;
		fixtureDef.friction = 0.8f;
		fixtureDef.density = 0.1f;
		body.createFixture(fixtureDef).setUserData("Metal");
		Vector2 forc= new Vector2();
		forc.x = (float) (Math.cos(ang) * movementForce);
		forc.y = (float) (Math.sin(ang) * movementForce);
		body.setTransform(x, y, ang);
		body.applyForceToCenter(forc, true);
		
		arrowSprite = new Sprite(arrowText);
		arrowSprite.setOrigin(body.getLocalCenter().x+1.5f, body.getLocalCenter().y);
		arrowSprite.setSize(2.8f,0.4f);
		arrowSprite.setRegion(5, 6, 75, 10);
		body.setUserData(arrowSprite);
		

	}

	
}
