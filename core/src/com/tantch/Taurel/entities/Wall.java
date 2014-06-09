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

public class Wall extends Obstacle {

	public Wall(World world, float x, float y, float size,
			OrthographicCamera camera, GameScreen screen, boolean vertical,
			Texture wallText) {

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
		if (vertical){
			shape.setAsBox(3, SIZE / 2f);
		System.out.println(SIZE);
	}		else
			shape.setAsBox(SIZE / 2f, 3);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_OBS;
		fixtureDef.filter.maskBits = B2DVars.BIT_FIBI | B2DVars.BIT_BIRD
				| B2DVars.BIT_OBS;
		fixtureDef.restitution = 0f;
		fixtureDef.friction = 1f;
		fixtureDef.density = 6;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData("Wall");
		cloudSprite = new Sprite(wallText);
		cloudSprite.setRegion(0, 0, 465, 22);
		if (vertical) {
			cloudSprite.setSize(7, size);
			cloudSprite.rotate90(false);

		}
		else
			cloudSprite.setSize(size, 7);

		body.setUserData(cloudSprite);
	}

}
