/* Author: Ivan Yanez
 */

package com.tonsilitis.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NaiveBayes 
{
	private int totalCount; // amount of data inputted i.e. the data set in the excel file
	private static Map<String, Integer> hm1; // store counts - https://www.geeksforgeeks.org/java-util-hashmap-in-java/
	private static Map<String, Float> hm2; // store probabilities in floats - https://www.geeksforgeeks.org/java-util-hashmap-in-java/
	private String temperatureInput, soreThroatInput, achesInput, tonsilitisInput; // values inputed by user in the GUI
	private DataProcessor dp;
	private FileProcessor fp;
	private ArrayList<Data> evalSet = new ArrayList<Data>();
	private String[] optionString = { "No", "Yes" };
	private String[] temperatureString = { "Hot", "Cool", "Normal" };
	
	NaiveBayes(String temperatureInput, String soreThroatInput, String achesInput)
	{
		// instantiate
		hm1 = new HashMap<>();
		hm2 = new HashMap<>();
		dp = new DataProcessor();
		fp = new FileProcessor();
		
		// get the total data inputed
		totalCount = fp.getDataCount();
		
		// get the users input from the GUI
		this.temperatureInput = temperatureInput;
		this.achesInput = achesInput;
		this.soreThroatInput = soreThroatInput;
				
		// update the values count based on the data entered
		getCount();
		
		// calculate the probabilities
		calculateProbabilities();
		
		// calculate tonsilitis
		// calculateTonsilitis();
		
		// evaluate
		getEvaluation();
	}
	
	// evaluate the evaluation set
	public void getEvaluation()
	{	
		// evaluationSet
		evalSet = dp.getEvaluationSet();
		
		for(int i = 0; i < evalSet.size(); i++)
		{
			setAchesInput(evalSet.get(i).getAches());
			setSoreThroatInput(evalSet.get(i).getSoreThroat());
			setTemperatureInput(evalSet.get(i).getTemperature());
			setTonsilitisInput(evalSet.get(i).getTonsilitis());
			
			calculateTonsilitis();
		}

	}
	
	
	
	// https://www.techiedelight.com/increment-map-value-java-8/
	// get the count of the keys
	public void getCount()
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
				hm1.put(temperatureString[i]+" & Tonsilitis"+optionString[j],  dp.getTempProbabilities(temperatureString[i], optionString[j]));
			}
		}
	
	}

	

	
	// calculate probabilities -  https://javatutorial.net/java-iterate-hashmap-example  -  https://stackoverflow.com/questions/5067942/what-is-the-best-way-to-extract-the-first-word-from-a-string-in-java
	public void calculateProbabilities()
	{			
		// get the probability of everyone that had tonsilitis or no tonsiliits
		hm2.put("TonsilitisYes", (float) hm1.get("TonsilitisYes") / totalCount);
		hm2.put("TonsilitisNo", (float) hm1.get("TonsilitisNo") / totalCount);
		
		// get probability of sore throat, aches and temperature states
		for(Map.Entry<String, Integer> entry : hm1.entrySet())
		{
			String key = entry.getKey();
			int value = entry.getValue();
			String[] splitted = key.split(" ", 3);
			String firstword = splitted[0];
			
			// if the key had 3 words i.e. had two predictors
			if(splitted.length == 3)
			{
				if(firstword.contains("SoreThroat"))
				{
					if(firstword.contains("Yes"))
					{
						float probability = (float) value/hm1.get("SoreThroatYes");
						hm2.put(key, probability);
					}
					else if(firstword.contains("No"))
					{
						float probability = (float) value/hm1.get("SoreThroatNo");
						hm2.put(key, probability);
					}
						
				}
				else if(firstword.contains("Aches"))
				{
					if(firstword.contains("Yes"))
					{
						float probability = (float) value/hm1.get("AchesYes");
						hm2.put(key, probability);
					}
					else if(firstword.contains("No"))
					{
						float probability = (float) value/hm1.get("AchesNo");
						hm2.put(key, probability);
					}
				}
				else if(firstword.contains("Hot"))
				{
					String tempword = splitted[2];
					
					if(tempword.contains("Yes"))
					{
						float probability = (float) value/hm1.get("Hot");
						hm2.put(key, probability);
					}
					else if(tempword.contains("No"))
					{
						float probability = (float) value/hm1.get("Hot");
						hm2.put(key, probability);
					}
				}
				else if(firstword.contains("Normal"))
				{
					String tempword = splitted[2];
					
					if(tempword.contains("Yes"))
					{
						float probability = (float) value/hm1.get("Normal");
						hm2.put(key, probability);
					}
					else if(tempword.contains("No"))
					{
						float probability = (float) value/hm1.get("Normal");
						hm2.put(key, probability);
					}
				}
				else if(firstword.contains("Cool"))
				{
					String tempword = splitted[2];
					
					if(tempword.contains("Yes"))
					{
						float probability = (float) value/hm1.get("Cool");
						hm2.put(key, probability);
					}
					else if(tempword.contains("No"))
					{
						float probability = (float) value/hm1.get("Cool");
						hm2.put(key, probability);
					}
				}
			}
		}
		
	} 
	
	
	
	// calculate the probability of tonsilitis
	public String calculateTonsilitis()
	{
		String result = "";
		Float probabilityYes = null, 
			  probabilityNo = null, 
			  TInput = null, 
			  StInput = null, 
			  AInput = null, 
			  OppT = null, 
			  OppSt = null, 
			  OppA = null;
		
		// check what the user's temperature input is
		if(temperatureInput.equals("Hot"))
		{
			TInput = hm2.get("Hot & TonsilitisYes");
			OppT =  hm2.get("Hot & TonsilitisNo");
		}
		else if(temperatureInput.equals("Normal"))
		{
			TInput = hm2.get("Normal & TonsilitisYes");
			OppT =  hm2.get("Normal & TonsilitisNo");
		}
		else if(temperatureInput.equals("Cool"))
		{
			TInput = hm2.get("Cool & TonsilitisYes");
			OppT =  hm2.get("Cool & TonsilitisNo");
		}
		
		
		// check what the user's aches input is
		if(achesInput.equals("Yes"))
		{
			AInput = hm2.get("AchesYes & TonsilitisYes");
			OppA = 	hm2.get("AchesYes & TonsilitisNo");
		}
		else if(achesInput.equals("No"))
		{
			AInput = hm2.get("AchesNo & TonsilitisYes");
			OppA = 	hm2.get("AchesNo & TonsilitisNo");
		}
		
		
		// check what the user's sore throat input is
		if(soreThroatInput.equals("Yes"))
		{
			StInput = hm2.get("SoreThroatYes & TonsilitisYes");
			OppSt = hm2.get("SoreThroatYes & TonsilitisNo");
		}
		else if(soreThroatInput.equals("No"))
		{
			StInput = hm2.get("SoreThroatNo & TonsilitisYes");
			OppSt = hm2.get("SoreThroatNo & TonsilitisNo");
		}
		
		probabilityYes = TInput * StInput * AInput * hm2.get("TonsilitisYes");
		probabilityNo = OppT * OppSt * OppA * hm2.get("TonsilitisNo");
		
		
		// print out to user
		if(probabilityYes > probabilityNo)
		{
			result = "User likely has tonsilitis";
		}
		else
		{
			result = "User likely has no tonsilitis";
		}
		
		return result;
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

	public String getTonsilitisInput() {
		return tonsilitisInput;
	}

	public void setTonsilitisInput(String tonsilitisInput) {
		this.tonsilitisInput = tonsilitisInput;
	}	
	
	
}
