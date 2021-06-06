package ca.poc.djj.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args ) throws FileNotFoundException
	{
	//	Scanner sc = new Scanner(new File("C:\\Users\\vishu\\Documents\\djj-2021\\D365FO.Implementation.IntegrationTests\\src\\test\\resources\\data\\csvdata\\TestCreateData.csv"));  
		CSVReader reader = null;
        try
        {
            //Get the CSVReader instance with specifying the delimiter to be used
            reader = new CSVReader(new FileReader("C:\\Users\\vishu\\Documents\\djj-2021\\D365FO.Implementation.IntegrationTests\\src\\test\\resources\\data\\csvdata\\TestDataScrapconnect.csv"), '|');
 
            String [] nextLine;
            List<String> li = new ArrayList<String>();
 Map<String, String> map = new HashMap<String, String>();
 while ((nextLine = reader.readNext()) != null) 
 {
	 if(nextLine[0].equals("ScenarioName")) {
		 for(String hkey : nextLine)
         {   
			 System.out.println(hkey);
     		li.add(hkey);
         }
	 }
 }
            //Read one line at a time
            while ((nextLine = reader.readNext()) != null) 
            {
            	for(int i=1 ; i<nextLine.length ; i++) {
            		
            		 if(nextLine[i].equals("Scenario2")) {
            		
            			 for(String hkey : nextLine)
                         {    
                     		map.put(li.get(i), hkey);                
                         }
            		 }
            	}
            }
            System.out.println(map);
            for(String st : li)
            {    
            	System.out.println(st);
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
