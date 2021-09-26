import java.io.File;
import java.util.Scanner;

public class UserScanner {
	//variables for Scanner
	private static UserScanner _instance;
	private static Scanner myScanner;
	@SuppressWarnings("resource")
	
	//Constructor of Singleton
	private UserScanner()
	{
		//Starting the scanner 
		try {
			myScanner = new Scanner(new File("AllUsers/LastUser.txt")).useDelimiter(" ");
		} catch (Exception e){}
	}
	//get the last instance of the File
	public static UserScanner getInstance()
	{
		if(_instance == null)
		{
			_instance = new UserScanner();
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
	public static void _newScanner(String s)
	{
	try{
		myScanner = new Scanner(new File("AllUsers/"+s+".txt")).useDelimiter(" ");
	}
	catch(Exception e){}
	}
	
	//get the next word from the scanner
	public String read()
	{
		return myScanner.next();
	}
}
