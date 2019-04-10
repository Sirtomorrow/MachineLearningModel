/* Author: Ivan Yanez
 */

package com.tonsilitis.assignment;

import java.util.ArrayList;

public class DataProcessor 
{
	private ArrayList<Data> trainingSet = new ArrayList<Data>(); // list of the training data instances
	private ArrayList<Data> evalSet = new ArrayList<Data>(); // list of the evaluation data instances
	private FileProcessor fp = new FileProcessor();
	private int totalCount; 
	private String trainingFile = "datainput.csv"; // training file
	private String evaluationFile = "evaluateinput.csv"; // evaluation file
	
	// constructor
	DataProcessor()
	{
		// read the training file
		fp.readFile(trainingFile);
		
		// get the training list from file processor
		trainingSet = fp.returnList();
		
		// set the total amount of list
		setTotalCount(trainingSet.size());
		
		// close the training file
		fp.closeFile();
		
	}
	
	
	// evaluate the program on its accurracy
	public ArrayList<Data> getEvaluationSet()
	{	
		// read the evaluation file
		fp.readFile(evaluationFile);
		
		// get the evaluation list from file processor
		evalSet = fp.returnEvalList();
		
		// close the evaluation file
		fp.closeFile();
		
		// return the evaluation set
		return evalSet;
	}
	
	
	// get the count of all the temperature states i.e people who were cool, normal or hot
	public int getTemperatureCount(String state)
	{
		int count = 0;
		
		// loop through the trainingSet
		for(Data d: trainingSet)
		{
			// if the temperature of the instance matches the state 
			if(d.getTemperature().equals(state))
			{
				
				// increment by 1
				count++;
			}
		}
		
		// return the count result
		return count;
	}
	
	
	// get the count of all the aches states i.e. those that had aches or those that didnt have aches
	public int getAchesCount(String state)
	{
		int count = 0;
		
		// loop through the trainingSet
		for(Data d: trainingSet)
		{
			// if aches of the instance matches the state
			if(d.getAches().equals(state))
			{
				
				// increment by 1
				count++;
			}
		}
		
		// return the count result
		return count;
	}
	
	
	// get the count of all sore throat states i.e. those that had a sore throat or not
	public int getSoreThroatCount(String state)
	{
		int count = 0;
		
		// loop through the trainingSet
		for(Data d: trainingSet)
		{
			// if sore throat instance matches the state
			if(d.getSoreThroat().equals(state))
			{
				
				// increment by 1
				count++;
			}
		}
		
		// return the count result
		return count;
	}

	// get the count of all tonsilitis states i.e. has tonsilitis or no tonsilitis
	public int getTonsilitisCount(String state)
	{
		int count = 0;
		
		// loop through the trainingSet
		for(Data d: trainingSet)
		{
			// if tonsilitis instance matches the state 
			if(d.getTonsilitis().equals(state))
			{
				
				// increment
				count++;
			}
		}
		
		// return the count result
		return count;
	}

	
	// get the count of aches or sore throat states given tonsilitis state, e.g. aches=Yes & tonsilitis=No
	public int getCountGivenTonsilitis(String predictor, String predictorState, String tonsilitisState)
	{
		int count = 0;
		
		// if the predictor is sore throat
		if(predictor.equals("SoreThroat"))
		{
			
			// loop through the trainingSet
			for(Data d: trainingSet)
			{
				// if tonsilitis state matches sore throat state, e.g. tonsilitis=Yes & sorethroat=Yes
				if(d.getTonsilitis().equals(tonsilitisState) && d.getSoreThroat().equals(predictorState))
				{
					// increment the count
					count++;
				}
			}
			
		}
		// if the predictor is aches
		else if(predictor.equals("Aches"))
		{
			
			// loop through the trainingSet
			for(Data d: trainingSet)
			{
				// if tonsilitis state matches aches state, e.g. tonsilitis=Yes &  aches=Yes
				if(d.getTonsilitis().equals(tonsilitisState) && d.getAches().equals(predictorState))
				{
					// increment the count
					count++;
				}
			}
		}
		
		// return the count result
		return count;
	}
	
	// get the count of temperature states given tonsilitis state
	public int getTempCountGivenTonsilitis(String predictor, String tonsilitisState)
	{
		int count = 0;
		
		// if the predictor is hot
		if(predictor.equals("Hot"))
		{
			
			// loop through the trainingSet
			for(Data d: trainingSet)
			{
				// if tonsilitis state matches hot, e.g. tonsilitis=Yes & temperature=Hot
				if(d.getTemperature().equals("Hot") && d.getTonsilitis().equals(tonsilitisState))
				{
					// increment the count
					count++;
				}
			}
			
		}
		// if the predictor is normal
		else if(predictor.equals("Normal"))
		{
			
			// loop through the trainingSet
			for(Data d: trainingSet)
			{
				// if tonsilitis state matches normal, e.g. tonsilitis=Yes & temperature=Normal
				if(d.getTemperature().equals("Normal") && d.getTonsilitis().equals(tonsilitisState))
				{
					// increment count
					count++;
				}
			}
		}
		// if the predictor is cool
		else if(predictor.equals("Cool"))
		{
			
			// loop through the trainingSet
			for(Data d: trainingSet)
			{
				// if tonsilitis state matches cool, e.g. tonsilitis=Yes & temperature=Cool
				if(d.getTemperature().equals("Cool") && d.getTonsilitis().equals(tonsilitisState))
				{
					// increment count
					count++;
				}
			} 
		}
		
		// return the count result
		return count;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
