package com.tonsilitis.assignment;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener 
{
	private JLabel label1, label2, label3;
	private	JComboBox box1, box2, box3;
	private JPanel panel1, panel2, panel3, panel4;
	private JButton button1;
	private String[] optionString = { "No", "Yes" };
	
	public GUI(String title)
	{
		//set the title
		super("My Screen");
		
		//instantiate the labels
		label1 = new JLabel("Temperature: ");
		label2 = new JLabel("Sore Throat: ");
		label3 = new JLabel("Aches: ");
		
		//instantiate combo boxes
		box1 = new JComboBox(optionString);
		box2 = new JComboBox(optionString);
		box3 = new JComboBox(optionString);
		
		//instantiate panels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		
		//instantiate buttons
		button1 = new JButton("Submit");
		
		//add components
		panel1.add(label1);
		panel1.add(box1);
		panel2.add(label2);
		panel2.add(box2);
		panel3.add(label3);
		panel3.add(box3);
		panel4.add(button1);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		
		//set screen layouts
		setLocation(100,100);
		setSize(200, 300);
		setVisible(true);
		setLayout(new FlowLayout());
	}
	
	public void actionPerformed(ActionEvent event) 
	{
		// TODO Auto-generated method stub
		
	}
	
	
}
