package me.oliver.textgame.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

@SuppressWarnings("serial")
public class ScoreViewerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList<String> list;
	private JButton okButton;
	private JPanel buttonPane;
	private JDialog dialog;
	private ArrayList<String> statData;

	/**
	 * Create the dialog.
	 */
	public ScoreViewerDialog() {
		statData = new ArrayList<String>();
		dialog = new JDialog(dialog, "Previous game stats");
		dialog.setBounds(100, 100, 250, 160);
		dialog.setResizable(false);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}});
			}
		}
		
		try
		{
			FileReader fileReader = 
					new FileReader("/Score.txt");
	        BufferedReader file = 
	        		new BufferedReader(fileReader);
	        
	        String line;

	        while ((line = file.readLine()) != null) 
	        {
	        	statData.add(line);
	        	
	        }
	        
	        String level, accuracy, score, time;
        	
        	level = statData.get(0);
        	accuracy = statData.get(1);
        	score = statData.get(2);
        	time = statData.get(3);
        	
        	String[] data = {level, accuracy, score, time};
        	
        	list = new JList<String>(data);
			contentPanel.add(list);
	        
	        file.close();
	        System.out.println("Congrats");
		}
		catch (FileNotFoundException ex){
			File file = new File("/Score.txt");
			System.out.println(file.exists());
			System.out.println("Couldn't find file");
			
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

}
