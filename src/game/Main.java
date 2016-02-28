package game;

import comm.CommManager;
import gUtils.*;

public class Main extends java.applet.Applet {

	public void init()
	{
		Logger.turnOnDebugging();
		Logger.log("TEST");
		
		new CommManager("Ak47C", "1,2,3,4,5");
	}
	
	
}
