package com.tonsilitis.assignment;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JFrame implements ActionListener 
{
	private JLabel label1, label2, label3, label4, label5;
	private	JComboBox<String> tempBox, achesBox, soreThroatBox;
	private JPanel panel1, panel2, panel3, panel4, panel5, panel6;
	private JButton submit_button, eval_button;
	private String[] optionString = { "No", "Yes" }; // options for sore throat and aches
	private String[] temperatureString = { "Hot", "Cool", "Normal" }; // options for temperature
	private String temperatureInput, soreThroatInput, achesInput; // the users input
	private NaiveBayes nb = new NaiveBayes("Hot", "No", "No"); // default settings; initially setup the training set
	
	public GUI()
	{
		// set the title
		super("Tonsilitis Program");
		
		// instantiate the labels
		label1 = new JLabel("Temperature: ");
		label2 = new JLabel("Aches: ");
		label3 = new JLabel("Sore Throat: ");
		label4 = new JLabel("TONSILTIS TESTER");
		label5 = new JLabel("");
		label5.setVisible(false);
		label4.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		label5.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
		// instantiate combo boxes
		tempBox = new JComboBox<String>(temperatureString);
		achesBox = new JComboBox<String>(optionString);
		soreThroatBox = new JComboBox<String>(optionString);
		tempBox.setPreferredSize(new Dimension(200, 50)); // set size of buttons
		achesBox.setPreferredSize(new Dimension(240, 50));
		soreThroatBox.setPreferredSize(new Dimension(210, 50));
		
		// instantiate panels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		
		// instantiate buttons
		submit_button = new JButton("Submit");
		eval_button = new JButton("Evaluate");
		submit_button.setPreferredSize(new Dimension(150, 50)); // set size of buttons
		eval_button.setPreferredSize(new Dimension(150, 50));
		
		// add action listeners to the buttons and combo boxes
		submit_button.addActionListener(this);
		eval_button.addActionListener(this);
		tempBox.addActionListener(this);
		achesBox.addActionListener(this);
		soreThroatBox.addActionListener(this);
		
		// add components to panels, then add panels
		panel1.add(label1);
		panel1.add(tempBox);
		panel2.add(label2);
		panel2.add(achesBox);
		panel3.add(label3);
		panel3.add(soreThroatBox);
		panel4.add(submit_button);
		panel4.add(eval_button);
		panel5.add(label4);
		panel6.add(label5);
		add(panel5);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel6);
		
		// set the screens layout
		setLocation(400,100);
		setSize(500, 600);
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	// if button is pressed
	public void actionPerformed(ActionEvent event) 
	{
		// if submit button is pressed
		if(event.getSource() == submit_button)
		{			
			// get the values given by the user through the combo box
			setTemperatureInput(tempBox.getSelectedItem().toString());
			setAchesInput(achesBox.getSelectedItem().toString());
			setSoreThroatInput(soreThroatBox.getSelectedItem().toString());
			
			// pass the input from the boxes to the Naive Bayes classifier
			//  and insert the input to the classifier
			nb = new NaiveBayes(temperatureInput, achesInput, soreThroatInput);
			
			// get the result of tonsilitis in the form of a string "Yes" or "No"
			String result = nb.calculateTonsilitis();

			// show result
			// if user has tonsilitis
			if(result.equals("Yes"))
			{
				label5.setText("User likely has tonsilitis = "+nb.getPercentageYes()+"%");
				label5.setVisible(true);
			}
			// if user doesnt have tonsilitis
			else
			{
				label5.setText("User likely has no tonsilitis = "+nb.getPercentageNo()+"%");
				label5.setVisible(true);
			}
			

		}
		// if evaluate button is pressed
		else if(event.getSource() == eval_button)
		{
			// the accuracy of the program based on the evaluation results
			float evaluationAccuracy;
			
			// evaluate and get the accuracy of the evaluation
			evaluationAccuracy = nb.getEvaluation();
			
			// Show the programs accuracy percentage
			label5.setText("Accuracy rate (based on the evaluation)= "+(evaluationAccuracy*100)+"%");
			label5.setVisible(true);
		}
	}

	// getters & setters
	public String getTemperatureInput() 
	{
		return temperatureInput;
	}

	public void setTemperatureInput(String temperatureInput) 
	{
		this.temperatureInput = temperatureInput;
	}

	public String getSoreThroatInput() 
	{
		return soreThroatInput;
	}

	public void setSoreThroatInput(String soreThroatInput) 
	{
		this.soreThroatInput = soreThroatInput;
	}

	public String getAchesInput() 
	{
		return achesInput;
	}

	public void setAchesInput(String achesInput) 
	{
		this.achesInput = achesInput;
	}
	
	
}
