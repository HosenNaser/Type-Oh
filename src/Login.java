import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login   {
	//Variables for the Frame
	static String UserName;
	static JFrame frame = new JFrame("Login");
	static JPanel panel = new JPanel();
	static JTextField userText = new JTextField(20);
	static JPasswordField passwordText = new JPasswordField(20);
	static JButton loginButton = new JButton("login");
	static JButton exitButton = new JButton("Exit");
	static JButton registerButton = new JButton("register");
	
	//arrays to save the Users,Passwords And Scores
	static String[] Users = new String[100] ;
	static String[] Passwords = new String[100] ;
	static int[] Scores = new int[100] ;
	static int LastUser;
	
	public Login()
	{
		//Scan all Users And Insert Them to Arrays
		LastUser = Integer.valueOf(UserScanner.getInstance().read());
		UserScanner._newScanner("UserName");
		for(int i=0;i<LastUser;i++)
		{
			Users[i]=UserScanner.getInstance().read();
			Passwords[i]=UserScanner.getInstance().read();
			Scores[i]=Integer.valueOf(UserScanner.getInstance().read());
		}
		//Settings for the Frame
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		_Login(panel);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
			
	}
	//Function to Add Variables to panel
	private static void _Login(JPanel panel) {
		//Disable division of JPanel
		panel.setLayout(null);
		//Add label to the Panel
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);
		//Add TextField to the Panel
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);
		//Add label to the Panel
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);
		//Add TextField to the Panel
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);
		//Add Login Button to the Panel
		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);
		loginButton.addActionListener(new ActionLogin());
		//Add Register Button to the Panel
		registerButton.setBounds(180, 80, 80, 25);
		panel.add(registerButton);
		registerButton.addActionListener(new ActionRegister());
	}
	//Function to change the Login to Register
	private static void _Register(JPanel panel) {
		//Disable division of JPanel
		panel.setLayout(null);
		//Add label to the Panel
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);
		//Add TextField to the Panel
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);
		//Add label to the Panel
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);
		//Add TextField to the Panel
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);
		//Add Register Button to the Panel
		registerButton.setBounds(180, 80, 80, 25);
		panel.add(registerButton);
		registerButton.addActionListener(new newUser());
		//Add Exit Button to the Panel
		exitButton.setBounds(10, 80, 80, 25);
		panel.add(exitButton);
		exitButton.addActionListener(new Main.Action3());
	}
	public static void main(String[] args){
		   new Login();
		}
	//Action Listener for Login Button 
	static class ActionLogin implements ActionListener {        
		@SuppressWarnings("deprecation")
		public void actionPerformed (ActionEvent e)
		{ 
			//check if the User information are Exist 
			for(int i=0;i<LastUser;i++)
			{
				if(userText.getText().equals(Users[i]) && passwordText.getText().equals(Passwords[i]) )
				{
					UserName=Users[i];
					frame.dispose();
					try {
						new Main();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	} 
	//Action Listener for the Register Button to change the Panel to Register mode
	static class ActionRegister implements ActionListener {        
		public void actionPerformed (ActionEvent e)
		{ 
			panel.removeAll();
			panel.repaint();
			_Register(panel);
		}
	} 
	//Action Listener To Add New User
	static class newUser implements ActionListener  {        
		@SuppressWarnings("deprecation")
		public void actionPerformed (ActionEvent e)
		{
			// Add New User To The Text File
			if(userText.getText().length()>0 && passwordText.getText().length()>0)
			{
				File f = new File("AllUsers/UserName.txt");
				File f1 = new File("AllUsers/LastUser.txt");
				try {
					PrintWriter pw = new PrintWriter(f);
					for(int i=0;i<LastUser;i++)
					{
						pw.print(Users[i]+" "+Passwords[i]+" "+Scores[i]+" ");
					}
					pw.print(userText.getText()+" "+passwordText.getText()+" "+"0"+" ");
					pw.close();
					LastUser++;
					//Save The Number Of The All users
					PrintWriter pw1 = new PrintWriter(f1);
					pw1.print(LastUser+" ");
					pw1.close();
				}
				
				catch (FileNotFoundException e1) {}
				//Insert all the users to arrays
				UserScanner._newScanner("UserName");
				for(int i=0;i<LastUser;i++)
				{
					Users[i]=UserScanner.getInstance().read();
					Passwords[i]=UserScanner.getInstance().read();
					Scores[i]=Integer.valueOf(UserScanner.getInstance().read());
				}
				UserName=userText.getText();
				//Stop the Frame
				frame.dispose();
				//start the Main
				try {
					new Main();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	} 
}