import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

	public class Runner  {
		
		public Runner() {
			// Declare Screen Resolution For any Computer
			Dimension TexLO = Toolkit.getDefaultToolkit().getScreenSize();
	        int x = (int) ((int) TexLO.getWidth()/2.4);
	        int y = (int) ((int) TexLO.getHeight()/1.2);
	        
	        //Set location,Size And color for the TextField
	      	Main.T.setBounds(x,y,200,40);
	      	Main.T.setFont(new Font("Comic",Font.BOLD+Font.PLAIN,30));
	      	Main.T.setBackground(Color.white);
	      	//Set KeyListener to the TextField
	      	Main.T.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
	                	level._nextWord(Main.T.getText());
	                	Main.T.setText(null);
	                }
	                else 
	                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	                	System.exit(0);
	                }   
	            }
	        });
	      	//Focus on the TextField on start game
	      	SwingUtilities.invokeLater( new Runnable() { 
	      		public void run() { 
	      		        Main.T.requestFocus(); 
	      		    } 
	      		} );
	      	//Add Settings to the Main Frame 
	      	Main.F.add(Main.T);
	       	Main.F.add(new ScrollingBackground());
	      	Main.F.setVisible(true);
	      	Main.F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	    }
}