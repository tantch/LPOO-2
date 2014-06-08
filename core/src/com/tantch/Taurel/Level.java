package com.tantch.Taurel;



import java.util.Vector;

import com.badlogic.gdx.physics.box2d.World;
import com.tantch.Taurel.entities.Bird;
import com.tantch.Taurel.entities.Block;
import com.tantch.Taurel.entities.Cannon;
import com.tantch.Taurel.entities.Coin;
import com.tantch.Taurel.entities.Minion;
import com.tantch.Taurel.entities.Obstacle;
import com.tantch.Taurel.entities.Wall;
import com.tantch.Taurel.screens.GameScreen;

public class Level {
	
	int levelId;
	World world;
	GameScreen screen;
	private Vector<Coin> coins;
	private Vector<Minion> minions;
	private Vector<Obstacle> obstacles;
	public Vector<Cannon> cannons;
	public Vector<Bird> birds;

	public Level(World world, GameScreen screen, int levelId) {
		this.world = world;
		this.screen = screen;
		this.levelId=levelId;
		coins=new Vector<Coin>();
		minions=new Vector<Minion>();
		obstacles=new Vector<Obstacle>();
		cannons=new Vector<Cannon>();
		birds=new Vector<Bird>();
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public Vector<Coin> getCoins() {
		return coins;
	}

	public Vector<Minion> getMinions() {
		return minions;
	}

	public Vector<Obstacle> getObstacles() {
		return obstacles;
	}

	public Vector<Cannon> getCannons() {
		return cannons;
	}

	public Vector<Bird> getBirds() {
		return birds;
	}

	public void addMinion(float x, float y,String type, int order) {

		minions.add(new Minion(world, x, y,type, screen.camera, order, screen,
				screen.fibiText));
	}

	public void addWall(float x, float y, float size, boolean vertical) {
		obstacles.add(new Wall(world, x, y, size, screen.camera, screen,
				vertical));

	}

	public void addBlock(float x, float y, float size) {
		obstacles.add(new Block(world, x, y, size, screen.camera, screen,
				screen.blockText));
	}

	public void addCannon(float x, float y, float ang) {
		cannons.add(new Cannon(x, y, ang, world,screen.cannonText));
	}

	public void addCoin(float x, float y) {

		coins.add(new Coin(world, x, y,screen.coinText));
	}

	public void addBird(float x, float y, float vx, float vy) {

		birds.add(new Bird(world, x, y, vx, vy, screen.birdText, screen));
	}

	public void createBox(float w, float h) {
		obstacles
				.add(new Wall(world, -w / 2, 0, h, screen.camera, screen, true));
		obstacles
				.add(new Wall(world, w / 2, 0, h, screen.camera, screen, true));
		obstacles.add(new Wall(world, 0, -h / 2, w, screen.camera, screen,
				false));
		obstacles
				.add(new Wall(world, 0, h / 2, w, screen.camera, screen, false));

	}

}
