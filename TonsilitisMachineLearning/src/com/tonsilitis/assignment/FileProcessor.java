/* Author: Ivan Yanez
 */

package com.tonsilitis.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor 
{
	private File dataSet;
	private Scanner fileScanner;
	private String temperatureToken, achesToken, soreThroatToken, tonsilitisToken;
	private static int i = 0;
	private Data theData;
	private ArrayList<Data> list = new ArrayList<Data>();
	private ArrayList<Data> evalList = new ArrayList<Data>();
	
	// read the file entered in
	public void readFile(String filename)
	{	
		// check the file
		try 
		{
			dataSet = new File(filename);
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
			temperatureToken = fileScanner.next();
			achesToken = fileScanner.next();
			soreThroatToken = fileScanner.next();
			tonsilitisToken = fileScanner.next();
			
			if(filename.equals("evaluationFile.csv"))
			{
				theData = new Data(temperatureToken, achesToken, soreThroatToken, tonsilitisToken);
				evalList.add(theData);
			}
			else
			{
				// insert data instance into data list
				createDataInstances(temperatureToken, achesToken, soreThroatToken, tonsilitisToken);
			}
		}
	}
	
	public void createDataInstances(String temperature, String aches, String soreThroat, String tonsilitis)
	{
		theData = new Data(temperature, aches, soreThroat, tonsilitis);
		list.add(theData);
		setDataCount(getDataCount() + 1);
	}
	
	public ArrayList<Data> returnList()
	{
		return list;
	}
	
	public ArrayList<Data> returnEvalList()
	{
		return evalList;
	}
	
	public int getDataCount() 
	{
		return i;
	}

	public void setDataCount(int i) 
	{
		FileProcessor.i = i;
	}
	
	// close the file
	public void closeFile()
	{
		fileScanner.close();
	}
	
}
