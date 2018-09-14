/** SWEN20003 Object Oriented Software Development 
 *  Project 1
 *  @author Syed Ahammad Newaz Saif
 *  Student Number: 684933
 *  Email: snewaz@student.unimelb.edu.au
 * 
 *  Tile class inheriting from the Sprite class.
 * */

import org.newdawn.slick.*;

public class Tile extends Sprite
{
    /** Passes the Image from the subclasses which are Water and Grass to
	 *  the Sprite.
	 *  @params: imagSrc,the string-form source of the image.
	 *  @params: x, x co-ordinate
	 *  @params: y, y co-ordinate
	 * */
	
	public Tile(String imageSrc,float x, float y) throws SlickException
	{
		super(imageSrc,x,y);
	}
}