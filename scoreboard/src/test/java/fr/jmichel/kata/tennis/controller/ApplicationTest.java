/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.jmichel.kata.tennis.controller;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import fr.jmichel.kata.tennis.bean.GamePart;
import fr.jmichel.kata.tennis.tool.GamePartTool;
import fr.jmichel.kata.tennis.tool.ScorePoint;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ScoreboardController.class)
public class ApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	private GamePart partie;

	private List<Integer> scoreExpectedPlayer1;
	private List<Integer> scoreExpectedPlayer2;

	private int nombreDeJoueur = 2;
	private int idPlayer1 = 0;
	private int idPlayer2 = 1;

	protected void setUp() throws Exception {
		partie = GamePartTool.getNewGamePart(nombreDeJoueur);
	}

	@Test
	public void createNewGamepartWithNplayer() throws Exception {
		partie = GamePartTool.getNewGamePart(nombreDeJoueur);

		assertNotNull(partie);
		assertEquals(partie.getPlayersScore().size(), nombreDeJoueur);

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);

		// Test position des joueurs dans la liste
		for (int i = 0; i < nombreDeJoueur; i++) {
			assertEquals(partie.getPlayersScore().get(i).getId(), i);
		}

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));

		}
	}

	@Test
	public void addFirstPointForPlayer1() throws Exception {
		setUp();
		GamePartTool.addPointPlayer(partie, idPlayer1);

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_15);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));

		}
	}

	@Test
	public void addSecondPointForPlayer1() throws Exception {
		setUp();

		// Ajout de 2 points pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_30);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));

		}

	}

	@Test
	public void addThirdPointForPlayer1() throws Exception {
		setUp();

		// Ajout de 3 points pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));
		}
	}

	@Test
	public void player1wins() throws Exception {
		setUp();

		// Ajout de 4 points pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));
		}

		// Test si le joueur 1 a gagné
		assertEquals(true, partie.getPlayersScore().get(idPlayer1).isWinner());
	}

	@Test
	public void addFirstPointForPlayer2() throws Exception {
		setUp();

		// Ajout de 3 points pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);

		// Ajout de 1 point pour le joueur 2
		GamePartTool.addPointPlayer(partie, idPlayer2);

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_15);

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));
		}
	}

	@Test
	public void addSecondPointForPlayer2() throws Exception {
		setUp();

		// Ajout de 3 points pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);

		// Ajout de 2 point pour le joueur 2
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_30);

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));

		}
	}

	@Test
	public void addThirdPointForPlayer2() throws Exception {
		setUp();

		// Ajout de 3 points pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);

		// Ajout de 3 point pour le joueur 2
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_40);

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));

		}
	}

	@Test
	public void activationDeuceAndplayer2WithAdvantage() throws Exception {
		setUp();

		// Ajout de 3 points pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);

		// Ajout de 4 point pour le joueur 2
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_ADV);

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));
		}
		// Test que les règles du deuce sont activées
		assertEquals(true, partie.isDeuceActivated());
		
		// Test que lenumero de set lors de l'activation du deuce est bien le bon
		assertEquals(true, partie.getNumberSetActivatedCheuce() == 7);

		// Test que le joueur 2 a l'avantage
		assertEquals(true, partie.getPlayersScore().get(idPlayer2).isAdvantage());
	}

	@Test
	public void player2LooseAdvantageAndDeuch() throws Exception {
		setUp();

		// Ajout de 3 points pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);

		// Ajout de 4 point pour le joueur 2
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);// Joueur 2 prend l'avantage

		// Ajout d'un point pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_DEUCH);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_ADV);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_DEUCH);

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));
		}

		// Test qu'aucun joueur n'a plus d'avantage notamment pouyr le joueur 2
		assertEquals(false, partie.getPlayersScore().get(idPlayer1).isAdvantage());
		assertEquals(false, partie.getPlayersScore().get(idPlayer2).isAdvantage());

	}

	@Test
	public void player2WithAdvantageWins() throws Exception {
		setUp();

		// Ajout de 3 points pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);

		// Ajout de 5 point pour le joueur 2
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);

		// Test si le joueur 1 n'a pas gagné
		assertEquals(false, partie.getPlayersScore().get(idPlayer1).isWinner());

		// Test si le joueur 2 a gagné
		assertEquals(true, partie.getPlayersScore().get(idPlayer2).isWinner());

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_ADV);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));
		}
	}

	@Test
	public void player2LooseAdvantageAndPlayer1Wins() throws Exception {
		setUp();

		// Ajout de 3 points pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);
		GamePartTool.addPointPlayer(partie, idPlayer1);

		// Ajout de 4 point pour le joueur 2
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);
		GamePartTool.addPointPlayer(partie, idPlayer2);// Joueur 2 prend l'avantage

		// Test que le joueur 2 a l'avantage
		assertEquals(true, partie.getPlayersScore().get(idPlayer2).isAdvantage());

		// Ajout d'un point pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);

		// Test que le joueur 2 n'a plus l'avantage
		assertEquals(false, partie.getPlayersScore().get(idPlayer2).isAdvantage());

		// Ajout d'un point pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);

		// Test que le joueur 1 a pris l'avantage
		assertEquals(true, partie.getPlayersScore().get(idPlayer1).isAdvantage());

		// Ajout du point de la victoire pour le joueur 1
		GamePartTool.addPointPlayer(partie, idPlayer1);

		// Test que le joueur 1 a gagné
		assertEquals(true, partie.getPlayersScore().get(idPlayer1).isWinner());

		// Test que le joueur 2 n'a pas gagné
		assertEquals(false, partie.getPlayersScore().get(idPlayer2).isWinner());

		scoreExpectedPlayer1 = new ArrayList<>();
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_DEUCH);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_ADV);
		scoreExpectedPlayer1.add(ScorePoint.SCORE_0);

		scoreExpectedPlayer2 = new ArrayList<>();
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_15);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_30);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_ADV);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_DEUCH);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_40);
		scoreExpectedPlayer2.add(ScorePoint.SCORE_0);

		for (int i = 0; i < scoreExpectedPlayer1.size(); i++) {
			// Test score joueur 1
			assertEquals(true,
					scoreExpectedPlayer1.get(i) == partie.getPlayersScore().get(idPlayer1).getScore().get(i));

			// Test score joueur 2
			assertEquals(true,
					scoreExpectedPlayer2.get(i) == partie.getPlayersScore().get(idPlayer2).getScore().get(i));
		}
	}

	@Test
	public void scoreBoardPage() throws Exception {
		// N.B. jsoup can be useful for asserting HTML content
		mockMvc.perform(get("/scoreboard")).andExpect(content().string(containsString("Score Game")))
				.andExpect(content().string(containsString("Start the game")))
				.andExpect(content().string(containsString("Player 1")))
				.andExpect(content().string(containsString("Player 2")))
				.andExpect(content().string(containsString("/scoreboard/addPoint?playerId=0")))
				.andExpect(content().string(containsString("/scoreboard/addPoint?playerId=1")))

		;
	}

	@Test
	public void addPointForPlayersAndPlayer1WinAndDisplayScorePlayer() throws Exception {
		mockMvc.perform(get("/scoreboard"));
		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString(String.valueOf(ScorePoint.SCORE_15))))
				.andExpect(content().string(containsString("SET 1 : Player 1 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString(String.valueOf(ScorePoint.SCORE_30))))
				.andExpect(content().string(containsString("SET 2 : Player 1 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=1"))
				.andExpect(content().string(containsString("SET 3 : Player 2 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString(String.valueOf(ScorePoint.SCORE_40))))
				.andExpect(content().string(containsString("SET 4 : Player 1 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString(String.valueOf(ScorePoint.SCORE_0))))
				.andExpect(content().string(containsString("SET 5 : Player 1 win 1 point")))
				.andExpect(content().string(containsString("Player 1 win the game")));

	}

	@Test
	public void addPointForPlayer1GetAdvantageAndWinAndDisplayScorePlayer() throws Exception {
		mockMvc.perform(get("/scoreboard"));
		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString("SET 1 : Player 1 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString("SET 2 : Player 1 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString("SET 3 : Player 1 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=1"))
				.andExpect(content().string(containsString("SET 4 : Player 2 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=1"))
				.andExpect(content().string(containsString("SET 5 : Player 2 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=1"))
				.andExpect(content().string(containsString("SET 6 : Player 2 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString("SET 7 : Player 1 win 1 point")))
				.andExpect(content().string(containsString("ADV")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString(String.valueOf(ScorePoint.SCORE_0))))
				.andExpect(content().string(containsString("SET 8 : Player 1 win 1 point")))
				.andExpect(content().string(containsString("Player 1 win the game")));
	}

	@Test
	public void addPointForPlayersForActivateCheuchAndPlayer2WinAndDisplayScorePlayer() throws Exception {
		mockMvc.perform(get("/scoreboard"));
		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString("SET 1 : Player 1 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString("SET 2 : Player 1 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString("SET 3 : Player 1 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=1"))
				.andExpect(content().string(containsString("SET 4 : Player 2 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=1"))
				.andExpect(content().string(containsString("SET 5 : Player 2 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=1"))
				.andExpect(content().string(containsString("SET 6 : Player 2 win 1 point")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=0"))
				.andExpect(content().string(containsString("SET 7 : Player 1 win 1 point")))
				.andExpect(content().string(containsString("ADV")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=1"))
				.andExpect(content().string(containsString("SET 8 : Player 2 win 1 point")))
				.andExpect(content().string(containsString("DEUCE")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=1"))
				.andExpect(content().string(containsString("SET 9 : Player 2 win 1 point")))
				.andExpect(content().string(containsString("ADV")));

		mockMvc.perform(get("/scoreboard/addPoint?playerId=1"))
				.andExpect(content().string(containsString(String.valueOf(ScorePoint.SCORE_0))))
				.andExpect(content().string(containsString("SET 10 : Player 2 win 1 point")))
				.andExpect(content().string(containsString("Player 2 win the game")));
	}

}
