package com.tantch.Taurel;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tantch.Taurel.screens.GameScreen;

public class MyContactListener implements ContactListener, ContactFilter {
	private GameScreen screen;

	/*
	 * Saves screen where it is initiated so that it can change it's variables
	 */
	public MyContactListener(GameScreen screen) {
		this.screen = screen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#beginContact(com.badlogic
	 * .gdx.physics.box2d.Contact)
	 */
	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		String sta = (String) fa.getUserData();
		String stb = (String) fb.getUserData();

		if (sta.contains("Minion") && stb.equals("Exit"))
			minionReachExit();
		else if (stb.contains("Minion") && sta.equals("Exit"))
			minionReachExit();
		else if (sta.contains("Minion") && stb.equals("Button")) {
			activateButton();
		} else if (stb.contains("Minion") && sta.equals("Button")) {
			activateButton();
		} else if (sta.contains("Minion") && stb.equals("Coin")) {
			catchCoin(fa, fb);
		} else if (stb.contains("Minion") && sta.equals("Coin")) {
			catchCoin(fb, fa);
		} else if (sta.contains("Minion") && stb.contains("Bird")) {
			birdAttackMinion(fa, fb);
		} else if (stb.contains("Minion") && sta.contains("Bird")) {
			birdAttackMinion(fb, fa);
		} else if (sta.contains("Minion")
				&& (stb.contains("Wall") || stb.contains("Block"))) {
			minionHitWall(fa, fb);
		} else if (stb.contains("Minion")
				&& (sta.contains("Wall") || sta.contains("Block"))) {
			minionHitWall(fb, fa);
		} else if (sta.contains("Minion") && stb.equals("Metal")) {
			arrowHitMinion(fa, fb);
		} else if (stb.contains("Minion") && sta.equals("Metal")) {
			arrowHitMinion(fb, fa);
		} else {
			if ((sta.contains("Stick") || sta.equals("Metal"))
					&& !stb.equals("Cannon")) {
				arrowHitWall(fa);
			}
			if ((stb.contains("Stick") || stb.equals("Metal"))
					&& !sta.equals("Cannon")) {
				arrowHitWall(fb);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#endContact(com.badlogic
	 * .gdx.physics.box2d.Contact)
	 */
	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#preSolve(com.badlogic.
	 * gdx.physics.box2d.Contact, com.badlogic.gdx.physics.box2d.Manifold)
	 */
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stubo
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#postSolve(com.badlogic
	 * .gdx.physics.box2d.Contact,
	 * com.badlogic.gdx.physics.box2d.ContactImpulse)
	 */
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

	}

	/*
	 * Manages collision of arrows with Walls and obstacles
	 */

	public void arrowHitWall(Fixture arrow) {
		arrow.getBody().setUserData("delete");
	}

	/*
	 * Manages collision between arrows and minions
	 */
	public void arrowHitMinion(Fixture minion, Fixture arrow) {

		arrow.getBody().setUserData("delete");
		if (!((String) minion.getUserData()).contains("Guardian")) {
			minion.getBody().setUserData("delete");
			String s = (String) minion.getUserData();
			screen.deleteMinion(Integer.parseInt(s.replaceAll("[\\D]", "")));
		}
	}

	public void activateButton() {
		screen.rotateCannons();
	}

	/*
	 * Manages the colision of a minion catching a coin
	 */
	public void catchCoin(Fixture Minion, Fixture Coin) {

		Coin.getBody().setUserData("delete");
		screen.coins++;

	}

	/*
	 * Manages the colision of a minion hitting the wall which breafly stuns him
	 */
	public void minionHitWall(Fixture minion, Fixture wall) {
		String stmp = (String) minion.getUserData();
		screen.stun(Integer.parseInt(stmp.replaceAll("[\\D]", "")) - 1);
		UpdateTask upd = new UpdateTask(screen);
		upd.deStun(Integer.parseInt(stmp.replaceAll("[\\D]", "")) - 1);

	}

	public void minionReachExit() {
		screen.end();
	}

	/*
	 * Manages the rotation of the cannons
	 */
	public void moveCannon(Fixture Cannon, boolean clock, boolean move) {
		if (!move) {
			Cannon.getBody().setAngularVelocity(0);

			return;
		}

		if (clock)
			Cannon.getBody().setAngularVelocity(-2);
		else
			Cannon.getBody().setAngularVelocity(2);

	}

	public void birdAttackMinion(Fixture minion, Fixture bird) {
		if (!((String) minion.getUserData()).contains("Shield")) {
			minion.getBody().setUserData("delete");
			String s = (String) minion.getUserData();
			screen.deleteMinion(Integer.parseInt(s.replaceAll("[\\D]", "")));
		}
	}

	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		// TODO Auto-generated method stub
		return false;
	}
}
