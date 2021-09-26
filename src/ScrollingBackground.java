import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScrollingBackground extends JPanel   {
    // Two copies of the background image to scroll
    private Background backOne;
    private Background backTwo;
    private BufferedImage back;
    //Variables To Move Between The Levels
    static int Flag=0,Timer=0;	
    //X,Y Locations For Any Screen To Print Information Between Levels 
    static Dimension TexLO = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((int) TexLO.getWidth()/2.2);
    int y = (int) ((int) TexLO.getHeight()/2);
    //PrintWriter to save the miss words to text file
    static File misswords = new File("level8.txt");
    private static PrintWriter pw;
    
   static  int MinTmp;
   static int SecTmp;
   	 //Constructor To Scroll The Background 
    public ScrollingBackground() {
        backOne = new Background();
        backTwo = new Background(backOne.getImageWidth(), 0);
        setVisible(true);
        if(level.LVL!=8)
        {
	      //initialization the PrintWriter
	      		try {
	      			pw = new PrintWriter(misswords);
	      		} catch (FileNotFoundException e1) {
	      			e1.printStackTrace();
	      		}
        }
    }
    @Override
    public void update(Graphics g2) {
        paint(g2);
    }
    
    //Drawing Things On The Background
	public void paint(Graphics g2) {
		super.paint(g2);
        Graphics2D twoD = (Graphics2D)g2;
        if (back == null)
            back = (BufferedImage)(createImage(getWidth(), getHeight()));
        // Create a buffer to draw to
        Graphics buffer = back.createGraphics();
        // Put the two copies of the background image onto the buffer
        backOne.draw(buffer);
        backTwo.draw(buffer);
        // Draw the image onto the window
        twoD.drawImage(back, 0, 0, null);
        twoD.setFont(getFont().deriveFont(ABORT));
        
        try {
        	Thread.sleep(5);
        	
        	if(Flag==1)
        	{  
        		//Drawing  Things On the Frame
        	 	   Font Lives =new Font("Comic",Font.BOLD+Font.PLAIN,30);
        	        g2.setFont(Lives); 
        	        g2.setColor(Color.GREEN);
        	        if(level.LVL!=8)
        	        {
	        	        g2.drawString("Lives:", 50, 50);
	        	        g2.drawString(String.valueOf(level.Lifes), 150,50);
	        	        g2.drawString("Score:", Main.F.getWidth()-200, 50);
	        	        g2.drawString(String.valueOf(level.Score),  Main.F.getWidth()-100,50);
        	        }
        	        g2.setColor(Color.gray);
        	        g2.drawString("Timer:", 50, Main.F.getHeight()-100);
        	        g2.drawString(String.valueOf(CountTimer.Minutes)+":"+String.valueOf(CountTimer.Seconds),150,Main.F.getHeight()-100);
        	        
        	        Font font =new Font("Comic",Font.BOLD+Font.PLAIN,50);
        	        g2.setFont(font); 
        	      // Loop For Drawing Words
        	        for(int i=0;i<5;i++)
        	        {
        	           g2.setColor(level.WordsColor[i]);
        	     	   g2.drawString(level.WordsArray[i],level.Stringlocation[i],level.LocationY);
        	     	   level.LocationY+=(int) (TexLO.getHeight()/8);
        	     	// Change The Word's Color If Passed Specific Location
        	     	   if(level.Stringlocation[i]>TexLO.getWidth()/1.2)
        	     	   {
        	     		   level.WordsColor[i]=Color.red;
        	     	   }
        	        }
        	        level.LocationY=(int) (TexLO.getHeight()/6);
        	       // If Player Entered The All Words End The game
        	        if(level.LVL==6 && level.WordsCNT==UserWords.WordsCount)
        	        {
        	        	Flag=2;
        	        }
	        	}
	        	
	        	if(Flag==0)
	        	{	  
	        		//Drawing  Things On the Frame
	        	   Font Lives =new Font("Comic",Font.BOLD+Font.PLAIN,30);
	        	   g2.setFont(Lives); 
	        	   g2.setColor(Color.GREEN);
	        	   g2.drawString("Lives:", 50, 50);
	        	   g2.drawString(String.valueOf(level.Lifes), 150,50);
	        	   g2.drawString("Score:", Main.F.getWidth()-200, 50);
	        	   g2.drawString(String.valueOf(level.Score),  Main.F.getWidth()-100,50);
	        	   Font font =new Font("Comic",Font.BOLD+Font.PLAIN,50);
	        	   g2.setFont(font); 
	        	   g2.setColor(Color.blue);
	        	   Font levelup =new Font("Comic",Font.BOLD+Font.PLAIN,100);
	        	   g2.setFont(levelup);
	        	   g2.setColor(Color.red);
	        	 // Info For Level Mode
	        	   if(level.LVL==6)
	        	   {
	        		   g2.drawString("User Mode",x-150, y);
	        	   }
	        	   else
	        	   if(level.LVL==7)
	        	   {
	        		   g2.drawString("Sentences Mode",x-300, y);
	        	   }
	        	   else
	        	   if(level.LVL==8)
	        	   {
	        		   g2.drawString("Miss Words",x-300, y);
	        	   }  
	        	   else
	        	   g2.drawString("level"+String.valueOf(level.LVL),x-100, y);
        	   Timer++;
           //Change The FLag To new Level Game    	        
        	   if(Timer>=100)
        	   {
        		   if(level.LVL==7)
        			   Flag=4;
        		   else
        			   Flag=1;
        		   Timer=0;
        		   level.levels="level"+String.valueOf(level.LVL);
        		   Singleton._newScanner();
        		   new level();
        		   CountTimer.start();
        	   }
        	}
        				
        	if(Flag==2)
        	{
        		MinTmp = CountTimer.Minutes;
        		SecTmp = CountTimer.Seconds;
        		// Flag To End The Game 
        		Font levelup =new Font("Comic",Font.BOLD+Font.PLAIN,100);
        		g2.setFont(levelup);
        		g2.setColor(Color.red);
        		g2.drawString("GAME OVER",x-200, y);
        		Timer++;
        		//Starting the game over page
        		if(Timer>=100)
        		{
        			Timer=0;
        			Main.F.getContentPane().removeAll();
        			Main.F.getContentPane().repaint();	
        			Main.F.add(new GameOver());
        			Main.F.setVisible(true);
        			Main.F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		}
        	}
        	if(Flag==3)
        	{
        		// Stop moving words
        		level.MoveX=0;
        		//Drawing Things On the Frame
        		Font Lives =new Font("Comic",Font.BOLD+Font.PLAIN,30);
        		g2.setFont(Lives); 
        		g2.setColor(Color.GREEN);
        		g2.drawString("Lives:", 50, 50);
        		g2.drawString(String.valueOf(level.Lifes), 150,50);
        		g2.drawString("Score:", Main.F.getWidth()-200, 50);
        		g2.drawString(String.valueOf(level.Score),  Main.F.getWidth()-100,50);
        		Font levelup =new Font("Comic",Font.BOLD+Font.PLAIN,100);
        		g2.setFont(levelup);
        		g2.setColor(Color.red);
        		//Drawing the name of Project on the Screen
        		g2.drawString(level.Typeoh,x-200, y);
        		Timer++;
        		//return  to the game and Set the past speed of words
        		if(Timer>=1000)
        		{
        			Timer=0;
        			Flag=1;
        			level.MoveX=level.SaveX;
        			level.WordsArray[1]=Singleton.getInstance().read();
        		}
        		
        	}
        	if(Flag==4)
        	{
        		//Drawing Things On the Frame
        		Font Lives =new Font("Comic",Font.BOLD+Font.PLAIN,30);
    	        g2.setFont(Lives); 
    	        g2.setColor(Color.gray);
    	        g2.drawString("Timer:", 50, Main.F.getHeight()-100);
    	        g2.drawString(String.valueOf(CountTimer.Minutes)+":"+String.valueOf(CountTimer.Seconds),150,Main.F.getHeight()-100);
        		Font levelup =new Font("Comic",Font.BOLD+Font.PLAIN,40);
        		g2.setFont(levelup);
        		FontMetrics F=g2.getFontMetrics(levelup);
        		// draw the Sentences on the screen
        		for(int i=0,l=0,y=200; level.Sentences[i]!=null;i++)
        		{
        			g2.setColor(level.SentencesColor[i]);
        			g2.drawString(level.Sentences[i],100+l,y);
        			l+=F.stringWidth(level.Sentences[i]+" ");
        			if(l>TexLO.getWidth()/1.2)
        			{
        				y+=50;
        				l=0;
        			}
        		}
        		
        		for(int i=0,cnt=0;level.Sentences[i]!=null ;i++)
        		{
        			if(level.SentencesColor[i]==Color.ORANGE)
        				cnt++;
        			if(cnt==level.SentencesLength)
        				Flag=2;
        		}
        	}
        	
        }
        catch(Exception ex){}
        //move the Words on axis X
        for(int i=0;i<5;i++)
		{
			level.Stringlocation[i]+=level.MoveX;
		}
        
        //if the word passed the end of screen ,Set new word from the beginning 
		for(int i=0;i<5;i++)
		{
			if(level.Stringlocation[i]> Main.F.getWidth())
			{ 
				if(level.LVL!=8)
				{
					pw.print(level.WordsArray[i]+" ");
					level.WordsArray[i]=GameOver.MostWrongLetter+Singleton.getInstance().read();
					level.Lifes--;
				}
				level.Stringlocation[i]=-80;
				level.WordsColor[i]=Color.orange;
				
			}
		}    
		//Show the name of the Game on this scores
		if(level.Score==50 || level.Score==100 || level.Score==150 || level.Score==200)
		{
			Flag=3;
			level.Score+=1;
			level.WordsArray[1]=level.Typeoh;
		}
		//level up when passed 2 minutes 
		if(level.LVL!=7)
		{
			if(CountTimer.Minutes==2)
			{
				CountTimer.Minutes=0;
				level.LVL++;
				Flag=0;
				Timer=0;
				if(level.LVL==2)
					level.MoveX=2;
				if(level.LVL==3)
					level.MoveX=3;
				if(level.LVL==4)
					level.MoveX=1;
				if(level.LVL==5)
					level.MoveX=4;
			}
		}
		if(level.LVL==8 && level.Score==10)
		{
			Flag=2;
		}
		//End the game if 10 word is passed
		if(level.Lifes<=0)
		{
			Flag=2;
			if(level.LVL!=8)
			{
				pw.close();
			}
		}
        repaint();
	}
 }