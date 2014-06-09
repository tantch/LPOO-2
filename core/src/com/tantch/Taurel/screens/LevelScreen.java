package com.tantch.Taurel.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tantch.Taurel.Player;

public class LevelScreen implements Screen {

	private Table table;
	private Stage stage;
	private Skin skin;
	private TextButton button;
	private Player player;

	/**
	 * creates a map with the levels and the player access permittions
	 * 
	 * @param player
	 */
	public LevelScreen(Player player) {
		this.player = player;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

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
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// adding the buttons
		for (int i = 1; i < 5; i++) {

			final int level = i;
			String levelT;

			if (player.currentLevel >= i) {
				levelT = Integer.toString(i);
				button = new TextButton(levelT, skin);
				button.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						((Game) Gdx.app.getApplicationListener())
								.setScreen(new GameScreen(player, level));
					}
				});
			} else {
				levelT = "X";
				button = new TextButton(levelT, skin);
				button.setDisabled(true);
			}
			table.add(button).spaceRight(15).width(70).height(70);
		}

		stage.addActor(table);

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
