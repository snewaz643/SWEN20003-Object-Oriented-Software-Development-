/**
 * SWEN20003 Object Oriented Software Development
 *  Project 2B
 *  @author Syed Ahammad Newaz Saif
 *  Student Number: 684933
 *  Email: snewaz@student.unimelb.edu.au
 *  
 *  @co-author Eleanor McMurtry
 *  template
 * 
 *  Sprite class used in the World class for the App to use it.
 * */

import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public abstract class Sprite
{
	private Image image;
	private float x;
	private float y;
	private BoundingBox bbox;
	private static boolean collision;

	/**
	* A sprite constructor its initializes parameters.
	* @param imageSrc, the source file of the sprite image, type String.
	* @param x, x co-ordinate, type float.
	* @param y, y co-ordinate, type float.
	**/
	
	public Sprite(String imageSrc,float x, float y) throws SlickException
	{
		/**
		 * Just creates the sprite for the particular category of the
		 * sprite in the World.
		 **/
		
		this.image = new Image(imageSrc);
		this.x = x;
		this.y = y;
		this.setCollisions(this.getCollisions());
		this.setBbox(new BoundingBox(this.image,x,y));
	}

	/**
	 * Setter for setting the flag of the collision.
	 * @param collisions, the flag signifying if the sprite collided, type boolean.
	 **/

	public  void setCollisions(boolean collisions)
	{
		this.collision = collisions;
	}

	/**
	 * Getter for getting the flag of the sprite's collision status.
	 * @return collision, the collision status, type boolean.
	 **/

	public static boolean getCollisions()
	{
		return collision;
	}


	/**
	* Getter for getting the sprite's current x co-ordinate .
	* @return x, the current x co-ordinate, type float.
	**/

	public float getX() 
	{
		return this.x;
	}

	/**
	* Getter for getting the sprite's current y co-ordinate .
	* @return y, the current x co-ordinate, type float.
	**/

	public float getY() 
	{
		return this.y;
	}

	/**
	 * Setters for getting the sprite's current x co-ordinate.
	 * @param x, the sprite's current x co-ordinate, type float.
	 **/
	public void setX(float x) 
	{
		this.x = x;
	}

	/**
	* Setters for getting the sprite's current y co-ordinate.
	* @param y, the sprite's current y co-ordinate, type float.
	**/
	public void setY(float y)
	{
		this.y = y;
	}
	
	/**
	 * This method gets overridden by the subclasses.
	 **/
	public void update(Input input,int delta) throws SlickException
	{
	}
	
	/**
	 * This method is used to draw the sprites center-wise using the x,y co-ordinates.
	 **/
	public void render() throws SlickException
	{
		/*** The images are drawn centred in the screen. ***/
		image.drawCentered(x,y);
	}

	/**
	 * This method checks if the two sprites make contact with each other.
	 * @param other, the other sprite whose BoundingBox are compared for intersection.
	 * @return true (boolean) if they make contact.
	 **/
	public boolean contactSprite(Sprite other) 
	{
		boolean value  = false;
		/*** It should be called to check when one sprite makes contact with another. ***/
		if((this.bbox.intersects(other.getBbox()))==true)
		{
			return true;
		}
		return value;
	}

	/**
	 * Setter to set Bounding box for each sprite.
	 * @param bbox, the bounding box object of the sprite, type BoundingBox.
	 **/
	
	public void setBbox(BoundingBox bbox)
	{
		this.bbox = bbox;
	}

	/**
	 * Getter to get the bounding box for each sprite.
	 * @return bbox, the bounding box of the sprite, type BoundingBox.
	 **/

	public BoundingBox getBbox() 
	{
		return bbox;
	}
}
