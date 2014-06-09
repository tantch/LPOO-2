package com.tantch.Taurel.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.tantch.Taurel.HighScore;
import com.tantch.Taurel.Level;
import com.tantch.Taurel.MyContactListener;
import com.tantch.Taurel.MyInputProcessor;
import com.tantch.Taurel.Player;
import com.tantch.Taurel.UpdateTask;
import com.tantch.Taurel.entities.Bird;
import com.tantch.Taurel.entities.BtCan;
import com.tantch.Taurel.entities.Cannon;
import com.tantch.Taurel.entities.Coin;
import com.tantch.Taurel.entities.Minion;

public class GameScreen implements Screen {
	public int coins = 0;
	private float GRAV = 20f;
	public World world;
	private Level level;
	private int lvl;
	private Player player;
	private Box2DDebugRenderer debugRenderer;
	private SpriteBatch batch;
	public OrthographicCamera camera;
	private boolean pause = false;
	public Texture backgroundText, fibiText, blockText, coinText, cannonText,
			arrowText, birdText, wallText, exitText;
	private float sgns = 0, tempo = 0;
	private float TIMESTEP = 1 / 60f;
	private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
	private Array<Body> tmpBodies = new Array<Body>();

	public GameScreen(Player player, int level2) {
		this.player = player;
		lvl=level2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);

		sgns++;
		if (sgns == 60) {
			sgns = 0;
			tempo++;
		}
		if (sgns % 30 == 0) {
			for (Cannon cannon : level.getCannons()) {
				cannon.fireArrow(arrowText);
			}

		}
		if (sgns % 5 == 0) {
			for (Minion minion : level.getMinions()) {
				minion.updateSprite();
			}
			for (Coin coin : level.getCoins()) {
				coin.updateSprite();
			}
			for (Bird bird : level.getBirds()) {
				bird.updateSprite();
				level.getExit().updateSprite();
			}
		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		moveAll();

		for (int i = 0; i < level.getMinions().size(); i++) {
			level.getMinions().get(i).update();

		}
		for (Bird bird : level.getBirds()) {
			bird.update();
		}

		// move camera
		if (level.getMinions().size() > 0)
			camera.position.set(level.getMinions().get(0).body.getPosition().x,
					0, 0);
		camera.update();

		// draw
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		Sprite spr = new Sprite(backgroundText);
		spr.setCenter(0, 29);
		spr.setScale(0.3f);
		spr.draw(batch);
		world.getBodies(tmpBodies);
		for (Body body : tmpBodies) {
			if (body.getUserData() instanceof String) {
				world.destroyBody(body);
			}

			if (body.getUserData() instanceof Sprite) {
				Sprite sprite = (Sprite) body.getUserData();
				sprite.setCenter(body.getPosition().x, body.getPosition().y);
				sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
				sprite.draw(batch);
			}
		}
		batch.end();
		// debugRenderer.render(world, camera.combined);

	}

	@Override
	public void resize(int width, int height) {
		double w = width * 72 / height;
		camera.viewportWidth = (float) w;
		camera.viewportHeight = 72;

	}

	public Minion getMainMinion() {
		if (level.getMinions().size() > 0)
			return level.getMinions().get(0);
		return null;
	}

	public void deStun(int elem) {
		if (elem >= level.getMinions().size()) {
			for (Minion minion : level.getMinions())
				minion.deStun();
			return;
		}
		level.getMinions().get(elem).deStun();
	}

	public void stun(int elem) {
		level.getMinions().get(elem).stun();
	}

	public void moveAll() {
		for (int i = 1; i < level.getMinions().size(); i++) {
			level.getMinions()
					.get(i)
					.updateFollow(
							level.getMinions().get(i - 1).body.getPosition().x,
							level.getMinions().get(i - 1).body.getPosition().y);
		}
	}

	public void deleteMinion(int j) {
		level.getMinions().remove(j - 1);
		for (int i = 1; i <= level.getMinions().size(); i++) {
			level.getMinions().get(i - 1).setOrder(i);
		}
	}

	@Override
	public void show() {
		batch = new SpriteBatch();

		world = new World(new Vector2(0, -GRAV), true);
		backgroundText = new Texture("img/Untitled-3.png");
		fibiText = new Texture("img/bubbles.png");
		coinText = new Texture("img/gold.png");
		cannonText = new Texture("img/cannon.png");
		arrowText = new Texture("img/arrow.png");
		birdText = new Texture("img/bird.png");
		wallText = new Texture("img/wall.png");
		exitText = new Texture("img/exit.png");
		debugRenderer = new Box2DDebugRenderer();
		double w = Gdx.graphics.getWidth() * 72 / Gdx.graphics.getHeight();
		camera = new OrthographicCamera((float) w, 72);

		level = new Level(world, this, lvl);
		loadLevel();
		Gdx.input.setInputProcessor(new MyInputProcessor(this));
		world.setContactListener(new MyContactListener(this));
	}

	public void loadLevel() {
		switch (level.getLevelId()) {

		case 1:
			level.createBox(500, 72);
			level.addMinion(-200, 20, "Normal", 1);
			level.addMinion(-200, 20, "Guardian", 2);
			level.addMinion(-200, 20, "Shield", 3);
			level.addCoin(-200, 30);
			level.addBlock(-190, 25, 3);
			level.addCannon(-200, -30, 0f);
			level.addBird(-150, 30, 0, -30);
			level.addExit(100, 20);

			break;

		}
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		TIMESTEP = 0;
		pause = true;
	}

	@Override
	public void resume() {
		TIMESTEP = 1 / 60f;
		pause = false;
	}

	@Override
	public void dispose() {
		batch.dispose();
		backgroundText.dispose();
		fibiText.dispose();
		blockText.dispose();
		coinText.dispose();
		cannonText.dispose();
		arrowText.dispose();
		birdText.dispose();
		wallText.dispose();
	}

	public void rotateCannons() {
		for (Cannon cannon : level.cannons) {
			cannon.body.setAngularVelocity(1);
		}

	}

	public void stopCannon() {
		for (Cannon cannon : level.cannons) {
			cannon.body.setAngularVelocity(0);
		}
	}

	public void end() {
		pause();
		float score = coins * 10 + level.getMinions().size() * 10 + 1000
				- tempo;
		player.addScore(new HighScore(level.getLevelId(), score));
		UpdateTask task = new UpdateTask(this);
		Json json = new Json();
		FileHandle file = Gdx.files.local("player.fw");
		file.writeString(json.toJson(player), false);
		task.end();
		((Game) Gdx.app.getApplicationListener())
		.setScreen(new RoundScore(score));

	}
}
