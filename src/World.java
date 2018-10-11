/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *  
 * @co-author Eleanor McMurtry
 * template
 * 
 * World class used by the App, the class that creates all the objects
 * and makes other objects interact with it.
 **/

import org.newdawn.slick.*;
import java.lang.*;

import java.util.*;

public class World 
{
    private Music mainMusic;
    private Player player;
    private ArrayList<Lives> lives;
    private boolean appear;
    private boolean left;
    private float changeinX;
    private float changeinY;
    private boolean contactedPlayerFirst;
    private boolean contactedHeadOn;
    private ArrayList<Boolean> Slots_availability;
    private static int curr_lvl;
    private boolean safe;
	private int pointsWon;
	private int counter;
    private ArrayList<Float>  Target_x_coordinates;
    private ExtraLife extraLife;
    private boolean extraLifePresent;
    private boolean extraLifeNeverCreated;
    private ArrayList<Sprite> sprites_array;
    private int direction;
    private Sprite randomLog;
    private ArrayList<Sprite> LogsArray;
	private ArrayList<Sprite> intersecting_sprites;
    private int time1;
	private boolean newflag;
	private  float x;
	private float currXplayer;
	private float currYplayer;
	private float prevXplayer;
    private float prevYplayer;
    private ArrayList<Player> dummies;

    /**
     * World constructor setting up all the objects in the
     * world for all forms of interaction.
     * @param level, the number of levels inputted from the World, type integer.
     **/
    
	public World(int level) throws SlickException
	{

	    /*** This guard checks if the game doesn't bypass the maximum level searching up for files not provided. **/
	    if(curr_lvl== ConstantValues.MAX_LEVEL)
	    {
	        System.exit(0);
        }

		/*** This creates the music object. ***/

		mainMusic = new Music("res/Super Mario Bros. - Full.ogg");
		
		/*** This creates the array to store the sprites  ***/

		Target_x_coordinates = new ArrayList<Float>();

        /*** This creates the array to store the lives object representing number of lives left for the player.  ***/

		lives  = new ArrayList<Lives>();

		intersecting_sprites = new ArrayList<Sprite>();

        /*** This creates the array of logs and long logs to later generate random log to place the extralife.  ***/
		LogsArray = new ArrayList<Sprite>();

        /*** Resetting the points initially to zero. ***/
		pointsWon = 0;

        /*** This creates the live objects starting at x = 56 in increments of 32 and y = 722. ***/
		for(int i = 1; i < ConstantValues.NUM_LIVES+1;i++)
		{
			lives.add(new Lives(ConstantValues.LIVES_START_X_PART1 + (i * ConstantValues.LIVES_SEPARATION_X), ConstantValues.LIVES_Y));
		}

		/*** Stores the sprites array. ***/

        sprites_array = new ArrayList<Sprite>();

        /*** Create the player with x  = 512 and y = 720. ***/

		player = new Player(ConstantValues.FROG_POS_X,ConstantValues.FROG_POS_Y);

        curr_lvl = level;

        /*** Load and store all the sprites in an array. ***/

		sprites_array =  Loader.LoadSprites(sprites_array,"assets/levels/" + Integer.toString(curr_lvl) + ".lvl");

		/*** Storing the coordinates to find start and end of each target black holes that indicate player point. ***/

		for(int i = 0; i < sprites_array.size();i++)
        {
            if(sprites_array.get(i) instanceof Tree)
            {
                if(sprites_array.get(i).getY() == App.TILE_SIZE) /*** The trees at y = 48. ***/
                {
                   /*** The black holes(slots) are more than a unit tile size greater ( > 48) and are grabbed here.***/
                   if(((sprites_array.get(i+1).getX()-sprites_array.get(i).getX())/App.TILE_SIZE)> 1.00)
                   {
                      Target_x_coordinates.add(sprites_array.get(i).getX()); /*** The starting hole x co-ordinate.***/
                      Target_x_coordinates.add(sprites_array.get(i+1).getX()); /*** The ending hole x co-ordinate.***/
                   }
                }
            }
        }

        /*** Set the dummies first so that when it hits the black holes we turn them on. ***/

		dummies = new ArrayList<Player>();

		/*** Create the flag that makes sure there is single entry into a slot and frog dies upon tries to enter a
             preoccupied slot. ***/

        Slots_availability = new ArrayList<Boolean>();

        /*** Initialize the slots to false and initialized the dummies to each centre of the slots
             that appear when player reaches the black hole(slots). ***/

        for(int d  = 0;d < Target_x_coordinates.size();d+=2)
        {
            dummies.add(new Player((Target_x_coordinates.get(d+1)+Target_x_coordinates.get(d))/2,App.TILE_SIZE));
            Slots_availability.add(false);
        }

        /*** Create an extralife that only gets rendered after 25seconds to a random log. ***/

        extraLife = new ExtraLife(0,0);
        /*** Flag used to make the turtles appear and disappear. ***/

		appear  = true;

		/*** The randomised time (between 25 and 35 seconds) that will be used to make the turtles appear and disappear
             in level 2. ***/

        time1 = (int) (Math.random() * (35000 - 25000)) + 25000;
        /*** Counter useful in resetting time for the turtles. ***/

        counter  = 0;

        /*** Some flags for extralife . ***/

        extraLifeNeverCreated = true;
        direction = 1;
        newflag = false;
        left =false;
        x = 0.0f;  /*** Previous x value of extralife. ***/

        prevXplayer = ConstantValues.FROG_POS_X; /*** Initial x value of player is the previous x value of player. ***/
        prevYplayer = ConstantValues.FROG_POS_Y; /*** Initial y value of player is the previous y value of player. ***/
	}
	
	/**
     * The update method keeps on updates objects its interaction and settings(everything related to the game).
	 * @param input, Keyboard input inserted by the user, type Input.
	 * @param delta, Time passed since last frame (milliseconds),type integer.
	 */
	public void update(Input input, int delta) throws SlickException
    {

		if (hasWon())
		{
			if(curr_lvl == ConstantValues.MAX_LEVEL)
			{
			    System.exit(0);
			}
            else if(pointsWon == 3)
            {
                LoadNextLevel();
            }
		}

        /*** Some keyboard based handling of the game execution. ***/

		if (input.isKeyPressed(Input.KEY_ESCAPE))
		{
			System.exit(0);
		}

        /*** If R, restart level. ***/

        if (input.isKeyPressed(Input.KEY_R)) {
            restartLevel();
        }


        safe = false;

        counter += delta;

        for(Sprite sprite1: sprites_array)
        {
            if(sprite1 instanceof  Turtles)
            {
                if (counter > 7000 && counter < 9000)
                {
                    appear = false; /*** Make turtles disappear between 7 and 9 seconds for 2 seconds. ***/
                } else if (counter >= 9000)
                {
                    appear = true; /***  Make turtles appear after 9 seconds and reset counter. ***/
                    counter = 0;
                }
            }
            if(sprite1 instanceof Log || sprite1 instanceof LongLog)
            {
                LogsArray.add(sprite1); /*** Accumulate the Log and LongLog into an array to find the random log for
                                             the extralife. ***/
            }

            sprite1.update(input,delta); /*** Updates the all sprite on the screen. ***/
        }

        /*** Creates the extralife at the random log after the random time between 25 and 35.
         *** It gives life if they contact the player.
         ***/

        if(extraLifeNeverCreated)
        {
            time1 -= delta;
        }
        if(time1<=0)
        {
            if(extraLife != null)
            {
                randomLog = getRandomLog(LogsArray); /*** Extralife created after 25 seconds so do not need to keep
                                                          decrease the old time  ***/
                extraLifePresent = true;
                extraLifeNeverCreated = false;
            }
        }

        if(!extraLifeNeverCreated)
        {
            if(extraLife!=null) {
                time1 += delta; /*** Adds the time so that it can be used to disappear, move extralife ***/

                if (newflag) {
                    extraLife.setX(randomLog.getX() + (extraLife.getX() - x)); /*** Adds the extralife to the random
                     log's new x co-ordinate since it moved and shift by the previous it made on the log.  ***/

                    x = randomLog.getX(); /*** Stores the randomLog's current log to keep track of its movement. ***/

                } else {
                    extraLife.setX(randomLog.getX()); /*** Set the extralife on the randomLog. ***/

                    x = randomLog.getX();  /*** Stores the randomLog's current log to keep track of its movement. ***/
                }

                /*** Update the Y value of extralife and the BoundingBox, and set the newflag to true
                     that signifies that the extralife is on board. ***/
                extraLife.setY(randomLog.getY());
                extraLife.setBbox(randomLog.getBbox());
                newflag = true;
            }
        }

        /*** After 2 seconds the extralife moves  ***/
        if(time1 % 2000 == 0  && time1 > 0 && !extraLifeNeverCreated && extraLife!= null)
        {
             /*** Moves the extralife per 2 seconds on board if it goes to the edge back in the opposite direction. ***/
            if(!left) {
                if (extraLife.getX() < randomLog.getBbox().getRight()-App.TILE_SIZE)
                {
                    direction *= 1;
                } else {
                    direction *= -1;
                    left = true;  /*** If the extralife hits the end of the left, move it to the right. ***/
                }
            }
            if(left)
            {
                if (extraLife.getX() > randomLog.getBbox().getLeft()+App.TILE_SIZE)
                {
                    direction *= 1;
                }
                else
                {
                    direction *= -1;
                    left = false; /*** If the extralife hit the end of the right, moves it to the left. ***/
                }
            }
            extraLife.setX((extraLife.getX() + direction*App.TILE_SIZE));
            extraLife.setY(extraLife.getY()); /*** Sets x and y with appropriate direction. ***/
        }

        if(time1 >= 10000 && !extraLifeNeverCreated && extraLife!= null)
        {
            extraLifePresent = false; /*** Makes extralife disappear after 10 seconds from the time it
                                           appeared on the log. ***/
        }
        intersecting_sprites.clear(); /*** Resets and clears the previous intersecting sprites with the player. ***/

        contactedHeadOn =  false;
        contactedPlayerFirst = false;

        /*** Updates the player. ***/

		player.update(input, delta);

		/*** Stores the previous x and y co-ordinates and detects change in x and y. ***/
        currXplayer = player.getX();
        currYplayer = player.getY();

		changeinX = currXplayer-prevXplayer;
        changeinY = currYplayer-prevYplayer;

		float xVal = player.getX();
        float yVal =  player.getY();

        if(extraLife != null)
        {
            if (player.contactSprite(extraLife) && extraLifePresent) /*** If the player contacts an existent extralife
             lives for the player increases and the extralife gets destroyed. ***/
            {
                if (extraLifePresent)
                {
                    /*** Lives are created 32 units more to the previous lives' x co-ordinate in x co-ordinate direction
                         and extralife destroyed. ***/
                    lives.add(new Lives((lives.get(lives.size() - 1).getX() + 32), (lives.get(lives.size() - 1).getY())));
                    extraLife = null;
                }
                extraLifePresent = false; /*** Extralife is destroyed so the flag is turned off. ***/

            }
        }

        for(Sprite sprite: sprites_array)
        {
            if(player.contactSprite(sprite))
            {
                intersecting_sprites.add(sprite); /*** Find the sprites that contacted with player and
                                                       store them in an array. ***/
            }
        }
            for(Sprite sprite3: intersecting_sprites)
            {

                find_collisions(sprite3,delta, prevXplayer, prevYplayer,changeinX,changeinY); /*** Find the contacts
                                                                                    and make appropriate updates. ***/


                /*** If the player is stationary and the bulldozer had a head on collision. ***/
                if(sprite3 instanceof Bulldozer)
                {
                    if (sprite3.contactSprite(player) == true && !contactedPlayerFirst && !contactedHeadOn)
                    {
                        /*** Moves along the bulldozer by changing by the bulldozer's change in x with
                             extra 48 positive x co-ordinate displacement(left). ***/
                        player.setX(sprite3.getX()+App.TILE_SIZE);
                        player.setY(sprite3.getY());

                        /*** If the player goes out of screen makes the player lose a life. ***/
                        if (player.getX() >= App.SCREEN_WIDTH)
                        {
                            /*** Resets the player to initial position.***/
                            player = new Player(ConstantValues.FROG_POS_X, ConstantValues.FROG_POS_Y);
                            lives.remove(lives.size() - 1);
                            /*** Exit game if all lives are lost. ***/
                            if (lives.size() == 0) {
                                System.exit(0);
                            }
                        }
                    }
                }
            }

        /*** If the player touches the water ***/
        if(!safe)
        {
            for(Sprite sprite4: intersecting_sprites)
            {
                if(sprite4 instanceof Water)
                {
                       /*** Resets the player to initial position and lose a life. ***/
                       player = new Player(ConstantValues.FROG_POS_X, ConstantValues.FROG_POS_Y);
                       lives.remove(lives.size()-1);

                       /*** Exit game if all lives are lost. ***/
                       if (lives.size() == 0)
                       {
                           System.exit(0);
                       }
                       /*** Break out at first instance of contact with water. ***/
                       break;
                }
            }
        }

        boolean flag = false; /*** Flag indicating the player entering an unvisited black hole(slot). ***/

        for (int i = 0; i < Target_x_coordinates.size(); i += 2)
        {
                /*** A nifty way to find the slot to turn the dummy player on and avoid re-entry. ***/
                int index = (((Math.round(Target_x_coordinates.get(i))) /(Math.round(Target_x_coordinates.get(0)))-1)/4);
                if ((player.getY() == ConstantValues.TARGET_Y) & (player.getX() >= Target_x_coordinates.get(i)) & (player.getX() <= Target_x_coordinates.get(i + 1)) )
                {
                    /*** If the slot is open, it uses the flag to increment points and resets player to initial position. ***/
                    if(((Slots_availability.get(index))==false))
                    {
                        Slots_availability.set(index, true);
                        flag = true;
                        player = new Player(ConstantValues.FROG_POS_X, ConstantValues.FROG_POS_Y);
                        break;
                    }else{
                        /*** The player attempted to move into a hole or slot that was already visited, so it loses
                             a life by resetting back to original position. ***/
                        player = new Player(ConstantValues.FROG_POS_X, ConstantValues.FROG_POS_Y);
                        lives.remove(lives.size() - 1);
                        /*** When the player loses all its life, it exits the game. ***/
                        if (lives.size() == 0)
                        {
                            System.exit(0);
                        }
                    }
                }
        }

        /***  If the player hit an unvisited slot, increase points. ***/
        if(flag)
        {
            pointsWon++;
        }

        /*** We keep on updating the lives. ***/
        for (Lives life : lives)
        {
            life.update(input, delta);
        }

        /*** Store the current values of x and y values of the player for the updates in
             the moves in the next call to update by App. ***/
        prevXplayer = currXplayer;
        prevYplayer = currYplayer;
    }

	/**
     * Draws all the sprites in the world, creates music.
	 * @param g, The Slick graphics object, used for drawing, type Graphics.
	 */
	public void render(Graphics g) throws SlickException
	{

		this.mainMusic.setVolume(0.5f);
		this.mainMusic.play();
		
		/*** Draw all of the sprites in the game and then the player ***/

		for(Sprite sprite: sprites_array)
		{
		    if(sprite instanceof Turtles)
		    {
		        if(appear == true) /*** If turtles are present, draws them. ***/
		        {
		            sprite.render();
		        }
		    }
		    else
		    {
		        sprite.render(); /*** Draws the other sprites. ***/
		    }
		}

		for(int i  = 0;i < lives.size();i++)
		{
			lives.get(i).render(); /*** Draws the lives of the player. ***/
		}

		if(extraLifePresent && extraLife != null)
		{
			extraLife.render(); /*** If the extralife existent, draws them. ***/
		}

		for(int i = 0;i < Slots_availability.size();i++)
		{
		    if(Slots_availability.get(i)== true)
		    {
		        dummies.get(i).render();  /*** Draws all the dummies if they had been
                                               visited, 3(maximum level) visible at max ***/
            }
        }
		player.render(); /*** Draws the player. ***/
	}


	/**
     * This method finds all the contacts with the player and makes necessary updates as per specification.
     * @param sprite, the sprite objected player contacted, type Sprite.
     * @param delta,  Time passed since last frame (milliseconds),type integer.
     * @param x, previous x co-ordinate of the player, type float.
     * @param y previous y co-ordinate of the player, type float.
     * @param changeinX, change in x co-ordinate of the player, type float.
     * @param changeinY, change in y co-ordinate of the player, type float.
     **/
    public void find_collisions( Sprite sprite,int delta,float x, float y,float changeinX, float changeinY) throws SlickException
    {

        if (sprite instanceof Bus || sprite instanceof RacingCar || sprite instanceof Bike)
        {
            if (player.contactSprite(sprite) == true)
            {
                player = new Player(ConstantValues.FROG_POS_X, ConstantValues.FROG_POS_Y); /*** If the player collides
                with either bus, racing car or bike (incorporating all outcomes and  levels) it loses a life, resets to
                initial spot. ***/
                lives.remove(lives.size() - 1);

                if (lives.size() == 0)
                {
                    System.exit(0); /*** Exit game when all lives are lost. ***/
                }
            }
        }

        if (sprite instanceof Bulldozer)
        {
            if(player.contactSprite(sprite))
            {
                /*** If the player contacts from top or bottom or sideways to a bulldozer,
                     we don't make a player move. ***/
                if (((changeinX == 0.00f && changeinY != 0.00f) || (changeinY == 0.00f && changeinX > 0.00f)) &&!contactedHeadOn && !contactedPlayerFirst)
                {
                    player.setX(x);/*** It sets the previous x and y value to the current thus making it not move. ***/
                    player.setY(y);
                    contactedPlayerFirst = true;/*** This flag is used to make sure player contact bulldozer first. ***/
                }else if(changeinX < 0.00f &&changeinY ==0.00f && !contactedHeadOn && !contactedPlayerFirst)
                {
                    /*** Moves the player along without overlap with bulldozer. ***/
                    player.setX(sprite.getX()+App.TILE_SIZE);
                    player.setY(sprite.getY());
                    if (currXplayer >= App.SCREEN_WIDTH)
                    {
                        /*** Resets the player to initial spot and reduces a life of the player. ***/
                        player = new Player(ConstantValues.FROG_POS_X, ConstantValues.FROG_POS_Y);
                        lives.remove(lives.size() - 1);
                        if (lives.size() == 0)
                        {
                            System.exit(0);/*** When the player loses all its life, it exits the game. ***/
                        }
                    }
                    contactedPlayerFirst = true;/*** This flag is used to make sure player contact bulldozer first. ***/
                    contactedHeadOn = true;/*** This flag is used to make sure player contact bulldozer head-on. ***/
                }
            }
        }

        if(sprite instanceof Log || sprite instanceof LongLog)
        {
            /*** If the player contacts the either long log or small log, it sets itself to be safe(flag)
                 and moves along the type of log it is on board. ***/
            safe = true;
            if(sprite instanceof Log)
            {
                Log l1 = (Log)sprite; /*** Down-casting to grab the speed. ***/
                l1.getSpeed();
                player.setX(player.getX()+delta*l1.getSpeed()); /*** Rides and moves along the small log. ***/
            }
            else if(sprite instanceof LongLog)
            {
                LongLog l2 = (LongLog)sprite;/*** Down-casting to grab the speed. ***/
                l2.getSpeed();
                player.setX(player.getX()+delta*l2.getSpeed()); /*** Rides and moves along the long log. ***/
            }
            if (player.getX() == App.SCREEN_WIDTH)
            {
                player.setX(player.getX());/*** Resets back to the screen if goes to the edge to prevent
                                                player going out of bounds. ***/
            }
        }

        if (sprite instanceof Turtles)
        {
            if(appear)
            {
                if (player.contactSprite(sprite) == true)
                {
                    safe = true; /*** If the player contacts the turtles(existent), then it moves along the turtles,
                                      makes movement controlled by keyboard and makes the player safe
                                      turning on the flag. ***/
                    Turtles t1 = (Turtles) sprite; /*** Down-casting. ***/
                    player.setX(player.getX()+delta*t1.getSpeed()); /*** Moves along the turtles. ***/
                }
            }
        }

        if (sprite instanceof Tree)
        {
            if ((player.contactSprite(sprite)) == true)
            {
                player.setX(sprite.getX()-App.TILE_SIZE); /*** Moves the player back by a tile horizontally. ***/
                player.setY(sprite.getY()+App.TILE_SIZE); /*** Moves the player back by a tile vertically. ***/
            }
        }
    }

    /**
     * This method loads the next level or exits the game if maximum level is reached. It takes no arguments
     * and just uses setters to create the world with the new level.
     **/
	public void LoadNextLevel() throws SlickException
	{
	    if(curr_lvl == ConstantValues.MAX_LEVEL)
	    {
	        System.exit(0); /*** Exit the game if maximum level reached***/
        }
		else
		{
		    curr_lvl++;
			App.setWorld(new World(curr_lvl)); /***  Move to new level and create the new world. ***/
		}
	}

	/**
     * This method checks maximum points are attained. It does not take any parameter.
     * @return if maximum point(which is 3) is attained, type boolean.
     **/
	public boolean hasWon()
	{
		return pointsWon == 3;
	}

    /**
     *  This method uses the setter of the world and current level(curr_lvl variable) to create the same world,
     *  which is same as reloading the same level. It takes no parameters.
     **/

	public void restartLevel() throws SlickException
    {
        App.setWorld(new World(curr_lvl));
    }

    /**
     * This method is used to randomly select a log from the log array and the log is then returned as
     * a sprite (as Log is a sprite).
     * @param Logs, the array list of all the possible logs let that be LongLogs
     *              or Logs(smaller one), type ArrayList<Sprite>.
     * @return sprite(Log or Long Log) that the extralife will be placed.
     **/

    public  Sprite getRandomLog(ArrayList<Sprite> Logs)
    {
        int index  =  (int) Math.random()*(Logs.size()+1);
        return Logs.get(index);
    }
}