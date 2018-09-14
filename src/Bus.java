/** SWEN20003 Object Oriented Software Development 
 *  Project 1
 *  @author Syed Ahammad Newaz Saif
 *  Student Number: 684933
 *  Email: snewaz@student.unimelb.edu.au
 * 
 *  Bus class inheriting from the Sprite class
 * */

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Bus extends Sprite
{
	private float Speed = 0.15f; // Constant speed to be used 
	
	
	/** Passes the Image of the Bus
	 *  to the Sprite class(superclass)
	 *  @params x, x co-ordinate, type float
	 *  @params y, y co-ordinate, type float
	 * */
	
	public Bus(float x, float y) throws SlickException
	{
		super(ImageSources.BUS,x,y);
	}
	
	// setters and getters to get the Speed
	
	public float getSpeed() 
	{
		return Speed;
	}
	
	public void setSpeed(float speed)
	{
		this.Speed = speed;
	}
	
	
	/** Overrides the update method in Sprite to 
	 *  keep the buses moving in alternating ways.
	 *  @params input, Keyboard input inserted by the user. 
	 *  @params delta, Time passed since last frame (milliseconds).
	 */
	
	@Override
	public void update(Input input, int delta) throws SlickException
	{
		// Since the buses are drawn half-centered, the buses have to be
		// translated to accommodate it into the frame so that it keeps 
		// on drawing to the screen and adjust opposite directions as well
		
		this.setX(((this.getX() + 24) + (delta*(this.Speed))+(App.SCREEN_WIDTH+48))%(App.SCREEN_WIDTH+48)-24);
		BoundingBox bbox  = this.getBbox();
		bbox.setX(this.getX());
	}
}
