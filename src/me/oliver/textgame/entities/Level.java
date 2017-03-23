package me.oliver.textgame.entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Level 
{
	private ArrayList<String> levelData;
	
	private int score;
	private int levelNum;
	private int startStringAmount;
	private int currentQuestion;
	
	/**
	 * Read in the level text file through the use of a passed in integer to determine the file name and assign each line in the file
	 *  as a new element to the level data array list. Then initialise currentQuestion and
	 * startStringAmount.
	 * 
	 * @param levelNum	A number passed in to find out which text file to read from the res folder
	 */
	public Level(int levelNum)
	{
		levelData = new ArrayList<String>();
		this.levelNum = levelNum;
		
		InputStream filename = this.getClass().getResourceAsStream("/levels/" + levelNum + ".txt");
		
		String line = null;

		try {
			
			InputStreamReader inputReader = new InputStreamReader(filename);

			//Wrapping file readers with buffered readers saves data for other stuff
			BufferedReader bufferedReader = 
					new BufferedReader(inputReader);

			while((line = bufferedReader.readLine()) != null){
				levelData.add(line);
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex){
			System.out.println(
					"Unable to find file '" +
							filename + "'");
		} catch (IOException ex){
			System.out.println(
					"Error reading file '" +
							filename + "'");
		}
		startStringAmount = levelData.size();
		currentQuestion = -1;
	}
	
	/**
	 * @return	The integer value of what the current question is.
	 */
	public int getCurrentQuestion() {
		return currentQuestion;
	}

	/**
	 * @return	The value of the user's score.
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * @return	The value of how many strings were initially loaded.
	 */
	public int getStartStringAmount() {
		return startStringAmount;
	}
	
	/**
	 * @return	The size of the levelData array list i.e how many strings it is currently comprised of.
	 */
	public int getLevelDataSize()
	{
		return levelData.size();
	}
	
	/**
	 * Increments the score based on the length of the string
	 * multiplied by a modifier based on the current difficulty,
	 * as well as incrementing correctStrings to reflect the current
	 * question the user is on.
	 * 
	 * @param answeredString 	The string through which the score is increased
	 */
	public void incrementScore(String answeredString){
		this.score += answeredString.length() * 
				(1 + (1 - (1 / this.levelNum)));
	}	
	
	/**
	 * Randomly selects a question from the available
	 * strings in the levelData then removes the used string
	 * to prevent duplicates, then increments currentQuestion to 
	 * reflect which question the user is on.
	 * 
	 * @return	The selected string
	 */
	public String getQuestion(){
		Random rand = new Random();
		int index = rand.nextInt(levelData.size());
		String s = levelData.get(index);
		levelData.remove(index);
		currentQuestion++;
		return s;
	}
	
	/**
	 * @return	A boolean to determine if levelData is empty
	 */
	public boolean isStringsEmpty()
	{
		return levelData.isEmpty();
	}
}
