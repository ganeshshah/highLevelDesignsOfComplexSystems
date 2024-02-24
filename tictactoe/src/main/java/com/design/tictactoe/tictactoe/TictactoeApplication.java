package com.design.tictactoe.tictactoe;

import com.design.tictactoe.tictactoe.controllers.GameController;
import com.design.tictactoe.tictactoe.models.*;
import com.design.tictactoe.tictactoe.strategies.botplayingstrategies.RandomizedBotPlayingStrategy;
import com.design.tictactoe.tictactoe.strategies.gamewinningstrategies.BaseWinningStrategyWithOrderOfOne;
import com.design.tictactoe.tictactoe.strategies.gamewinningstrategies.GameWinningStrategy;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TictactoeApplication {

	public static void main(String[] args) {
		//SpringApplication.run(TictactoeApplication.class, args);


		System.out.println("hello world");

		int dimension = 3;
		Player p1 = new HumanPlayer(new Symbol('X'),"Ganesh Shah");
		Player p2 = new Bot(new Symbol('O'), "Simulated Bot",BotDifficultyLevel.EASY, new RandomizedBotPlayingStrategy());
		GameWinningStrategy strategy = new BaseWinningStrategyWithOrderOfOne();
		GameController gameController = new GameController();

		Game game = gameController.createGame(dimension, List.of(p1, p2), List.of(strategy));

		while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
			System.out.println("Please Make the Next Move. This is current Status");
			gameController.display(game);
			gameController.makeMove(game);
		}

		if (gameController.getGameStatus(game).equals(GameStatus.ENDED)) {
			System.out.println("WINNER WINNER CHICKEN DINNER");
			gameController.display(game);
		}

		if (gameController.getGameStatus(game).equals(GameStatus.DRAW)) {
			System.out.println("UH OH. Try AGain. No one won");
			gameController.display(game);
		}



	}

}
