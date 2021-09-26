import java.util.Timer;
import java.util.TimerTask;

public class CountTimer {
	//variables for Timer
	public static  int Seconds=0;
	public  static int Minutes=0;
	static double Wpm;
	static Timer MyTimer=new Timer();
	//Anonymous Class To Run The Timer 
	static TimerTask task=new TimerTask()
	{
		@Override
		public void run() {
			if(Seconds==59) {
				Seconds=0;
				Minutes++;
			}
			Seconds++;
		}		
	};
	//start the Timer
	public static void start(){
		Minutes=0;
		Seconds=0;
		MyTimer.scheduleAtFixedRate(task,1000,1000);
		
	}
}
