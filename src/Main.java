import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.lang.Object;

public class Main {
	//Settings to the Main Frame
	static TextField T = new TextField();
	static JFrame F = new JFrame("Type-oh");
	static JPanel P = new JPanel();
	
		public Main() throws IOException  {	
			    F.setExtendedState(F.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		        //text Field Location On any Screen
		        Dimension TexLO = Toolkit.getDefaultToolkit().getScreenSize();
		        int x = (int) ((int) TexLO.getWidth()/2.4);
		        int y = (int) ((int) TexLO.getHeight()/1.2);
		        
		      //ParaMeters For Main BackGround Image Location
		        Dimension imageLoc = Toolkit.getDefaultToolkit().getScreenSize();
		        int xImage = (int) ((int) imageLoc.getWidth());
		        int yImage = (int) ((int) imageLoc.getHeight());
		        
		     // Load the background image And Send It To Resize
                BufferedImage img = ImageIO.read(new File("images/TypeOh.jpg"));
                BufferedImage resizedImage=resize(img,xImage,yImage);//
                P.add(new JLabel(new ImageIcon(resizedImage)));
                
             // Buttons on The Main Frame
		        JButton button = new JButton("PLAY");
				  button.setBounds((int) (x*1.1),y/2, 100, 40);
				  F.add(button);
				  button.addActionListener (new Action1());	
				  	
				 JButton button1 = new JButton("Instruction");
			     button1.setBounds((int) (x*1.1),y/2+50, 100, 40);
			     F.add(button1);
				 button1.addActionListener (new Action2());	

				  JButton button2 = new JButton("EXIT");
				  button2.setBounds((int) (x*1.1),y/2+100,100, 40);
				  F.add(button2);
				  button2.addActionListener (new Action3());
				  // Adding the Panel to the Frame And Visible it
				  F.add(P);
			      F.setVisible(true);
			      F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    
			    
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
		//Play Button to delete all the Content from the Frame And Start the game
		static class Action1 implements ActionListener {        
			public void actionPerformed (ActionEvent e) { 
				
				F.getContentPane().removeAll();
				F.getContentPane().repaint();
				new Runner();
			}
		} 
		//Instructions Button to delete all the Content from the Frame And start the Instructions pages
		static class Action2 implements ActionListener {        
			public void actionPerformed (ActionEvent e) { 
				
				F.getContentPane().removeAll();
				F.getContentPane().repaint();
			  	F.add(new Instructions());
		      	Main.F.setVisible(true);
		      	Main.F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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