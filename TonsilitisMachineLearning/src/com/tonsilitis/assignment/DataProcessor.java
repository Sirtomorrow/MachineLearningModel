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
	
	// get amount of people that had tonsilitis from the sample set
	public int getYesTonsilitis()
	{
		int tonsilitisYes = 0;
		
		for(Data d: trainingSet)
		{
			if(d.getTonsilitis().equals("Yes"))
			{
				tonsilitisYes++;
			}
		}
		return tonsilitisYes;
	}
	
	// get amount of peple that dont have tonsilitis from the sample set
	public int getNoTonsilitis()
	{
		int tonsilitisNo = 0;
		
		for(Data d: trainingSet)
		{
			if(d.getTonsilitis().equals("No"))
			{
				tonsilitisNo++;
			}
		}
		
		return tonsilitisNo;
	}
	
	// get amount of peple that have sore throat and tonsilitis
	public int checkSoreThroat(String soreThroatState, String tonsilititisState)
	{
		int soreThroat = 0;
		
		for(Data d: trainingSet)
		{
			/* if(d.getSoreThroat().equals(soreThroatState) && d.getTonsilitis().equals(tonsilititisState))
			{
				soreThroat++;
			} */ 
			i++;
			System.out.println("the loop is "+i);
		}
		
		
		return soreThroat;
	}
	
}
