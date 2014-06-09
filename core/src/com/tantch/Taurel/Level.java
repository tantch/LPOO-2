package com.tantch.Taurel;

import java.util.Vector;

import com.badlogic.gdx.physics.box2d.World;
import com.tantch.Taurel.entities.Bird;
import com.tantch.Taurel.entities.Block;
import com.tantch.Taurel.entities.Cannon;
import com.tantch.Taurel.entities.Coin;
import com.tantch.Taurel.entities.Exit;
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
	public Exit exit;

	/**
	 * Creates a level instance ready to receive elements
	 * 
	 * @param world
	 *            world in which the bodies will be created
	 * @param screen
	 *            screen where the game is going to be played
	 * @param levelId
	 *            the id of the level
	 */
	public Level(World world, GameScreen screen, int levelId) {
		this.world = world;
		this.screen = screen;
		this.levelId = levelId;
		coins = new Vector<Coin>();
		minions = new Vector<Minion>();
		obstacles = new Vector<Obstacle>();
		cannons = new Vector<Cannon>();
		birds = new Vector<Bird>();
	}

	/**
	 * gets the level Id
	 * 
	 * @return the id of the level
	 */
	public int getLevelId() {
		return levelId;
	}

	/**
	 * Sets the current Level Id
	 * @param levelId the new level Id
	 */
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
/**
 * returns the coins in the level
 * @return the vector with the coin objects stored in the level
 */
	public Vector<Coin> getCoins() {
		return coins;
	}
/**
 * returns the minions in the level
 * @return the vector with the minion objects stored in the level
 */
	public Vector<Minion> getMinions() {
		return minions;
	}
/**
 * returns the blocks and walls in the level
 * @return the vector with the obstacles stored in the level
 */
	public Vector<Obstacle> getObstacles() {
		return obstacles;
	}
/**
 * returns the cannons in the level
 * @return the vector with the cannons stored in the level
 */
	public Vector<Cannon> getCannons() {
		return cannons;
	}
/**
 * returns the birds in the level
 * @return the vector with the birds stored in the level
 */
	public Vector<Bird> getBirds() {
		return birds;
	}
/**
 * adds a minion to the level
 * @param x horizontal center position
 * @param y vertical center positon
 * @param type type of minion to create
 * @param order follow order
 */
	public void addMinion(float x, float y, String type, int order) {

		minions.add(new Minion(world, x, y, type, screen.camera, order, screen,
				screen.fibiText));
	}
/**
 * adds a wall to the level
 * @param x the horizontal center position
 * @param y the vertical center position
 * @param size the size of the wall
 * @param vertical if it is vertical
 */
	public void addWall(float x, float y, float size, boolean vertical) {
		obstacles.add(new Wall(world, x, y, size, screen.camera, screen,
				vertical, screen.wallText));

	}
/**
 * adds a block to the level
 * @param x the horizontal center position
 * @param y the vertical center position
 * @param size the size of the block
 */
	public void addBlock(float x, float y, float size) {
		obstacles.add(new Block(world, x, y, size, screen.camera, screen,
				screen.wallText));
	}
/**
 * adds a cannon to the level
 * @param x the horizontal center position
 * @param y the vertical center position
 * @param ang the angler of the cannon
 */
	public void addCannon(float x, float y, float ang) {
		cannons.add(new Cannon(x, y, ang, world, screen.cannonText));
	}
/**
 * adds a coin to the level
 * @param x the horizontal center position
 * @param y the vertical center position
 */
	public void addCoin(float x, float y) {

		coins.add(new Coin(world, x, y, screen.coinText));
	}

	/**
	 * adds a bird to the level
	 * @param x the horizontal center position
	 * @param y the vertical center position
	 * @param vx the x component of the vector of the bird movement
	 * @param vy the y component of the vector of the bird movement
	 */
	public void addBird(float x, float y, float vx, float vy) {

		birds.add(new Bird(world, x, y, vx, vy, screen.birdText, screen));
	}
	
	/**
	 * creates the box which surround the level
	 * @param w the width of the box
	 * @param h the height of the box
	 */
	public void createBox(float w, float h) {
		obstacles.add(new Wall(world, -w / 2, 0, h, screen.camera, screen,
				true, screen.wallText));
		obstacles.add(new Wall(world, w / 2, 0, h, screen.camera, screen, true,
				screen.wallText));
		obstacles.add(new Wall(world, 0, -h / 2, w, screen.camera, screen,
				false, screen.wallText));
		obstacles.add(new Wall(world, 0, h / 2, w, screen.camera, screen,
				false, screen.wallText));

	}

	/**
	 * adds an exit to the level
	 * @param x the horizontal center position
	 * @param y the vertical center position
	 */
	public void addExit(float x, float y) {
		exit = new Exit(world, x, y, screen.exitText);
	}
/**
 * gets the exit of the level
 * @return the exit
 */
	public Exit getExit() {
		return exit;
	}

}
