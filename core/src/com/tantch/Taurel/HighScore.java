package com.tantch.Taurel;

public class HighScore {
	int levelId;
	float score;
	
	
	public HighScore(){
		levelId=0;
		score=0;
	}
	public HighScore(int levelId,float score2){
		this.levelId = levelId;
		this.score = score2;
		
	}

	

	



	
	@Override
	public String toString() {
		return ("Level " + levelId + " HighScore: " + score);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HighScore other = (HighScore) obj;
		if (levelId != other.levelId){
			System.out.println("nao sao iguais");
			return false;
		}
		return true;
	}
	public void setScore(float score2) {
		
		if(this.score<score2){
			this.score=score2;
		}
		
	}

}
