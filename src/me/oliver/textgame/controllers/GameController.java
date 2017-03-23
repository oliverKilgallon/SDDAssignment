package me.oliver.textgame.controllers;

import java.util.ArrayList;

import me.oliver.textgame.entities.Level;

public class GameController 
{
	
	private ArrayList<Level> levels;
	private int currentLevel;
	private int accuracy;
	private int enteredStrings;
	private int correctStrings;
	private final int maxDifficulty = 5;
	
	/**
	 * Instantiates the levels array list and currentLevel then
	 * adds a new Level object to the levels array list for each increment
	 * of the for loop based on the difficulty passed in.
	 * 
	 * @param difficulty	The level of difficult passed in via the constructor
	 */
	public GameController(int difficulty)
	{
		enteredStrings = 0;
		correctStrings = 0;
		levels = new ArrayList<Level>();
		currentLevel = 0;
		if(difficulty > 0 && difficulty <= maxDifficulty)
		{
			for(int i = 1; i <= difficulty; i++)
			{
				Level level = new Level(i);
				levels.add(level);
			}
		}
	}
	
	/**
	 * @return	The value of the current level's score
	 */
	public int getCurrentScore()
	{
		return levels.get(currentLevel).getScore();
	}
	
	/**
	 * @return	The value of which level the user is currently on
	 */
	public int getCurrentLevel()
	{
		return currentLevel;
	}
	
	/**
	 * @return The value of the user's accuracy on the current game
	 */
	public float getCurrentAccuracy()
	{
		return accuracy;
	}
	
	/**
	 * @return	The value of how many answers the user has entered
	 */
	public int getEnteredStrings()
	{
		return enteredStrings;
	}
	
	/**
	 * @return	The value of how many correct answers the user has entered
	 */
	public int getCorrectStrings()
	{
		return correctStrings;
	}
	
	/**
	 * @return	The float casted value of the current question number divided by the how many strings were initially loaded, then all multiplied by 100
	 */
	public float getProgress()
	{
		return ((float) levels.get(currentLevel).getCurrentQuestion() / (float) levels.get(currentLevel).getStartStringAmount()) * 100f;
	}
	
	/**
	 * Increments the enteredStrings by 1 
	 * then sets the accuracy to a value between 0 and 100 percent
	 * by dividing how many questions the user has got right by how many
	 * strings were initially loaded in then multiplying by 100
	 * 
	 */
	public void updateAccuracy()
	{
		
		if(correctStrings == 0)
		{
			accuracy = 0;
		} else 
		{
			accuracy = (int)  ( ( (float) correctStrings / 
					(float) enteredStrings ) * 100.0f);
		}
	}
	
	/**
	 * @return The string value of the next question
	 */
	public String nextQuestion()
	{
		return levels.get(currentLevel).getQuestion();
	}
	
	/**
	 * If the level isn't at maximum, increment the currentLevel
	 */
	public void levelUp()
	{
		if(currentLevel < maxDifficulty)
		{
			currentLevel++;
		}
	}
	
	/**
	 * Check the user's entered string against the current question, then returns based on this comparison
	 * @param question	The value of the question text box
	 * @param answer	The value of the user's text box
	 * @return			True if the strings match, false otherwise
	 */
	public boolean checkAnswer(String question, String answer)
	{
		enteredStrings++;
		boolean isCorrect = false;
		if(question.equals(answer))
		{
			levels.get(currentLevel).incrementScore(question);
			isCorrect = true;
			correctStrings++;
		}
		updateAccuracy();
		return isCorrect;
	}
	
	/**
	 * @return True if the level data isn't empty, false otherwise
	 */
	public boolean isQuestionAvailable()
	{		
		return !levels.get(currentLevel).isStringsEmpty();
	}
	
	/**
	 * @return	True if the current level is the last one, false otherwise
	 */
	public boolean isLastLevel()
	{		
		return (currentLevel + 1 == levels.size());
	}
}
