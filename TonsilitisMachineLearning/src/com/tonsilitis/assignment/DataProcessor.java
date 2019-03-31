/* Author: Ivan Yanez
 */

package com.tonsilitis.assignment;

import java.util.ArrayList;

public class DataProcessor 
{
	private ArrayList<Data> trainingSet = new ArrayList<Data>(); // list of the data instances
	private static int i = 0;
	private FileProcessor fp = new FileProcessor();
	
	DataProcessor()
	{
		// read the file
		fp.readFile();
		
		trainingSet = fp.returnList();
		System.out.println(trainingSet);
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
}
