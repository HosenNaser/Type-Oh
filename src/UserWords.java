import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserWords {
	//Variables for the Frame
	static JFrame F = new JFrame("Your Own Words");
	static JPanel P = new JPanel();
	static JTextField T= new JTextField(20);
	static File UserFile = new File("level6.txt");
	static JButton SavePlay = new JButton("Save & Play");
	static JButton Exit = new JButton("Exit");
	//PrintWriter variables
	static boolean closePW=true;
	private static PrintWriter pw;
	//count the correct words
	static int WordsCount=0;
	
	public UserWords()
	{
		//variables for the Frame 
		F.setSize(400, 250);
		F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		F.setLocationRelativeTo(null);
		F.setAlwaysOnTop(true);
		//initialization the PrintWriter
		try {
			pw = new PrintWriter(UserFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		//Key Listener for the TextField to Add words To Text File
		T.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                	if(T.getText().length()>0)
                		pw.print(T.getText()+" ");
                	WordsCount++;
                	T.setText("");
                }
            }
        });
		
		//variables for the Frame 
		F.add(P);
		AddToPanel(P);
		F.setVisible(true);
	}
	//Function to Enter Component to Panel
	private static void AddToPanel(JPanel P)
	{
		//Disable division of JPanel
		P.setLayout(null);
		//Add label to the Panel
		JLabel TEXT = new JLabel("Enter Your Words And Press Enter");
		TEXT.setBounds(100, 10, 250, 25);
		P.add(TEXT);
		//Add Save & Play Button to the Panel
		SavePlay.setBounds(20, 150, 120, 30);
		P.add(SavePlay);
		SavePlay.addActionListener(new Save());
		//Add Exit Button to the Panel
		Exit.setBounds(250, 150, 120, 30);
		P.add(Exit);
		Exit.addActionListener(new Exit());
		//variables for the Panel
		T.setBounds(100, 50, 200, 30);
		T.setFocusable(true);
		P.add(T);
	}
	//Action Listener for the Save & start Game Button
	static class Save implements ActionListener {        
		public void actionPerformed (ActionEvent e)
		{ 
			pw.close();
			F.dispose();
			level.LVL=6;
			level.Lifes=10;
			level.Score=0;
			level.MoveX=2;
			ScrollingBackground.Flag=0;
			Main.F.getContentPane().removeAll();
			Main.F.getContentPane().repaint();
			new Runner();
		}
	} 
	//Action Listener for Exit button
	static class Exit implements ActionListener {        
		public void actionPerformed (ActionEvent e)
		{ 
			F.dispose();
		}
	} 
}