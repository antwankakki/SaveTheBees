package utils;

/**
 * This class is a helper to allow us to debug the project.
 * @author antwan
 */
public class Logger {
	private static boolean debug = false;
	
	/* Turn on or off debugging */
	public static void turnOnDebugging() {debug = true;}
	public static void turnOffDebugging() {debug = false;}
	
	/**
	 * Print a message to the error console if debugging is on
	 * @param message
	 */
	public static void log(String message)
	{
		// is debugging is on, print out the statements
		if (debug)
			System.err.println(message);
	}
}
