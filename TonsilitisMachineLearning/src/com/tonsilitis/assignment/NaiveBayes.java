package com.tonsilitis.assignment;

import java.util.HashMap;

public class NaiveBayes 
{
	private FileProcessor fp = new FileProcessor();
	private int dataCount, i;
	private HashMap<String, Integer> dataSet = new HashMap<>();//https://www.geeksforgeeks.org/java-util-hashmap-in-java/
	
	public NaiveBayes()
	{
		// read in the data
		dataCount = fp.getDataAmount();
		dataSet.put("Tonsilitis Yes",fp.getYesTonsilitis());
		dataSet.put("Tonsilitis No", fp.getNoTonsilitis());
		dataSet.put("Sore Throat Yes", fp.getYesSoreThroat());
		dataSet.put("Sore Throat No", fp.getNoSoreThroat());
	}
	
}
