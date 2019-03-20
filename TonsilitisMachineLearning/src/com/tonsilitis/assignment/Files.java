package com.tonsilitis.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Files 
{
	private File dataSet;
	private Scanner fileScanner;
	private String tempToken, achesToken, soreThroatToken, tonsilitisToken;
	private String data_instance;
	private int i = 0;

	public Files()
	{
		
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
			tempToken = fileScanner.next();
			achesToken = fileScanner.next();
			soreThroatToken = fileScanner.next();
			tonsilitisToken = fileScanner.next();
			data_instance = nameDataInstance(i);
			
			// create data instance
			Data data_instance = new Data(tempToken, achesToken, soreThroatToken, tonsilitisToken);
			
			System.out.println(data_instance.toString());
		}
		
		fileScanner.close();
	}
	
	public String nameDataInstance(int instance_num)
	{
		String name = "data"+i;
		
		return name;
	}
}
