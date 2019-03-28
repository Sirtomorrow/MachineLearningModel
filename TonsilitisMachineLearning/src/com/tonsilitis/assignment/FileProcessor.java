package com.tonsilitis.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor 
{
	private File dataSet;
	private Scanner fileScanner;
	private String temperatureToken, achesToken, soreThroatToken, tonsilitisToken, dataInstance;
	private int dataAmount;
	private int i = 0;
	private ArrayList<Data> dataList = new ArrayList<Data>();
	private Data theData;
	
	// constructor
	FileProcessor()
	{
		readFile();
		closeFile();
	}
	
	// read the file entered in
	public void readFile()
	{
		i = 0;
		
		// check the file
		try 
		{
			dataSet = new File("datainput.csv");
			fileScanner = new Scanner(dataSet);
			
			// change delimiter
			fileScanner.useDelimiter(",|\r\n");
			
			// skip the first line 
			fileScanner.nextLine();

		}
		// handling for file, i.e. if file not found
		catch (FileNotFoundException event) 
		{
			// if file not found, notify user
			System.out.println("File not found");
		}
		
		// while the file still has a next word
		while(fileScanner.hasNext())
		{	
			i++;
			temperatureToken = fileScanner.next();
			achesToken = fileScanner.next();
			soreThroatToken = fileScanner.next();
			tonsilitisToken = fileScanner.next();
			
			// insert data instance into data list
			addData(temperatureToken, achesToken, soreThroatToken, tonsilitisToken);

		}
		
		// set the amount of data inputed
		setDataAmount(i);
		

	}
	

	// setters  & getters for the amount of data stored
	public int getDataAmount() 
	{
		return dataAmount;
	}

	public void setDataAmount(int i) 
	{
		this.dataAmount = i;
	}
	
	// store data instances into a list
	public void addData(String temperature, String aches, String soreThroat, String tonsilitis)
	{
		theData = new Data(temperature, aches, soreThroat, tonsilitis);
		dataList.add(theData);
		// System.out.println(theData);
	}

	// get amount of people that had tonsilitis from the sample set
	public int getYesTonsilitis()
	{
		int tonsilitisYes = 0;
		
		for(Data d: dataList)
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
		
		for(Data d: dataList)
		{
			if(d.getTonsilitis().equals("No"))
			{
				tonsilitisNo++;
			}
		}
		
		return tonsilitisNo;
	}
	
	// the amount of people that had a sore throat from the sample set
	public int getYesSoreThroat()
	{
		int soreThroatYes = 0;
		
		for(Data d: dataList)
		{
			if(d.getSoreThroat().equals("Yes"))
			{
				soreThroatYes++;
			}
		}
		
		return soreThroatYes;
	}
	
	// the amount of people that didnt have a sore throat from the sample set 
	public int getNoSoreThroat()
	{
		int soreThroatNo = 0;
		
		for(Data d: dataList)
		{
			if(d.getSoreThroat().equals("No"))
			{
				soreThroatNo++;
			}
		}
		
		return soreThroatNo;
	}
	
	// the amount of people that had aches from the sample set
	public int getYesAches()
	{
		int achesYes = 0;
		
		for(Data d: dataList)
		{
			if(d.getAches().equals("Yes"))
			{
				achesYes++;
			}
		}
		
		return achesYes;
	}
	
	// the amount of people that had aches from the sample set
	public int getNoAches()
	{
		int achesNo = 0;
		
		for(Data d: dataList)
		{
			if(d.getAches().equals("No"))
			{
				achesNo++;
			}
		}
		
		return achesNo;
	}
	
	// get the amount of people with hot temp
	public int getHotTemp()
	{
		int hotTemp = 0;
		
		for(Data d: dataList)
		{
			if(d.getTemperature().equals("hot"))
			{
				hotTemp++;
			}
		}
		
		return hotTemp;
	}
	
	// get the amount of people with normal temp
	public int getNormalTemp()
	{
		int normalTemp = 0;
		
		for(Data d: dataList)
		{
			if(d.getTemperature().equals("normal"))
			{
				normalTemp++;
			}
		}
		
		return normalTemp;
	}
	
	// get the amount of people with cold temp
	public int getCoolTemp()
	{
		int coolTemp = 0;
		
		for(Data d: dataList)
		{
			if(d.getTemperature().equals("cool"))
			{
				coolTemp++;
			}
		}
		
		return coolTemp;
	}
	
	// close the file
	public void closeFile()
	{
		fileScanner.close();
	}
	
}
