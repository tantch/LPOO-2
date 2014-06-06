package com.tantch.Taurel;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tantch.Taurel.screens.MovementTestScreen;

public class MyContactListener implements ContactListener {
	private MovementTestScreen screen;

	public MyContactListener(MovementTestScreen screen) {
		this.screen = screen;
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		String sta = (String) fa.getUserData();
		String stb = (String) fb.getUserData();

		if (sta.contains("Minion") && stb.contains("Wall")) {
			minionHitWall(fa, fb);
		} else if (stb.contains("Minion") && sta.contains("Wall")) {
			minionHitWall(fb, fa);
		} else if (sta.contains("Minion") && stb.contains("Stick")) {
			arrowHitMinion(fa, fb);
		} else if (stb.contains("Minion") && sta.contains("Stick")) {
			arrowHitMinion(fb, fa);
		} else {
			if (sta.equals("Stick") && !stb.equals("Cannon")) {
				arrowHitWall(fa);
			}
			if (stb.equals("Stick") && !sta.equals("Cannon")) {
				arrowHitWall(fb);
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stubo
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (((String) fa.getUserData()).contains("Minion")
				&& fb.getUserData().equals("Coin")) {
			catchCoin(fa, fb);
		} else if (((String) fb.getUserData()).contains("Minion")
				&& fa.getUserData().equals("Coin")) {
			catchCoin(fb, fa);
		}

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

	}

	public void arrowHitWall(Fixture arrow) {
		arrow.getBody().setUserData("delete");
	}

	public void arrowHitMinion(Fixture minion, Fixture arrow) {

		arrow.getBody().setUserData("delete");
		minion.getBody().setUserData("delete");
		String s = (String) minion.getUserData();
		screen.deleteMinion(Integer.parseInt(s.replaceAll("[\\D]", "")));
	}

	public void catchCoin(Fixture Minion, Fixture Coin) {

		Coin.getBody().setUserData("delete");

	}
	public void minionHitWall(Fixture minion,Fixture wall){
		String stmp = (String) minion.getUserData();
		screen.stun(Integer.parseInt(stmp.replaceAll("[\\D]", "")) - 1);
		UpdateTask upd = new UpdateTask(screen);
		upd.deStun(Integer.parseInt(stmp.replaceAll("[\\D]", "")) - 1);
		
	}
	
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
}
