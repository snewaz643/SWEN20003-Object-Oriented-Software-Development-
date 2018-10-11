/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * Log class inheriting from the Rideable class
 * that inherits from the Movable class that itself
 * inherits from Sprite class.
 **/

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Log extends Rideable
{
    private float Speed = 0.1f;  /*** Constant speed to be used for the Log. ***/
    private boolean isLeft; /*** It keeps track of whether LongLog is left or not. ***/

    /**
     * A constructor that passes the Image of the Log to the
     * Rideable class(superclass) that passes to Movable class
     * (passes itself to Sprite class) and initialises its
     * parameters to create the Log objects.
     * @param x, x co-ordinate, type float.
     * @param y, y co-ordinate, type float.
     * @param z, checks if left, type boolean.
     **/

    public Log(float x, float y,boolean z) throws SlickException
    {
        super(ImageSources.LOG,x,y);
        isLeft = z;
        if(isLeft == false)
        {
            Speed = -1*Speed;
        }
        else
        {
            Speed = 1*Speed;
        }
    }

    /**
     * Getter for getting the Log's Speed.
     * @return Speed, the current speed of the Log, type float.
     **/
    public float getSpeed()
    {
        return Speed;
    }


    public void setSpeed(float speed)
    {
        Speed = speed;
    }

    /**
     * Overrides the update method in Rideable(inherits from
     * Movable that inherits from Sprite) to keep the Logs
     * moving in alternating ways.
     * @param input, Keyboard input inserted by the user, type Input.
     * @param delta, Time passed since last frame (milliseconds), type integer.
     **/

    @Override
    public void update(Input input, int delta)
    {
        /**
         * Since the Logs are drawn half-centered, the Logs have to be
         * translated to accommodate it into the frame so that it keeps
         * on drawing to the screen and adjust opposite directions as well
         **/

        this.setX(((this.getX() + App.TILE_SIZE/2) + (delta*(this.Speed))+(App.SCREEN_WIDTH+ App.TILE_SIZE))%(App.SCREEN_WIDTH+App.TILE_SIZE)-App.TILE_SIZE/2);

        /*** Get and set the BoundingBox of the Logs. ***/

        BoundingBox bbox  = this.getBbox();
        bbox.setX(this.getX());
    }
}