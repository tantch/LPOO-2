package com.tantch.Taurel.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tantch.Taurel.B2DVars;
import com.tantch.Taurel.screens.GameScreen;
import com.tantch.utilities.MathUtilities;

public class Minion {

	public Body body;
	String type;
	private Fixture fixture;
	private GameScreen screen;
	public Vector2 velocity = new Vector2();
	private float movementForce = 60;
	private float screenW, screenH;
	private OrthographicCamera camera;
	private float minDis = (float) 7.4;
	private float cx, cy;
	private boolean stunned = false;
	private int order;
	private int frame = 0;
	boolean right=true;
	private Sprite minionSprite;

	public Minion(World world, float x, float y, String type,
			OrthographicCamera camera, int order, GameScreen screen,
			Texture text) {
		this.type = type;
		cx = x;
		cy = y;
		float size = 0;
		this.screen = screen;
		this.order = order;
		this.camera = camera;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;
		if (type.equals("Normal"))
			size = 3;
		else if (type.equals("Guardian"))
			size = 4.5f;
		CircleShape shape = new CircleShape();

		shape.setRadius(size / 2);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = B2DVars.BIT_FIBI;
		fixtureDef.filter.maskBits = B2DVars.BIT_OBS | B2DVars.BIT_CANNON
				| B2DVars.BIT_PICKUP | B2DVars.BIT_BIRD;
		fixtureDef.restitution = 0.2f;
		fixtureDef.friction = .8f;
		fixtureDef.density = 3;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
		minionSprite = new Sprite(text);
		updateSprite();
		minionSprite.setSize(size, size);
		body.setUserData(minionSprite);
		if (type.equals("Normal"))
			fixture.setUserData("Minion " + order);
		else if (type.equals("Guardian"))
			fixture.setUserData("Guardian Minion " + order);

	}

	public void updateSprite() {
		int tex = 0, tey = 0;
		int tew, teh;
		int iy=0;
		frame++;
		if (frame == 12)
			frame = 0;
		if(type.equals("Normal"))
			iy=4;
		else if(type.equals("Guardian"))
			iy=106;
		teh = tew = 32;
		tex = (frame % 4) * 32 + 1;
		tey = iy + (frame / 4) * 32;

		if(velocity.x>0 && !right)
			right=true;
		
		if(velocity.x<0 && right)
			right=false;
		
		if(right)
		minionSprite.setRegion(tex+tew, tey, -tew, teh);
		else
			minionSprite.setRegion(tex, tey, tew, teh);
			
	}

	public void setOrder(int i) {
		order = i;
		if (type.equals("Normal"))
			fixture.setUserData("Minion " + order);
		else if (type.equals("Guardian"))
			fixture.setUserData("Guardian Minion " + order);
	}

	public void updateFollow(float cx, float cy) {
		this.cx = cx;
		this.cy = cy;
	}

	public void deStun() {
		stunned = false;
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
		if (!stunned)
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
		stunned = true;

	}

}
