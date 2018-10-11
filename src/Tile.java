/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * Tile class inheriting from the Sprite class.
 **/

import org.newdawn.slick.SlickException;

public class Tile extends Sprite
{
    /**
	 * A constructor that passes the image source to the sprite class
	 * and initialize parameters to create Tile objects.
	 * @param imageSrc, the source file of the sprite image, type String.
	 * @param x, x co-ordinate, type float.
	 * @param y, y co-ordinate, type float.
	 **/
	
	public Tile(String imageSrc,float x, float y) throws SlickException
	{
		super(imageSrc,x,y);
	}
}