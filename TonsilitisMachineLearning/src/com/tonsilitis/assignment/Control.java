/* Author: Ivan Yanez
 */

package com.tonsilitis.assignment;

import java.util.HashMap;
import java.util.Map;

public class Control 
{

	public static void main(String[] args) 
	{
		/* GUI gui1 = new GUI();
		System.out.println(gui1); */
		
		NaiveBayes nb = new NaiveBayes("Hot", "Yes", "Yes");
		System.out.println(nb);
		
	}

}
