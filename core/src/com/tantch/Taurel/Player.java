package com.tantch.Taurel;

import java.util.Vector;

public class Player {

	public String nome;
	public int currentLevel;
	public Vector<HighScore> highscores;

	public Player() {
		this.nome = "default";
		currentLevel = 0;
		highscores = new Vector<HighScore>();

	}

	/**
	 * initializes a valid player
	 * @param nome the name of the player
	 */
	public Player(String nome) {
		this.nome = nome;
		currentLevel = 1;
		highscores = new Vector<HighScore>();
	}
/**
 * adds or updates a player's highscore on a level
 * @param highscore the highscore made
 */
	public void addScore(HighScore highscore) {
		if (highscore.levelId >= currentLevel)
			currentLevel = highscore.levelId + 1;

		for (HighScore high : highscores) {
			if (high.equals(highscore)) {
				System.out.println("placed score");
				high.setScore(highscore.score);
				return;
			}

		}
		highscores.add(highscore);
		System.out.println("highscore added");

	}

}
