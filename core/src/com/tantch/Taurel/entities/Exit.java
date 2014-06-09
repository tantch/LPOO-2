package com.tantch.Taurel.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tantch.Taurel.B2DVars;

public class Exit {
	private World world;
	public Body body;
	private int frame;
	private Fixture fixture;
	private Sprite exitSprite;
/**
 * creates an exit and initializes it in the world
 * @param world where it is placed
 * @param x the horizontal center position
 * @param y the vertical center position
 * @param exitText the flag texture image representing the exit
 */
	public Exit(World world, float x, float y, Texture exitText) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;
		this.world = world;
		CircleShape shape = new CircleShape();
		shape.setRadius(0.3f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_PICKUP;
		fixtureDef.filter.maskBits = B2DVars.BIT_FIBI;
		fixtureDef.restitution = 0.8f;
		fixtureDef.friction = .8f;
		fixtureDef.density = 3;
		fixtureDef.isSensor = true;
		exitSprite = new Sprite(exitText);
		updateSprite();
		exitSprite.setSize(2.8f, 2.8f);
		body = world.createBody(bodyDef);
		body.setUserData(exitSprite);
		fixture = body.createFixture(fixtureDef);

		fixture.setUserData("Exit");
	}
/**
 * updates the sprite being drawn making it shake
 */
	public void updateSprite() {
		int tex = 0, tey = 5;
		int tew, teh;

		frame++;
		if (frame == 4)
			frame = 0;

		teh = tew = 45;
		switch(frame){
		case 0:
			tex=0;
			break;
		case 1:
			tex=48;
			break;
		case 2:
			tex=97;
			break;
		case 3:
			tex=157;
			break;
		}

		exitSprite.setRegion(tex, tey, tew, teh);

	}
}
