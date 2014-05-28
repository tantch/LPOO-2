package com.tantch.Taurel.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
	private Fixture fixture;
	private float cx, cy;

	
	public Cannon(float x,float y,World world){
		
		cx=x;
		cy=y;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(x, y);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(2, 3);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits=B2DVars.BIT_CANNON;
		fixtureDef.filter.maskBits= B2DVars.BIT_FIBI | B2DVars.BIT_OBS;
		fixtureDef.restitution = 0.01f;
		fixtureDef.friction = 1f;
		fixtureDef.density =40;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
		
		fixture.setUserData("Cannon");
		
		
	}
	
	

}
