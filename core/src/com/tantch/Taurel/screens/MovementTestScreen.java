package com.tantch.Taurel.screens;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tantch.Taurel.MyContactListener;
import com.tantch.Taurel.MyInputProcessor;
import com.tantch.Taurel.entities.Block;
import com.tantch.Taurel.entities.Cannon;
import com.tantch.Taurel.entities.Coin;
import com.tantch.Taurel.entities.Minion;
import com.tantch.Taurel.entities.Obstacle;
import com.tantch.Taurel.entities.Wall;

public class MovementTestScreen implements Screen {
	private float GRAV = 20f;
	public World world;
	private Box2DDebugRenderer debugRenderer;
	private SpriteBatch batch;
	public OrthographicCamera camera;
	private Vector<Minion> minions;
	private Vector<Obstacle> obstacles;
	public Vector<Cannon> cannons;
	private float sgns = 0;
	private final float TIMESTEP = 1 / 60f;
	private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
	private int minionsNumb = 3;
	private Array<Body> tmpBodies = new Array<Body>();

	@Override
	public void render(float delta) {
		sgns++;
		if (sgns == 30) {
			for (Cannon cannon : cannons)
				cannon.fireArrow();
			sgns = 0;
		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		moveAll();
		world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
	
		for (int i = 0; i < minions.size(); i++) {
			minions.elementAt(i).update();
		}
		if (minions.size() > 0)
			camera.position
					.set(minions.elementAt(0).body.getPosition().x, 0, 0);
		camera.update();
		debugRenderer.render(world, camera.combined);

		// batch.setProjectionMatrix(camera.combined);
		// batch.begin();
		world.getBodies(tmpBodies);
		for (Body body : tmpBodies) {
			if (body.getUserData() instanceof String) {
				world.destroyBody(body);
			}
		}
		// if (body.getUserData() instanceof Sprite) {
		// Sprite sprite = (Sprite) body.getUserData();
		// sprite.setSize(4, 4);
		// sprite.setPosition(body.getPosition().x - 2f,
		// body.getPosition().y - 2f);
		// sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
		// sprite.draw(batch);
		// }
		// batch.end();

	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width / 10;
		camera.viewportHeight = height / 10;

	}

	public Minion getMainMinion() {
		if (minions.size() > 0)
			return minions.elementAt(0);
		return null;
	}

	public void deStun(int elem){
		minions.elementAt(elem).deStun();
	}
	public void stun(int elem){
		minions.elementAt(elem).stun();
	}
	public void moveAll() {
		for (int i = 1; i < minions.size(); i++) {
			minions.elementAt(i).updateFollow(
					minions.elementAt(i - 1).body.getPosition().x,
					minions.elementAt(i - 1).body.getPosition().y);
		}
	}

	private void createBox(float w, float h) {
		obstacles.add(new Wall(world, -w / 2, 0, h, camera, this, true));
		obstacles.add(new Wall(world, w / 2, 0, h, camera, this, true));
		obstacles.add(new Wall(world, 0, -h / 2, w, camera, this, false));
		obstacles.add(new Wall(world, 0, h / 2, w, camera, this, false));

	}

	public void deleteMinion(int j) {
		System.out.println("deleting minion " + j);
		minions.remove(j - 1);
		for (int i = 1; i <= minions.size(); i++) {
			minions.elementAt(i - 1).setOrder(i);
		}
	}

	private void setLevel(int level) {
		switch (level) {
		case 0:
			createBox(500, 72);
			obstacles.add(new Wall(world,-220,11,50,camera,this,true));
			obstacles.add(new Block(world,-200,0,3,camera,this));
			cannons.add(new Cannon(-240,-29,(float)-0.9,world));
			new Coin(world, -230, 20);
			new Coin(world, -230, 15);
			new Coin(world, -230, 10);
			new Coin(world, -230, 5);
			new Coin(world, -230, 0);
			new Coin(world, -230, -5);
			new Coin(world, -230, -10);
			new Coin(world, -230, -15);
			new Coin(world, -230, -20);
			new Coin(world, -230, -25);
			
			break;
		}
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		minions = new Vector<Minion>();
		obstacles = new Vector<Obstacle>();
		cannons = new Vector<Cannon>();
		world = new World(new Vector2(0, -GRAV), true);

		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth() / 10,
				Gdx.graphics.getHeight() / 10);
		minions.add(new Minion(world, -230, 30, 2, camera, 1, this));
		for (int i = 2; i <= minionsNumb; i++) {
			minions.add(new Minion(world, -230, 30, 2, camera, i, this));
		}
		
		setLevel(0);
		
		Gdx.input.setInputProcessor(new MyInputProcessor(this));
		world.setContactListener(new MyContactListener(this));
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
