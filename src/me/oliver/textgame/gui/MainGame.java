package me.oliver.textgame.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import me.oliver.textgame.controllers.EditGameStats;
import me.oliver.textgame.controllers.GameController;
import me.oliver.textgame.controllers.LevelTimer;
import me.oliver.textgame.entities.StatisticType;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JProgressBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

/**
 * @author Oliver Kilgallon
 *
 */
@SuppressWarnings("serial")
public class MainGame extends JFrame
{

	private JFrame frame;
	
	private JPanel contentPane;
	private JPanel panelGame;
	private JPanel panelProgress;
	private JPanel panelState;
	private JPanel panelScore;
	private JPanel panelAccuracy;
	private JPanel panelTime;
	
	private JLabel lblQuestion;
	private JLabel lblAnswer;
	private JLabel lblProgress;
	private JLabel lblScoreText;
	private JLabel lblAccuracy;
	private JLabel lblTime;
	private JLabel lblSeconds;
	private JLabel lblCurrentLevel;
	private JLabel lblLevelNum;
	private JLabel lblMinutes;
	private JLabel lblDivider;
	private JLabel lblAccuracyNum;
	private JLabel lblScoreNum;
	
	private JTextField tfQuestion;
	private JTextField tfAnswer;
	
	private JProgressBar pbCompletion;
	
	private GameController game;
	
	private LevelTimer timer;
	
	private boolean isStringCorrect;

	/**
	 * Create the frame.
	 */
	public MainGame(int difficulty) 
	{
		frame = this;
		frame.setVisible(true);
		frame.setTitle("Type that Text!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 140);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 202, 68));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setMinimumSize(new Dimension(500, 140));
		
		panelProgress = new JPanel();
		contentPane.add(panelProgress, BorderLayout.NORTH);
		
		lblProgress = new JLabel("Percentage of level complete");
		panelProgress.add(lblProgress);
		
		pbCompletion = new JProgressBar();
		panelProgress.add(pbCompletion);
		pbCompletion.setForeground(Color.GREEN);
		
		lblCurrentLevel = new JLabel("Current Level:");
		panelProgress.add(lblCurrentLevel);
		
		panelGame = new JPanel();
		contentPane.add(panelGame);
		
		lblQuestion = new JLabel("Enter exactly:");
		panelGame.add(lblQuestion);
		
		tfQuestion = new JTextField();
		panelGame.add(tfQuestion);
		tfQuestion.setColumns(16);
		tfQuestion.setEditable(false);
		
		lblAnswer = new JLabel("Answer:");
		panelGame.add(lblAnswer);
		
		tfAnswer = new JTextField();
		panelGame.add(tfAnswer);
		tfAnswer.setColumns(10);
		tfAnswer.setText("Type Here");
		
		panelState = new JPanel();
		contentPane.add(panelState, BorderLayout.SOUTH);
		
		panelScore = new JPanel();
		panelState.add(panelScore);
		
		lblScoreText = new JLabel("Score:");
		panelScore.add(lblScoreText);
		
		lblScoreNum = new JLabel("0");
		panelScore.add(lblScoreNum);
		
		panelAccuracy = new JPanel();
		panelState.add(panelAccuracy);
		
		lblAccuracy = new JLabel("Accuracy:");
		panelAccuracy.add(lblAccuracy);
		
		lblAccuracyNum = new JLabel("N/A");
		panelAccuracy.add(lblAccuracyNum);
		
		panelTime = new JPanel();
		panelState.add(panelTime);
		
		lblTime = new JLabel("Time:");
		panelTime.add(lblTime);
		
		lblMinutes = new JLabel("00");
		panelTime.add(lblMinutes);
		
		lblDivider = new JLabel(":");
		panelTime.add(lblDivider);
		
		lblSeconds = new JLabel("00");
		panelTime.add(lblSeconds);
		
		lblLevelNum = new JLabel("");
		panelProgress.add(lblLevelNum);
		
		frame.addWindowFocusListener(new WindowAdapter() 
		{
		    public void windowGainedFocus(WindowEvent e) 
		    {
		        tfAnswer.requestFocusInWindow();
		    }
		});
		
		game = new GameController(difficulty);
		
		addUserKeyChecker();
		textResetter();
		
		lblLevelNum.setText("" + (game.getCurrentLevel() + 1));
		tfQuestion.setText(game.nextQuestion());
		
		timer = new LevelTimer(lblMinutes, lblSeconds);
	}
	
	/**
	 * Responds to the user's key presses, specifically the enter key. Upon this key being typed, the user's answer is compared
	 * to the question, the answer field is then colour changed based on said comparison, score and accuracy are then updated,
	 * the answer field is reset for the next question, then a check is done to see if the game needs to end or progress to the next level,
	 * or simply update the progress bar and load the next question.
	 */
	public void addUserKeyChecker()
	{
		
		tfAnswer.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent key)
			{				
			}
	
			@Override
			public void keyReleased(KeyEvent key) 
			{
			}
	
			@Override
			public void keyTyped(KeyEvent key) 
			{
				if(key.getKeyChar() == KeyEvent.VK_ENTER)
				{
					isStringCorrect = game.checkAnswer(tfQuestion.getText(), tfAnswer.getText());
					colorChanger(tfAnswer, isStringCorrect);
					updateScore();
					updateAccuracy();
					tfAnswer.setText("");
					
					if(game.isQuestionAvailable() == true)
					{
						tfQuestion.setText(game.nextQuestion());
						pbCompletion.setValue((int) (game.getProgress()));
					} 
					
					else if(game.isLastLevel() && !game.isQuestionAvailable())
					{
						timer.endTimer();
						
						new EditGameStats(StatisticType.ACCURACY, String.valueOf( game.getCurrentAccuracy() ) );
						
						new EditGameStats(StatisticType.LEVEL, String.valueOf( game.getCurrentLevel() + 1) );
						
						new EditGameStats(StatisticType.SCORE, String.valueOf( game.getCurrentScore() ) );
						
						if(timer.getElapsedSeconds() < 10)
						{
							new EditGameStats(StatisticType.COMPLETION_TIME, "" + 
									timer.getElapsedMinutes() + 
									":" + 
									"0" +
									timer.getElapsedSeconds()
							);
						} 
						
						else 
						{
							new EditGameStats(StatisticType.COMPLETION_TIME, "" + 
								timer.getElapsedMinutes() + 
								":" + 
								timer.getElapsedSeconds()
							);
						}
						
						frame.dispose();
						String[] options = {"OK"};
						JOptionPane.showOptionDialog(null, "You completed the game!", "Congratulations", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
						new MainMenu();
					}
					
					else 
					{
						game.levelUp();
						lblLevelNum.setText("" + (game.getCurrentLevel() + 1));
						pbCompletion.setValue(0);
						tfQuestion.setText(game.nextQuestion());
						
						switch(game.getCurrentLevel() + 1)
						{
							case 1:
								contentPane.setBackground(Color.GREEN);
								break;
							case 2:
								contentPane.setBackground(Color.CYAN);
								break;
							case 3:
								contentPane.setBackground(Color.PINK);
								break;
							case 4:
								contentPane.setBackground(new Color(182, 130, 255));
								break;
							case 5:
								contentPane.setBackground(new Color(99, 255, 156));
								break;
							default:
								contentPane.setBackground(Color.WHITE);
								break;
						}
					}
				}
				
				else 
				{
					tfAnswer.setBackground(Color.white);
				}
			}
		});
	}

	/**
	 * Switches on the value of the passed in boolean to determine what colour to set the background of the passed in JComponent
	 * @param component	The passed in JComponent
	 * @param isCorrect The passed in boolean value which is switched on
	 * 
	 */
	public void colorChanger(JComponent component, boolean isCorrect)
	{
		int boolInt = isCorrect ? 1 : 0;
		switch(boolInt)
		{
			case 1:
				component.setBackground(Color.GREEN);
				break;
			case 0:
				component.setBackground(Color.ORANGE);
				break;
			default:
				component.setBackground(Color.WHITE);
				break;
		}
	}
	
	/**
	 * Sets the text value of the score label to the current score value in game
	 */
	public void updateScore()
	{
			lblScoreNum.setText("" + game.getCurrentScore());
	}
	
	/**
	 * Sets the text of the accuracy label to the current accuracy stored in label
	 */
	public void updateAccuracy()
	{
		lblAccuracyNum.setText(game.getCurrentAccuracy() + "%");
	}
	
	/**
	 * Upon the answer text field gaining focus, the text within (if any) is deleted and the
	 * background is set to white. Along with this, a dark gray dashed border is set around the text field.
	 * 
	 * Upon the answer text field losing focus, the text is set to "Type here" and the field is given a
	 * gray background and the border is set to null to remove it.
	 */
	public void textResetter()
	{
		tfAnswer.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e) 
			{
				tfAnswer.setText("");
				
				tfAnswer.setBackground(Color.WHITE);
				
				Border border = BorderFactory.createDashedBorder(Color.DARK_GRAY, 10, 5);
				
				tfAnswer.setBorder(border);
				
				
			}
			
		    public void focusLost(FocusEvent e) 
		    {
		    	tfAnswer.setText("Type here");
		    	
		    	tfAnswer.setBackground(Color.CYAN);
		    	
		    	tfAnswer.setBorder(null);
		    }
		});
	}
	
	/**
	 * @return The value of the question text field. For testing purposes
	 */
	public String getQuestionText()
	{
		return tfQuestion.getText();
	}
	
	/**
	 * Set the value of the answer text field to the passed in string
	 * @param answer The passed in string value for the answer text field
	 */
	public void setAnswer(String answer)
	{
		tfAnswer.setText(answer);
	}
	
	/**
	 * @return A boolean that is normally immediately assigned to a variable. This method is for JUnit testing
	 */
	public boolean checkAnswer()
	{
		return game.checkAnswer(tfQuestion.getText(), tfAnswer.getText());
	}
}
