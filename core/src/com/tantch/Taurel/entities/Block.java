package com.tantch.Taurel.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tantch.Taurel.B2DVars;
import com.tantch.Taurel.screens.GameScreen;

public class Block extends Obstacle {

	public Block(World world, float x, float y, float size,
			OrthographicCamera camera, GameScreen screen, Texture text) {

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
		shape.setAsBox(SIZE, SIZE / 2f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_OBS;
		fixtureDef.filter.maskBits = B2DVars.BIT_FIBI | B2DVars.BIT_OBS
				| B2DVars.BIT_BIRD;
		fixtureDef.restitution = 0f;
		fixtureDef.friction = .8f;
		fixtureDef.density = 6;
		body = world.createBody(bodyDef);
		cloudSprite = new Sprite(text);
		fixture = body.createFixture(fixtureDef);
		body.setUserData(cloudSprite);
		cloudSprite.setSize(SIZE * 2.6f, SIZE*1.3f);
		cloudSprite.setOrigin(body.getLocalCenter().x, body.getLocalCenter().y);
		cloudSprite.setRegion(0, 0, 73, 22);
		body.setUserData(cloudSprite);
		fixture.setUserData("Block");
	}

}
