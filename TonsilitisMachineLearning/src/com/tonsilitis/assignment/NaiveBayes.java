package com.tonsilitis.assignment;

import java.util.HashMap;

public class NaiveBayes 
{
	private FileProcessor fp = new FileProcessor();
	private int totalCount; // amount of data inputted i.e. the data set in the excel file
	private HashMap<String, Integer> hm = new HashMap<>();//https://www.geeksforgeeks.org/java-util-hashmap-in-java/
	private String temperatureInput, soreThroatInput, achesInput; 
	
	public NaiveBayes(String temperatureInput, String soreThroatInput, String achesInput)
	{
		// read in the data
		totalCount = fp.getDataAmount();
		hm.put("Tonsilitis Yes",fp.getYesTonsilitis());
		hm.put("Tonsilitis No", fp.getNoTonsilitis());
		hm.put("Sore Throat Yes", fp.getYesSoreThroat());
		hm.put("Sore Throat No", fp.getNoSoreThroat());
		hm.put("Aches Yes", fp.getYesAches());
		hm.put("Aches No", fp.getNoAches());
		hm.put("Hot", fp.getHotTemp());
		hm.put("Normal", fp.getNormalTemp());
		hm.put("Cool", fp.getCoolTemp());
		
		// the users input from the GUI
		this.temperatureInput = temperatureInput;
		this.achesInput = achesInput;
		this.soreThroatInput = soreThroatInput;
	}
}
