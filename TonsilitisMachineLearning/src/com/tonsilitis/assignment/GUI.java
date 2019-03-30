package com.tonsilitis.assignment;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI extends JFrame implements ActionListener 
{
	private JLabel label1, label2, label3;
	private	JComboBox tempBox, achesBox, soreThroatBox;
	private JPanel panel1, panel2, panel3, panel4;
	private JButton submit_button;
	private String[] optionString = { "No", "Yes" };
	private String[] temperatureString = { "Hot", "Cool", "Normal" };
	private String temperatureInput, soreThroatInput, achesInput;
	private NaiveBayes nb;
	
	public GUI()
	{
		// set the title
		super("Tonsilitis Program");
		
		// instantiate the labels
		label1 = new JLabel("Temperature: ");
		label2 = new JLabel("Sore Throat: ");
		label3 = new JLabel("Aches: ");
		
		// instantiate combo boxes
		tempBox = new JComboBox(temperatureString);
		achesBox = new JComboBox(optionString);
		soreThroatBox = new JComboBox(optionString);
		
		// instantiate panels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		
		// instantiate buttons
		submit_button = new JButton("Submit");
		
		// add action listeners
		submit_button.addActionListener(this);
		tempBox.addActionListener(this);
		achesBox.addActionListener(this);
		soreThroatBox.addActionListener(this);
		
		// add components
		panel1.add(label1);
		panel1.add(tempBox);
		panel2.add(label2);
		panel2.add(achesBox);
		panel3.add(label3);
		panel3.add(soreThroatBox);
		panel4.add(submit_button);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		
		// set screen layouts
		setLocation(100,100);
		setSize(400, 400);
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER));
	}
	
	public void actionPerformed(ActionEvent event) 
	{
		// if submit button pressed
		if(event.getSource() == submit_button)
		{			
			// get the values given by the user through the combo box
			setTemperatureInput(tempBox.getSelectedItem().toString());
			setAchesInput(achesBox.getSelectedItem().toString());
			setSoreThroatInput(soreThroatBox.getSelectedItem().toString());
			
			// pass the input from the boxes to the Naive Bayes classifier
			getTonsilitisResult();
		}
		
	}
	
	// calculate the result; send the input to Naive Bayes class
	public void getTonsilitisResult()
	{	
		nb = new NaiveBayes(temperatureInput, achesInput, soreThroatInput);
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
