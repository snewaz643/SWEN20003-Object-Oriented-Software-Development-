/**
 * SWEN20003 Object Oriented Software Development
 * Project 2
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * LongLog class inheriting from the Rideable class
 **/

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class LongLog extends Rideable
{
    private float Speed = 0.07f; /*** Constant speed to be used for the LongLog. ***/
    private boolean isLeft; /*** It keeps track of whether LongLog is left or not. ***/


    /**
     A constructor that passes the Image of the LongLog to the
     * Rideable class(superclass) that passes to Movable class
     * (passes itself to Sprite class) and initialises its
     * parameters to create the LongLog objects.
     * @param x, x co-ordinate, type float
     * @param y, y co-ordinate, type float
     * @param z, checks if left, type boolean
     **/

    public LongLog(float x, float y,boolean z) throws SlickException
    {
        super(ImageSources.LONGLOG,x,y);
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
     * Getter for getting the LongLong's Speed.
     * @return Speed, the current speed of the LongLog, type float.
     **/
    public float getSpeed()
    {
        return Speed;
    }

    /**
     * Setter for LongLog's Speed.
     * @param speed, the speed of the Long Log, type float.
     **/
    public void setSpeed(float speed)
    {
        Speed = speed;
    }

    /**
     * Overrides the update method in Rideable(inherits from
     * Movable that inherits from Sprite) to keep the LongLogs
     * moving in alternating ways.
     * @param input, Keyboard input inserted by the user,type Input.
     * @param delta, Time passed since last frame (milliseconds),type integer.
     **/

    @Override
    public void update(Input input, int delta)
    {
        /**
         * Since the LongLogs are drawn half-centered, the LongLogs have to be
         * translated to accommodate it into the frame so that it keeps
         * on drawing to the screen and adjust opposite directions as well
         **/

        this.setX(((this.getX() + 24) + (delta*(this.Speed))+(App.SCREEN_WIDTH+48))%(App.SCREEN_WIDTH+48)-24);

        /*** Get and set the BoundingBox of the LongLog. ***/
        BoundingBox bbox  = this.getBbox();
        bbox.setX(this.getX());
    }
}