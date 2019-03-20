package com.tonsilitis.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileProcessor 
{
	private File dataSet;
	private Scanner fileScanner;
	private String temperatureToken, achesToken, soreThroatToken, tonsilitisToken, dataInstance;
	private int dataAmount;
	private int i;

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
			
			// set the data instance of the given data
			setDataInstance(nameDataInstance(i));
			
			// create data instance
			Data data_instance = new Data(temperatureToken, achesToken, soreThroatToken, tonsilitisToken);
			System.out.println("Data "+i+" has been entered in");
			System.out.println(data_instance.toString());
		}
		
		// set the amount of data inputted
		setDataAmount(i);
		
		// close file
		fileScanner.close();
	}
	
	// create data instance for whole data
	public String nameDataInstance(int instance_num)
	{
		// name data based on the count
		String name = "data"+i;
		
		return name;
	}

	// setters & getters for data instances
	public String getDataInstance() 
	{
		return dataInstance;
	}
	
	public void setDataInstance(String data_instance) 
	{
		this.dataInstance = data_instance;
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
	
	
}
