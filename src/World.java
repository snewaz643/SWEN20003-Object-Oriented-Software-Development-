/** SWEN20003 Object Oriented Software Development 
 *  Project 1
 *  @author Syed Ahammad Newaz Saif
 *  Student Number: 684933
 *  Email: snewaz@student.unimelb.edu.au
 *  
 *  @author Eleanor McMurtry
 *  template
 * 
 *  World class used by the App, the class that creates all the objects
 *  and makes other objects interact with it.
 * */

import org.newdawn.slick.*;
import java.util.*;

public class World 
{	
	private String message;
    private Music mainMusic;
    private Sprite player;
    private boolean gameOver;
    private ArrayList<Sprite> sprites_array;
    
    /** Constructor setting up all the objects in the 
     *  world.
     **/
    
	public World() throws SlickException
	{
		//create a welcome message 
		
		message = "Welcome to the game!";
		mainMusic = new Music("res/Super Mario Bros. - Full.ogg");
		
		//create the player and load up all the sprites
		
		sprites_array=  new ArrayList<Sprite>();
		
		player = new Player(ConstantValues.FROG_POS_X,ConstantValues.FROG_POS_Y);
		
		sprites_array = Loader.LoadSprites(sprites_array);
	}
	
	/** Update all of the sprites in the game.
	 *  @params input, Keyboard input inserted by the user. 
	 *  @params delta, Time passed since last frame (milliseconds).
	 */
	public void update(Input input, int delta) throws SlickException
	{
		// Some keyboard based handling of the game execution
		
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			System.exit(0);
		}
		
		// Update all the sprites in the game
		
		for(Sprite sprite: sprites_array ) 
		{
			sprite.update(input, delta);
		}
		
		// Update the player continuously
		
		player.update(input,delta);
		
		// Detects collision of the player with the bus
		
		for(Sprite sprite: sprites_array) 
		{
			if(sprite instanceof Bus) 
			{
				if((player.contactSprite(sprite))== true) 
				{
					System.exit(0);
				}
			}	
		} 
		
		// Detects if the player fell into the water
		
		for(Sprite sprite: sprites_array) 
		{
			if(sprite instanceof Water) 
			{
				if((player.contactSprite(sprite))== true) 
				{
					System.exit(0);
				}
			}	
		} 
	}

	/** Draws all the sprites in the world
	 *  @params g, The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g) throws SlickException 
	{
		
		// Attempt at making a prompt of Game Over! in basic games
		
		if(this.gameOver==true) 
		{
			g.drawString("Game Over!\n",App.SCREEN_WIDTH/2,App.SCREEN_HEIGHT-10);
		}
		
		// Show the message on the screen
			
		
		g.drawString(this.message,App.SCREEN_WIDTH/2,50);
		
		this.mainMusic.setVolume(0.5f);
		this.mainMusic.play();
		
		// Draw all of the sprites in the game and then the player
		
		for(Sprite sprite:sprites_array) 
		{
			sprite.render();
		}
		
		player.render();
	}
	
	
	
	
	public void restartLevel() throws SlickException
	{
		//Restarts a new level by creating a new World 
		
		new World();
	}
}
