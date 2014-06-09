package com.tantch.Taurel;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.tantch.Taurel.screens.GameScreen;

public class MyInputProcessor implements InputProcessor {
	GameScreen screen;

	/**
	 * initializes the input processor
	 * @param screen the screen where it is activated
	 */
	public MyInputProcessor(GameScreen screen) {
		this.screen = screen;
	}

	@Override
	public boolean scrolled(int amount) {

		screen.camera.zoom += amount / 25f;
		screen.camera.update();
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (screen.getMainMinion() != null)
			screen.getMainMinion().moveTo(screenX, screenY, true);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (screen.getMainMinion() != null)
			screen.getMainMinion().moveTo(screenX, screenY, true);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (screen.getMainMinion() != null) {
			screen.getMainMinion().velocity.x = 0;
			screen.getMainMinion().velocity.y = 0;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

}
