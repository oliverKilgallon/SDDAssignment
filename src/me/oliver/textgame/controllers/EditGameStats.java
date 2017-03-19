package me.oliver.textgame.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import me.oliver.textgame.entities.Level;
import me.oliver.textgame.entities.StatisticType;

public class EditGameStats {

	private ArrayList<String> statData;
	public EditGameStats(StatisticType type, String value)
	{
		statData = new ArrayList<String>();
		String filename = "/Score.txt";
		
		File bell = new File("/Score.txt");
		if(!bell.exists())
		{
			try {
				Level.ExportResource("Score.txt");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
	        // input the file content to the String "input"
			FileReader fileReader = 
					new FileReader(filename);
	        BufferedReader file = 
	        		new BufferedReader(fileReader);
	        
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

	        // write the new String with the replaced line OVER the same file
	        FileOutputStream fileOut = new FileOutputStream(filename);
	        fileOut.write(input.getBytes());
	        fileOut.close();
	        
	        System.out.println("Edited correct");

		} catch (FileNotFoundException ex){
			System.out.println(
					"Unable to find file '" +
							filename + "'");
		} catch (IOException ex){
			System.out.println(
					"Error reading file '" +
							filename + "'");
		}
	}
}
