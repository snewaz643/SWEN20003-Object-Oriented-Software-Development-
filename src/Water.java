/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 * 
 * Water class inheriting from the Tile class that itself inherits from
 * the Sprite class.
 **/

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Water extends Tile
{
	/**
	 * Water constructor that initializes the water object and return to the superclass.
	 * @param x, x co-ordinate, type float.
	 * @param y, y co-ordinate, type float.
	 **/
	
	public Water(float x, float y) throws SlickException
	{
		super(ImageSources.WATER,x,y);
	}
	
	/**
	 * Overrides the update that it inherits (Tile) and resets Bounding box of
	 * each sprite based on x and y coordinate from World.
	 * @param input, Keyboard input inserted by the user,type Input.
	 * @param delta, Time passed since last frame (milliseconds), type integer.
	 **/ 
	
	@Override
	public void update(Input input, int delta)
	{
		BoundingBox bbox  = this.getBbox();
		bbox.setX(this.getX());
	}
}