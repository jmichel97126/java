package fr.jmichel.kata.tennis.bean;

import java.util.ArrayList;
import java.util.List;

import fr.jmichel.kata.tennis.tool.ScorePoint;

public class Player {
	
	private int id;
	private List<Integer> score;
	private boolean winner;
	private boolean advantage;

	public Player(int id) {
			this.id = id;
			score = new ArrayList<>();
			score.add(ScorePoint.SCORE_0);
			winner = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getScore() {
		return score;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public boolean isAdvantage() {
		return advantage;
	}

	public void setAdvantage(boolean advantage) {
		this.advantage = advantage;
	}

}
