/* Author: Ivan Yanez
 */

package com.tonsilitis.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class NaiveBayes 
{
	private int totalCount; // amount of data inputted i.e. the data set in the excel file
	private static Map<String, Integer> hm1 = new HashMap<>(); // store counts - https://www.geeksforgeeks.org/java-util-hashmap-in-java/
	private static Map<String, Float> hm2 = new HashMap<>(); // store probabilities in floats - https://www.geeksforgeeks.org/java-util-hashmap-in-java/
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
		
		//claculate the probabilities
		calculateProbabilities();
		
		System.out.println(hm1.keySet());
		// System.out.println(hm2.values());
		
		// calculate the probability of tonsilitis given user's input
		// calculateTonsilitis();
		
	}
	
	
	// https://www.techiedelight.com/increment-map-value-java-8/
	// update the vaules of the keys based on the counts of each predictor
	public void updateValues()
	{
		
		// update sore throat, tonsilitis and aches count values
		for(int i = 0; i < optionString.length; i++)
		{
			hm1.put("SoreThroat"+optionString[i], dp.getSoreThroatCount(optionString[i]));
			hm1.put("Aches"+optionString[i], dp.getAchesCount(optionString[i]));
			hm1.put("Tonsilitis"+optionString[i], dp.getTonsilitisCount(optionString[i]));
			
			//  update aches & sore throats states count given tonsilitis
			for(int j = 0; j < optionString.length; j++)
			{
				hm1.put("SoreThroat"+optionString[i]+" & Tonsilitis"+optionString[j], dp.getProbabilities("SoreThroat", optionString[i], optionString[j]));
				hm1.put("Aches"+optionString[i]+" & Tonsilitis"+optionString[j], dp.getProbabilities("Aches", optionString[i], optionString[j]));
			}
		}
		
		// update the temperature count values
		for(int i = 0; i < temperatureString.length; i++)
		{
			hm1.put(temperatureString[i], dp.getTemperatureCount(temperatureString[i]));
			
			// store all temperature states given tonsilitis
			for(int j = 0; j < optionString.length; j++)
			{
				hm1.put(temperatureString[i]+"Yes"+" & Tonsilitis"+optionString[j],  dp.getProbabilities(temperatureString[i] , "Yes" , optionString[j]));
				hm1.put(temperatureString[i]+"No"+" & Tonsilitis"+optionString[j],  dp.getProbabilities(temperatureString[i] , "No", optionString[j]));
			}
		}
		
	}

	
	
	// https://www.techiedelight.com/increment-map-value-java-8/
	// set the tonsilitis initially
	public void initialiseValues()
	{
		// input total counts
		hm1.putIfAbsent("TotalSoreThroat", 0);
		hm1.putIfAbsent("TotalAches", 0);
		hm1.putIfAbsent("TotalHot", 0);
		hm1.putIfAbsent("TotalNormal", 0);
		hm1.putIfAbsent("TotalCool", 0);
		
		// store all temperature states counts
		for(int i = 0; i < temperatureString.length; i++)
		{
			hm1.putIfAbsent(temperatureString[i], 0);
			
			// store all temperature states given tonsilitis
			for(int j = 0; j < optionString.length; j++)
			{
				hm1.putIfAbsent(temperatureString[i]+"Yes"+" & Tonsilitis"+optionString[j], 0);
				hm1.putIfAbsent(temperatureString[i]+"No"+" & Tonsilitis"+optionString[j], 0);
			}
		}
		
		// store both tonsilitis, aches & sore throats states count
		for(int i = 0; i < optionString.length; i++)
		{
			hm1.putIfAbsent("Tonsilitis"+optionString[i], 0);
			hm1.putIfAbsent("SoreThroat"+optionString[i], 0);
			hm1.putIfAbsent("Aches"+optionString[i], 0);
			
			//  store aches & sore throats states count given tonsilitis
			for(int j = 0; j < optionString.length; j++)
			{
				hm1.putIfAbsent("SoreThroat"+optionString[i]+" & Tonsilitis"+optionString[j], 0);
				hm1.putIfAbsent("Aches"+optionString[i]+" & Tonsilitis"+optionString[j], 0);
			}
		}
		
	}
	
	
	// calculate probabilities -  https://javatutorial.net/java-iterate-hashmap-example  -  https://stackoverflow.com/questions/5067942/what-is-the-best-way-to-extract-the-first-word-from-a-string-in-java
	public void calculateProbabilities()
	{	
		
		/* get probability of sore throat, aches and temperature states
		for(Map.Entry<String, Integer> entry : hm1.entrySet())
		{
			String key = entry.getKey();
			float entryValue = entry.getValue();
			String[] splitted = key.split(" ", 3);
			String firstword = splitted[0];;
			System.out.println(hm1.get("SoreThroat"));
			
			// if the key had 3 words
			if(splitted.length == 3)
			{
				if(firstword.contains("SoreThroat"))
				{
					// float value = (float) entryValue / dp.getSO;
					System.out.println();
					hm2.put(key, (float) 2.65);
				}
				else if(firstword.contains("Aches"))
				{
					
				}
				else if(firstword.contains("Hot"))
				{

				}
				else if(firstword.contains("Normal"))
				{
					
				}
				else if(firstword.contains("Cool"))
				{
					
				}
			}
		}
		
		for(Map.Entry<String, Float> set : hm2.entrySet())
		{
			// System.out.println("float: "+set.getKey());
		} */
	} 
	
	// calculate the probability of tonsilitis
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
