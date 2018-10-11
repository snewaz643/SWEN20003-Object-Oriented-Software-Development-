/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 * 
 * Player class inheriting from the Sprite class.
 **/

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import utilities.BoundingBox;


public class Player extends Sprite
{
	/** Player constructor that initializes the parameters in the class.
	 *  @param x, x co-ordinate, type float.
	 *  @param y, y co-ordinate, type float.
	 **/

	private boolean safe_status = false; /*** Safety flag status. ***/

	public Player(float x,float y) throws SlickException
	{
		super(ImageSources.FROG,x,y);
		this.setSafeStatus(this.getSafeStatus());
	}

	/**
	 * Setter for setting new safety status.
	 * @param safe_status, the new safety status, type boolean.
	 **/
    public void setSafeStatus(boolean safe_status)
    {
        this.safe_status = safe_status;
    }

	/**
	 * Getting the new safety status of the player.
	 * @return safe_status, the new safety status, type boolean.
	 **/
    public boolean getSafeStatus()
    {
        return safe_status;
    }

    /**
	 * 	Overrides the method in Sprite to make Player move.
	 *  @param input, Keyboard input inserted by the user,type Input.
	 *  @param delta, Time passed since last frame (milliseconds),type integer.
	 **/

	@Override
	public void update(Input input, int delta)
	{
		if(input.isKeyPressed(Input.KEY_LEFT)) 
		{
			/*** Makes sure player doesn't go out of the  screen as it moves left. ***/
			
			if(this.getX()-(App.TILE_SIZE)> 0)
			{ 
				this.setX(this.getX()-(App.TILE_SIZE));
			}
		}
		if(input.isKeyPressed(Input.KEY_RIGHT)) 
		{
			/*** Makes sure player doesn't go out of the  screen as it moves right ***/
			
			if(this.getX()+(App.TILE_SIZE)< App.SCREEN_WIDTH) 
			{
				this.setX(this.getX()+(App.TILE_SIZE));
			}	
		}
		if(input.isKeyPressed(Input.KEY_UP)) 
		{
			/*** Makes sure player doesn't go out of the screen as it moves up ***/
			
			if(this.getY()-App.TILE_SIZE > 0) 
			{
				this.setY(this.getY()-(App.TILE_SIZE));
			}	
		}
		if(input.isKeyPressed(Input.KEY_DOWN)) 
		{
			/** Makes sure player doesn't go out of the  screen as it moves down. ***/
			
			if(this.getY()+App.TILE_SIZE < App.SCREEN_HEIGHT) 
			{
				this.setY(this.getY()+(App.TILE_SIZE));
			}	
		}
		
		/*** Gets the Bounding box for the player which detects collision in the world. ***/
		
		BoundingBox bbox = this.getBbox();
			
		bbox.setY(this.getY());
		bbox.setX(this.getX());
	}
}