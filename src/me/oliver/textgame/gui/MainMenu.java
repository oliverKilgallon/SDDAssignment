package me.oliver.textgame.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import me.oliver.textgame.controllers.EditGameStats;
import me.oliver.textgame.entities.StatisticType;


@SuppressWarnings("serial")
public class MainMenu extends JFrame{

	private JPanel contentPane;
	private JPanel panel;
	
	private JLabel lblTypeThatText;
	private JPanel panelButtons;
	
	private JButton btnPlay;
	private JButton btnShowHiscore;
	private JButton btnQuit;
	
	private JFrame frame;
	private JPanel panelImage;
	//private JLabel lblImageHolder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					new EditGameStats(StatisticType.ACCURACY, String.valueOf( "0") );
					
					new EditGameStats(StatisticType.LEVEL, String.valueOf( 0) );
					
					new EditGameStats(StatisticType.SCORE, String.valueOf( 0) );
					
					new EditGameStats(StatisticType.COMPLETION_TIME, String.valueOf("00:00"));
					
					new MainMenu();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		frame = this;
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		lblTypeThatText = new JLabel("Type That Text!");
		panel.add(lblTypeThatText);
		
		panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.CENTER);
		
		btnPlay = new JButton("Play");
		panelButtons.add(btnPlay);
		
		btnPlay.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer[] difficulties = 
					{
						1,
						2,
						3,
						4,
						5
					};
				int difficulty = (int) JOptionPane.showInputDialog(frame, "Select difficulty:", "Difficulty", JOptionPane.PLAIN_MESSAGE, null, difficulties, "Easy");
				new MainGame(difficulty);
				frame.dispose();
			}
		});
		
		btnShowHiscore = new JButton("Show Score");
		panelButtons.add(btnShowHiscore);
		
		btnShowHiscore.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new ScoreViewerDialog();
			}});
		
		btnQuit = new JButton("Quit");
		panelButtons.add(btnQuit);
		
		panelImage = new JPanel();
		contentPane.add(panelImage, BorderLayout.SOUTH);
		
		btnQuit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		});
		}
}