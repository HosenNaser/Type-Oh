
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameOver extends JPanel{
	//X,Y Locations For Any Screen To Print Information Between Levels
	static Dimension TexLO = Toolkit.getDefaultToolkit().getScreenSize();
    static int x = (int) ((int) TexLO.getWidth()/2.4);
    static int y = (int) ((int) TexLO.getHeight()/1.2);
    static String MostWrongLetter="";
    //WPA variable 
    static float sum;
    //buttons On The Game Over Page 
		JButton button = new JButton("PLAY AGIN");
		JButton button2 = new JButton("EXIT");
	  	JButton button3 = new JButton("Play Your's");
	  	JButton button4 = new JButton("Play Sentences");
	  	JButton button5 = new JButton("Miss Words");
	  	int TmpS;
	  	
	 // Constructor Of GameOver Page
	public GameOver() throws IOException
	{
		
		//button location and ActionLisstener
		  button.setBounds((int) (x*1.1),y/2, 125, 40);
		  button.addActionListener (new Action1());
		  
		  button3.setBounds((int) (x*1.1),y/2+50, 125, 40);
		  button3.addActionListener (new Action2());
		  
		  button4.setBounds((int) (x*1.1),y/2+100, 125, 40);
		  button4.addActionListener (new Action4());
		  
		  button5.setBounds((int) (x*1.1),y/2+150,125, 40);
		  button5.addActionListener (new MissWords());
		  
		  button2.setBounds((int) (x*1.1),y/2+200,125, 40);
		  button2.addActionListener (new Action3());
		  
  
		  //ParaMeters For Main BackGround Image Location
	        Dimension imageLoc = Toolkit.getDefaultToolkit().getScreenSize();
	        int xImage = (int) ((int) imageLoc.getWidth());
	        int yImage = (int) ((int) imageLoc.getHeight());
	        
	        // Load the background image And Send It To Resize
            BufferedImage img = ImageIO.read(new File("images/GameOverBackGround.jpg"));
            BufferedImage resizedImage=resize(img,xImage,yImage);
            JLabel l=new JLabel(new ImageIcon(resizedImage));
    
            //Add component to Frame
            l.add(button);
			l.add(button2);
			l.add(button3);
			l.add(button4);
			l.add(button5);
			this.add(l);
			Main.F.add(this);

		  //Fined the most wrong letter was Entered 
		  for(int i=0;i<level.j;i++)
		  {
			  String x=level.DiffWord.substring(i, i+1);
			  for(int j=0;j<26;j++)
		   		{
		   			if( String.valueOf(level.Letters[j]).equals(x))
		   			{
		   				level.LettersIndix[j]++;
		   				break;
		   			}
		   		}
		  }
		  //insert the Most Wrong letter to value MostWrongLetter
		  for(int max=0,i=0;i<26;i++)
		  {
			  if(level.LettersIndix[i]>max)
			  {
				  max=level.LettersIndix[i];
				  MostWrongLetter=String.valueOf(level.Letters[i]);
			  }
		  }
		  //Calculating the Speed of typing
		  sum=(float)level.Counter/(CountTimer.Seconds+(CountTimer.Minutes*60));
		  
		  //Save the Entered Words on UserMode to Text File
		  File f = new File("AllUsers/UserName.txt");
		  try {
				PrintWriter pw = new PrintWriter(f);
				for(int i=0;i<Login.LastUser;i++)
				{
					if(Login.Users[i]==Login.UserName)
					{
						TmpS=Login.Scores[i];
						if(Login.Users[i]==Login.UserName && Login.Scores[i]<level.Score)
						{
							Login.Scores[i]=level.Score;
						}
					}
					pw.print(Login.Users[i]+" "+Login.Passwords[i]+" "+Login.Scores[i]+" ");
				}
				pw.close();
		  }
		  catch (FileNotFoundException e1) {}
		  
	}
	public void paint(Graphics g){
		super.paint(g);
		//drawing Informations About Users
			Font levelup =new Font("Comic",Font.BOLD,30);
			Font Con =new Font("Comic",Font.ITALIC,80);  
			Font Well =new Font("Comic",Font.ITALIC,50);
	    	g.setFont(Con);
	    	g.setColor(Color.CYAN);
	       	g.drawString(" Congratulations",(int)(TexLO.getWidth()/3),(int)(TexLO.getHeight()/6));
	       	g.setFont(Well);
	    	g.drawString("  Well Done !! ",(int)(TexLO.getWidth()/2.5),(int)(TexLO.getHeight()/4.2));
	    	g.setFont(levelup);
	       	g.setColor(Color.red);
	       	g.drawString("WPS: "+String.valueOf(sum),(int) (TexLO.getWidth()/2.3),(int) (TexLO.getHeight()/2.5));
	       	if(level.LVL!=7)
	       	{
		       	//draw all users Name And High Score
		       	g.drawString(Login.UserName+" Your Score Is: "+String.valueOf(level.Score),(int) (TexLO.getWidth()/2.3),(int) (TexLO.getHeight()/3));
				g.drawString("Users",(int)(TexLO.getWidth()/25),(int)(TexLO.getHeight()/3.3));
				g.drawString("Score",(int)(TexLO.getWidth()/7),(int)(TexLO.getHeight()/3.3));
				for(int i=0,k=0;i<Login.LastUser;i++)
				{
					g.drawString(Login.Users[i],(int)(TexLO.getWidth()/25),(int)(TexLO.getHeight()/3)+k);
					g.drawString(String.valueOf(Login.Scores[i]),(int)(TexLO.getWidth()/7),(int)(TexLO.getHeight()/3)+k);
					k+=30;
				}
			
		
			//draw different Sentences if you got better score or not 
				for (int i = 0; i < Login.LastUser; i++) 
				{
					if(Login.UserName.equals(Login.Users[i]))
					{
						if(level.Score>TmpS)
						{
				    		g.drawString("Good Job :) "+Login.UserName,(int)(TexLO.getWidth()/25),(int)(TexLO.getHeight()/5));
						}
				    	else
				    	{
				    		g.setColor(Color.YELLOW);
				    		g.drawString("You Need More Typing "+Login.UserName,(int)(TexLO.getWidth()/25),(int)(TexLO.getHeight()/5));
				    	}
						break;
					}
				}
	       	}
			// draw The Time You was played to end the game
			g.setColor(Color.MAGENTA);
			g.drawString("You Practiced For "+String.valueOf(ScrollingBackground.MinTmp)+ ":" +String.valueOf(ScrollingBackground.SecTmp), (int)(TexLO.getWidth()/25),(int)(TexLO.getHeight()/4));
		
			repaint();
	}
	
	//Method That Resize The Main BackGroundImage
			private BufferedImage resize(BufferedImage img, int width, int height) {
				BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
			    Graphics2D g2d = (Graphics2D) bi.createGraphics();
			    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			    g2d.drawImage(img, 0, 0, width, height, null);
			    g2d.dispose();
			    return bi;
			}
			
	//Play Again Button to start the Game 
	static class Action1 implements ActionListener {        
		public void actionPerformed (ActionEvent e) { 
			  level.LVL=1;
			  level.Lifes=10;
			  level.Score=0;
			  level.MoveX=1;
			  ScrollingBackground.Flag=0;
			Main.F.getContentPane().removeAll();
			Main.F.getContentPane().repaint();
			new Runner();
			
		}
	} 
	
	//Play With The User Own Words ;
	static class Action2 implements ActionListener {        
		public void actionPerformed (ActionEvent e) { 
			new UserWords();
		}
	}
	//Play With The User Own Words ;
	static class Action4 implements ActionListener {        
		public void actionPerformed (ActionEvent e) { 
			level.MoveX=0;
			level.LVL=7;
			level.Lifes=10;
			level.Score=0;
			ScrollingBackground.Flag=0;
			Main.F.getContentPane().removeAll();
			Main.F.getContentPane().repaint();
			new Runner();
		}
	}
	//Playing The Miss Words ;
		static class MissWords implements ActionListener {        
			public void actionPerformed (ActionEvent e) { 
				level.LVL=8;
				level.Lifes=10;
				level.Score=0;
				level.MoveX=2;
				ScrollingBackground.Flag=0;
				Main.F.getContentPane().removeAll();
				Main.F.getContentPane().repaint();
				new Runner();
			}
		}
	//Exit Button
	static class Action3 implements ActionListener {        
		public void actionPerformed (ActionEvent e)
		{ 
			System.exit(0);
		}
	}

}
