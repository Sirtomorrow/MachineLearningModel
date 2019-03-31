/* Author: Ivan Yanez
 */

package com.tonsilitis.assignment;

import java.util.HashMap;

public class NaiveBayes 
{
	private int totalCount; // amount of data inputted i.e. the data set in the excel file
	private static HashMap<String, Integer> hm = new HashMap<>(); // https://www.geeksforgeeks.org/java-util-hashmap-in-java/
	private String temperatureInput, soreThroatInput, achesInput; // values inputed by user in the GUI
	private DataProcessor dp = new DataProcessor();
	private FileProcessor fp = new FileProcessor();
	private String[] optionString = { "No", "Yes" };
	private String[] temperatureString = { "Hot", "Cool", "Normal" };
	
	public NaiveBayes(String temperatureInput, String soreThroatInput, String achesInput)
	{	
		// get the total data inputed
		totalCount = fp.getDataCount();
		
		// get the users input from the GUI
		this.setTemperatureInput(temperatureInput);
		this.setAchesInput(achesInput);
		this.setSoreThroatInput(soreThroatInput);
				
		// set values of the predictors states
		initialiseValues();
		
		// update the values count based on the data entered
		updateValues();
		
		System.out.println(hm.keySet());
		System.out.println(hm.values());
		
		// calculate the probability of tonsilitis given user's input
		calculateTonsilitis();
		
	}
	
	
	// https://www.techiedelight.com/increment-map-value-java-8/
	// update the vaules of the keys based on the counts of each predictor
	public void updateValues()
	{
		// update sore throat, tonsilitis and aches predictor values
		for(int i = 0; i < optionString.length; i++)
		{
			for(int j = 0; j < optionString.length; j++)
			{
				hm.put("SoreThroat"+optionString[i], dp.getSoreThroatCount(optionString[i]));
				hm.put("Aches"+optionString[i], dp.getAchesCount(optionString[i]));
				hm.put("Tonsilitis"+optionString[i], dp.getTonsilitisCount(optionString[i]));
			}
		}
		
		// update temperature predictor values
		for(int i = 0; i < temperatureString.length; i++)
		{
			hm.put(temperatureString[i], dp.getTemperatureCount(temperatureString[i]));
			
		}
	}

	
	// https://www.techiedelight.com/increment-map-value-java-8/
	// set the tonsilitis initially
	public void initialiseValues()
	{
		// store all temperature states counts
		for(int i = 0; i < temperatureString.length; i++)
		{
			hm.putIfAbsent(temperatureString[i], 0);
			
		}
		
		// store both tonsilitis & non-tonsilitis states count
		for(int i = 0; i < optionString.length; i++)
		{
			hm.putIfAbsent("Tonsilitis"+optionString[i], 0);
			hm.putIfAbsent("SoreThroat"+optionString[i], 0);
			hm.putIfAbsent("Aches"+optionString[i], 0);
		}
		
	}
	
	
	// calculate the posterior probability
	public void calculateTonsilitis()
	{
		
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
	
	
	// getters & setters
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
