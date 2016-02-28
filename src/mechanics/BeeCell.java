package mechanics;

import gUtils.Logger;

/**
 * This class represents a bee cell. It gives references to the neighbors of the cell and 
 * allows the user to check on the kids.
 * @author antwan
 *
 */
public class BeeCell {
	
	/* Constants */
	private static final String DEBUG_CONSTRICT = "NEW CELL MADE";
	
	public static final int TOP_LEFT = 0, TOP = 1, TOP_RIGHT = 2, BOTTOM_RIGHT = 3,
							BOTTOM = 4, BOTTOM_LEFT = 5;
	
	public static final int PESTICIDE = 6, DYING = 5, VERY_SICK = 4, SICK = 3, 
							NORMAL = 2, VERY_HAPPY = 1; 
	
	/* The honeycomb's neighbors */
	public BeeCell[] neighbors = new BeeCell[6];
	
	/* Status of the bee */
	private int mStatus = 0;
	
	/**
	 * Constructor
	 */
	public BeeCell(){
		Logger.log(DEBUG_CONSTRICT);
	}
	
	/**
	 * Returns the status of the bee responsible for this hive
	 * @return
	 */
	public int getStatus()
	{
		return mStatus;
	}
	
}
