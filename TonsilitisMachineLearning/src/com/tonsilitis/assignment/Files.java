package com.tonsilitis.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Files 
{
	private File dataSet;
	private Scanner fileScanner;
	private String tempResult, achesResult, soreThroatResult, tonsilitisResult;
	private String data_instance;
	private int i = -1;

	public Files()
	{
		
		// check the file
		try 
		{
			dataSet = new File("datainput.csv");
			fileScanner = new Scanner(dataSet);
			
			// change delimiter
			fileScanner.useDelimiter(",|\\n|\\s");
			
			fileScanner.nextLine();
			
			// while the file still has a next word
			while(fileScanner.hasNextLine())
			{	
				i++;
				tempResult = fileScanner.next();
				achesResult = fileScanner.next();
				soreThroatResult = fileScanner.next();
				tonsilitisResult = fileScanner.next();
				data_instance = nameDataInstance(i);
				
				// create data instance
				Data data_instance = new Data(tempResult, achesResult, soreThroatResult, tonsilitisResult);
				
				System.out.println(data_instance.toString());
			}
			

		}
		// handling for file, i.e. if file not found
		catch (FileNotFoundException event) 
		{
			// if file not found, notify user
			System.out.println("File not found");
		}
		
		System.out.println(tempResult);
		System.out.println(achesResult);
		System.out.println(soreThroatResult);
		System.out.println(tonsilitisResult);
		
		fileScanner.close();
	}
	
	public String nameDataInstance(int instance_num)
	{
		String name = "data"+i;
		
		return name;
	}
}
