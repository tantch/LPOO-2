package com.tantch.Taurel.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tantch.Taurel.tween.SpriteAcessor;

public class Splash implements Screen {

	private Sprite splash;
	private SpriteBatch batch;
	private TweenManager tweenManager;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		splash.draw(batch);
		batch.end();
		tweenManager.update(delta);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

		batch = new SpriteBatch();

		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAcessor());

		Texture splashTexture = new Texture("img/splash.png");
		splash = new Sprite(splashTexture);
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Tween.set(splash, SpriteAcessor.ALPHA).target(0).start(tweenManager);
		Tween.to(splash, SpriteAcessor.ALPHA, 1.5f).target(1)
				.repeatYoyo(1, .5f).setCallback(new TweenCallback() {

					@Override
					public void onEvent(int type, BaseTween<?> source) {
						((Game) Gdx.app.getApplicationListener())
								.setScreen(new MainMenu());
					}
				}).start(tweenManager);

		tweenManager.update(Float.MIN_VALUE);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		splash.getTexture().dispose();
		batch.dispose();
	}

}
