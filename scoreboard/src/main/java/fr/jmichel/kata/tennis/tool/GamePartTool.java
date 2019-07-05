package fr.jmichel.kata.tennis.tool;

import java.util.ArrayList;
import java.util.List;

import fr.jmichel.kata.tennis.bean.Player;
import fr.jmichel.kata.tennis.bean.GamePart;

public class GamePartTool {

	private static boolean loseAdvantage = false;

	public static GamePart getNewGamePart(int numberOfPlayer) {
		GamePart gamePart = new GamePart();
		gamePart.setPlayersScore(new ArrayList<Player>());
		gamePart.setSetHistoric(new ArrayList<>());
		gamePart.setDeuceActivated(false);

		for (int i = 0; i < numberOfPlayer; i++) {
			gamePart.getPlayersScore().add(i, new Player(i));
		}

		return gamePart;
	}

	public static void addPointPlayer(GamePart gamePart, int idPlayer) {
		// Nous enregistrons dans cette liste le joueur qui a marqué, elle nous sert à
		// connaitre le dernier joueur qui a recu un point
		gamePart.getSetHistoric().add(idPlayer);
		
		for (Player player : gamePart.getPlayersScore()) {
			if (player.getId() == idPlayer) {
				if (player.getScore().contains(ScorePoint.SCORE_40)) {
					player.getScore().add(ScorePoint.SCORE_40);
					winningCalculation(gamePart, idPlayer);
				} else if (player.getScore().contains(ScorePoint.SCORE_30)) {
					player.getScore().add(ScorePoint.SCORE_40);
				} else if (player.getScore().contains(ScorePoint.SCORE_15)) {
					player.getScore().add(ScorePoint.SCORE_30);
				} else {
					player.getScore().add(ScorePoint.SCORE_15);
				}
			} else {
				// Après avoir eu un Avantage ou un deuch lors du dernier set, il faut remettre
				// le score à 40
				if (player.getScore().get(player.getScore().size() - 1) == ScorePoint.SCORE_ADV) {
					player.getScore().add(ScorePoint.SCORE_40);
				} else {
					if (player.getScore().get(player.getScore().size() - 1) == ScorePoint.SCORE_DEUCH) {
						player.getScore().add(ScorePoint.SCORE_40);
					} else {
						// Si le joueur n'est pas celui qui a marqué, il faut dupliquer son dernier
						// score
						player.getScore().add(player.getScore().get(player.getScore().size() - 1));
					}
				}
			}
		}
		// Si l'un des joueurs a perdu son avantage, le score pour les deux joueur est
		// 40 et affiché DEUCH pour tous les joueurs à l'écran
		if (loseAdvantage) {
			inialiserizeScoreDeuch(gamePart);
			loseAdvantage = false;
		}

		// Si la partie est finie on modifie les derniers score pour afficher 0 pour les
		// deux joueurs
		if (gamePart.isFinish()) {
			for (Player player : gamePart.getPlayersScore()) {
				player.getScore().set(player.getScore().size() - 1, ScorePoint.SCORE_0);
			}
		}
	}

	private static void inialiserizeScoreDeuch(GamePart gamePart) {
		// Un deuch, c'est quand tous les joueus ont tous le score à 40 et qui n'ont
		// pas l'avantage
		int numberPlayerLastScoreEquals40 = 0;
		int numberPlayerAdvantage = 0;

		for (Player player : gamePart.getPlayersScore()) {
			if (player.getScore().get(player.getScore().size() - 1).equals(new Integer(ScorePoint.SCORE_40))) {
				numberPlayerLastScoreEquals40++;
			}

			if (player.isAdvantage()) {
				numberPlayerAdvantage++;
			}
		}

		if (numberPlayerLastScoreEquals40 == gamePart.getPlayersScore().size() && numberPlayerAdvantage == 0) {
			for (Player player : gamePart.getPlayersScore()) {
				player.getScore().set(player.getScore().size() - 1, ScorePoint.SCORE_DEUCH);
			}
		}
	}

	private static void winningCalculation(GamePart gamePart, int lastPlayerToScore) {
		Player playerLastPlayerToScore = gamePart.getPlayersScore().get(lastPlayerToScore);
		
		List<Integer> playerWithScoreTo40 = new ArrayList<Integer>();
		for (Player player : gamePart.getPlayersScore()) {
			if ((player.getScore().get(player.getScore().size() - 1) >= ScorePoint.SCORE_40)) {
				playerWithScoreTo40.add(player.getId());
			}
		}

		if (playerWithScoreTo40.size() == 1) {
			gamePart.getPlayersScore().get(playerWithScoreTo40.get(0)).setWinner(true);
			gamePart.setFinish(true);
		} else {
			if (gamePart.isDeuceActivated()) {
				updatePlayersScoreForDeuce(gamePart, playerLastPlayerToScore);
			} else {
				activateDeuch(gamePart, playerLastPlayerToScore);
			}
		}
	}

	private static void activateDeuch(GamePart gamePart, Player playerLastPlayerToScore) {
		gamePart.setDeuceActivated(true);
		gamePart.setNumberSetActivatedCheuce(gamePart.getSetHistoric().size());
		playerLastPlayerToScore.setAdvantage(true);
		playerLastPlayerToScore.getScore().set(playerLastPlayerToScore.getScore().size() - 1, ScorePoint.SCORE_ADV);
	}

	private static void updatePlayersScoreForDeuce(GamePart gamePart, Player playerLastPlayerToScore) {
		if (playerLastPlayerToScore.isAdvantage()) {// Si le dernier joueur à avoir marquer a l'avantage, il
													// gagne
			playerLastPlayerToScore.setWinner(true);
			gamePart.setFinish(true);
		} else {
			// Sinon, il nous faut déterminé si le joueur n'ayant pas marqué possédait unavantage et si oui, il faut lui enlever
			int numberPlayerAvantage = 0;
			for (Player player : gamePart.getPlayersScore()) {
				if (player.isAdvantage()) {
					numberPlayerAvantage++;
				}
			}

			if (numberPlayerAvantage == 0) {
				// Si aucun joueur n'avait l'avantage, le joueur venant de marquer prend l'avantage
				playerLastPlayerToScore.setAdvantage(true);
				playerLastPlayerToScore.getScore().set(playerLastPlayerToScore.getScore().size() - 1, ScorePoint.SCORE_ADV);
			} else {// Si le joueur n'ayant pasmarqueravait l'avantage, on lui retire
				for (Player player : gamePart.getPlayersScore()) {
					player.setAdvantage(false);
					loseAdvantage = true;
				}
			}
		}
	}

}
