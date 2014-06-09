package com.tantch.Taurel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.tantch.Taurel.entities.Bird;
import com.tantch.Taurel.screens.GameScreen;
import com.tantch.Taurel.screens.MainMenu;

public class UpdateTask extends Timer {
	public GameScreen screen;

	/**
	 * Initiates an object made for running functions with specified delay
	 * @param screen the screen in effect
	 */
	public UpdateTask(GameScreen screen) {
		super();
		this.screen = screen;
	}
	
	/**
	 * Removes the stun from a minion after a delay
	 * @param i the element of the minion
	 */
	public void deStun(final int i){
		Task tsk= new Task() {
			
			@Override
			public void run() {
				screen.deStun(i);
				
			}
		};
		
		schedule(tsk, 0.5f);
	}
	/**
	 * Inverts the direction the bird is flying
	 * @param bird the bird to invert 
	 * @param x the new horizontal component of the movement
	 * @param y the new vertical component of the movement
	 */
	public void changeDirection(final Bird bird,final float x,final float y){
Task tsk= new Task() {
			
			@Override
			public void run() {
				bird.moveTo(x, y);				
				
			}
		};
		schedule(tsk, 1.7f);
	}
	/**
	 * Returns the game to the main menu
	 */
	public void end(){
		Task tsk= new Task() {
			
			@Override
			public void run() {
			
				((Game) Gdx.app.getApplicationListener())
				.setScreen(new MainMenu());
	
				
			}
		};
		schedule(tsk,5f);
	}
	
}
