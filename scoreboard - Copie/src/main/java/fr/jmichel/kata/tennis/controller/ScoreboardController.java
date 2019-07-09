package fr.jmichel.kata.tennis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.jmichel.kata.tennis.bean.GamePart;
import fr.jmichel.kata.tennis.tool.GamePartTool;

@Controller
public class ScoreboardController {

	private GamePart gamePart;

	private final int numberPlayer = 2;

	@GetMapping("/scoreboard")
	public String getTableauScore(Model model, HttpServletRequest request) {
		gamePart = GamePartTool.getNewGamePart(numberPlayer);
		model.addAttribute("gamePart", gamePart);

		return "scoreboard";
	}

	@GetMapping("/scoreboard/addPoint")
	public String addPoint(@RequestParam(name = "playerId") String idPlayer, Model model) {
		if (!gamePart.isFinish()) {
			GamePartTool.addPointPlayer(gamePart, Integer.parseInt(idPlayer));
		}
		
		model.addAttribute("gamePart", gamePart);

		return "scoreboard";
	}
}
