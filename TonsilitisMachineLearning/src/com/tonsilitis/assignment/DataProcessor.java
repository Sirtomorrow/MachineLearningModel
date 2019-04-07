/* Author: Ivan Yanez
 */

package com.tonsilitis.assignment;

import java.util.ArrayList;

public class DataProcessor 
{
	private ArrayList<Data> trainingSet = new ArrayList<Data>(); // list of the data instances
	private FileProcessor fp = new FileProcessor();
	
	DataProcessor()
	{
		// read the file
		fp.readFile();
		
		// get the list from file processor
		trainingSet = fp.returnList();
		
		// close the file
		fp.closeFile();
	}
	
	
	// get the count of all the temperature states
	public int getTemperatureCount(String predictor)
	{
		int count = 0;
		
		for(Data d: trainingSet)
		{
			// if this matches the predictor and the state
			if(d.getTemperature().equals(predictor))
			{
				count++;
			}
		}
		return count;
	}
	
	// get the count of all the aches states
	public int getAchesCount(String predictor)
	{
		int count = 0;
		
		for(Data d: trainingSet)
		{
			// if this matches the predictor and the state
			if(d.getAches().equals(predictor))
			{
				count++;
			}
		}
		
		return count;
	}
	
	// get the count of all sore throat states
	public int getSoreThroatCount(String predictor)
	{
		int count = 0;
		
		for(Data d: trainingSet)
		{
			// if this matches the predictor and the state
			if(d.getSoreThroat().equals(predictor))
			{
				count++;
			}
		}
		
		return count;
	}

	// get the count of all tonsilitis states
	public int getTonsilitisCount(String predictor)
	{
		int count = 0;
		
		for(Data d: trainingSet)
		{
			if(d.getTonsilitis().equals(predictor))
			{
				count++;
			}
		}
		return count;
	}

	// get the probabilities of aches & sore throat states given tonsilitis
	public int getProbabilities(String predictor, String predictorState, String tonsilitisState)
	{
		int count = 0;
		
		// get sore throat
		if(predictor.equals("SoreThroat"))
		{
			for(Data d: trainingSet)
			{
				// if tonsilitis state matches aswell as sore throat state, e.g. tonsilitis = "Yes" & sore throat = "Yes"
				if(d.getTonsilitis().equals(tonsilitisState) && d.getSoreThroat().equals(predictorState))
				{
					count++;
				}
			}
			
		}
		// get aches
		else if(predictor.equals("Aches"))
		{
			for(Data d: trainingSet)
			{
				if(d.getTonsilitis().equals(tonsilitisState) && d.getAches().equals(predictorState))
				{
					count++;
				}
			}
		}
		
		return count;
	}
	
	// get the probabilities for temperature states given tonsilitis
	public int getTempProbabilities(String predictor, String tonsilitisState)
	{
		int count = 0;
		
		// get hot
		if(predictor.equals("Hot"))
		{
			for(Data d: trainingSet)
			{
				// if tonsilitis state matches aswell as sore throat state, e.g. tonsilitis = "Yes" & sore throat = "Yes"
				if(d.getTemperature().equals("Hot") && d.getTonsilitis().equals(tonsilitisState))
				{
					count++;
				}
			}
			
		}
		// get normal
		else if(predictor.equals("Normal"))
		{
			for(Data d: trainingSet)
			{
				if(d.getTemperature().equals("Normal") && d.getTonsilitis().equals(tonsilitisState))
				{
					count++;
				}
			}
		}
		// get cool
		else if(predictor.equals("Cool"))
		{
				for(Data d: trainingSet)
				{
					if(d.getTemperature().equals("Cool") && d.getTonsilitis().equals(tonsilitisState))
					{
						count++;
					}
				} 
		}
		

		return count;
	}
}
