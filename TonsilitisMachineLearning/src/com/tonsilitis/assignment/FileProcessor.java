/* Author: Ivan Yanez
 */

package com.tonsilitis.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor 
{
	private File dataSet; // the training set used to teach the machine
	private Scanner fileScanner; 
	private String temperatureToken, achesToken, soreThroatToken, tonsilitisToken; // each word taken from the file
	private Data theData; 
	private ArrayList<Data> list = new ArrayList<Data>(); // list of the data instances for training
	private ArrayList<Data> evalList = new ArrayList<Data>();
	
	
	// read the file entered in
	public void readFile(String filename)
	{	
		// check the file to see if it exists
		try 
		{
			dataSet = new File(filename);
			fileScanner = new Scanner(dataSet);
			
			// change delimiter to read in the .csv file
			fileScanner.useDelimiter(",|\r\n");
			
			// skip the first line, first line are only the headers/title 
			fileScanner.nextLine();

		}
		// handling for file if file not found
		catch (FileNotFoundException event) 
		{
			// if file not found, notify user
			System.out.println("File not found");
		}
		
		
		// while the file still has a next word
		while(fileScanner.hasNext())
		{	
			// 1st token is always the temperature information
			// 2nd token is always the aches information
			// 3rd token is always the sore throat information 
			// 4th token is always the tonsilitis information
			temperatureToken = fileScanner.next();
			achesToken = fileScanner.next();
			soreThroatToken = fileScanner.next();
			tonsilitisToken = fileScanner.next();
			
			// if evaluating, i.e the evaluation file
			if(filename.equals("evaluateinput.csv"))
			{
				// instantiate the data
				theData = new Data(temperatureToken, achesToken, soreThroatToken, tonsilitisToken);
				
				// add to the evaluation list
				evalList.add(theData);
			}
			// if using training file
			else
			{
				// insert data instance into data list
				createDataInstances(temperatureToken, achesToken, soreThroatToken, tonsilitisToken);
			}
		}
	}
	
	
	// use the token informations to create a data instance 
	public void createDataInstances(String temperature, String aches, String soreThroat, String tonsilitis)
	{
		// instantiate the data
		theData = new Data(temperature, aches, soreThroat, tonsilitis);
		
		// add data to the training list
		list.add(theData);
		
	}
	
	
	// return the training list
	public ArrayList<Data> returnList()
	{
		return list;
	}
	
	
	// return the evaluation list
	public ArrayList<Data> returnEvalList()
	{
		return evalList;
	}

	
	// close the file
	public void closeFile()
	{
		fileScanner.close();
	}
	
}
