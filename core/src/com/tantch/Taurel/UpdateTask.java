package com.tantch.Taurel;

import com.badlogic.gdx.utils.Timer;
import com.tantch.Taurel.screens.MovementTestScreen;

public class UpdateTask extends Timer {
	public MovementTestScreen screen;

	public UpdateTask(MovementTestScreen screen) {
		super();
		this.screen = screen;
	}

	public void deStun(final int i){
		Task tsk= new Task() {
			
			@Override
			public void run() {
				screen.deStun(i);
				
			}
		};
		
		schedule(tsk, 0.5f);
	}
	
}
