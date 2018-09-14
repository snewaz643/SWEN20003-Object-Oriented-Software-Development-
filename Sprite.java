/** SWEN20003 Object Oriented Software Development 
 *  Project 1
 *  @author Syed Ahammad Newaz Saif
 *  Student Number: 684933
 *  Email: snewaz@student.unimelb.edu.au
 *  
 *  @author Eleanor McMurtry
 *  template
 * 
 *  Sprite class used in the World class for the App to use it
 * */

import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public class Sprite {
	private Image image;
	private float x;
	private float y;
	private BoundingBox bbox;
	
	public Sprite(String imageSrc,float x, float y) throws SlickException
	{
		// Why would the constructor need a path to an image, and a coordinate?
		
		/**  Just creates the sprite for the particular category of the
		 *   sprite in the World
		 **/
		
		this.image = new Image(imageSrc);
		this.x = x;
		this.y = y;
		this.setBbox(new BoundingBox(this.image,x,y));
		placeInScreen();
	}
	
	/*public Position getPos()
	{
		Position pos = new Position(x,y);
		return pos;
	}*/
	
	/** Setters and getters to get x and y coordinates
	 * */
	
	public float getX() 
	{
		return this.x;
	}
	
	public float getY() 
	{
		return this.y;
	}
	
	public void setX(float x) 
	{
		this.x = x;
	}
	
	public void setY(float y) 
	
	{
		this.y = y;
	}
	
	/** Gets overridden by the subclasses
	 * */
	public void update(Input input,int delta) throws SlickException {
		// How can this one method deal with different types of sprites?
	}
	
	/** Method to draw the sprites center-wise
	 * */
	public void render() throws SlickException{
		// This should be pretty simple.
		image.drawCentered(x,y);
	}
	
	/** Class checks if the two sprites make contact with each other
	 *  overriden by each sprite subclass when created as objects in
	 *  World  
	 **/
	public boolean contactSprite(Sprite other) 
	{
		// Should be called when one sprite makes contact with another.
		boolean value  = false;
		if((this.bbox.intersects(other.getBbox()))==true) 
		{
			return true;
		}
		return value;
	}
	
	/** Setters and Getters for the Bounding box for each sprite
	 **/
	
	public void setBbox(BoundingBox bbox) 
	{
		this.bbox = bbox;
	}
	
	public BoundingBox getBbox() 
	{
		return bbox;
	}
	
	/**  Turn the float coordinates to integers to put them into
	 *   the grids
	 **/
	
	public void placeInScreen() 
	{
		x /= App.TILE_SIZE;
		y /= App.TILE_SIZE;
		x = Math.round(x);
		y = Math.round(y);
		x *= App.TILE_SIZE;
		y *= App.TILE_SIZE;
	}
}
