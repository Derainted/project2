package com.example.demo;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class rpsController {
	
	private ArrayList<scoreBean> scores = new ArrayList<scoreBean>();

	public void registerScore(String matchOutcome, int point) {
		
		scoreBean score = new scoreBean();
		
		score.setGames(1);
		
		if (matchOutcome.equals("w"))
		{
			score.setWins(1);
			
		} else if (matchOutcome.equals("l"))
		{
			score.setLosses(1);	
			
		} else if (matchOutcome.equals("t"))
		{
			score.setTies(1);	
		}
		
		scores.add(score);
	}
	

	// The game
	@RequestMapping(method = RequestMethod.POST, value = "/rps")
	public String rpsGame(String playerMove) {

		String[] rps = { "rock", "paper", "scissors" };
		String computerMove = rps[new Random().nextInt(rps.length)];

		System.out.println("You played: " + playerMove);
		System.out.println("Computer played: " + computerMove);

		if (playerMove.equals(computerMove))
		{		
			
			System.out.println("The game was a tie!");
			
			return "You played: " + playerMove
					+ "<br>"
					+ "Computer played: " + computerMove
					+ "<br>"
					+ "The game was a tie!";

		} else if (playerMove.equals("rock")) 
		{
			if (computerMove.equals("paper")) 
			{
				
				System.out.println("You lose!");
				
				return "You played: " + playerMove
						+ "<br>"
						+ "Computer played: " + computerMove
						+ "<br>"
						+ "You lose!";

			} else if (computerMove.equals("scissors"))
			{
				System.out.println("You win!");	
				
				return "You played: " + playerMove
						+ "<br>"
						+ "Computer played: " + computerMove
						+ "<br>"
						+ "You win!";
			}
		} else if (playerMove.equals("paper")) 
		{
			if (computerMove.equals("rock")) 
			{
				System.out.println("You win!");
				
				return "You played: " + playerMove
						+ "<br>"
						+ "Computer played: " + computerMove
						+ "<br>"
						+ "You win!";

			} else if (computerMove.equals("scissors")) 
			{
				System.out.println("You lose!");

				return "You played: " + playerMove
						+ "<br>"
						+ "Computer played: " + computerMove
						+ "<br>"
						+ "You lose!";
			}
		} else if (playerMove.equals("scissors")) 
		{
			if (computerMove.equals("paper")) 
			{
				System.out.println("You win!");

				return "You played: " + playerMove
						+ "<br>"
						+ "Computer played: " + computerMove
						+ "<br>"
						+ "You win!";

			} else if (computerMove.equals("rock")) 
			{
				System.out.println("You lose!");

				return "You played: " + playerMove
						+ "<br>"
						+ "Computer played: " + computerMove
						+ "<br>"
						+ "You lose!";
			}
		}

		return "Something whent wrong!";
	}

	
	
	@RequestMapping(value = "/showJson", method = RequestMethod.GET)
	public String ShowObservationsJSON()
	{
		return objectsJSON(scores);
	}
	
	
	private String objectJSON(scoreBean score) {
		return "{"
				+ "\"Games\": \"" + score.getGames()
				+ "\"," + "\"Wins\": " + score.getWins()
				+ "\"," + "\"Ties\": " + score.getTies()
				+ "\"," + "\"Losses\": " + score.getLosses()
				+ "\"," + "\"Losses\": " + score.getHighscore()
				+ "}";
	}
	
private String objectsJSON(ArrayList<scoreBean> scores) {
		
		String result = "";
		
		for(var score: scores) {
			result += objectJSON(score) + ",";
		}
		
		if (result.length() > 2) {
			result = result.substring(0, result.length() - 1);			
		}
		
		result = "{ \"Scores\": [" + result + "]}";
		
		return result;
	}
	
}