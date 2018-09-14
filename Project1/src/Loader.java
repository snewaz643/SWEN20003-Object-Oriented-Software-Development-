import org.newdawn.slick.*;
import java.util.*;

/** SWEN20003 Object Oriented Software Development 
*  Project 1
*  @author Syed Ahammad Newaz Saif
*  Student Number: 684933
*  Email: snewaz@student.unimelb.edu.au
* 
*  Helper class to load the sprites except the player.
* */

public class Loader
{
	/** This method creates each of the sprite separately.
	*   @params: name, the name of the sprite.
	*   @params: x, sprite's x co-ordinate.
	*   @params: y, sprite's y co-ordinate.
	**/
	
	public static Sprite createSprite(String name, int x, int y) throws SlickException
	{
        switch (name)
        {
        	case Tag.BUS:
        		return new Bus(x,y);
        	case Tag.GRASS:
        		return new Grass(x,y);
        	case Tag.WATER:
        		return new Water(x,y);
        	default:
        		return null;
	
		}
	}
	
	
	/** This method loads the sprite into the ArrayList.
	*   @params: name, the name of the sprite.
	*   @params: x, sprite's x co-ordinate.
	*   @params: y, sprite's y co-ordinate.
	**/
	
	public static ArrayList<Sprite> LoadSprites(ArrayList<Sprite> Sprites)throws SlickException
	{
		// Creates the water and stores in sprite object arraylist.
		
		for(int j = 0; j < App.SCREEN_WIDTH;j+=App.TILE_SIZE) 
		{
			for(int i = ConstantValues.WATER_START_Y; i > ConstantValues.WATER_END_Y; i-=App.TILE_SIZE) 
			{
				Sprites.add(createSprite(Tag.WATER,j,i));
			}
		}
		
		// Creates the grass and stores in sprite object arraylist
		// to render at two sides of the roads.
		
		int k = ConstantValues.GRASS_POS_Y1;
		for(int l = 0; l < App.SCREEN_WIDTH;l+=App.TILE_SIZE) 
		{
			
			Sprites.add(createSprite(Tag.GRASS,l,k));
		}
		
		int m = ConstantValues.GRASS_POS_Y2;
		for(int n = 0; n < App.SCREEN_WIDTH;n+=App.TILE_SIZE) 
		{
			Sprites.add(createSprite(Tag.GRASS,n,m));
		}
		
		// Uses the offsets and the separation coordinates to create the 
		// Buses into the sprite arraylist.
		
		int array_y_coordinates[] = {432,480,528,576,624};
		int offsets[] = {48,0,64,128,250};
		double separation[] = {6.5,5.0,12.0,5.0,6.5};

		for(int o = 0; o < array_y_coordinates.length; o++) 
		{	
			for(int p = offsets[o]; p < App.SCREEN_WIDTH;p+= ((App.TILE_SIZE)*(separation[o]))) 
			{
			    Bus buses  = new Bus(p,array_y_coordinates[o]);
				if((o%2)!=0) 
				{
					buses.setSpeed(buses.getSpeed());
				}
				else 
				{
					buses.setSpeed(-1*buses.getSpeed());
				}
				Sprites.add(buses);
			}
		}
	   
	   return Sprites;	
	}
}
