/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 * 
 * Grass class inheriting from the Tile class that itself inherits from
 * the Sprite class.
 **/

import org.newdawn.slick.*;

public class Grass extends Tile
{
	/**
	 * Passes the image and x-y coordinates to the
	 * superclass Tile that then passes to the Sprite class
	 * to create the Grass objects.
	 * @param x, x co-ordinate, type float
	 * @param y, y co-ordinate, type float
	 **/
	
	public Grass(float x, float y) throws SlickException
	{
		super(ImageSources.GRASS,x,y);
	}
	
}