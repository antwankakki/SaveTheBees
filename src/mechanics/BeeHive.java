package mechanics;

import gUtils.Logger;

public class BeeHive {
	
	/* Constants */
	private static final String DEBUG_CONSTRUCT = "New Beehive made";
	
	
	private static final int DEFAULT_SIZE = 7;
	
	
	/**
	 * Constructor 
	 */
	public BeeHive()
	{
		this(DEFAULT_SIZE);
	}
	
	public BeeHive(int size)
	{
		Logger.log(DEBUG_CONSTRUCT);
		/* create the number of cells required to have exactly */
	}
}
