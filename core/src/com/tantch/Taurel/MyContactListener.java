package com.tantch.Taurel;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Fixture fa=contact.getFixtureA();		
		Fixture fb=contact.getFixtureB();	
		
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		Fixture fa=contact.getFixtureA();		
		Fixture fb=contact.getFixtureB();
		
		if(fa.getUserData().equals("Cannon")){
		//	fa.getBody().getPosition().x;
			fa.getBody().setAngularVelocity(-1);
		}
		
		if(fb.getUserData().equals("Cannon")){
			fb.getBody().setAngularVelocity(-1);
		}
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
			Fixture fa=contact.getFixtureA();		
			Fixture fb=contact.getFixtureB();

			if(fa.getUserData().equals("Cannon")){
				fa.getBody().setAngularVelocity(0);
			}
			
			if(fb.getUserData().equals("Cannon")){
				fb.getBody().setAngularVelocity(0);
			}
			
			
			
	}

}
