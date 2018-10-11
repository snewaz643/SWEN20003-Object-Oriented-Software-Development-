/**
 * SWEN20003 Object Oriented Software Development
*  Project 2B
*  @author Syed Ahammad Newaz Saif
*  Student Number: 684933
*  Email: snewaz@student.unimelb.edu.au
* 
*  Helper class to load the sprites except the player.
**/

import org.newdawn.slick.*;

import java.io.*;
import java.util.*;

public class Loader
{
	/**
	 * Creates all the movable sprite objects and initializes it's x,y co-ordinates and sets up it's isLeft flag.
	 * @param name, The source-file name for the object to be created, type String.
	 * @param x, x co-ordinate, type float.
	 * @param y, y co-ordinate, type float.
	 * @param z, boolean flag to check if it is to the left, type boolean.
	 * @return sprite object, the sprite object created then returned to be stored in the ArrayList, type Sprite.
	 **/
	public static Sprite createMovableSprite(String name, float x, float y,boolean z) throws SlickException
	{
        switch (name)
        {
			case Tag.BIKE:
				return new Bike(x,y,z);
			case Tag.BULLDOZER:
			    return new Bulldozer(x,y,z);
			case Tag.BUS:
				return new Bus(x,y,z);
			case Tag.LOG:
				return new Log(x,y,z);
			case Tag.LONGLOG:
				return new LongLog(x,y,z);
			case Tag.RACECAR:
				return new RacingCar(x,y,z);
			case Tag.TURTLES:
				return new Turtles(x,y,z);
        	default:
        		return null;
		}
	}

	/**
	 * Creates all the stationary sprite objects and initializes it's x,y co-ordinates.
	 * @param name, The source-file name for the object to be created, type String.
	 * @param x, x co-ordinate, type float.
	 * @param y, y co-ordinate, type float.
	 * @return sprite object, the sprite object created then returned to be stored in the ArrayList, type Sprite.
	 **/

	public static Sprite createTileSprite(String name, float x, float y) throws SlickException
	{
		switch (name)
		{
			case Tag.GRASS:
				return new Grass(x,y);
			case Tag.TREE:
				return new Tree(x,y);
			case Tag.WATER:
				return new Water(x,y);
			default:
				return null;

		}
	}

	/**
	 * A method that delegates it's task to make separate movable and stationary objects and returns an array of
	 * sprite objects back to the place the method is called.
	 * @param SpriteArray, the ArrayList to store the objects, type ArrayList<Sprite>.
	 * @param filename, the source file name of the object containing the information about the sprite
	 * object, type String.
	 * @return ArrayList of Sprite objects that contains all the sprite objects created from subsequent
	 * helper methods in this class.
	 */
	public static ArrayList <Sprite> LoadSprites(ArrayList<Sprite> SpriteArray, String filename) throws SlickException
    {

		/*** Reads the relevant csv files ***/
		try(BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String line;
			String entities[];
			float x,y;
			boolean z;

			while((line  = br.readLine())!=null)
			{
				entities = line.split(","); /**** Split data to parse information. ***/
				if(entities.length == 4 ) /*** Movable object ***/
				{
					x = Integer.parseInt(entities[1]); /*** X co-ordinate ***/
					y = Integer.parseInt(entities[2]); /*** Y co-ordinate ***/
					z = Boolean.parseBoolean(entities[3]); /*** Boolean flag for checking if it is left or not***/
					SpriteArray.add(createMovableSprite(entities[0], x, y,z));
				}
				else if(entities.length ==3) /*** Stationary object ***/
				{
					x = Integer.parseInt(entities[1]); /*** X co-ordinate ***/
					y = Integer.parseInt(entities[2]); /*** Y co-ordinate ***/
					SpriteArray.add(createTileSprite(entities[0], x, y));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();  /*** File Exception handled. ***/
		} catch (IOException e) {
			e.printStackTrace(); /*** Input-Output Exception handled. ***/
		}
		return SpriteArray;
	}
}