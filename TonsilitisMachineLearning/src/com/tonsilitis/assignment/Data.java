package com.tonsilitis.assignment;

public class Data 
{
	private String temperature;
	private String aches;
	private String soreThroat;
	private String tonsilitis;
	
	Data(String temperature, String aches, String soreThroat, String tonsilitis)
	{
		this.setTemperature(temperature);
		this.setAches(aches);
		this.setSoreThroat(soreThroat);
		this.setTonsilitis(tonsilitis);
	}

	// getters & setters
	public String getTemperature() 
	{
		return temperature;
	}

	public void setTemperature(String temperature) 
	{
		this.temperature = temperature;
	}

	public String getAches() 
	{
		return aches;
	}

	public void setAches(String aches) 
	{
		this.aches = aches;
	}

	public String getSoreThroat() 
	{
		return soreThroat;
	}

	public void setSoreThroat(String soreThroat)
	{
		this.soreThroat = soreThroat;
	}

	public String getTonsilitis() 
	{
		return tonsilitis;
	}

	public void setTonsilitis(String tonsilitis) 
	{
		this.tonsilitis = tonsilitis;
	}
	
	// check if the data instance reported user has a sore throat AND tonsilitis
	public boolean checkSoreThroat()
	{
		boolean state = false;
		
		// if person had sore throat AND tonsilitis
		if(soreThroat.equals("Yes") && tonsilitis.equals("Yes"))
		{
			state = true;
		}
		// if person had sore throat AND didnt have tonsilitis
		else if(soreThroat.equals("Yes") && tonsilitis.equals("No"))
		{
			state = false;
		}
		
		return state;
	}
	
	// check if the data instance reported user has a sore throat AND tonsilitis
	public boolean checkNoSoreThroat()
	{
		boolean state = false;
		
		// if person had no sore throat AND tonsilitis
		if(soreThroat.equals("No") && tonsilitis.equals("Yes"))
		{
			state = true;
		}
		// if person had no sore throat AND didnt have tonsilitis
		else if(soreThroat.equals("No") && tonsilitis.equals("No"))
		{
			state = false;
		}
		
		return state;
	}
}
