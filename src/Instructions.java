import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Instructions extends JPanel {
		// Variables for Instructions
		  static int i=0,H=0;
		  static String[] str;
		  static Scanner S;
		  private static BufferedImage image;
		  JButton B = new JButton("Next");
		  
		  //X,Y Locations For Any Screen To Print the Photos
		  Dimension imageLoc = Toolkit.getDefaultToolkit().getScreenSize();
		        int x = (int) ((int) imageLoc.getWidth()/4);
		        int y = (int) ((int) imageLoc.getHeight()/22);
		        
		 @SuppressWarnings("resource")
		 
		public Instructions() 
		  {
			  str= new String[16];
			  //Location And Size For the Next Button
			  B.setBounds((int) (imageLoc.getWidth()/1.2),(int) (imageLoc.getHeight()/1.2), 100, 40);
			  B.addActionListener (new Action1());
			  //Opening the Instructions Images
			  try {
				  S = new Scanner(new File("InstructionsTips.txt")).useDelimiter(";");
		          image=ImageIO.read(new File("InstructionsImages/"+String.valueOf(i+1)+".jpg"));   
		        }
		        catch (Exception e) { System.out.println(e);}
			  //Insert the Instructions to array
			  for(int k=0;k<16;k++)
			  {
				  str[k]= S.next();
			  }
		  }       
		 //Print Function to draw the images and the Instructions sentences
		public void paint(Graphics g){
			super.paint(g);
			Font Lives =new Font("Comic",Font.BOLD+Font.PLAIN,30);
	        g.setFont(Lives); 
	        g.setColor(Color.RED);
	        	for(int k=0,D=20;k<4;k++)
	        	{
	        		g.drawString(str[i+k+H],50, (int) (imageLoc.getHeight()/1.5)+D);
	        		D+=30;
	        	}
			this.setBackground(Color.BLACK);
			g.drawImage(image, x, 10, (int)(imageLoc.getWidth()/2), (int) ((int) imageLoc.getHeight()/1.6), null);
			B.setVisible(true);
			this.add(B);
			repaint();
	}
		//Action Listener for the Next Button
		static class Action1 implements ActionListener {        
			public void actionPerformed (ActionEvent e) { 
				if(i<3)
				{
				i++;
				H+=3;
				new Instructions();
				}
				else{
					Main.F.getContentPane().removeAll();
					Main.F.getContentPane().repaint();
					new Runner();
				}
			}
		} 
}
