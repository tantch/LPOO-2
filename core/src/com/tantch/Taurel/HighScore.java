package com.tantch.Taurel;

public class HighScore {
	int levelId;
	int score;
	
	public HighScore(int levelId,int score){
		this.levelId = levelId;
		this.score = score;
		
	}

	

	@Override
	public boolean equals(Object obj) {

		if (getClass() != obj.getClass())
			return false;
		HighScore other = (HighScore) obj;
		if (score != other.score)
			return false;
		return true;
	}

}
