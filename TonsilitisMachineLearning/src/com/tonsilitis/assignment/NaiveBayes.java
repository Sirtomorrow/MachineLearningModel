package com.tonsilitis.assignment;

import java.util.ArrayList;
import java.util.HashMap;

public class NaiveBayes 
{
	private int totalCount; // amount of data inputted i.e. the data set in the excel file
	private static HashMap<String, Integer> hm = new HashMap<>(); // https://www.geeksforgeeks.org/java-util-hashmap-in-java/
	private String temperatureInput, soreThroatInput, achesInput; // values inputed by user in the GUI
	private DataProcessor dp = new DataProcessor();
	private FileProcessor fp = new FileProcessor();
	
	public NaiveBayes(String temperatureInput, String soreThroatInput, String achesInput)
	{	
		dp.checkSoreThroat("No", "Yes");
		// read in the data probabilities and store to a hashmap
		totalCount = fp.getDataCount();
				
		// input of patients that had tonsilitis and had no tonsilitis i.e. the whole set
		hm.put("has tonsilitis",dp.getYesTonsilitis());
		hm.put("no tonsilitis", dp.getNoTonsilitis());
		
		// https://www.techiedelight.com/increment-map-value-java-8/
		// set initial values to 0
		hm.putIfAbsent("has sore throat, has tonsilitis", 0); // sore throat states
		hm.putIfAbsent("has sore throat, no tonsilitis", 0);
		hm.putIfAbsent("no sore throat, has tonsilitis", 0);
		hm.putIfAbsent("no sore throat, no tonsilitis", 0);
		hm.putIfAbsent("has aches, has tonsilitis", 0); // aches states
		hm.putIfAbsent("has aches, no tonsilitis", 0);
		hm.putIfAbsent("no aches, has tonsilitis", 0);
		hm.putIfAbsent("no aches, no tonsilitis", 0);
		hm.putIfAbsent("hot, has tonsilitis", 0); // temperature states
		hm.putIfAbsent("hot, no tonsilitis", 0);
		hm.putIfAbsent("normal, has tonsilitis", 0);
		hm.putIfAbsent("normal, no tonsilitis", 0);
		hm.putIfAbsent("cool, has tonsilitis", 0);
		hm.putIfAbsent("cool, no tonsilitis", 0);
				
		// the users input from the GUI
		this.setTemperatureInput(temperatureInput);
		this.setAchesInput(achesInput);
		this.setSoreThroatInput(soreThroatInput);
		
		// calculate the probability of tonsilitis given user's input
		calculateTonsilitis();
		
	}
	
	public void calculateTonsilitis()
	{
		// values that contain the probabilities of the input given
		int tempState, achesState, soreThroatState;
		
		// check what the user's temperature input is
		if(temperatureInput.equals("Hot"))
		{
			System.out.println(("\nTemperature is hot"));
		}
		else if(temperatureInput.equals("Normal"))
		{
			System.out.println("\nTemperature is normal");
		}
		else
		{
			System.out.println("\nTemperature is cool");
		}
		
		
		// check what the user's aches input is
		if(achesInput.equals("Yes"))
		{
			System.out.println("User has aches");
		}
		else
		{
			System.out.println("User does not have aches");
		}
		
		
		// check what the user's sore throat input is
		if(soreThroatInput.equals("Yes"))
		{
			System.out.println("User has sore throat");
		}
		else
		{
			System.out.println("User does not have sore throat");
		}
		
	}
	
	public String getSoreThroatInput() 
	{
		return soreThroatInput;
	}

	public void setSoreThroatInput(String soreThroatInput) 
	{
		this.soreThroatInput = soreThroatInput;
	}

	public String getTemperatureInput() 
	{
		return temperatureInput;
	}

	public void setTemperatureInput(String temperatureInput) 
	{
		this.temperatureInput = temperatureInput;
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
