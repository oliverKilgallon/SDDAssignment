package me.oliver.textgame.controllers;

import javax.swing.JLabel;

public class LevelTimer implements Runnable 
{
	
	private Thread levelTimer;
	private int elapsedSeconds = 0;
	private int elapsedMinutes = 0;
	private JLabel lblMinutes;
	private JLabel lblSeconds;
	
	private boolean threadEnd;
	
	public LevelTimer(JLabel lblMinutes, JLabel lblSeconds)
	{
		this.lblMinutes = lblMinutes;
		this.lblSeconds = lblSeconds;
		
		levelTimer = new Thread(this);
		levelTimer.start();
		threadEnd = false;
	}

	public int getElapsedSeconds()
	{
		return elapsedSeconds;
	}
	
	public int getElapsedMinutes()
	{
		return elapsedMinutes;
	}
	
	
	@Override
	public void run() 
	{
		while (!threadEnd)
		{
			
			elapsedSeconds++;
			if(elapsedSeconds == 60)
			{
				elapsedSeconds = 0;
				elapsedMinutes++;
			}
			
			if(elapsedSeconds < 10)
			{
				lblSeconds.setText("0" + elapsedSeconds);
			} else 
			{
				lblSeconds.setText("" + elapsedSeconds);
			}
			
			if(elapsedMinutes < 10)
			{
				lblMinutes.setText("0" + elapsedMinutes);
			}else 
			{
				lblMinutes.setText("" + elapsedMinutes);
			}
			
			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Ends the level timer when called by exiting the while loop then joining the thread to end it
	 */
	public void endTimer()
	{
		try 
		{
			threadEnd = true;
			levelTimer.join();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
