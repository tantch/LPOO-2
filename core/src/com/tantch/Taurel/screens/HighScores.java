package com.tantch.Taurel.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.tantch.Taurel.HighScore;
import com.tantch.Taurel.Player;
import com.tantch.Taurel.tween.ActorAccessor;

public class HighScores implements Screen {
	private Stage stage;
	private Table table;
	private Skin skin;
	private Player player;
	private TweenManager tweenManager;

	public HighScores(Player player2) {
		player = player2;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
		tweenManager.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),
				new TextureAtlas("ui/atlas.pack"));

		table = new Table(skin);
		table.setFillParent(true);

		// setting header
		Label heading = new Label("Highscores", skin, "big");
		heading.setFontScale(2);

		List list = new List(skin);
		Array<HighScore> scores = new Array<HighScore>();
		for (HighScore highscore : player.highscores) {
			scores.add(highscore);
		}
		list.setItems(scores);

		ScrollPane scrollPane = new ScrollPane(list, skin);

		TextButton buttonExit = new TextButton("EXIT", skin, "big");
		buttonExit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Timeline.createParallel()
						.beginParallel()
						.push(Tween.to(table, ActorAccessor.ALPHA, .75f)
								.target(0))
						.push(Tween.to(table, ActorAccessor.Y, .75f)
								.target(table.getY() - 50)
								.setCallback(new TweenCallback() {

									@Override
									public void onEvent(int type,
											BaseTween<?> source) {

										((Game) Gdx.app
												.getApplicationListener())
												.setScreen(new MainMenu());
									}
								})).end().start(tweenManager);
			}
		});
		buttonExit.pad(15);

		table.add(heading).colspan(3).expandX().spaceBottom(50).row();
		table.add(scrollPane).expandX().expandY().top().center();
		table.add(buttonExit).bottom().right();

		if (Gdx.app.getType() == ApplicationType.Android) {
			table.setTransform(true);
			table.setOrigin(Gdx.graphics.getWidth() / 2,
					Gdx.graphics.getHeight() / 2);
			table.setScale((float) (Gdx.graphics.getWidth() / 1000.0));
		}
		stage.addActor(table);
		// creating animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());

		// heading and buttons fade-in
		Timeline.createSequence()
				.beginSequence()
				.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
				.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
				.push(Tween.to(buttonExit, ActorAccessor.ALPHA, .25f).target(1))
				.end().start(tweenManager);

		// table fade-in
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0)
				.start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f)
				.target(Gdx.graphics.getHeight() / 8).start(tweenManager);

		tweenManager.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
