/** SWEN20003 Object Oriented Software Development 
 *  Project 1
 *  @author Syed Ahammad Newaz Saif
 *  Student Number: 684933
 *  Email: snewaz@student.unimelb.edu.au
 * 
 *  Water class inheriting from the Tile class that itself inherits from
 *  the Sprite class
 * */

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Water extends Tile
{
	/** Passes the image and x-y coordinates to the 
	 *  superclass Tile that then passes to the Sprite class.
	 *  @params: x, x co-ordinate, type float
	 *  @params y, y co-ordinate, type float
	 **/
	
	public Water(float x, float y) throws SlickException
	{
		super(ImageSources.WATER,x,y);
	}
	
	/** Overrides the update that it inherits and resets Bounding box of 
	 *  each pixel based on x and y coordinate from World
	 *  @params: input, Keyboard input inserted by the user. 
	 *  @params: delta, Time passed since last frame (milliseconds). 
	 **/ 
	
	@Override
	public void update(Input input, int delta) throws SlickException 
	{
		BoundingBox bbox  = this.getBbox();
		bbox.setX(this.getX());
	}
}
