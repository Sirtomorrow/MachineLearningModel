package com.tonsilitis.assignment;

// POJO
public class Data 
{
	private String temperature;
	private String aches;
	private String sore_throat;
	private String tonsilitis;
	
	Data(String temperature, String aches, String sore_throat, String tonsilitis)
	{
		this.setTemperature(temperature);
		this.setAches(aches);
		this.setSore_throat(sore_throat);
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

	public String getSore_throat() 
	{
		return sore_throat;
	}

	public void setSore_throat(String sore_throat)
	{
		this.sore_throat = sore_throat;
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
		return "Temperature: "+temperature+"\nAches: "+aches+"\nSore Throat: "+sore_throat+"\nTonsilitis: "+tonsilitis+"\n";
	}
}
