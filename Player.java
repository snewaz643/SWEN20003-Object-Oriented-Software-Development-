/** SWEN20003 Object Oriented Software Development 
 *  Project 1
 *  @author Syed Ahammad Newaz Saif
 *  Student Number: 684933
 *  Email: snewaz@student.unimelb.edu.au
 * 
 *  Player class inheriting from the Sprite class
 * */

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Player extends Sprite
{
	/** Passes the Image of the player
	 *  to the Sprite class(superclass)
	 *  @params x, x co-ordinate, type float
	 *  @params y, y co-ordinate, type float
	 * */
	public Player(float x,float y) throws SlickException 
	{
		super(ImageSources.FROG,x,y);	
	}

	
	/** Overrides the method in Sprite to make Player move.
	 *  @params input, Keyboard input inserted by the user. 
	 *  @params delta, Time passed since last frame (milliseconds).
	 **/
	@Override
	public void update(Input input, int delta) 
	{
		if(input.isKeyPressed(Input.KEY_LEFT)) 
		{
			// Makes sure player doesn't go out of the  screen as it moves left 
			
			if(this.getX()-(App.TILE_SIZE)> 0)
			{ 
				this.setX(this.getX()-(App.TILE_SIZE));
			}
		}
		if(input.isKeyPressed(Input.KEY_RIGHT)) 
		{
			// Makes sure player doesn't go out of the  screen as it moves right
			
			if(this.getX()+(App.TILE_SIZE)< App.SCREEN_WIDTH) 
			{
				this.setX(this.getX()+(App.TILE_SIZE));
			}	
		}
		if(input.isKeyPressed(Input.KEY_UP)) 
		{
			// Makes sure player doesn't go out of the screen as it moves up
			
			if(this.getY()-App.TILE_SIZE > 0) 
			{
				this.setY(this.getY()-(App.TILE_SIZE));
			}	
		}
		if(input.isKeyPressed(Input.KEY_DOWN)) 
		{
			// Makes sure player doesn't go out of the  screen as it moves down 
			
			if(this.getY()+App.TILE_SIZE < App.SCREEN_HEIGHT) 
			{
				this.setY(this.getY()+(App.TILE_SIZE));
			}	
		}
		
		// Gets the Bounding box for the object which detects collision in the world
		
		BoundingBox bbox = this.getBbox();
			
		bbox.setY(this.getY());
		bbox.setX(this.getX());
		}
}