/** SWEN20003 Object Oriented Software Development 
 *  Project 1
 *  @author Syed Ahammad Newaz Saif
 *  Student Number: 684933
 *  Email: snewaz@student.unimelb.edu.au
 * 
 *  Grass class inheriting from the Tile class that itself inherits from
 *  the Sprite class
 * */

import org.newdawn.slick.*;

public class Grass extends Tile
{
	/** Passes the image and x-y coordinates to the 
	 *  superclass Tile that then passes to the Sprite class.
	 *  @params: x, x co-ordinate, type float
	 *  @params y, y co-ordinate, type float
	 **/
	
	public Grass(float x, float y) throws SlickException
	{
		super(ImageSources.GRASS,x,y);
	}
	
}
