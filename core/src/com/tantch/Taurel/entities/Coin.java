package com.tantch.Taurel.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.tantch.Taurel.B2DVars;

public class Coin {
	private World world;
	public Body body;
	private int frame;
	private Fixture fixture;
	private Sprite coinSprite;
/**
 * creates and initiates a coin
 * @param world the world where it is created
 * @param x the horizontal center position
 * @param y the vertical center position
 * @param coinText the coin texture image
 */
	public Coin(World world, float x, float y,Texture coinText) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;
		this.world=world;
		CircleShape shape= new CircleShape();
		shape.setRadius(1.4f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_PICKUP;
		fixtureDef.filter.maskBits = B2DVars.BIT_FIBI;
		fixtureDef.restitution = 0.8f;
		fixtureDef.friction = .8f;
		fixtureDef.density = 3;
		fixtureDef.isSensor=true;
		coinSprite = new Sprite(coinText);
		updateSprite();
		coinSprite.setSize(2.8f, 2.8f);
		body = world.createBody(bodyDef);
		body.setUserData(coinSprite);
		fixture = body.createFixture(fixtureDef);

		fixture.setUserData("Coin");
	}
	/**
	 * updates the sprite being drawn making it spin
	 */
	public void updateSprite() {
		int tex = 0, tey = 0;
		int tew, teh;
		
		frame++;
		if (frame == 8)
			frame = 0;
		
		teh = tew = 31;
		tex = 1*frame+frame * 31;

		coinSprite.setRegion(tex, tey, tew, teh);

	}

}
