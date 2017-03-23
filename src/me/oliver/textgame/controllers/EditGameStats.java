package me.oliver.textgame.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import me.oliver.textgame.entities.StatisticType;

public class EditGameStats 
{

	private ArrayList<String> statData;
	public EditGameStats(StatisticType type, String value)
	{
		statData = new ArrayList<String>();
		
		InputStream filename = this.getClass().getResourceAsStream("/Score.txt");
		
		try {
			InputStreamReader inputReader = new InputStreamReader(filename);
	        // input the file content to the String "input"
	        BufferedReader file = 
	        		new BufferedReader(inputReader);
	        
	        String line;
	        String input = "";

	        while ((line = file.readLine()) != null) 
	        {
	        	statData.add(line);
	        	input += line + "\n";
	        }
	        file.close();
	        
	        switch(type)
	        {
	        	case LEVEL:
	        		for(String s : statData)
	        		{
	        			if(s.contains("Level finished on: "))
	        			{
	        				statData.set(0, "Level finished on: " + value);
	        			}
	        		}
	        		break;
	        	case ACCURACY:
	        		for(String s : statData)
	        		{
	        			if(s.contains("Game accuracy: "))
	        			{
	        				statData.set(1, "Game accuracy: " + value + "%");
	        			}
	        		}	        
	        		break;
	        	case SCORE:
	        		for(String s : statData)
	        		{
	        			if(s.contains("Game score: "))
	        			{
	        				statData.set(2, "Game score: " + value);
	        			}
	        		}
	        		break;
	        	case COMPLETION_TIME:
	        		for(String s : statData)
	        		{
	        			if(s.contains("Game completion time: "))
	        			{
	        				statData.set(3, "Game completion time: " + value);
	        			}
	        		}
	        		break;
			default:
				break;
	        }
	        
	        input = "";
	        
	        for(String s : statData)
	        {
	        	input += s + "\n";
	        }
	        
	        FileOutputStream out = null;
	        File score;
	        
	        try 
	        {
	        	score = new File("/Score.txt");
	        	out = new FileOutputStream(score);
	        	
	        	byte[] inputBytes = input.getBytes();
	        	
	        	out.write(inputBytes);
	        	out.close();
	        	
	        } 
	        
	        catch (IOException ex)
	        {
	        	ex.printStackTrace();
	        } 
	        
	        finally 
	        {
	        	try 
	        	{
	        		if(out != null)
	        		{
	        			out.close();
	        		}
	        	} 
	        	
	        	catch (IOException ex)
	        	{
	        		ex.printStackTrace();
	        	}
	        }
	        

		} 
		
		catch (FileNotFoundException ex)
		{
			System.out.println("Unable to find file '" + filename + "'");
		} 
		
		catch (IOException ex)
		{
			System.out.println("Error reading file '" +	filename + "'");
		}
	}
}
