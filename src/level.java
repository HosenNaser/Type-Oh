import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class level {
	//String array to save words
   	static String[] WordsArray = new String[5];
   	//Location for the words 
   	static int[] Stringlocation = new int[5];
   	//Color array for the words color 
   	static Color[] WordsColor={Color.orange,Color.orange,Color.orange,Color.orange,Color.orange};
   	//Locations X and Y for the words
   	static int LocationY=(int) (ScrollingBackground.TexLO.getHeight()/6),LocationX=-80;
   	//Variables for the game
   	static int Lifes = 10,Score=0,i,j;
   	static String levels="level1";
   	static int MoveX=1,LVL=1,SaveX;
   	static String Typeoh="Type-oh";
   	//String to save the wrong words
   	static String DiffWord= "";
   	//a-z letters to get the most wrong letter
   	static char[] Letters={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
   	static int[] LettersIndix=new int[26];
   	//Variables for Typing Speed 
   	static int Counter=0,WordsCNT=0;
   	//enter all Sentences To array
   	static String[] Sentences=new String[1000];
   	static Color[] SentencesColor= new Color[1000];
   	static Scanner S;
   	static int SentencesLength;
   	//Constructor to start level 
   	@SuppressWarnings("resource")
	public level()
   	{
   		SentencesLength=0;
   		SaveX=MoveX;
   		//Entering the words to the String And it's location from the TEXT file
   		for(int i=0;i<5;i++)
   		{
   			WordsArray[i]=GameOver.MostWrongLetter+Singleton.getInstance().read();
   			Stringlocation[i]=LocationX;
   			LocationX-=50;
   		}
   		try {
			S=new Scanner(new File("level7.txt")).useDelimiter(" ");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
   		//Entering the Sentences words to array
   		for(int i=0;S.hasNext();i++)
   		{
   			Sentences[i]=S.next();
   			SentencesColor[i]=Color.red;
   			SentencesLength++;
   		}
   	}
   	
   	//Check if the TextFeild and The Word is Equals  
	public static void _nextWord(String s)
	{
		if(LVL!=7)
		{
			for(i=0;i<5;i++)
			{
				if(WordsArray[i]==Typeoh && s.equals(WordsArray[i]))
	   			{
					Score+=4;
					ScrollingBackground.Flag=1;
					MoveX=SaveX;
					WordsColor[i]=Color.orange;
					WordsArray[i]=GameOver.MostWrongLetter+Singleton.getInstance().read();
					break;
	   			}
				if(LVL==6)
				{
					if(s.equals(WordsArray[i]))
					{
						WordsCNT++;
						Stringlocation[i]=-80;
						WordsArray[i]=GameOver.MostWrongLetter+Singleton.getInstance().read();
						Score+=1;
						WordsColor[i]=Color.orange;
						Counter++;
						break;
					}
				}
				else
					if(s.equals(WordsArray[i]))
					{
						Stringlocation[i]=-80;
						WordsArray[i]=GameOver.MostWrongLetter+Singleton.getInstance().read();
						Score+=1;
						WordsColor[i]=Color.orange;
						Counter++;
						break;
					}
			}
		//if the word is wrong,concatenation to the DiffWord String
			if(i==5)
			{
				DiffWord=DiffWord.concat(s);
				j=DiffWord.length();
			}
		}
			if(LVL==7)
			{
				for(int i=0;i<Sentences.length;i++)
				{
					if(s.equals(Sentences[i]) && SentencesColor[i]==Color.red)
					{
						WordsCNT++;
						SentencesColor[i]=Color.orange;
						Counter++;
						break;
					}
				}
			}
	}
	
}
