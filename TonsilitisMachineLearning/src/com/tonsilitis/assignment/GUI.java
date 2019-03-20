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
	private	JComboBox temp_box, aches_box, soreThroat_box;
	private JPanel panel1, panel2, panel3, panel4;
	private JButton submit_button;
	private String[] optionString = { "No", "Yes" };
	
	public GUI()
	{
		// set the title
		super("Tonsilitis Program");
		
		// instantiate the labels
		label1 = new JLabel("Temperature: ");
		label2 = new JLabel("Sore Throat: ");
		label3 = new JLabel("Aches: ");
		
		// instantiate combo boxes
		temp_box = new JComboBox(optionString);
		aches_box = new JComboBox(optionString);
		soreThroat_box = new JComboBox(optionString);
		
		// instantiate panels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		
		// instantiate buttons
		submit_button = new JButton("Submit");
		
		// add action listeners
		submit_button.addActionListener(this);
		temp_box.addActionListener(this);
		aches_box.addActionListener(this);
		soreThroat_box.addActionListener(this);
		
		// add components
		panel1.add(label1);
		panel1.add(temp_box);
		panel2.add(label2);
		panel2.add(aches_box);
		panel3.add(label3);
		panel3.add(soreThroat_box);
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
		
		
		if(event.getSource() == submit_button)
		{
			System.out.println("\nTemperature: "+temp_box.getSelectedItem());
			System.out.println("Aches: "+aches_box.getSelectedItem());
			System.out.println("Sore Throat: : "+soreThroat_box.getSelectedItem());
			
			if(temp_box.getSelectedItem() == "Yes" )
			{
				System.out.println("TYes");
			}
			
			if(temp_box.getSelectedItem() == "No" )
			{
				System.out.println("TNo");
			}
			
			if(aches_box.getSelectedItem() == "Yes" )
			{
				System.out.println("AYes");
			}
			
			if(aches_box.getSelectedItem() == "No" )
			{
				System.out.println("ANo");
			}
			
			if(soreThroat_box.getSelectedItem() == "Yes" )
			{
				System.out.println("SYes");
			}
			
			if(soreThroat_box.getSelectedItem() == "No" )
			{
				System.out.println("SNo");
			}
		}
		
	}
	
	
}
