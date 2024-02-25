package com.design.snakeladder.snakeladdergame;

import com.design.snakeladder.snakeladdergame.controller.GameController;
import com.design.snakeladder.snakeladdergame.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SnakeladdergameApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SnakeladdergameApplication.class, args);

		System.out.println("Starting Snake Ladder game :");
		GameController gameController = new GameController();
		Player player1 = new Player(List.of(new Button(ButtonColour.GREEN)), "Ganesh");
		Player player2 = new Player(List.of(new Button(ButtonColour.YELLOW)), "Sai");
		Game game = gameController.createGame(new Board(100, GameLevel.EASY), new Dice(6), List.of(player1, player2), 1);

		List<Player> players = game.getPlayers();
		Integer lastPlayerIndex = game.getLastPlayerMoveIndex();

		Dice dice = new Dice(6);
		Scanner sc = new Scanner(System.in);

		while (game.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
			lastPlayerIndex = lastPlayerIndex + 1;
			lastPlayerIndex = lastPlayerIndex % players.size();
			Player player = game.getPlayers().get(lastPlayerIndex);
			System.out.println("Type roll to roll the dice : ");
			String input = sc.next();
			Integer outcome = dice.roll();
			if (player.getScore() == 0) {
				if (outcome == 1) {
					System.out.println("Great you have unlocked a button and you are ready to start");
                    player.setScore(1);
				}else{
					System.out.println("your button is still locked as you didn't get a 1, your outcome was : " + outcome);
				}
			}else{
				Integer totalScore = player.getScore() + outcome;

				// iterate through positionMap and adjust total score accordingly

				player.setScore(totalScore);
				if(totalScore >= 100){
					System.out.println("Player : " + player.getName() + " won ");
					game.setGameStatus(GameStatus.ENDED);
				}
			}

			System.out.println("Player : "+ player.getName() + " score : " + player.getScore());
		}

	}
}
