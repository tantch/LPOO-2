package com.tantch.Taurel.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tantch.Taurel.B2DVars;
import com.tantch.Taurel.screens.MovementTestScreen;
import com.tantch.utilities.MathUtilities;

public class Minion {

	public Body body;
	private Fixture fixture;
	private MovementTestScreen screen;
	public final float SIZE;
	public Vector2 velocity = new Vector2();
	private float movementForce = 50;
	private float screenW, screenH;
	private OrthographicCamera camera;
	private float minDis = (float) 5.4;
	private float cx, cy;
	private boolean stunned=false;
	private int order;
	private static Sprite minionSprite;

	public Minion(World world, float x, float y, float size,
			OrthographicCamera camera, int order, MovementTestScreen screen) {

		cx = x;
		cy = y;
		SIZE = size;
		this.screen = screen;
		this.order = order;
		this.camera = camera;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(SIZE / 2f, SIZE / 2f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_FIBI;
		fixtureDef.filter.maskBits = B2DVars.BIT_OBS | B2DVars.BIT_CANNON | B2DVars.BIT_PICKUP;
		fixtureDef.restitution = 0.2f;
		fixtureDef.friction = .8f;
		fixtureDef.density = 3;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
		minionSprite = new Sprite(new Texture("img/mag.jpg"));

		body.setUserData(minionSprite);
		fixture.setUserData("Minion " + order);
	}

	public void setOrder(int i) {
		order = i;
		fixture.setUserData("Minion " + order);
	}

	public void updateFollow(float cx, float cy) {
		this.cx = cx;
		this.cy = cy;
	}
	public void deStun(){
		stunned=false;
	}

	public void update() {

		body.applyForceToCenter(new Vector2(0, 20f * body.getMass()), true);
		float bx = body.getPosition().x;
		float by = body.getPosition().y;
		float dif = 0;
		if (order != 1) {
			moveTo(cx, cy, false);

		} else
			dif = 5f;

		if (MathUtilities.getDis(cx, cy, bx, by) < (minDis - dif)) {
			velocity.x = 0;
			velocity.y = 0;
		}
		if(!stunned)
		body.setLinearVelocity(velocity);
	}

	public void moveTo(float screenX, float screenY, boolean conv) {
		MathUtilities mthu = new MathUtilities();
		Vector2 vetm = body.getPosition();
		Vector3 v = new Vector3(screenX, screenY, 0);
		if (conv)
			camera.unproject(v);

		cx = v.x;
		cy = v.y;
		float bx = body.getPosition().x;
		float by = body.getPosition().y;

		double ang = mthu.GetAngleOfLineBetweenTwoPoints(bx, by, cx, cy);
		ang = Math.toRadians(ang);

		velocity.x = (float) (Math.cos(ang) * movementForce);
		velocity.y = (float) (Math.sin(ang) * movementForce);
	}

	public void stun() {
		stunned=true;
		
	}

}
