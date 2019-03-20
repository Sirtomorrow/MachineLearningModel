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
	
	public String toString()
	{
		return "Temperature: "+temperature+"\nAches: "+aches+"\nSore Throat: "+soreThroat+"\nTonsilitis: "+tonsilitis+"\n";
	}
}
