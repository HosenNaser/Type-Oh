import java.io.File;
import java.util.Scanner;

public class Singleton {
	//variables for Scanner
	private static Singleton _instance; 
	private static Scanner myScanner;
	@SuppressWarnings("resource")
	
	//Constructor of Singleton 
	private Singleton()
	{
		//Starting the scanner 
		try{
		myScanner = new Scanner(new File(level.levels+".txt")).useDelimiter(" ");
		}
		catch(Exception e)
		{
		}
	}
	//get the last instance of the File
	public static Singleton getInstance()
	{
		if(_instance == null)
		{
			_instance = new Singleton();
		}
		return _instance; 
	}
	//return the scanner
	public Scanner getScanner()
	{
		return myScanner; 
	}
	//start new scanner
	@SuppressWarnings("resource")
	public static void _newScanner()
	{
		try{
			myScanner = new Scanner(new File(level.levels+".txt")).useDelimiter(" ");
			}
				catch(Exception e)
			{
			}
	}
	//get the next word from the scanner
	public String read()
	{
		if(myScanner.hasNext())
		{
			return myScanner.next();
		}
		return " ";
	}
}
