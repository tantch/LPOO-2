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
import com.tantch.Taurel.entities.Block;
import com.tantch.Taurel.entities.Cannon;
import com.tantch.Taurel.entities.Minion;
import com.tantch.Taurel.entities.Obstacle;
import com.tantch.Taurel.entities.Wall;

public class MovementTestScreen implements Screen {

	private World world;
	private Vector<Vector2> blockPos;
	private Box2DDebugRenderer debugRenderer;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Vector<Minion> minions;
	private Vector<Obstacle> obstacles;
	private final float TIMESTEP = 1 / 60f;
	private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
	private int minionsNumb = 3;
	private Array<Body> tmpBodies = new Array<Body>();

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		moveAll();
		world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);

		for (int i = 0; i < minionsNumb; i++) {
			minions.elementAt(i).update();
		}
		camera.position.set(minions.elementAt(0).body.getPosition().x,
				Gdx.graphics.getHeight()/10-70, 0);
		camera.update();
		debugRenderer.render(world, camera.combined);
		/*
		 * batch.setProjectionMatrix(camera.combined); batch.begin();
		 * world.getBodies(tmpBodies); for(Body body : tmpBodies)
		 * if(body.getUserData() instanceof Sprite) { Sprite sprite = (Sprite)
		 * body.getUserData(); sprite.setSize(4, 4);
		 * sprite.setPosition(body.getPosition().x -2f, body.getPosition().y
		 * -2f); sprite.setRotation(body.getAngle() *
		 * MathUtils.radiansToDegrees); sprite.draw(batch); } batch.end();
		 */
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width/10;
		camera.viewportHeight = height/10;

	}

	public void moveAll() {
		for (int i = 1; i < minionsNumb; i++) {
			minions.elementAt(i).updateFollow(
					minions.elementAt(i - 1).body.getPosition().x,
					minions.elementAt(i - 1).body.getPosition().y);
		}
	}

	private void createBox(float w,float h){
		obstacles.add(new Wall(world, -w/2, 0, h, camera, this,true));
		obstacles.add(new Wall(world, w/2, 0, h, camera, this,true));
		obstacles.add(new Wall(world, 0, -h/2, w, camera, this,false));
		obstacles.add(new Wall(world, 0, h/2, w, camera, this,false));
		
	}
	
	private void setLevel(int level){
		switch(level){
		case 0:
			
			blockPos.add(new Vector2(-180, 10));
			blockPos.add(new Vector2(-170, 10));
			blockPos.add(new Vector2(-160, 10));
			blockPos.add(new Vector2(-150, 10));
			blockPos.add(new Vector2(-140, 10));
			blockPos.add(new Vector2(-130, 20));
			blockPos.add(new Vector2(-120, 10));
			blockPos.add(new Vector2(-110, 0));
			break;
		}
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		minions = new Vector<Minion>();
		obstacles = new Vector<Obstacle>();
		
		world = new World(new Vector2(0, -9.81f), true);
		
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth()/10,
				Gdx.graphics.getHeight()/10);
		minions.add(new Minion(world, -200, 0, 2, camera, 1, this));
		for (int i = 2; i <= minionsNumb; i++) {
			minions.add(new Minion(world, -200, 0, 2, camera, i, this));
		}
		blockPos= new Vector<Vector2>();
		setLevel(0);
		for(int i=0;i<blockPos.size();i++){
		obstacles.add(new Block(world, blockPos.elementAt(i).x, blockPos.elementAt(i).y, 3, camera, this));
		}
		new Cannon(0,0,world);
	
		
		
		
		createBox(500, 60);
		Gdx.input.setInputProcessor(minions.elementAt(0));
		world.setContactListener(new MyContactListener());
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
