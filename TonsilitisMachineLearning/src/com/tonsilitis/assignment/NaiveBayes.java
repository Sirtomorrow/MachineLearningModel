/* Author: Ivan Yanez
 */

package com.tonsilitis.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NaiveBayes 
{
	private static Map<String, Integer> hm1; // store the counts - concept take from https://www.geeksforgeeks.org/java-util-hashmap-in-java/
	private static Map<String, Float> hm2; // store the probabilities - concept take from https://www.geeksforgeeks.org/java-util-hashmap-in-java/
	private String temperatureInput, soreThroatInput, achesInput, tonsilitisInput; // values inputed by user in the GUI
	private DataProcessor dp;
	private float	 percentageYes, percentageNo; // percentage of the yes tonsilitis or no tonsilitis
	private ArrayList<Data> evalSet = new ArrayList<Data>();
	private String[] optionString = { "No", "Yes" }; // options for aches and sore throat
	private String[] temperatureString = { "Hot", "Cool", "Normal" }; // options for temperature
	private float probabiltiyYes, probabiltiyNo; // probability of having tonsilitis or probability of not having tonsilitis
	
	NaiveBayes(String temperatureInput, String soreThroatInput, String achesInput)
	{
		// instantiate the hashmap and the processors
		hm1 = new HashMap<>();
		hm2 = new HashMap<>();
		dp = new DataProcessor();
		
		// get the users input from the GUI
		this.temperatureInput = temperatureInput;
		this.achesInput = achesInput;
		this.soreThroatInput = soreThroatInput;
		
		// get the various predictors count based on the data from the set and store in the hashmap
		getCount();
		
		// calculate the probabilities from the count hashmap
		calculateProbabilities();
		
	}
	
	// evaluate the evaluation set
	public float getEvaluation()
	{	
		float result = 0; // probability of correct rows based on the evaluation results
		int correct = 0; // amount of correct rows
		String tonsilitisResult;
		
		// evaluationSet
		evalSet = dp.getEvaluationSet();
		
		// loop through evaluation
		for(int i = 0; i < evalSet.size(); i++)
		{
			setAchesInput(evalSet.get(i).getAches());
			setSoreThroatInput(evalSet.get(i).getSoreThroat());
			setTemperatureInput(evalSet.get(i).getTemperature());
			setTonsilitisInput(evalSet.get(i).getTonsilitis());
			
			System.out.println("Evaluating ...   Temperature="+getTemperatureInput()+
											   "   Aches="+getAchesInput()+
											   "   SoreThroat="+getSoreThroatInput());
			
			// expected tonsilitis outcome
			System.out.println("\nExpected tonsilitis outcome: "+getTonsilitisInput());
			
			// get the result of the NaiveBayes calculations i.e. has tonsilitis or no tonsilitis
			tonsilitisResult = calculateTonsilitis();
			
			// calculate chances of tonsilitis
			System.out.println("Actual tonsilitis outcome: "+tonsilitisResult+"\n");
			
			// if the tonsilitis given matches the result
			if(getTonsilitisInput().equals(tonsilitisResult))
			{
				// increment
				correct++;
			}
		}
		
		// accuracy of the result
		result =  (float) correct / evalSet.size();
		
		// return the result
		return result;

	}
	
	
	
	// https://www.techiedelight.com/increment-map-value-java-8/
	// get the count of the keys/predictors
	public void getCount()
	{
		// get sore throat, tonsilitis and aches count values
		for(int i = 0; i < optionString.length; i++)
		{
			// get the counts of the predictors based on the states i.e. yes/no
			hm1.put("SoreThroat"+optionString[i], dp.getSoreThroatCount(optionString[i]));
			hm1.put("Aches"+optionString[i], dp.getAchesCount(optionString[i]));
			hm1.put("Tonsilitis"+optionString[i], dp.getTonsilitisCount(optionString[i]));
			
			//  get aches & sore throats states count given tonsilitis
			for(int j = 0; j < optionString.length; j++)
			{
				// get counts of the predictors given tonsilitis
				hm1.put("SoreThroat"+optionString[i]+" & Tonsilitis"+optionString[j], dp.getCountGivenTonsilitis("SoreThroat", optionString[i], optionString[j]));
				hm1.put("Aches"+optionString[i]+" & Tonsilitis"+optionString[j], dp.getCountGivenTonsilitis("Aches", optionString[i], optionString[j]));
			}
		}
		
		// get the temperature count values
		// loop through temperature states
		for(int i = 0; i < temperatureString.length; i++)
		{
			// get temperature count for different statess
			hm1.put(temperatureString[i], dp.getTemperatureCount(temperatureString[i]));
			
			// loop thorugh temperature states gvien tonsilitis
			for(int j = 0; j < optionString.length; j++)
			{
				// get counts of the predictors given tonsilitis
				hm1.put(temperatureString[i]+" & Tonsilitis"+optionString[j],  dp.getCountGivenTonsilitis("Temperature",temperatureString[i], optionString[j]));
			}
		}
	
	}

	

	
	// calculate probabilities
	public void calculateProbabilities()
	{			
		// get the probability of everyone that had tonsilitis or no tonsiliits
		hm2.put("TonsilitisYes", ( (float) hm1.get("TonsilitisYes") / dp.getTotalCount() ));
		hm2.put("TonsilitisNo", ( (float) hm1.get("TonsilitisNo") / dp.getTotalCount() ));
		
		
		// loop through the count hashmap
		for(Map.Entry<String, Integer> entry : hm1.entrySet())
		{
			// START - https://stackoverflow.com/questions/5067942/what-is-the-best-way-to-extract-the-first-word-from-a-string-in-java
			// get the key
			String key = entry.getKey();
			
			// get the value of the key
			int value = entry.getValue();
			
			// split the key into a max of 3 words
			String[] splitted = key.split(" ", 3);
			
			// get the firstword of the splitted, which is the predictor
			String firstword = splitted[0];
			// END - https://stackoverflow.com/questions/5067942/what-is-the-best-way-to-extract-the-first-word-from-a-string-in-java
			
			
			// if the key had 3 words, which implies they had 2 predictors e.g. "soreThroatNo & tonsilitisYes"
			if(splitted.length == 3)
			{
				// the tonsilitis state
				String tonsilitisState = splitted[2];
				
				// if the predictor is sore throat
				if(firstword.contains("SoreThroat"))
				{
					// if the person had a sore throat e.g. firstword = "soreThroatYes"
					if(firstword.contains("Yes"))
					{
						// if the person had tonsilitis
						if(tonsilitisState.contains("Yes"))
						{
							// divide the value of the entry by the people that had tonsilitis
							float probability = (float) value/hm1.get("TonsilitisYes");
							
							// put the key and probability to the probability hashmap
							hm2.put(key, probability);
						}
						// if the person had no tonsilitis
						else
						{
							// divide the value of the entry by the people that had no tonsilitis
							float probability = (float) value/hm1.get("TonsilitisNo");
						
							// put the key and probability to the probability hashmap
							hm2.put(key, probability);
						}
					}
					// if the person didnt have a sore throat  e.g. firstword = "soreThroat=No"
					else if(firstword.contains("No"))
					{
						// if the person had tonsilitis
						if(tonsilitisState.contains("Yes"))
						{
							// divide the value of the entry by the people that had tonsilitis
							float probability = (float) value/hm1.get("TonsilitisYes");
							
							// put the key and probability to the probability hashmap
							hm2.put(key, probability);
						}
						// if the person had no tonsilitis
						else
						{
							// divide the value of the entry by the people that had no tonsilitis
							float probability = (float) value/hm1.get("TonsilitisNo");
						
							// put the key and probability to the probability hashmap
							hm2.put(key, probability);
						}
					}
						
				}
				
				
				
				// if the predictor is aches
				else if(firstword.contains("Aches"))
				{
					// if the person had aches
					if(firstword.contains("Yes"))
					{
						// if the person had tonsilitis
						if(tonsilitisState.contains("Yes"))
						{
							// divide the value of the entry by the people that had tonsilitis
							float probability = (float) value/hm1.get("TonsilitisYes");
							
							// put the key and probability to the probability hashmap
							hm2.put(key, probability);
						}
						// if the person had no tonsilitis
						else
						{
							// divide the value of the entry by the people that had no tonsilitis
							float probability = (float) value/hm1.get("TonsilitisNo");
						
							// put the key and probability to the probability hashmap
							hm2.put(key, probability);
						}
					}
					// if the person had no aches
					else if(firstword.contains("No"))
					{
						// if the person had tonsilitis
						if(tonsilitisState.contains("Yes"))
						{
							// divide the value of the entry by the people that had tonsilitis
							float probability = (float) value/hm1.get("TonsilitisYes");
							
							// put the key and probability to the probability hashmap
							hm2.put(key, probability);
						}
						// if the person had no tonsilitis
						else
						{
							// divide the value of the entry by the people that had no tonsilitis
							float probability = (float) value/hm1.get("TonsilitisNo");
						
							// put the key and probability to the probability hashmap
							hm2.put(key, probability);
						}
					}
				}
				
				
				// if temperature predictor is hot
				else if(firstword.contains("Hot"))
				{
					// if the person had tonsilitis
					if(tonsilitisState.contains("Yes"))
					{
						// divide the value of the entry by the people that had tonsilitis
						float probability = (float) value/hm1.get("TonsilitisYes");
						
						// put the key and probability to the probability hashmap
						hm2.put(key, probability);
					}
					// if the person had no tonsilitis
					else
					{
						// divide the value of the entry by the people that had no tonsilitis
						float probability = (float) value/hm1.get("TonsilitisNo");
					
						// put the key and probability to the probability hashmap
						hm2.put(key, probability);
					}
				}
				// if temperature predictor is normal
				else if(firstword.contains("Normal"))
				{
					// if the person had tonsilitis
					if(tonsilitisState.contains("Yes"))
					{
						// divide the value of the entry by the people that had tonsilitis
						float probability = (float) value/hm1.get("TonsilitisYes");
						
						// put the key and probability to the probability hashmap
						hm2.put(key, probability);
					}
					// if the person had no tonsilitis
					else
					{
						// divide the value of the entry by the people that had no tonsilitis
						float probability = (float) value/hm1.get("TonsilitisNo");
					
						// put the key and probability to the probability hashmap
						hm2.put(key, probability);
					}
				}
				// if temperature predictor is cool
				else if(firstword.contains("Cool"))
				{
					// if the person had tonsilitis
					if(tonsilitisState.contains("Yes"))
					{
						// divide the value of the entry by the people that had tonsilitis
						float probability = (float) value/hm1.get("TonsilitisYes");
						
						// put the key and probability to the probability hashmap
						hm2.put(key, probability);
					}
					// if the person had no tonsilitis
					else
					{
						// divide the value of the entry by the people that had no tonsilitis
						float probability = (float) value/hm1.get("TonsilitisNo");
					
						// put the key and probability to the probability hashmap
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
			  temperatureYes = null, 
			  soreThroatYes = null, 
			  achesYes = null, 
			  temperatureNo = null, 
			  soreThroatNo = null, 
			  achesNo = null,
			  total = null;
		
		// check what the user's temperature input is
		if(temperatureInput.equals("Hot"))
		{
			temperatureYes = hm2.get("Hot & TonsilitisYes");
			temperatureNo =  hm2.get("Hot & TonsilitisNo");
			
			// if any of the input is 0, set to 1
			if(temperatureYes == 0)
			{
				temperatureYes = (float) 1;			
			}
			else if(temperatureNo == 0)
			{
				temperatureNo = (float) 1;
			}
			
		}
		else if(temperatureInput.equals("Normal"))
		{
			temperatureYes = hm2.get("Normal & TonsilitisYes");
			temperatureNo =  hm2.get("Normal & TonsilitisNo");
			
			// if any of the input is 0, set to 1
			if(temperatureYes == 0)
			{
				temperatureYes = (float) 1;		
			}
			else if(temperatureNo == 0)
			{
				temperatureNo = (float) 1;
			}
		}
		else if(temperatureInput.equals("Cool"))
		{
			temperatureYes = hm2.get("Cool & TonsilitisYes");
			temperatureNo =  hm2.get("Cool & TonsilitisNo");
			// if any of the input is 0, set to 1
			if(temperatureYes == 0)
			{
				temperatureYes = (float) 1;		
			}
			else if(temperatureNo == 0)
			{
				temperatureNo = (float) 1;
			}
		}
		
		
		// check what the user's aches input is
		if(achesInput.equals("Yes"))
		{
			achesYes = hm2.get("AchesYes & TonsilitisYes");
			achesNo = 	hm2.get("AchesYes & TonsilitisNo");
			
			// if any of the input is 0, set to 1
			if(achesYes == 0)
			{
				achesYes = (float) 1;		
			}
			else if(achesNo == 0)
			{
				achesNo = (float) 1;
			}
		}
		else if(achesInput.equals("No"))
		{
			achesYes = hm2.get("AchesNo & TonsilitisYes");
			achesNo = 	hm2.get("AchesNo & TonsilitisNo");
			
			// if any of the input is 0, set to 1
			if(achesYes == 0)
			{
				achesYes = (float) 1;		
			}
			else if(achesNo == 0)
			{
				achesNo = (float) 1;
			}
		}
		
		
		// check what the user's sore throat input is
		if(soreThroatInput.equals("Yes"))
		{
			soreThroatYes = hm2.get("SoreThroatYes & TonsilitisYes");
			soreThroatNo = hm2.get("SoreThroatYes & TonsilitisNo");
			
			// if any of the input is 0, set to 1
			if(soreThroatYes == 0)
			{
				soreThroatYes = (float) 1;		
			}
			else if(soreThroatNo == 0)
			{
				soreThroatNo = (float) 1;
			}
		}
		else if(soreThroatInput.equals("No"))
		{
			soreThroatYes = hm2.get("SoreThroatNo & TonsilitisYes");
			soreThroatNo = hm2.get("SoreThroatNo & TonsilitisNo");
			
			// if any of the input is 0, set to 1
			if(soreThroatYes == 0)
			{
				soreThroatYes = (float) 1;		
			}
			else if(soreThroatNo == 0)
			{
				soreThroatNo = (float) 1;
			}
		}

			
		// calculate the probability of having tonsilits & not having tonsilitis
		probabilityYes = (temperatureYes * soreThroatYes * achesYes * hm2.get("TonsilitisYes"));
		probabilityNo = (temperatureNo * soreThroatNo * achesNo * hm2.get("TonsilitisNo"));
		System.out.println("probabilityYes: "+temperatureYes+"*"+soreThroatYes+"*"+achesYes+"*"+hm2.get("TonsilitisYes"));
		System.out.println("probabilityNo: "+temperatureNo+"*"+soreThroatNo+"*"+achesNo+"*"+hm2.get("TonsilitisNo"));
		
		total = probabilityYes + probabilityNo;
		
		setPercentageYes( probabilityYes / total);
		setPercentageNo( probabilityNo / total);
		
		// print out to user
		if(probabilityYes > probabilityNo)
		{
			result = "Yes";
		}
		else
		{
			result = "No";
		}
		
		// https://www.baeldung.com/java-round-decimal-number
		// show the percentage of the result
		System.out.printf("\nChances of tonsilitis is %.3f\n", probabilityYes);
		System.out.printf("Chances of no tonsilitis is %.3f\n", probabilityNo);
		
		return result;
	}
	
	
	
	// getters & setters
	public int getEvalSetLength()
	{
		return evalSet.size();
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

	public String getTonsilitisInput() {
		return tonsilitisInput;
	}

	public void setTonsilitisInput(String tonsilitisInput) {
		this.tonsilitisInput = tonsilitisInput;
	}

	public float getProbabiltiyNo() {
		return probabiltiyNo;
	}

	public void setProbabiltiyNo(float probabiltiyNo) {
		this.probabiltiyNo = probabiltiyNo;
	}

	public float getProbabiltiyYes() {
		return probabiltiyYes;
	}

	public void setProbabiltiyYes(float probabiltiyYes) {
		this.probabiltiyYes = probabiltiyYes;
	}

	public Float getPercentageYes() 
	{
		return percentageYes*100;
	}

	public void setPercentageYes(Float percentageYes) 
	{
		this.percentageYes = percentageYes;
	}
	
	public Float getPercentageNo() 
	{
		return percentageNo*100;
	}

	public void setPercentageNo(Float percentageNo) 
	{
		this.percentageNo = percentageNo;
	}	
	
}