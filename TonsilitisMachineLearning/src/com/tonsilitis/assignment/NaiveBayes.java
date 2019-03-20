package com.tonsilitis.assignment;

public class NaiveBayes 
{
	private String yesTonsilitis; // people that did get tonsilitis from sample size
	private String noTonsilitis; // people that did not get tonsilitis from sample size
	private FileProcessor fp = new FileProcessor();
	private int dataCount, i;
	public NaiveBayes()
	{
		// read in the data
		fp.readFile();
		dataCount = fp.getDataAmount();
		
		// get the data
		for(i = 0; i < dataCount; i++)
		{
			
		}
		
		setYesTonsilitis(getYesTonsilitis());
		setNoTonsilitis(getNoTonsilitis());
	}
	
	public void getTemperature()
	{
		
	}
	
	public void getAches()
	{
		
	}
	
	public void getSoreThroat()
	{
		
	}

	// get the amount of people that got tonsilitis from sample size
	public String getYesTonsilitis() 
	{
		return yesTonsilitis;
	}

	public void setYesTonsilitis(String yesTonsilitis) 
	{
		this.yesTonsilitis = yesTonsilitis;
	}
	
	// get the amount of people that did not get tonsilitis from sample size
	public String getNoTonsilitis() 
	{
		return noTonsilitis;
	}

	public void setNoTonsilitis(String noTonsilitis) 
	{
		this.noTonsilitis = noTonsilitis;
	}
	
}
