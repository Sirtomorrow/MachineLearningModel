package com.tonsilitis.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Files 
{
	private File dataSet;
	private Scanner myScanner;
	private String filename = "example.txt";
	
	public Files()
	{
		// set the data set
		dataSet = new File(filename);
		
		// read file
		try 
		{
			myScanner = new Scanner(dataSet);
			
			//read while there is a next line
			while (myScanner.hasNextLine())
			{
				// read word after delimiter
				String token = myScanner.next();
				System.out.println(token);
			}
			
		}
		// handling for file, i.e. if file not found
		catch (FileNotFoundException event) 
		{
			// if file not found, notify user
			System.out.println("File not found");
		}
		
	}
	
	
	// close file
	public void closeFile()
	{
		myScanner.close();
		System.out.println("File is closed");
	}
}
