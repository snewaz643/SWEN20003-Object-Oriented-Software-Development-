/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 * 
 * Bus class inheriting from the Vehicles class
 * that inherits from the Movable which itself
 * inherits from the Sprite class.
 **/

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Bus extends Vehicles
{
	private float Speed = 0.15f; /*** Constant speed to be used for the Bus. ***/
	private boolean isLeft;  /*** Check if direction is left or not. ***/
	
	/**
	 * Passes the Image of the Bus
	 * to the Rideable class that passes
	 * to the Movable class that itself passes
	 * to the  Sprite class to create Bus objects.
	 * @param x, x co-ordinate, type float
	 * @param y, y co-ordinate, type float
	 * @param z, flag to keep track if it's left, type boolean
	 **/
	
	public Bus(float x, float y,boolean z) throws SlickException
	{
		super(ImageSources.BUS,x,y);
		isLeft = z;
		if(isLeft == false)
		{
			Speed = -1*Speed;
		}
		else
	    {
			Speed = 1*Speed;
		}
	}

	/**
	 * Overrides the update method in Sprite to
	 * keep the buses moving in alternating ways.
	 * @param input, Keyboard input inserted by the user,type Input.
	 * @param delta, Time passed since last frame (milliseconds),type integer.
	 */
	
	@Override
	public void update(Input input, int delta)
	{
		/**
		 * Since the buses are drawn half-centered, the buses have to be
		 * translated to accommodate it into the frame so that it keeps
		 * on drawing to the screen and adjust opposite directions as well.
		 **/

		this.setX(((this.getX() + 24) + (delta*(this.Speed))+(App.SCREEN_WIDTH+48))%(App.SCREEN_WIDTH+48)-24);

		/*** Update the BoundingBox of the bus. ***/
		BoundingBox bbox  = this.getBbox();
		bbox.setX(this.getX());
	}
}