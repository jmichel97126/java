package fr.jmichel.kata.tennis.bean;

import java.util.List;

import org.springframework.context.annotation.Scope;

@Scope("session")
public class GamePart {
	
	private List<Player> playersScore;
	private boolean deuceActivated;
	private int numberSetActivatedCheuce;
	private boolean finish;
	private List<Integer> setHistoric;
	
	public List<Player> getPlayersScore() {
		return playersScore;
	}
	public void setPlayersScore(List<Player> playersScore) {
		this.playersScore = playersScore;
	}
	public boolean isDeuceActivated() {
		return deuceActivated;
	}
	public void setDeuceActivated(boolean deuceActivated) {
		this.deuceActivated = deuceActivated;
	}
	public int getNumberSetActivatedCheuce() {
		return numberSetActivatedCheuce;
	}
	public void setNumberSetActivatedCheuce(int numberSetActivatedCheuce) {
		this.numberSetActivatedCheuce = numberSetActivatedCheuce;
	}
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	public List<Integer> getSetHistoric() {
		return setHistoric;
	}
	public void setSetHistoric(List<Integer> setHistoric) {
		this.setHistoric = setHistoric;
	}



}
