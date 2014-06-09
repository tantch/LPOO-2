package com.tantch.Taurel.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tantch.Taurel.B2DVars;
import com.tantch.Taurel.UpdateTask;
import com.tantch.Taurel.screens.GameScreen;
import com.tantch.utilities.MathUtilities;

public class Bird {
	private UpdateTask task;
	public Body body;
	private Fixture fixture;
	private int frame = 0;
	boolean right = true;
	public Vector2 velocity = new Vector2();
	private float movementForce = 20;
	private Sprite birdSprite;
/**
 * Creates a bird and initializes it in the world
 * @param world the world where it is created
 * @param x the horizontal center position
 * @param y the vertical center position
 * @param bx the x component of the movement vector
 * @param by the y component of the movement vector
 * @param text the bird texture image 
 * @param screen the screen being played
 */
	public Bird(World world, float x, float y, float bx, float by,
			Texture text, GameScreen screen) {

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(3 / 2f, 3 / 2f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_BIRD;
		fixtureDef.filter.maskBits = B2DVars.BIT_OBS | B2DVars.BIT_CANNON
				| B2DVars.BIT_FIBI;
		fixtureDef.restitution = 0.2f;
		fixtureDef.friction = .8f;
		fixtureDef.density = 3;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
		birdSprite = new Sprite(text);
		birdSprite.setSize(4.5f, 3.5f);
		updateSprite();
		body.setUserData(birdSprite);
		fixture.setUserData("Bird");
		task = new UpdateTask(screen);
		moveTo(bx, by);
	}
/*
 * updates the sprite depending on the current frame
 */
	public void updateSprite() {
		int tex = 0, tey = 0;
		int tew = 46, teh = 31;
		int iy = 3;
		frame++;
		if (frame == 4)
			frame = 0;
		tey = 0;
		switch (frame) {
		case 0:
			tex = 0;
			break;
		case 1:
			tex = 52;
			break;
		case 2:
			tex = 100;
			break;
		case 3:
			tex = 150;
			break;
		}
		if (velocity.x > 0 && !right)
			right = true;

		if (velocity.x < 0 && right)
			right = false;

		if (right)
			birdSprite.setRegion(tex, tey, tew, teh);
		else
			birdSprite.setRegion(tex + tew, tey, -tew, teh);
	}
/**
 * applies a constant force to make the bird fly
 */
	public void update() {

		body.applyForceToCenter(new Vector2(0, 20f * body.getMass()), true);
	}
/**
 * Moves the bird according the given movement vector
 * @param cx the horizontal component of the movement vector
 * @param cy the vertical component of the movement vector
 */
	public void moveTo(float cx, float cy) {
		float bx = body.getPosition().x;
		float by = body.getPosition().y;

		double ang = MathUtilities.GetAngleOfLineBetweenTwoPoints(bx, by, cx,
				cy);
		ang = Math.toRadians(ang);

		velocity.x = (float) (Math.cos(ang) * movementForce);
		velocity.y = (float) (Math.sin(ang) * movementForce);
		body.setLinearVelocity(velocity);
		task.changeDirection(this, bx, by);

	}

}
