package com.tantch.Taurel;

import java.util.Vector;


public class Player {

	String nome;
	int currentLevel;
	Vector<HighScore> highscores;
	
	
	public Player(String nome){
		this.nome=nome;
		currentLevel=1;
		highscores= new Vector<HighScore>();
	}
	
	
	
	
}
